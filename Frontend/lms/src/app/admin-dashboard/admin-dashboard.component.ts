import { Component } from '@angular/core';
import { UserService } from '../services';
import { AdminResponse } from '../models/AdminResponse';
import { CenterCredentials } from '../models/CenterCredentials';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css']
})
export class AdminDashboardComponent {
  showViewAllUsers: boolean = false;
  showPublishRequests: boolean = false;
  showRemoveCourse: boolean = false;
  showAccountGeneration: boolean = false;
  showLogs: boolean = false;
  accountGenerationSecondForm: boolean = false;
  centerName: string = '';
  generatedEmail: string = '';
  generatedPassword: string = '';
  users: AdminResponse[] = [];

  constructor(private userService: UserService) {}

  toggleShowViewAllUsers(){
    this.showViewAllUsers = !this.showViewAllUsers;
    this.showPublishRequests = false;
    this.showRemoveCourse = false;
    this.showAccountGeneration = false;
    this.showLogs = false;
    this.accountGenerationSecondForm = false;

    if (this.showViewAllUsers) {
      this.fetchAllUsers();
    }
  }

  toggleShowPublishRequests(){
    this.showViewAllUsers = false;
    this.showPublishRequests = !this.showPublishRequests;
    this.showRemoveCourse = false;
    this.showAccountGeneration = false;
    this.showLogs = false;
    this.accountGenerationSecondForm = false;
  }

  toggleShowRemoveCourse(){
    this.showViewAllUsers = false;
    this.showPublishRequests = false;
    this.showRemoveCourse = !this.showRemoveCourse;
    this.showAccountGeneration = false;
    this.showLogs = false;
    this.accountGenerationSecondForm = false;
  }

  toggleShowAccountGeneration(){
    this.showViewAllUsers = false;
    this.showPublishRequests = false;
    this.showRemoveCourse = false;
    this.showAccountGeneration = !this.showAccountGeneration;
    this.showLogs = false;
    this.accountGenerationSecondForm = false;
  }

  toggleShowLogs(){
    this.showViewAllUsers = false;
    this.showPublishRequests = false;
    this.showRemoveCourse = false;
    this.showAccountGeneration = false;
    this.showLogs = !this.showLogs;
    this.accountGenerationSecondForm = false;
  }

  showAccountGenerationSecondForm(){
    this.accountGenerationSecondForm = true;
    this.showAccountGeneration = false;
  }

  fetchAllUsers() {
    this.userService.getAllUsers().subscribe(
      (data: AdminResponse[]) => {
        this.users = data;
      },
      (error) => {
        console.error('Error fetching users:', error);
      }
    );
  }

  removeUser(user: AdminResponse) {
    if (!user) {
      console.error('Invalid user object:', user);
      return;
    }
  
    const userID = user.userID;
  
    if (userID === undefined || userID === null) {
      console.error('Invalid userId:', userID);
      return;
    }
  
    this.userService.deleteUser(userID).subscribe(
      (success) => {
        if (success) {
          this.users = this.users.filter(u => u.userID !== userID);
        } else {
          console.error('Failed to delete user');
        }
      },
      (error) => {
        console.error('Error deleting user:', error);
      }
    );
  }

  generateTestCenterCredentials(centerName: string) {
    this.userService.generateTestCenterCredentials(centerName).subscribe(
      (result: CenterCredentials) => {
        if (result !== null) {
          this.generatedEmail = result.email;
          this.generatedPassword = result.password;
          this.showAccountGenerationSecondForm();
        } else {
          console.log('Center already exists or an error occurred');
        }
      },
      (error) => {
        console.error('Error generating center credentials:', error);
      }
    );
  } 
}
