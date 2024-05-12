package com.example.Exam.Management.Service.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateExamRequest {
    private Exam exam;
    private ExamDate examDate;
}
