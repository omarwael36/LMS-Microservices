import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms'; // Import Angular forms related modules
import { UserService } from '../user.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup; // Define a FormGroup for the login form

  constructor(private formBuilder: FormBuilder, private userService: UserService, private http: HttpClient, private router: Router) {
    // Initialize the login form in the constructor
    this.loginForm = this.formBuilder.group({
      email: ['', Validators.required], // Set up validators for email field
      password: ['', Validators.required] // Set up validators for password field
    });
  }

  ngOnInit(): void {
    // Additional initialization logic can go here
  }

  login() {
    if (this.loginForm.valid) { // Check if the form is valid before proceeding
      const credentials = {
        email: this.loginForm.value.email, // Access form values using value property
        password: this.loginForm.value.password // Access form values using value property
      };

      const headers = new HttpHeaders().set('Content-Type', 'application/json');

      this.http.post<any>('http://localhost:8080/User_Servicer-1.0-SNAPSHOT/api/user/UserLogin', credentials, { headers }).subscribe(
        (response) => {
          console.log('Login successful:', response);
          this.userService.setUserData(response); 
          this.navigateToDashboard(); 
        },
        (error) => {
          // Handle login error
          console.error('Login error:', error);
        }
      );
    } else {
      // Form is invalid, handle accordingly (e.g., display error messages)
    }
  }

  navigateToDashboard() {
    const userData = this.userService.getUserData();
    if (userData && userData.Role) {
      if (userData.Role === 'instructor') {
        this.router.navigate(['/instructor-dashboard']);
      } else if (userData.Role === 'student') {
        this.router.navigate(['/student-dashboard']);
      } else {
        // Handle other roles
      }
    } else {
      // Handle missing user data or role
    }
  }
}
