package com.example.Exam.Management.Service.Controller;


import com.example.Exam.Management.Service.Model.CreateExamRequest;
import com.example.Exam.Management.Service.Model.Exam;
import com.example.Exam.Management.Service.Model.ExamDate;
import com.example.Exam.Management.Service.Model.TestCenter;
import com.example.Exam.Management.Service.Service.TestCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public String createExam(@RequestBody CreateExamRequest request, @RequestParam("branchName") String branchName) {
        return testCenterService.createExam(request, branchName);
    }


}
