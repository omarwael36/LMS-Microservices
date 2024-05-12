package com.example.Exam.Management.Service.Model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;

@Data
@Document("exam_logs")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ExamLogs {
    @Id
    @MongoId(FieldType.OBJECT_ID)
    private String logId;
    private String name;
    private String role;
    private LocalDateTime timeStamp;
    private String action;
}