import { Component } from '@angular/core';

@Component({
  selector: 'app-instructor-dashboard',
  templateUrl: './instructor-dashboard.component.html',
  styleUrl: './instructor-dashboard.component.css'
})
export class InstructorDashboardComponent {
  showForm: boolean = false;
  showAllCoursesTable: boolean = false;
  showEnrollmentRequestsTable: boolean = false;

  newCourse: any = {}; // Object to store new course data

  toggleCreateCourseForm() {
    this.showForm = !this.showForm;
    this.showAllCoursesTable = false;
    this.showEnrollmentRequestsTable = false;
    this.showEnrollmentRequestsTable = false;


  }

  toggleShowAllCoursesTable(){
    this.showForm = false;
    this.showAllCoursesTable = !this.showAllCoursesTable;
    this.showEnrollmentRequestsTable = false;
  }

  toggleShowEnrollmentRequests(){
    this.showForm = false;
    this.showAllCoursesTable = false;
    this.showEnrollmentRequestsTable = !this.showEnrollmentRequestsTable;
  }

  createCourse() {
    console.log('Creating course:', this.newCourse);
    this.toggleCreateCourseForm();
  }
}
