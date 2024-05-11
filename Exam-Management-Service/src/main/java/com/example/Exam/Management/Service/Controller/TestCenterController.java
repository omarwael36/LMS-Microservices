package com.example.Exam.Management.Service.Controller;


import com.example.Exam.Management.Service.Service.TestCenterService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/TestCenter")

public class TestCenterController {
    TestCenterService testCenterService;

    @Autowired
    public TestCenterController(TestCenterService testCenterService) {
        this.testCenterService = testCenterService;
    }


}
