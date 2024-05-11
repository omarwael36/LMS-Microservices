package com.example.Exam.Management.Service.Model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@Document("exam")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Exam {
    @Id
    @MongoId(FieldType.OBJECT_ID)
    private String examId;
    private String examName;
    private int fullMark;
    private String createdBy;
}
