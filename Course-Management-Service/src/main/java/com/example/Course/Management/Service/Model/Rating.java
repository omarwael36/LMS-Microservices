package com.example.Course.Management.Service.Model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Document("ratings")
public class Rating {
    @MongoId(FieldType.OBJECT_ID)
    @Id
    private String ratingId;
    private String courseId;
    private int studentId;
    private double rating;
}
