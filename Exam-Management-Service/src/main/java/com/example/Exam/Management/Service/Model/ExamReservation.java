package com.example.Exam.Management.Service.Model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ExamReservation {
    private int studentId;
    private String studentName;
    private int examResult;
}
