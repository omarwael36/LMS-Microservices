import { Component } from '@angular/core';
import { UserService } from '../services';
import { Course } from '../models/Courses';
import { User } from '../user.service';

@Component({
  selector: 'app-instructor-dashboard',
  templateUrl: './instructor-dashboard.component.html',
  styleUrls: ['./instructor-dashboard.component.css']
})
export class InstructorDashboardComponent {

  showForm: boolean = false;
  showAllCoursesTable: boolean = false;
  showEnrollmentRequestsTable: boolean = false;
  newCourse: any = {}; 
  instructorData: any;
  courses: Course[] = [];
  searchQuery: string = '';
  searchOption: string = 'category';

  constructor(private courseService: UserService, private user: User) {
  
    this.instructorData = user.getUserData();
  }

  toggleCreateCourseForm() {
    this.showForm = !this.showForm;
    this.showAllCoursesTable = false;
    this.showEnrollmentRequestsTable = false;
  }

  toggleShowAllCoursesTable() {
    this.showForm = false;
    this.showAllCoursesTable = !this.showAllCoursesTable;
    this.showEnrollmentRequestsTable = false;
    if (this.showAllCoursesTable) {
      this.fetchAllCourses();
    }
  }

  toggleShowEnrollmentRequests() {
    this.showForm = false;
    this.showAllCoursesTable = false;
    this.showEnrollmentRequestsTable = !this.showEnrollmentRequestsTable;
  }

  createCourse() {
    console.log('Creating course:', this.newCourse);
    // Check if instructor data is available
    if (this.instructorData && this.instructorData.UserID && this.instructorData.Name) {
      this.newCourse.instructor = {
        instructorID: this.instructorData.UserID,
        name: this.instructorData.Name
      };
      this.courseService.addCourse(this.newCourse, this.instructorData.Name).subscribe(
        (response) => {
          console.log('Course created successfully:', response);
          this.newCourse = {};
          this.toggleCreateCourseForm();
        },
        (error) => {
          console.error('Error creating course:', error);
        }
      );
    } else {
      console.error('Instructor data not found.');
    }
  }


  fetchAllCourses() {
    this.courseService.viewAllCourses(this.instructorData.Name, 'instructor').subscribe(
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
        this.courseService.searchCoursesByName('name',searchQuery, this.instructorData.Name, 'instructor').subscribe(
          (courses) => {
            this.courses = courses;
          },
          (error) => {
            console.error('Error fetching courses by name:', error);
          }
        );
      } else if (searchOption === 'category') {
        this.courseService.searchCoursesByName('category', searchQuery, this.instructorData.Name, 'instructor').subscribe(
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
    this.courseService.sortCoursesByRating(sortOrder, this.instructorData.Name, 'instructor').subscribe(
      (courses) => {
        this.courses = courses;
      },
      (error) => {
        console.error('Error sorting courses:', error);
      }
    );
  }

  calculateDuration(startDate: string, endDate: string): string {
    const start = new Date(startDate);
    const end = new Date(endDate);
    const durationInMilliseconds = Math.abs(end.getTime() - start.getTime());
    const days = Math.ceil(durationInMilliseconds / (1000 * 60 * 60 * 24));
    return `${days} days`;
  }
}
