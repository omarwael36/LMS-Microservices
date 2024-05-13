package com.example.enrollmentservice.Service;

import com.example.enrollmentservice.DBConnection.DBConnection;
import com.example.enrollmentservice.Model.EnrollmentRequest;
import com.example.enrollmentservice.Model.Notification;

import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentService {

    public Response deleteMessage(String studentName, String courseName) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBConnection.getConnection();
            String sql = "DELETE FROM enrollment WHERE StudentName = ? AND CourseName = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, studentName);
            preparedStatement.setString(2, courseName);
            preparedStatement.executeUpdate();
            return Response.ok("Enrollment request deleted successfully!").build();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }


    public List<EnrollmentRequest> showStudentEnrollments(int studentID) throws SQLException {
        List<EnrollmentRequest> enrollments = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement selectStatement = connection.prepareStatement("SELECT * FROM enrollment WHERE StudentID = ?");
            selectStatement.setInt(1, studentID);
            ResultSet resultSet = selectStatement.executeQuery();
            while (resultSet.next()) {
                EnrollmentRequest enrollment = new EnrollmentRequest();
                enrollment.setStudentId(resultSet.getInt("StudentID"));
                enrollment.setInstructorId(resultSet.getInt("InstructorID"));
                enrollment.setCourseName(resultSet.getString("CourseName"));
                enrollment.setStatus(resultSet.getString("Status"));
                enrollments.add(enrollment);
            }
            return enrollments;
        }
    }

    public List<Notification> getNotifications(int studentId) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM notifications WHERE studentId = ?");
        ) {
            preparedStatement.setInt(1, studentId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return Notification.convertResultSetToList(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
