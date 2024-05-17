package com.example.Course.Management.Service.Model;


import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@Document("ratings")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Rating {
    @MongoId(FieldType.OBJECT_ID)
    @Id
    private ObjectId ratingId;
    private ObjectId courseId;
    private int studentId;
    private double rating;
}
