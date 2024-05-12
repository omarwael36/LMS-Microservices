package com.example.Exam.Management.Service.Repository;

import com.example.Exam.Management.Service.Model.TestCenter;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TestCenterRepository extends MongoRepository<TestCenter, String> {

    TestCenter findTestCenterByTestCenterName(String testCenterName);
    TestCenter findByBranches_BranchName(String branchName);
}
