package com.example.enrollmentservice.Service;

import com.example.enrollmentservice.DBConnection.DBConnection;
import com.example.enrollmentservice.Model.EnrollmentRequest;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@MessageDriven(name = "InstructorController", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/queue/queuename"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")
})
public class InstructorService implements MessageListener {

    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof ObjectMessage) {
                ObjectMessage objectMessage = (ObjectMessage) message;
                EnrollmentRequest enrollmentRequest = (EnrollmentRequest) objectMessage.getObject();
                processEnrollmentRequest(enrollmentRequest);
            } else {
                System.out.println("Received message of unsupported type: " + message.getClass().getName());
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to process message: " + e.getMessage(), e);
        }
    }

    private void processEnrollmentRequest(EnrollmentRequest enrollmentRequest) {
        Connection connection = null;
        PreparedStatement insertStatement = null;

        try {
            connection = DBConnection.getConnection();
            insertStatement = connection.prepareStatement("INSERT INTO Enrollment (CourseName, StudentID, Status, StudentName, InstructorID) VALUES (?,?,?,?,?)");
            insertStatement.setString(1, enrollmentRequest.getCourseName());
            insertStatement.setInt(2, enrollmentRequest.getStudentId());
            insertStatement.setString(3, enrollmentRequest.getStatus());
            insertStatement.setString(4, enrollmentRequest.getStudentName());
            insertStatement.setInt(5, enrollmentRequest.getInstructorId());
            insertStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Failed to process enrollment request: " + e.getMessage(), e);
        } finally {
            if (insertStatement != null) {
                try {
                    insertStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    public Response ManageEnrollments(int enrollmentID, String action) {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement selectStatement = connection.prepareStatement("SELECT * FROM enrollment WHERE EnrollmentID = ?");
            selectStatement.setInt(1, enrollmentID);
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                String currentStatus = resultSet.getString("Status");
                String newStatus;
                switch (action.toLowerCase()) {
                    case "accept":
                        newStatus = "Accepted";
                        break;
                    case "reject":
                        newStatus = "Rejected";
                        break;
                    default:
                        return Response.status(Response.Status.BAD_REQUEST).entity("Invalid action: " + action).build();
                }

                if (!currentStatus.equals(newStatus)) {
                    PreparedStatement updateStatement = connection.prepareStatement("UPDATE enrollment SET Status = ? WHERE EnrollmentID = ?");
                    updateStatement.setString(1, newStatus);
                    updateStatement.setInt(2, enrollmentID);
                    updateStatement.executeUpdate();

                    PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO notifications (NotificationContent, StudentID) VALUES (?, ?)");
                    insertStatement.setString(1, "Enrollment status changed to " + newStatus);
                    insertStatement.setInt(2, resultSet.getInt("StudentID"));
                    insertStatement.executeUpdate();

                    return Response.ok("Enrollment status updated successfully!").build();
                } else {
                    return Response.ok("Enrollment status is already " + newStatus).build();
                }
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Enrollment with ID " + enrollmentID + " not found").build();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to manage enrollment: " + e.getMessage(), e);
        }
    }

    public List<EnrollmentRequest> getEnrollRequests() {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM enrollment");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            return EnrollmentRequest.convertResultSetToList(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
