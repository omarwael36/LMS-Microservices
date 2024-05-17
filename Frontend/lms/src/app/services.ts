import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AdminResponse } from './models/AdminResponse';
import { Course } from './models/Courses';
import { ExamResults } from './models/ExamResults';
import { TestCenter } from './models/TestCenter';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiUrl = 'http://localhost:8080/User_Servicer-1.0-SNAPSHOT/api';

  constructor(private http: HttpClient) {}

  UserSignUp(user: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/user/UserSignUp`, user);
  }

  StudentSignUp(student: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/user/StudentSignUp`, student);
  }

  InstructorSignUp(instructor: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/user/InstructorSignUp`, instructor);
  }

  // Method to fetch all users
  getAllUsers(): Observable<AdminResponse[]> {
    return this.http.get<AdminResponse[]>(`${this.apiUrl}/admin/AdminViewAllUsers`);
  }

  // New method to delete a user
  deleteUser(userID: number): Observable<boolean> {
    return this.http.delete<boolean>(`${this.apiUrl}/admin/AdminDeleteUser`, {
      params: { userID: userID.toString() }
    });
  }

  generateTestCenterCredentials(centerName: string): Observable<any> {
    return this.http.post(`${this.apiUrl}/admin/AdminGenerateCenterCredentials?CenterName=${centerName}`, {});
  }

  addCourse(course: any, instructorName: string) {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    // Assuming your backend API endpoint for adding a course is '/AddCourse'
    return this.http.post<any>(`http://localhost:8083/api/v1/course/AddCourse?instructorName=${instructorName}`, course, { headers });
  }

  viewAllCourses(userName: string, userRole: string): Observable<Course[]> {
    return this.http.get<Course[]>(`http://localhost:8083/api/v1/course/ViewAllCourses?userName=${userName}&userRole=${userRole}`);
  }

  searchCoursesByName(key: string, value: string, userName: string, userRole: string): Observable<Course[]> {
    return this.http.get<Course[]>(`http://localhost:8083/api/v1/course/SearchCoursesByFilter?key=${key}&value=${value}&userName=${userName}&userRole=${userRole}`);
  }

  sortCoursesByRating(sortOrder: string, userName: string, userRole: string): Observable<Course[]> {
    return this.http.get<Course[]>(`http://localhost:8083/api/v1/course/SortCoursesByRating?sortOrder=${sortOrder}&userName=${userName}&userRole=${userRole}`);
  }

  // New method to save test center information
  saveTestCenterInfo(testCenterInfo: any): Observable<any> {
    return this.http.post<any>(`http://localhost:8081/api/v1/TestCenter/AddTestCenter`, testCenterInfo);
  }
  createExam(examInfo: any, testCenterName: string): Observable<any> {
    return this.http.post<any>(`http://localhost:8081/api/v1/TestCenter/CreateExam?testCenterName=${testCenterName}`, examInfo);
  }
  getStudentsGrades(testCenterName: string): Observable<ExamResults[]> {
    return this.http.get<ExamResults[]>(`http://localhost:8081/api/v1/TestCenter/GetStudentsGrades?TestCenterName=${testCenterName}`);
  }

  rateCourse(requestBody: any, userName: string, userRole: string) {
    const url = `http://localhost:8083/api/v1/course/RateCourse?userName=${userName}&userRole=${userRole}`;
    return this.http.post(url, requestBody);
  }

  addCourseReview(requestBody: any, userName: string, userRole: string) {
    const url = `http://localhost:8083/api/v1/course/AddCourseReview?userName=${userName}&userRole=${userRole}`;
    return this.http.post(url, requestBody);
  }

  getTestCentersByLocation(location: string): Observable<TestCenter[]> {
    return this.http.get<TestCenter[]>(`http://localhost:8081/api/v1/TestCenter/GetTestCentersByLocation?location=${location}`);
  }

  showPublishRequests(): Observable<Course[]> {
    return this.http.get<Course[]>(`http://localhost:8083/api/v1/course/ShowPublishRequests`);
  }

  adminPublishCourse(courseName: string): Observable<void> {
    return this.http.put<void>(`http://localhost:8083/api/v1/course/AdminPublishCourse`, { courseName });
  }

  adminRejectCourse(courseName: string): Observable<void> {
    return this.http.put<void>(`http://localhost:8083/api/v1/course/AdminRejectCourse`, { courseName });
  }


}
