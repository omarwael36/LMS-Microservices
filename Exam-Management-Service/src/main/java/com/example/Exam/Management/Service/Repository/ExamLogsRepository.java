package com.example.Exam.Management.Service.Repository;

import com.example.Exam.Management.Service.Model.ExamLogs;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExamLogsRepository extends MongoRepository<ExamLogs, String> {

}
