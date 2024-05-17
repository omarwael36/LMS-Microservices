import { Component } from '@angular/core';
import { UserService } from '../services';
import { Course } from '../models/Courses';
import { TestCenter } from '../models/TestCenter';

@Component({
  selector: 'app-student-dashboard',
  templateUrl: './student-dashboard.component.html',
  styleUrls: ['./student-dashboard.component.css']
})
export class StudentDashboardComponent {
  showViewAllCourses: boolean = false;
  showCourseEnrollmentRequests: boolean = false;
  showNotifications: boolean = false;
  showReviewAndRating: boolean = false;
  showNearbyTestCentersFirstForm: boolean = false;
  showNearbyTestCentersSecondForm: boolean = false;
  showRegisterAnExam: boolean = false;
  showViewExamGrades: boolean = false;
  Location: string = '';
  studentData: any; // Object to store instructor data
  courses: Course[] = [];
  searchQuery: string = '';
  searchOption: string = 'category';
  nearbyTestCenters: TestCenter[] = [];
  reviewCourseName: string = ''; // New property for review course name
  reviewContent: string = ''; // New property for review content
  reviewRating: number = 0; // New property for review rating

  constructor(private courseService: UserService) {
    // Assuming you retrieve instructor data from another source
    this.studentData = { UserID: 1, Name: "Student Name" };
  }

  toggleShowViewAllCourses() {
    this.showViewAllCourses = !this.showViewAllCourses;
    this.showCourseEnrollmentRequests = false;
    this.showNotifications = false;
    this.showReviewAndRating = false;
    this.showNearbyTestCentersFirstForm = false;
    this.showNearbyTestCentersSecondForm = false;
    this.showRegisterAnExam = false;
    this.showViewExamGrades = false;
    if (this.showViewAllCourses) {
      this.fetchAllCourses();
    }
  }

  toggleShowCourseEnrollmentRequests() {
    this.showViewAllCourses = false;
    this.showCourseEnrollmentRequests = !this.showCourseEnrollmentRequests;
    this.showNotifications = false;
    this.showReviewAndRating = false;
    this.showNearbyTestCentersFirstForm = false;
    this.showNearbyTestCentersSecondForm = false;
    this.showRegisterAnExam = false;
    this.showViewExamGrades = false;
  }

  toggleShowNotifications() {
    this.showViewAllCourses = false;
    this.showCourseEnrollmentRequests = false;
    this.showNotifications = !this.showNotifications;
    this.showReviewAndRating = false;
    this.showNearbyTestCentersFirstForm = false;
    this.showNearbyTestCentersSecondForm = false;
    this.showRegisterAnExam = false;
    this.showViewExamGrades = false;
  }

  toggleShowReviewAndRating() {
    this.showViewAllCourses = false;
    this.showCourseEnrollmentRequests = false;
    this.showNotifications = false;
    this.showReviewAndRating = !this.showReviewAndRating;
    this.showNearbyTestCentersFirstForm = false;
    this.showNearbyTestCentersSecondForm = false;
    this.showRegisterAnExam = false;
    this.showViewExamGrades = false;
  }

  toggleShowNearbyTestCentersFirstForm() {
    this.showViewAllCourses = false;
    this.showCourseEnrollmentRequests = false;
    this.showReviewAndRating = false;
    this.showNotifications = false;
    this.showNearbyTestCentersFirstForm = !this.showNearbyTestCentersFirstForm;
    this.showNearbyTestCentersSecondForm = false;
    this.showRegisterAnExam = false;
    this.showViewExamGrades = false;
  }

  toggleShowNearbyTestCentersSecondForm() {
    this.showViewAllCourses = false;
    this.showCourseEnrollmentRequests = false;
    this.showNotifications = false;
    this.showReviewAndRating = false;
    this.showNearbyTestCentersFirstForm = false;
    this.showNearbyTestCentersSecondForm = !this.showNearbyTestCentersSecondForm;
    this.showRegisterAnExam = false;
    this.showViewExamGrades = false;
    if(this.showNearbyTestCentersSecondForm) {
      this.getNearbyTestCenters(this.Location)
    }

  }

