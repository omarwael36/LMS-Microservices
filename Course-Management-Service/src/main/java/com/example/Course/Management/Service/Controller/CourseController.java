package com.example.Course.Management.Service.Controller;

import com.example.Course.Management.Service.Model.Course;
import com.example.Course.Management.Service.Model.CourseLogs;
import com.example.Course.Management.Service.Model.Rating;
import com.example.Course.Management.Service.Model.Review;
import com.example.Course.Management.Service.Service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/course")
public class CourseController {
    CourseService courseService;

    public CourseController() {

    }
    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }
    @PostMapping("/AddCourse")
    public void addCourse(@RequestBody Course course, @RequestParam String instructorName) {
        courseService.addCourse(course ,instructorName);
    }

    @GetMapping("/ViewAllCourses")
    public List<Course> viewAllCourses(@RequestParam String userName, @RequestParam String userRole) {
        return courseService.viewAllCourses(userName, userRole);
    }

    @GetMapping("/SearchCoursesByFilter")
    public List<Course> searchCoursesByName(@RequestParam String key, @RequestParam String value, @RequestParam String userName, @RequestParam String userRole) {
        return courseService.searchCoursesByName(key, value, userName, userRole);
    }

    @GetMapping("/SortCoursesByRating")
    public List<Course> sortCoursesByRating(@RequestParam String sortOrder, @RequestParam String userName, @RequestParam String userRole) {
        return courseService.sortCoursesByRating(sortOrder, userName, userRole);
    }

    @PostMapping("/RateCourse")
    public void rateCourse(@RequestBody Rating rating, @RequestParam String userName, @RequestParam String userRole) {
        courseService.rateCourse(rating, userName, userRole);
    }

    @PostMapping("/AddCourseReview")
    public void addCourseReview(@RequestBody Review review, @RequestParam String userName, @RequestParam String userRole) {
        courseService.addCourseReview(review, userName, userRole);
    }

    @GetMapping("/ShowCourseLogs")
    public List<CourseLogs> showCourseLogs() {
        return courseService.showCourseLogs();
    }

    @GetMapping("/ShowCourseStats")
    public List<Course> showCourseStats() {
        return courseService.showCourseStats();
    }

    @GetMapping("ShowPublishRequests")
    public List<Course> showPublishRequests() {
        return courseService.showPublishRequests();
    }

    @PutMapping("/AdminPublishCourse")
    public void AdminPublishCourse(@RequestParam String courseName) {
        courseService.adminPublishCourse(courseName);
    }

    @PutMapping("/AdminRejectCourse")
    public void adminRejectCourse(@RequestParam String courseName) {
        courseService.adminRejectCourse(courseName);
    }

    @PutMapping("/AdminEditCourse")
    public void adminEditCourse(@RequestBody Course course, @RequestParam String courseName) {
        courseService.adminEditCourse(course, courseName);
    }

    @PutMapping("/UpdateCourseENrollmentNumber")
    public void updateCourseEnrollmentNumber(@RequestParam String courseName) {
        courseService.updateCourseEnrollmentNumber(courseName);
    }

}
