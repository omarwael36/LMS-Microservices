package com.example.Course.Management.Service.Repository;

import com.example.Course.Management.Service.Model.Course;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CourseRepository extends MongoRepository<Course, String> {



}
