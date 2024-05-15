import { Component } from '@angular/core';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrl: './admin-dashboard.component.css'
})
export class AdminDashboardComponent {
  showViewAllUsers: boolean = false;
  showPublishRequests: boolean = false;
  showRemoveCourse: boolean = false;
  showAccountGeneration: boolean = false;
  showLogs: boolean = false;

  toggleShowViewAllUsers(){
    this.showViewAllUsers = !this.showViewAllUsers;
    this.showPublishRequests = false;
    this.showRemoveCourse = false;
    this.showAccountGeneration = false;
    this.showLogs = false;
    }
  toggleShowPublishRequests(){
    this.showViewAllUsers = false;
    this.showPublishRequests = !this.showPublishRequests;
    this.showRemoveCourse = false;
    this.showAccountGeneration = false;
    this.showLogs = false;
  }
  toggleShowRemoveCourse(){
    this.showViewAllUsers = false;
    this.showPublishRequests = false;
    this.showRemoveCourse = !this.showRemoveCourse;
    this.showAccountGeneration = false;
    this.showLogs = false;
  }

  toggleShowAccountGeneration(){
    this.showViewAllUsers = false;
    this.showPublishRequests = false;
    this.showRemoveCourse = false;
    this.showAccountGeneration = !this.showAccountGeneration;
    this.showLogs = false;
    
  }
  toggleShowLogs(){
    this.showViewAllUsers = false;
    this.showPublishRequests = false;
    this.showRemoveCourse = false;
    this.showAccountGeneration = false;
    this.showLogs = !this.showLogs;
    
  }


}
