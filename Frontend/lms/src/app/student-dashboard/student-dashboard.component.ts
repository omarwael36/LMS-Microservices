import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-student-dashboard',
  templateUrl: './student-dashboard.component.html',
  styleUrl: './student-dashboard.component.css'
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

  toggleShowViewAllCourses(){
    this.showViewAllCourses = !this.showViewAllCourses;
    this.showCourseEnrollmentRequests = false;
    this.showNotifications = false;
    this.showReviewAndRating = false;
    this.showNearbyTestCentersFirstForm = false;
    this.showNearbyTestCentersSecondForm = false;
    this.showRegisterAnExam = false;
    this.showViewExamGrades = false;
  }
  toggleShowCourseEnrollmentRequests(){
    this.showViewAllCourses = false;
    this.showCourseEnrollmentRequests = !this.showCourseEnrollmentRequests;
    this.showNotifications = false;
    this.showReviewAndRating = false;
    this.showNearbyTestCentersFirstForm = false;
    this.showNearbyTestCentersSecondForm = false;
    this.showRegisterAnExam = false;
    this.showViewExamGrades = false;
  }
  toggleShowNotifications(){
    this.showViewAllCourses = false;
    this.showCourseEnrollmentRequests = false;
    this.showNotifications = !this.showNotifications;
    this.showReviewAndRating = false;
    this.showNearbyTestCentersFirstForm = false;
    this.showNearbyTestCentersSecondForm = false;
    this.showRegisterAnExam = false;
    this.showViewExamGrades = false;
  }
  toggleShowReviewAndRating(){
    this.showViewAllCourses = false;
    this.showCourseEnrollmentRequests = false;
    this.showNotifications = false;
    this.showReviewAndRating = !this.showReviewAndRating;
    this.showNearbyTestCentersFirstForm = false;
    this.showNearbyTestCentersSecondForm = false;
    this.showRegisterAnExam = false;
    this.showViewExamGrades = false;

  }
  toggleShowNearbyTestCentersFirstForm(){
    this.showViewAllCourses = false;
    this.showCourseEnrollmentRequests = false;
    this.showReviewAndRating = false;
    this.showNotifications = false;
    this.showNearbyTestCentersFirstForm = !this.showNearbyTestCentersFirstForm;
    this.showNearbyTestCentersSecondForm = false;
    this.showRegisterAnExam = false;
    this.showViewExamGrades = false;
  }
  toggleShowNearbyTestCentersSecondForm(){
    this.showViewAllCourses = false;
    this.showCourseEnrollmentRequests = false;
    this.showNotifications = false;
    this.showReviewAndRating = false;
    this.showNearbyTestCentersFirstForm = false;
    this.showNearbyTestCentersSecondForm = !this.showNearbyTestCentersSecondForm;
    this.showRegisterAnExam = false;
    this.showViewExamGrades = false;
  }
  toggleShowRegisterAnExam(){
    this.showViewAllCourses = false;
    this.showCourseEnrollmentRequests = false;
    this.showNotifications = false;
    this.showReviewAndRating = false;
    this.showNearbyTestCentersFirstForm = false;
    this.showNearbyTestCentersSecondForm = false;
    this.showRegisterAnExam = !this.showRegisterAnExam;
    this.showViewExamGrades = false;
  }
  toggleShowViewExamGrades(){
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

  constructor(private http: HttpClient) {}

  registerForAnExam() {
    const url = 'http://your-spring-backend-url/api/exams'; // Replace with your backend URL
    this.http.post(url, this.exam)
      .subscribe(response => {
        console.log('Exam registered successfully', response);
        // Handle success
      }, error => {
        console.error('Error registering exam', error);
        // Handle error
      });
  }

}
