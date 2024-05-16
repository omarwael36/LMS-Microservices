package com.example.Exam.Management.Service.Model;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class CreateExamRequest {
    private Exam exam;
    private List<ExamDate> examDate;

    public List<ExamDate> getExamDates() {
        return examDate;
    }
}
