export interface TestCenter {
    id: string;
    name: string;
    branches: Branch[];
  }
  
  export interface Branch {
    id: string;
    name: string;
    location: string;
  }
  