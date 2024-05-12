package com.example.Exam.Management.Service.Service;

import com.example.Exam.Management.Service.Model.*;
import com.example.Exam.Management.Service.Repository.ExamLogsRepository;
import com.example.Exam.Management.Service.Repository.ExamRepository;
import com.example.Exam.Management.Service.Repository.TestCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TestCenterService {
    ExamRepository examRepository;
    TestCenterRepository testCenterRepository;
    ExamLogsRepository examLogsRepository;

    public TestCenterService() {
    }

    @Autowired
    public TestCenterService(ExamRepository examRepository, TestCenterRepository testCenterRepository, ExamLogsRepository examLogsRepository) {
        this.examRepository = examRepository;
        this.testCenterRepository = testCenterRepository;
        this.examLogsRepository = examLogsRepository;
    }

    public String addTestCenter(TestCenter testCenter) {
        TestCenter existingTestCenter = testCenterRepository.findTestCenterByTestCenterName(testCenter.getTestCenterName());

        if (existingTestCenter != null) {
            ExamLogs examLogs = new ExamLogs();
            existingTestCenter.setTestCenterName(testCenter.getTestCenterName());
            existingTestCenter.setBranches(testCenter.getBranches());
            existingTestCenter.setBio(testCenter.getBio());
            examLogs.setName(testCenter.getTestCenterName());
            examLogs.setRole("test center");
            examLogs.setTimeStamp(LocalDateTime.now());
            examLogs.setAction("Update Test Center information");
            examLogsRepository.save(examLogs);
            testCenterRepository.save(existingTestCenter);
            return "Test Center Updated Successfully";
        } else {
            ExamLogs examLogs = new ExamLogs();
            testCenterRepository.save(testCenter);
            examLogs.setName(testCenter.getTestCenterName());
            examLogs.setRole("test center");
            examLogs.setTimeStamp(LocalDateTime.now());
            examLogs.setAction("Added Test Center information");
            examLogsRepository.save(examLogs);
            return "Test Center Added Successfully";
        }
    }

    public String createExam(CreateExamRequest request, String branchName) {
        Exam savedExam = examRepository.save(request.getExam());
        request.getExamDate().setExamId(savedExam.getExamId());

        TestCenter testCenter = testCenterRepository.findByBranches_BranchName(branchName);
        if (testCenter != null) {
            List<Branch> branches = testCenter.getBranches();

            Branch branch = null;
            for (Branch b : branches) {
                if (b.getBranchName().equals(branchName)) {
                    branch = b;
                    break;
                }
            }

            if (branch != null) {
                List<ExamDate> examDates = branch.getExamDates();
                examDates.add(request.getExamDate());
                branch.setExamDates(examDates);
                testCenterRepository.save(testCenter);

                ExamLogs examLogs = new ExamLogs();
                examLogs.setName(branchName);
                examLogs.setRole("test center");
                examLogs.setTimeStamp(LocalDateTime.now());
                examLogs.setAction("Created exam and associated with exam date");
                examLogsRepository.save(examLogs);

                return "Exam created and associated with exam date successfully";
            } else {
                return "Branch not found";
            }
        } else {
            return "Test center with branch name not found";
        }
    }

    public String setStudentGrade(String examName, String studentName, int studentGrade, String branchName) {
        Exam exam = examRepository.findExamByExamName(examName);
        if (exam != null) {
            String examId = exam.getExamId();

            TestCenter testCenter = testCenterRepository.findByBranches_BranchName(branchName);
            if (testCenter != null) {
                List<Branch> branches = testCenter.getBranches();

                Branch branch = null;
                for (Branch b : branches) {
                    if (b.getBranchName().equals(branchName)) {
                        branch = b;
                        break;
                    }
                }

                if (branch != null) {
                    List<ExamDate> examDates = branch.getExamDates();
                    ExamDate examDate = null;
                    for (ExamDate ed : examDates) {
                        if (ed.getExamId().equals(examId)) {
                            examDate = ed;
                            break;
                        }
                    }

                    if (examDate != null) {
                        List<ExamReservation> examReservations = examDate.getExamReservations();
                        for (ExamReservation reservation : examReservations) {
                            if (reservation.getStudentName().equals(studentName)) {
                                reservation.setExamResult(studentGrade);
                                break;
                            }
                        }

                        examDate.setExamReservations(examReservations);
                        examDate.setFull(examReservations.size() >= examDate.getCapacity());
                        testCenterRepository.save(testCenter);

                        ExamLogs examLogs = new ExamLogs();
                        examLogs.setName(studentName);
                        examLogs.setRole("test center");
                        examLogs.setTimeStamp(LocalDateTime.now());
                        examLogs.setAction("Set grade for exam: " + examName + ", student: " + studentName + ", grade: " + studentGrade);
                        examLogsRepository.save(examLogs);

                        return "Student grade set successfully for exam: " + examName + ", student: " + studentName;
                    } else {
                        return "Exam with ID " + examId + " not found in branch " + branchName;
                    }
                } else {
                    return "Branch " + branchName + " not found in test center";
                }
            } else {
                return "Test center with branch name not found";
            }
        } else {
            return "Exam with name " + examName + " not found";
        }
    }

    public String registerForExam(int studentId, String examName, String studentName, String branchName, String testCenterName) {
        TestCenter testCenter = testCenterRepository.findTestCenterByTestCenterName(testCenterName);
        if (testCenter == null) {
            return "Test center with name " + testCenterName + " not found";
        }

        for (Branch branch : testCenter.getBranches()) {
            if (branch.getBranchName().equals(branchName)) {
                for (ExamDate examDate : branch.getExamDates()) {
                    if (examDate.getExamId().equals(getExamIdByName(examName))) {
                        if (examDate.isFull()) {
                            return "Exam is already full";
                        }
                        List<ExamReservation> examReservations = examDate.getExamReservations();
                        boolean alreadyRegistered = false;
                        for (ExamReservation reservation : examReservations) {
                            if (reservation.getStudentId() == studentId) {
                                alreadyRegistered = true;
                                break;
                            }
                        }
                        if (alreadyRegistered) {
                            return studentName + " with ID " + studentId + " has already registered for exam: " + examName;
                        } else {
                            examReservations.add(new ExamReservation(studentId, studentName, 0)); // Include studentId in constructor
                            examDate.setExamReservations(examReservations);
                            int remainingCapacity = examDate.getCapacity() - 1;
                            examDate.setCapacity(remainingCapacity);
                            if (remainingCapacity == 0) {
                                examDate.setFull(true);
                            }
                            testCenterRepository.save(testCenter);
                            ExamLogs examLogs = new ExamLogs();
                            examLogs.setName(studentName);
                            examLogs.setRole("student");
                            examLogs.setTimeStamp(LocalDateTime.now());
                            examLogs.setAction("Registered for exam: " + examName + ", student: " + studentName);
                            examLogsRepository.save(examLogs);
                            return "Successfully registered " + studentName + " with ID " + studentId + " for exam: " + examName;
                        }
                    }
                }
                return "Exam with name " + examName + " not found in branch " + branchName;
            }
        }
        return "Branch " + branchName + " not found in test center " + testCenterName;
    }


    private String getExamIdByName(String examName) {
        Exam exam = examRepository.findExamByExamName(examName);
        return (exam != null) ? exam.getExamId() : null;
    }

    public List<ExamResults> getStudentsGrades(String testCenterName) {
        List<ExamResults> results = new ArrayList<>();
        TestCenter testCenter = testCenterRepository.findTestCenterByTestCenterName(testCenterName);
        if (testCenter == null) {
            return results;
        }
        for (Branch branch : testCenter.getBranches()) {
            for (ExamDate examDate : branch.getExamDates()) {
                String examId = examDate.getExamId();
                Exam exam = examRepository.findById(examId).orElse(null);
                if (exam != null) {
                    for (ExamReservation reservation : examDate.getExamReservations()) {
                        results.add(new ExamResults(reservation.getStudentName(), reservation.getExamResult(), exam));
                    }
                }
            }
        }
        return results;
    }

    public List<TestCenter> getTestCentersByLocation(String location) {
        List<TestCenter> testCenters = testCenterRepository.findAll();
        List<TestCenter> filteredTestCenters = new ArrayList<>();

        for (TestCenter testCenter : testCenters) {
            for (Branch branch : testCenter.getBranches()) {
                if (branch.getBranchLocation().equals(location)) {
                    filteredTestCenters.add(testCenter);
                    break;
                }
            }
        }

        return filteredTestCenters;
    }

    public List<ExamReservation> getStudentExamHistory(int studentId) {
        List<ExamReservation> studentExamHistory = new ArrayList<>();

        for (TestCenter testCenter : testCenterRepository.findAll()) {
            for (Branch branch : testCenter.getBranches()) {
                for (ExamDate examDate : branch.getExamDates()) {
                    for (ExamReservation reservation : examDate.getExamReservations()) {
                        if (reservation.getStudentId() == studentId) {
                            studentExamHistory.add(reservation);
                        }
                    }
                }
            }
        }
        return studentExamHistory;
    }
}
