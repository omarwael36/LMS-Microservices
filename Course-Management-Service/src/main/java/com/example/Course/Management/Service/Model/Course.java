package com.example.Course.Management.Service.Model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Data
@Document("course")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Course {
    @Id
    @MongoId(FieldType.OBJECT_ID)
    private String courseId;
    private String courseName;
    private double courseRating;
    private String courseStartDate;
    private String courseEndDate;
    private String courseCategory;
    private int courseCapacity;
    private int courseEnrolled;
    private Instructor instructor;
    private List<Review> reviews;
    private boolean coursePublished;
}
