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

  secondForm: boolean = false;
  thirdForm: boolean = false;
  
  createExamsSecondForm: boolean = false;
  createExamsThirdForm: boolean = false;

  successfullySubmitted: boolean = false;
  

  testCenterName = "el basmala";
  bio = "ana basmala"

  toggleShowUpdateCenterInformation(){
    this.showUpdateCenterInformation = !this.showUpdateCenterInformation;
    this.showCreateExams = false;
    this.showSetGrade = false;
    this.showViewExamsAndGrades = false;

    this.secondForm = false;
    this.thirdForm = false;
    
    this.createExamsSecondForm = false;
    this.createExamsThirdForm = false;

    this.successfullySubmitted = false;

  }
  toggleShowCreateExams(){
    this.showUpdateCenterInformation = false;
    this.showCreateExams = !this.showCreateExams;
    this.showSetGrade = false;
    this.showViewExamsAndGrades = false;

    this.secondForm = false;
    this.thirdForm = false;

    this.createExamsSecondForm = false;
    this.createExamsThirdForm = false;

    this.successfullySubmitted = false;

  }
  toggleShowSetGrade(){
    this.showUpdateCenterInformation = false;
    this.showCreateExams = false;
    this.showSetGrade = !this.showSetGrade;
    this.showViewExamsAndGrades = false;

    this.secondForm = false;
    this.thirdForm = false;

    this.createExamsSecondForm = false;
    this.createExamsThirdForm = false;

    this.successfullySubmitted = false;

  }
  toggleShowViewExamsAndGrades(){
    this.showUpdateCenterInformation = false;
    this.showCreateExams = false;
    this.showSetGrade = false;
    this.showViewExamsAndGrades = !this.showViewExamsAndGrades;

    this.secondForm = false;
    this.thirdForm = false;

    this.createExamsSecondForm = false;
    this.createExamsThirdForm = false;

    this.successfullySubmitted = false;

  }

  showUpdateCenterInformationSecondForm(){
    this.showUpdateCenterInformation = false;
    this.secondForm = true;
    
  }

  showUpdateCenterInformationThirdForm(){
    this.secondForm = false;
    this.thirdForm = true;
  }

  saveBranchInfo(){

    //add code that takes the input values in input fields to be saved in center info

    this.thirdForm = false;
    this.secondForm = true;
    
    
  }

  saveUpdateCenterInfo(){
    //save center info and exit
    this.thirdForm = false;
    this.successfullySubmitted = true;
  }


  // set grades
  ////////////////////////////////////////////////////////////
  submitSetGrade(){
    //set grade procedures
  }


  // create exams
  ////////////////////////////////////////////////////////////
  showCreateExamsSecondForm(){
    this.showCreateExams = false;
    this.createExamsSecondForm = true;
    
  }

  showCreateExamsThirdForm(){
    this.createExamsSecondForm = false;
    this.createExamsThirdForm = true;
  }

  saveDateInfo(){
    this.createExamsSecondForm = true;
    this.createExamsThirdForm = false;
  }

  saveCreateExamsInfo(){
    this.createExamsThirdForm = false;
    this.successfullySubmitted = true;
  }
}

