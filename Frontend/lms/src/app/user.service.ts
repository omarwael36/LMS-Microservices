// user.service.ts
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  userData: any; // Store user data here

  constructor() { }

  setUserData(userData: any) {
    this.userData = userData;
  }

  getUserData() {
    return this.userData;
  }
}