  toggleShowRegisterAnExam() {
    this.showViewAllCourses = false;
    this.showCourseEnrollmentRequests = false;
    this.showNotifications = false;
    this.showReviewAndRating = false;
    this.showNearbyTestCentersFirstForm = false;
    this.showNearbyTestCentersSecondForm = false;
    this.showRegisterAnExam = !this.showRegisterAnExam;
    this.showViewExamGrades = false;
  }

  toggleShowViewExamGrades() {
    this.showViewAllCourses = false;
    this.showCourseEnrollmentRequests = false;
    this.showNotifications = false;
    this.showReviewAndRating = false;
    this.showNearbyTestCentersFirstForm = false;
    this.showNearbyTestCentersSecondForm = false;
    this.showRegisterAnExam = false;
    this.showViewExamGrades = !this.showViewExamGrades;
  }

  // Register an exam
  exam = {
    examName: '',
    testCenterName: '',
    branchName: ''
  };

  fetchAllCourses() {
    this.courseService.viewAllCourses(this.studentData.Name, 'instructor').subscribe(
      (courses) => {
        this.courses = courses;
      },
      (error) => {
        console.error('Error fetching courses:', error);
      }
    );
  }

  searchCourses(searchQuery: string, searchOption: string) {
    if (searchQuery.trim() !== '') {
      if (searchOption === 'name') {
        this.courseService.searchCoursesByName('name', searchQuery, this.studentData.Name, 'instructor').subscribe(
          (courses) => {
            this.courses = courses;
          },
          (error) => {
            console.error('Error fetching courses by name:', error);
          }
        );
      } else if (searchOption === 'category') {
        this.courseService.searchCoursesByName('category', searchQuery, this.studentData.Name, 'instructor').subscribe(
          (courses) => {
            this.courses = courses;
          },
          (error) => {
            console.error('Error fetching courses by category:', error);
          }
        );
      }
    }
  }

  sortCoursesByRating(sortOrder: string) {
    this.courseService.sortCoursesByRating(sortOrder, this.studentData.Name, 'instructor').subscribe(
      (courses) => {
        this.courses = courses;
      },
      (error) => {
        console.error('Error sorting courses:', error);
      }
    );
  }

  addReviewAndRating() {
    if (!this.reviewCourseName || !this.reviewContent || !this.reviewRating) {
      console.error('Please fill all fields');
      return;
    }

    // Call the addCourseReview method
    this.addCourseReview(this.reviewCourseName, this.reviewContent);

    // Call the rateCourse method
    this.rateCourse(this.reviewCourseName, this.reviewRating);

        // Optionally, you can reset the form fields after submission
        this.reviewCourseName = '';
        this.reviewContent = '';
        this.reviewRating = 0;
      }
    
      rateCourse(courseId: string, rating: number) {
        const userName = this.studentData.Name; // Assuming you have student data
        const userRole = 'student'; // Assuming the user role is fixed for students
        const requestBody = { courseId, rating };
        this.courseService.rateCourse(requestBody, userName, userRole).subscribe(
          (response) => {
            console.log('Course rated successfully:', response);
            // Optionally, you can update the UI to reflect the changes
          },
          (error) => {
            console.error('Error rating course:', error);
            // Handle error
          }
        );
      }
    
      addCourseReview(courseName: string, review: string) {
        const userName = this.studentData.Name; // Assuming you have student data
        const userRole = 'student'; // Assuming the user role is fixed for students
        const requestBody = { courseName, review };
        this.courseService.addCourseReview(requestBody, userName, userRole).subscribe(
          (response) => {
            console.log('Review added successfully:', response);
            // Optionally, you can update the UI to reflect the changes
          },
          (error) => {
            console.error('Error adding course review:', error);
            // Handle error
          }
        );
      }
    
      getNearbyTestCenters(location: string) {
        this.courseService.getTestCentersByLocation(location).subscribe(
          (testCenters) => {
            this.nearbyTestCenters = testCenters;
          },
          (error) => {
            console.error('Error fetching nearby test centers:', error);
          }
        );
      }
    }
    
   
