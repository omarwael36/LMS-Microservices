import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiUrl = 'http://localhost:8080/User_Servicer-1.0-SNAPSHOT/api/user';

  constructor(private http: HttpClient) {}

  UserSignUp(user: any) {
    return this.http.post<any>(`${this.apiUrl}/UserSignUp`, user);
  }

  StudentSignUp(student: any) {
    return this.http.post<any>(`${this.apiUrl}/StudentSignUp`, student);
  }

  InstructorSignUp(instructor: any) {
    return this.http.post<any>(`${this.apiUrl}/InstructorSignUp`, instructor);
  }
}
