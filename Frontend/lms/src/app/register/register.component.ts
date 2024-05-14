import { Component } from '@angular/core';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  name: string;
  email: string;
  password: string;
  affiliation: string;
  bio: string;
  role: string;
  yearsOfExperience: number;

  constructor() {
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
    if (this.role === 'instructor') {
      console.log('Years of Experience:', this.yearsOfExperience);
    }
  }
}