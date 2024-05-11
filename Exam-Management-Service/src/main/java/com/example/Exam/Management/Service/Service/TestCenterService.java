package com.example.Exam.Management.Service.Service;


import com.example.Exam.Management.Service.Repository.ExamRepository;
import com.example.Exam.Management.Service.Repository.TestCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestCenterService {
    ExamRepository examRepository;
    TestCenterRepository testCenterRepository;

    @Autowired
    public TestCenterService(ExamRepository examRepository, TestCenterRepository testCenterRepository) {
        this.examRepository = examRepository;
        this.testCenterRepository = testCenterRepository;
    }

}
