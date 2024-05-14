import { Component } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  username: string;
  password: string;

  constructor() {
    this.username = '';
    this.password = '';
  }

  login() {
    // Here you can implement your login logic
    console.log('Login button clicked');
    console.log('Username:', this.username);
    console.log('Password:', this.password);
    // Example: You can send a request to a server for authentication
  }
}
