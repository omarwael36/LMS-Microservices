package com.example.Course.Management.Service.Repository;

import com.example.Course.Management.Service.Model.CourseLogs;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LogsRepository extends MongoRepository<CourseLogs, String> {

}
