package com.example.Exam.Management.Service.Model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExamResults {
    private String studentName;
    private int examResult;
    private Exam exam;
}
