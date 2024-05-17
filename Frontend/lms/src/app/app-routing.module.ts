import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { InstructorDashboardComponent } from './instructor-dashboard/instructor-dashboard.component';
import { StudentDashboardComponent } from './student-dashboard/student-dashboard.component';
import { TestcenterDashboardComponent } from './testcenter-dashboard/testcenter-dashboard.component';

const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'register', component: RegisterComponent },

  { path: 'adminDashboard', component: AdminDashboardComponent },
  { path: 'instructorDashboard', component: InstructorDashboardComponent },
  { path: 'studentDashboard', component: StudentDashboardComponent },
  { path: 'testcenterDashboard', component: TestcenterDashboardComponent },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
