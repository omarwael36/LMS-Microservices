import { Component, OnInit } from '@angular/core';
import { UserService } from '../services';
import { User } from '../user.service';
import { ExamResults } from '../models/ExamResults'; // Import ExamResults model

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
  examDatesList: any[] = []; // New list to store exam dates

  studentsGrades: ExamResults[] = []; // New property to store exam results

  constructor(private testCenterService: UserService, private userService: User) {}

  ngOnInit() {
    const userData = this.userService.getUserData();
    if (userData) {
      this.testCenterName = userData.username;
    }
    this.getStudentsGrades();
  }

  toggleShowUpdateCenterInformation() {
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

  toggleShowCreateExams() {
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

  toggleShowSetGrade() {
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

  toggleShowViewExamsAndGrades() {
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
      examDate: this.examDate,
      examTime: this.examTime,
      capacity: this.capacity,
      branchName: this.branchName
    };
    this.examDatesList.push(dateInfo);
    this.createExamsSecondForm = true;
    this.createExamsThirdForm = false;

    // Clear input fields after saving date info
    this.examDate = "";
    this.examTime = "";
    this.capacity = 0;
    this.branchName = "";
  }

  saveCreateExamsInfo() {
    console.log(this.testCenterName)
    const examInfo = {
      exam: {
        examName: this.examName,
        fullMark: this.fullMark,
        createdBy: this.testCenterName
      },
      examDate: this.examDatesList
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

    // Reset the form and examDatesList after creating the exam
    this.resetForms();
    this.examDatesList = [];
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

  getStudentsGrades() {
    this.testCenterService.getStudentsGrades(this.testCenterName).subscribe(
      (response) => {
        console.log("Students grades retrieved successfully:", response);
        this.studentsGrades = response;
      },
      (error) => {
        console.error("Error occurred while getting students grades:", error);
      }
    );
  }
}
