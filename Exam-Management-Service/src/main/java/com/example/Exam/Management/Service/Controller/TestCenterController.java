package com.example.Exam.Management.Service.Controller;


import com.example.Exam.Management.Service.Model.CreateExamRequest;
import com.example.Exam.Management.Service.Model.ExamReservation;
import com.example.Exam.Management.Service.Model.ExamResults;
import com.example.Exam.Management.Service.Model.TestCenter;
import com.example.Exam.Management.Service.Service.TestCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/TestCenter")

public class TestCenterController {
    TestCenterService testCenterService;

    @Autowired
    public TestCenterController(TestCenterService testCenterService) {
        this.testCenterService = testCenterService;
    }

    @PostMapping("/AddTestCenter")
    public String addTestCenter(@RequestBody TestCenter testCenter) {
        return testCenterService.addTestCenter(testCenter);
    }

    @PostMapping("/CreateExam")
    public String createExam(@RequestBody CreateExamRequest request, @RequestParam ("testCenterName") String testCenterName) {
        return testCenterService.createExam(request,testCenterName);
    }

    @PostMapping("/SetStudentGrade")
    public String setStudentGrade(@RequestParam("examName") String examName, @RequestParam("studentName") String studentName, @RequestParam("studentGrade") int studentGrade, @RequestParam("branchName") String branchName) {
        return testCenterService.setStudentGrade(examName, studentName, studentGrade, branchName);
    }

    @PostMapping("/RegisterForExam")
    public String registerForExam(@RequestParam("studentId") int studentId, @RequestParam("examName") String examName, @RequestParam("studentName") String studentName, @RequestParam("branchName") String branchName, @RequestParam("testCenterName") String testCenterName) {
        return testCenterService.registerForExam(studentId, examName, studentName, branchName, testCenterName);
    }

    @GetMapping("/GetStudentsGrades")
    public List<ExamResults> getStudentsGrades(@RequestParam("TestCenterName") String testCenterName) {
        return testCenterService.getStudentsGrades(testCenterName);
    }

    @GetMapping("/GetTestCentersByLocation")
    public List<TestCenter> getTestCentersByLocation(@RequestParam("location") String location) {
        return testCenterService.getTestCentersByLocation(location);
    }

    @GetMapping("/GetStudentExamHistory")
    public List<ExamReservation> getStudentExamHistory(@RequestParam("studentId") int studentId) {
        return testCenterService.getStudentExamHistory(studentId);
    }

}
