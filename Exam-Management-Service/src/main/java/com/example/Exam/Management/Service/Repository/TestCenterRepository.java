package com.example.Exam.Management.Service.Repository;

import com.example.Exam.Management.Service.Model.TestCenter;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TestCenterRepository extends MongoRepository<TestCenter, String> {

}
