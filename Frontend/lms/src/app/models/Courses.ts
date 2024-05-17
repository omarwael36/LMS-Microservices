import { Review } from './Reviews'; // Import the Review model if it exists

export interface Course {
  courseId: string;
  courseName: string;
  courseRating: number;
  courseStartDate: string;
  courseEndDate: string;
  courseCategory: string;
  courseCapacity: number;
  courseEnrolled: number; // Add courseEnrolled property
  instructor: Instructor;
  reviews?: Review[]; // Make reviews optional
  coursePublished: boolean;
}

interface Instructor {
  instructorID: number;
  name: string;
}
