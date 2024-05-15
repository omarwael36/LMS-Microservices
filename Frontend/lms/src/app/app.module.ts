import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

import { HttpClientModule } from '@angular/common/http';

import { ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';

import { User } from './user.service';

import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { InstructorDashboardComponent } from './instructor-dashboard/instructor-dashboard.component';
import { StudentDashboardComponent } from './student-dashboard/student-dashboard.component';
import { TestcenterDashboardComponent } from './testcenter-dashboard/testcenter-dashboard.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    AdminDashboardComponent,
    InstructorDashboardComponent,
    StudentDashboardComponent,
    TestcenterDashboardComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    AppRoutingModule,
    RouterModule.forRoot([])
  ],
  providers: [User],
  bootstrap: [AppComponent]
})
export class AppModule { }
