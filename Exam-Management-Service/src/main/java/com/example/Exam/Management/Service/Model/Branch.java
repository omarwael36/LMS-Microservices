package com.example.Exam.Management.Service.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Branch {
    private String branchName;
    private String branchLocation;
    private String branchAddress;
    private List<ExamDate> examDates;
}
