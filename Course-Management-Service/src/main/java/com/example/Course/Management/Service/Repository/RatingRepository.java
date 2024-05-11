package com.example.Course.Management.Service.Repository;

import com.example.Course.Management.Service.Model.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface RatingRepository extends MongoRepository<Rating, String> {



    List<Rating> findAllByCourseId(String courseId);

    Rating findByCourseIdAndStudentId(String courseId, int studentId);
}
