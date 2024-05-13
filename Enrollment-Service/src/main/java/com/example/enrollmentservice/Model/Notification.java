package com.example.enrollmentservice.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Notification {
    private int notificationId;
    private String notificationContent;
    private int studentId;

    public Notification() {
    }

    public Notification(int notificationId, String notificationContent, int studentId) {
        this.notificationId = notificationId;
        this.notificationContent = notificationContent;
        this.studentId = studentId;
    }

    public static List<Notification> convertResultSetToList(ResultSet resultSet) {
        List<Notification> notifications = new ArrayList<>();
        try {
            while (resultSet.next()) {
                notifications.add(new Notification(resultSet.getInt("notificationId"),
                        resultSet.getString("notificationContent"),
                        resultSet.getInt("studentId")));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error in ConvertResultSetToList: " + e.getMessage(), e);
        }
        return notifications;
    }

    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public String getNotificationContent() {
        return notificationContent;
    }

    public void setNotificationContent(String notificationContent) {
        this.notificationContent = notificationContent;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
}
