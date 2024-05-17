package com.example.Exam.Management.Service.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ExamDate {
    @MongoId(FieldType.OBJECT_ID)
    private String examId;
    private String examDate;
    private String branchName;
    private String examTime;
    private int capacity;
    private List<ExamReservation> examReservations;
    private boolean isFull;

    public void setIsFull(boolean b) {
        isFull = b;
    }

}
