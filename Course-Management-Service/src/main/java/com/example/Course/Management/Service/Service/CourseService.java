package com.example.Course.Management.Service.Service;

import com.example.Course.Management.Service.Model.Course;
import com.example.Course.Management.Service.Model.CourseLogs;
import com.example.Course.Management.Service.Model.Rating;
import com.example.Course.Management.Service.Model.Review;
import com.example.Course.Management.Service.Repository.CourseRepository;
import com.example.Course.Management.Service.Repository.LogsRepository;
import com.example.Course.Management.Service.Repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {
    CourseRepository courseRepository;
    RatingRepository ratingRepository;
    LogsRepository courseLogsRepository;

    public CourseService() {

    }

    @Autowired
    public CourseService(CourseRepository courseRepository, RatingRepository ratingRepository, LogsRepository courseLogsRepository) {
        this.courseRepository = courseRepository;
        this.ratingRepository = ratingRepository;
        this.courseLogsRepository = courseLogsRepository;
    }

    public void addCourse(Course course , String instructorName) {
        Course existingCourse = courseRepository.findCoursesByCourseNameAndInstructor_Name(course.getCourseName(), instructorName);

        if (existingCourse == null) {
            CourseLogs courseLogs = new CourseLogs();
            courseLogs.setName(instructorName);
            courseLogs.setRole("instructor");
            courseLogs.setTimeStamp(LocalDateTime.now());
            courseLogs.setAction("Instructor added a new course");
            courseLogsRepository.save(courseLogs);
            courseRepository.save(course);
        }
    }

    public List<Course> viewAllCourses(String userName, String userRole) {
        CourseLogs courseLogs = new CourseLogs();
        courseLogs.setName(userName);
        courseLogs.setRole(userRole);
        courseLogs.setTimeStamp(LocalDateTime.now());
        courseLogs.setAction("Showed All courses");
        courseLogsRepository.save(courseLogs);
        List<Course> courses = courseRepository.findAll();
        for (int i = 0; i < courses.size(); i++) {
            if(!courses.get(i).isCoursePublished()) {
                courses.remove(i);
                i--;
            }
        }
        return courses;
    }

    public List<Course> searchCoursesByName(String key, String value, String userName, String userRole) {
        CourseLogs courseLogs = new CourseLogs();
        courseLogs.setName(userName);
        courseLogs.setRole(userRole);
        courseLogs.setTimeStamp(LocalDateTime.now());
        courseLogs.setAction("Searched for courses by " + key);
        courseLogsRepository.save(courseLogs);

        List<Course> courses;
        if ("name".equalsIgnoreCase(key)) {
            courses = courseRepository.findCoursesByCourseNameContainingIgnoreCase(value);
        } else if ("category".equalsIgnoreCase(key)) {
            courses = courseRepository.findCoursesByCourseCategoryContainingIgnoreCase(value);
        } else {
            return List.of();
        }
        courses.removeIf(course -> !course.isCoursePublished());

        return courses;
    }

    public List<Course> sortCoursesByRating(String sortOrder, String userName, String userRole) {
        CourseLogs courseLogs = new CourseLogs();
        courseLogs.setName(userName);
        courseLogs.setRole(userRole);
        courseLogs.setTimeStamp(LocalDateTime.now());
        courseLogs.setAction("sorted for courses by rating");
        courseLogsRepository.save(courseLogs);
        Sort sort;
        if(sortOrder.equals("asc")){
            sort = Sort.by(Sort.Direction.ASC, "courseRating");
        }
        else {
            sort = Sort.by(Sort.Direction.DESC, "courseRating");
        }
        List<Course> courses = courseRepository.findAll(sort);
        for (int i = 0; i < courses.size(); i++) {
            if(!courses.get(i).isCoursePublished()) {
                courses.remove(i);
                i--;
            }
        }
        return courses;
    }


    public void rateCourse(Rating rating, String userName, String userRole) {
        CourseLogs courseLogs = new CourseLogs();
        courseLogs.setName(userName);
        courseLogs.setRole(userRole);
        courseLogs.setTimeStamp(LocalDateTime.now());
        courseLogs.setAction("rated a course");
        courseLogsRepository.save(courseLogs);
        String courseId = rating.getCourseId();
        Rating existingRating = ratingRepository.findByCourseIdAndStudentId(courseId, rating.getStudentId());
        if (existingRating != null) {
            existingRating.setRating(rating.getRating());
            ratingRepository.save(existingRating);
        } else {
            ratingRepository.save(rating);
        }

        List<Rating> ratings = ratingRepository.findAllByCourseId(courseId);
        double totalRating = 0;
        for (Rating rate : ratings) {
            totalRating += rate.getRating();
        }
        double averageRating = 0;
        if (!ratings.isEmpty()) {
            averageRating = totalRating / ratings.size();
        }

        Course course = courseRepository.findById(courseId).orElse(null);
        if (course != null) {
            course.setCourseRating(averageRating);
            courseRepository.save(course);
        }
    }

    public List<CourseLogs> showCourseLogs() {
        return courseLogsRepository.findAll();
    }

    public void addCourseReview(Review review, String userName, String userRole) {
        Course course = courseRepository.findCourseByCourseName(review.getCourseName());
        CourseLogs courseLogs = new CourseLogs();
        courseLogs.setName(userName);
        courseLogs.setRole(userRole);
        courseLogs.setTimeStamp(LocalDateTime.now());
        courseLogs.setAction("added a review");
        courseLogsRepository.save(courseLogs);
        if (course != null) {
            List<Review> existingReviews = course.getReviews();
            if (existingReviews == null) {
                existingReviews = new ArrayList<>();
            }
            existingReviews.add(review);
            course.setReviews(existingReviews);
            courseRepository.save(course);
        }
    }


    public List<Course> showCourseStats() {
        Sort sortByEnrolledStudentsDesc = Sort.by(Sort.Direction.DESC, "courseEnrolled");
        Sort sortByRatingDesc = Sort.by(Sort.Direction.DESC, "courseRating");
        Sort sortByEnrolledStudentsAndRatingDesc = sortByEnrolledStudentsDesc.and(sortByRatingDesc);
        return courseRepository.findAll(sortByEnrolledStudentsAndRatingDesc);
    }

    public List<Course> showPublishRequests() {
        return courseRepository.findCoursesByCoursePublished(false);
    }

    public void adminPublishCourse(String courseName) {
        Course course = courseRepository.findCourseByCourseName(courseName);
        if (course != null) {
            course.setCoursePublished(true);
            courseRepository.save(course);
        }
    }

    public void adminRejectCourse(String courseName) {
        Course course = courseRepository.findCourseByCourseName(courseName);
        if (course != null) {
            courseRepository.delete(course);
        }
    }

    public void adminEditCourse(Course course, String courseName) {
        Course existingCourse = courseRepository.findCourseByCourseName(courseName);
        if (existingCourse != null) {
            existingCourse.setCourseName(course.getCourseName());
            existingCourse.setCourseRating(course.getCourseRating());
            existingCourse.setCourseStartDate(course.getCourseStartDate());
            existingCourse.setCourseEndDate(course.getCourseEndDate());
            existingCourse.setCourseCategory(course.getCourseCategory());
            existingCourse.setCourseCapacity(course.getCourseCapacity());
            existingCourse.setInstructor(course.getInstructor());
            existingCourse.setReviews(course.getReviews());
            existingCourse.setCoursePublished(course.isCoursePublished());
            courseRepository.save(existingCourse);
        }
    }

    public void updateCourseEnrollmentNumber(String courseName) {
        Course course = courseRepository.findCourseByCourseName(courseName);
        if (course != null) {
            course.setCourseEnrolled(course.getCourseEnrolled() + 1);
            courseRepository.save(course);
        }
        CourseLogs courseLogs = new CourseLogs();
        courseLogs.setName("student");
        courseLogs.setRole("Student");
        courseLogs.setTimeStamp(LocalDateTime.now());
        courseLogs.setAction("enrolled a course");
        courseLogsRepository.save(courseLogs);
    }
}
