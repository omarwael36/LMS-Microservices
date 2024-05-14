import { Component } from '@angular/core';
import { UserService } from '../services';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  name: string;
  email: string;
  password: string;
  affiliation: string;
  bio: string;
  role: string;
  yearsOfExperience: number;

  constructor(private userService: UserService) {
    this.name = '';
    this.email = '';
    this.password = '';
    this.affiliation = '';
    this.bio = '';
    this.role = 'student';
    this.yearsOfExperience = 0;
  }

  register() {
    console.log('Register button clicked');
    console.log('Name:', this.name);
    console.log('Email:', this.email);
    console.log('Password:', this.password);
    console.log('Affiliation:', this.affiliation);
    console.log('Bio:', this.bio);
    console.log('Role:', this.role);

    const user = {
      email: this.email,
      password: this.password,
      role: this.role
    };

    this.userService.UserSignUp(user).subscribe(
      (userID) => {
        console.log('User signed up, UserID:', userID);
        if (userID !== -1) {
          if (this.role === 'student') {
            this.userService.StudentSignUp({
              userID: userID,
              name: this.name,
              affiliation: this.affiliation,
              bio: this.bio
            }).subscribe(
              (result) => {
                console.log('Student signed up:', result);
              },
              (error) => {
                console.error('Student sign up error:', error);
              }
            );
          } else if (this.role === 'instructor') {
            this.userService.InstructorSignUp({
              userID: userID,
              name: this.name,
              affiliation: this.affiliation,
              bio: this.bio,
              yearsOfExperience: this.yearsOfExperience
            }).subscribe(
              (result) => {
                console.log('Instructor signed up:', result);
              },
              (error) => {
                console.error('Instructor sign up error:', error);
              }
            );
          }
        } else {
          console.error('User sign up failed');
        }
      },
      (error) => {
        console.error('User sign up error:', error);
      }
    );
  }
}
