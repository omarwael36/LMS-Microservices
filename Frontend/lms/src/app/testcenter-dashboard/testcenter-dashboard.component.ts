import { Component, OnInit } from '@angular/core';
import { UserService } from '../services';
import { User } from '../user.service';

@Component({
  selector: 'app-testcenter-dashboard',
  templateUrl: './testcenter-dashboard.component.html',
  styleUrls: ['./testcenter-dashboard.component.css']
})
export class TestcenterDashboardComponent implements OnInit {
  showUpdateCenterInformation: boolean = false;
  showCreateExams: boolean = false;
  showSetGrade: boolean = false;
  showViewExamsAndGrades: boolean = false;

  secondForm: boolean = false;
  thirdForm: boolean = false;
  
  createExamsSecondForm: boolean = false;
  createExamsThirdForm: boolean = false;

  successfullySubmitted: boolean = false;
  
  testCenterName: string = "";
  bio: string = "";

  branchName: string = "";
  branchLocation: string = "";
  branchAddress: string = "";

  branches: any[] = [];
  examName: string = "";
  fullMark: number = 0;
  examDate: string = "";
  examTime: string = "";
  capacity: number = 0;
  examDates: any[] = [];

  constructor(private testCenterService: UserService, private userService: User) {}

  ngOnInit() {
    const userData = this.userService.getUserData();
    if (userData) {
      this.testCenterName = userData.username;
    }
  }

  toggleShowUpdateCenterInformation() {
    this.showUpdateCenterInformation = !this.showUpdateCenterInformation;
    this.resetForms();
  }

  toggleShowCreateExams() {
    this.showCreateExams = !this.showCreateExams;
    this.resetForms();
  }

  toggleShowSetGrade() {
    this.showSetGrade = !this.showSetGrade;
    this.resetForms();
  }

  toggleShowViewExamsAndGrades() {
    this.showViewExamsAndGrades = !this.showViewExamsAndGrades;
    this.resetForms();
  }

  showUpdateCenterInformationSecondForm() {
    this.secondForm = true;
    this.showUpdateCenterInformation = false;
  }

  showUpdateCenterInformationThirdForm() {
    this.thirdForm = true;
    this.secondForm = false;
  }

  saveBranchInfo() {
    const branchInfo = {
      branchName: this.branchName,
      branchLocation: this.branchLocation,
      branchAddress: this.branchAddress,
      examDates: []
    };
    this.branches.push(branchInfo);
    this.resetBranchInputs();
    this.secondForm = true;
    this.thirdForm = false;
  }

  saveUpdateCenterInfo() {
    const testCenterInfo = {
      testCenterName: this.testCenterName,
      bio: this.bio,
      branches: this.branches
    };

    this.testCenterService.saveTestCenterInfo(testCenterInfo).subscribe(
      (response) => {
        console.log("Test center information saved successfully:", response);
        this.successfullySubmitted = true;
      },
      (error) => {
        console.error("Error occurred while saving test center information:", error);
      }
    );
  }

  submitSetGrade() {
    // Placeholder for setting grades
  }

  showCreateExamsSecondForm() {
    this.createExamsSecondForm = true;
    this.showCreateExams = false;
  }

  showCreateExamsThirdForm() {
    this.createExamsThirdForm = true;
    this.createExamsSecondForm = false;
  }

  saveDateInfo() {
    const dateInfo = {
      examId: '',
      examDate: this.examDate,
      branchName: this.branchName,
      examTime: this.examTime,
      capacity: this.capacity,
      examReservations: [],
      isFull: false
    };
    this.examDates.push(dateInfo);
    this.createExamsSecondForm = true;
    this.createExamsThirdForm = false;
  }

  saveCreateExamsInfo() {
    const examInfo = {
      exam: {
        examName: this.examName,
        fullMark: this.fullMark,
        createdBy: this.testCenterName
      },
      examDate: this.examDates
    };

    this.testCenterService.createExam(examInfo, this.testCenterName).subscribe(
      (response) => {
        console.log("Exam created successfully:", response);
        this.successfullySubmitted = true;
      },
      (error) => {
        console.error("Error occurred while creating exam:", error);
      }
    );

    this.createExamsThirdForm = false;
  }

  resetForms() {
    this.showCreateExams = false;
    this.showSetGrade = false;
    this.showViewExamsAndGrades = false;

    this.secondForm = false;
    this.thirdForm = false;

    this.createExamsSecondForm = false;
    this.createExamsThirdForm = false;

    this.successfullySubmitted = false;
  }

  resetBranchInputs() {
    this.branchName = "";
    this.branchLocation = "";
    this.branchAddress = "";
  }
}
