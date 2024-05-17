import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { User } from '../user.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;

  constructor(private formBuilder: FormBuilder, private userService: User, private http: HttpClient, private router: Router) {
    this.loginForm = this.formBuilder.group({
      email: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    
  }

  login() {
    if (this.loginForm.valid) {
      var credentials = {
        email: this.loginForm.value.email, 
        password: this.loginForm.value.password
      };
  
      const headers = new HttpHeaders().set('Content-Type', 'application/json');
  
      this.http.post<any>('http://localhost:8080/User_Servicer-1.0-SNAPSHOT/api/user/UserLogin', credentials, { headers }).subscribe(
        (response) => {
          console.log('Login successful:', response);
          if (response) {
            // Update user data with each login
            this.userService.setUserData(response); 
            this.navigateToDashboard(); 
          } else {
            console.error('Invalid response or login failure');
          }
        },
        (error) => {
          console.error('Login error:', error);
        }
      );
    } else {
      // Form is invalid, handle accordingly (e.g., display error messages)
    }
  }
  

  navigateToDashboard() {
    var userData = this.userService.getUserData();
    if (userData && userData.Role) {
      if (userData.Role === 'instructor') {
        this.router.navigate(['/instructorDashboard']);
      } else if (userData.Role === 'student') {
        this.router.navigate(['/studentDashboard']);
      } else if (userData.Role === 'admin') {
        this.router.navigate(['/adminDashboard']);
      } else if (userData.Role === 'center') {
        this.router.navigate(['/testcenterDashboard']);
      } else {
        // Handle other roles
      }
    } else {
      // Handle missing user data or role
    }
  }
}
