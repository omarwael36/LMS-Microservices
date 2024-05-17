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
  
  accountGenerationSecondForm: boolean = false;


  toggleShowViewAllUsers(){
    this.showViewAllUsers = !this.showViewAllUsers;
    this.showPublishRequests = false;
    this.showRemoveCourse = false;
    this.showAccountGeneration = false;
    this.showLogs = false;

    this.showAccountGeneration = false;
    this.accountGenerationSecondForm = false;


    }
  toggleShowPublishRequests(){
    this.showViewAllUsers = false;
    this.showPublishRequests = !this.showPublishRequests;
    this.showRemoveCourse = false;
    this.showAccountGeneration = false;
    this.showLogs = false;

    this.showAccountGeneration = false;
    this.accountGenerationSecondForm = false;


  }
  toggleShowRemoveCourse(){
    this.showViewAllUsers = false;
    this.showPublishRequests = false;
    this.showRemoveCourse = !this.showRemoveCourse;
    this.showAccountGeneration = false;
    this.showLogs = false;

    this.showAccountGeneration = false;
    this.accountGenerationSecondForm = false;


  }

  toggleShowAccountGeneration(){
    this.showViewAllUsers = false;
    this.showPublishRequests = false;
    this.showRemoveCourse = false;
    this.showAccountGeneration = !this.showAccountGeneration;
    this.showLogs = false;

    this.accountGenerationSecondForm = false;
    this.accountGenerationSecondForm = false;

    
  }
  toggleShowLogs(){
    this.showViewAllUsers = false;
    this.showPublishRequests = false;
    this.showRemoveCourse = false;
    this.showAccountGeneration = false;
    this.showLogs = !this.showLogs;
    

    this.showAccountGeneration = false;
    this.accountGenerationSecondForm = false;


  }


  showAccountGenerationSecondForm(){
    this.accountGenerationSecondForm = true;
    this.showAccountGeneration = false;

  }

}
