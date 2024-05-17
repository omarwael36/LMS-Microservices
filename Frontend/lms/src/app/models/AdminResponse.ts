export class AdminResponse {
    userID: number;
    name: string;
    bio: string;
    affiliation: string;
    experience: number;
    role: string;
    email: string;
    password: string;
  
    constructor(
      userID: number,
      name: string,
      bio: string,
      affiliation: string,
      experience: number,
      role: string,
      email: string,
      password: string
    ) {
      this.userID = userID;
      this.name = name;
      this.bio = bio;
      this.affiliation = affiliation;
      this.experience = experience;
      this.role = role;
      this.email = email;
      this.password = password;
    }
  }
  