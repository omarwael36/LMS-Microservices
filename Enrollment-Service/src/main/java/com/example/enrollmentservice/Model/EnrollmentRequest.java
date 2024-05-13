package com.example.enrollmentservice.Model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentRequest implements Serializable {
    private int studentId;
    private int instructorId;
    private String courseName;
    private String studentName;
    private String status;

    public EnrollmentRequest() {
    }

    public EnrollmentRequest(int studentId, int instructorId, String courseName, String studentName, String status) {
        this.studentId = studentId;
        this.instructorId = instructorId;
        this.courseName = courseName;
        this.studentName = studentName;
        this.status = status;
    }


    public static List<EnrollmentRequest> convertResultSetToList(ResultSet resultSet) {
        List<EnrollmentRequest> enrollmentRequests = new ArrayList<>();
        try {
            while (resultSet.next()) {
                enrollmentRequests.add(new EnrollmentRequest(resultSet.getInt("studentId"),
                        resultSet.getInt("instructorId"),
                        resultSet.getString("courseName"),
                        resultSet.getString("status"),
                        resultSet.getString("studentName")));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error in ConvertResultSetToList: " + e.getMessage(), e);
        }
        return enrollmentRequests;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(int instructorId) {
        this.instructorId = instructorId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
