package com.example.Exam.Management.Service.Repository;

import com.example.Exam.Management.Service.Model.Exam;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExamRepository extends MongoRepository<Exam, String> {
    public Exam findExamByExamName(String examName);
}
