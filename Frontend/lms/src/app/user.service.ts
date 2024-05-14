// user.service.ts
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class User {
  userData: any; 

  constructor() { }

  setUserData(userData: any) {
    this.userData = userData;
  }

  getUserData() {
    return this.userData;
  }
}
