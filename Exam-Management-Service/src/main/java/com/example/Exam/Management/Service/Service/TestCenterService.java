package com.example.Exam.Management.Service.Service;


import com.example.Exam.Management.Service.Model.*;
import com.example.Exam.Management.Service.Repository.ExamLogsRepository;
import com.example.Exam.Management.Service.Repository.ExamRepository;
import com.example.Exam.Management.Service.Repository.TestCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

        Optional<TestCenter> optionalTestCenter = testCenterRepository.findByBranches_BranchName(branchName);
        if (optionalTestCenter.isPresent()) {
            TestCenter testCenter = optionalTestCenter.get();
            List<Branch> branches = testCenter.getBranches();

            Optional<Branch> optionalBranch = branches.stream()
                    .filter(branch -> branch.getBranchName().equals(branchName))
                    .findFirst();

            if (optionalBranch.isPresent()) {
                Branch branch = optionalBranch.get();
                List<ExamDate> examDates = branch.getExamDates();
                examDates.add(request.getExamDate());
                branch.setExamDates(examDates);
                testCenterRepository.save(testCenter);

                // Log the action
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
}
