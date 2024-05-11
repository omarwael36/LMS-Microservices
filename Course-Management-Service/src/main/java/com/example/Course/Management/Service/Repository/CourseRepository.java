package com.example.Course.Management.Service.Repository;

import com.example.Course.Management.Service.Model.Course;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CourseRepository extends MongoRepository<Course, String> {

    public List<Course> findCoursesByCourseNameContainingIgnoreCase(String courseName);
    public Course findCoursesByCourseNameAndInstructor_Name(String courseName, String instructorName);
    public Course findCourseByCourseName( String courseName);
    public List<Course> findCoursesByCoursePublished(boolean coursePublished);
}
