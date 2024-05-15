import { Component } from '@angular/core';

@Component({
  selector: 'app-testcenter-dashboard',
  templateUrl: './testcenter-dashboard.component.html',
  styleUrl: './testcenter-dashboard.component.css'
})
export class TestcenterDashboardComponent {
  showUpdateCenterInformation: boolean = false;
  showCreateExams: boolean = false;
  showSetGrade: boolean = false;
  showViewExamsAndGrades: boolean = false;

  testCenterName = "el basmala";
  bio = "ana basmala"

  toggleShowUpdateCenterInformation(){
    this.showUpdateCenterInformation = !this.showUpdateCenterInformation;
    this.showCreateExams = false;
    this.showSetGrade = false;
    this.showViewExamsAndGrades = false;
  }
  toggleShowCreateExams(){
    this.showUpdateCenterInformation = false;
    this.showCreateExams = !this.showCreateExams;
    this.showSetGrade = false;
    this.showViewExamsAndGrades = false;
  }
  toggleShowSetGrade(){
    this.showUpdateCenterInformation = false;
    this.showCreateExams = false;
    this.showSetGrade = !this.showSetGrade;
    this.showViewExamsAndGrades = false;
  }
  toggleShowViewExamsAndGrades(){
    this.showUpdateCenterInformation = false;
    this.showCreateExams = false;
    this.showSetGrade = false;
    this.showViewExamsAndGrades = !this.showViewExamsAndGrades;
  }

}

