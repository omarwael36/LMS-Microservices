package com.example.user_servicer.Services;

import com.example.user_servicer.DBConnection.DBConnection;
import com.example.user_servicer.Models.AdminResponse;
import com.example.user_servicer.Models.User;

import javax.ejb.Stateless;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Stateless
public class AdminService {
    public AdminService() {
    }


    public int AdminGenerateCenterCredentials(String centername) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBConnection.getConnection();
            String sql = "SELECT COUNT(*) FROM user WHERE Email LIKE ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + centername + "%");
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next() && resultSet.getInt(1) > 0) {
                return -1;
            }
            SecureRandom random = new SecureRandom();
            byte[] randomBytes = new byte[8];
            random.nextBytes(randomBytes);
            String randomPassword = Base64.getEncoder().encodeToString(randomBytes);

            String insertSql = "INSERT INTO user (Email, Password, Role) VALUES (?,?,?)";
            preparedStatement = connection.prepareStatement(insertSql);
            preparedStatement.setString(1, centername + "@gmail.com");
            preparedStatement.setString(2, randomPassword);
            preparedStatement.setString(3, "center");
            preparedStatement.executeUpdate();
            return 1;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return 0;
    }

    public List<AdminResponse> AdminViewAllUsers() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<AdminResponse> adminResponses = new ArrayList<>();
        try {
            connection = DBConnection.getConnection();

            String selectSql = "SELECT u.UserID AS UserID, u.email AS Email, u.password AS Password, u.role AS Role, " +
                    "s.StudentID AS StudentID, s.name AS StudentName, s.Affiliation AS StudentAffiliated, s.bio AS StudentBio, s.UserID AS StudentUserID, " +
                    "i.InstructorID AS InstructorID, i.name AS InstructorName, i.affiliation AS InstructorAffiliated, i.YearsOfExperience AS Experience, i.bio AS InstructorBio, i.UserID AS InstructorUserID " +
                    "FROM user u " +
                    "LEFT JOIN student s ON u.UserID = s.UserID " +
                    "LEFT JOIN instructor i ON u.UserID = i.UserID";

            preparedStatement = connection.prepareStatement(selectSql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int userID = resultSet.getInt("UserID");
                String StudentName = resultSet.getString("StudentName");
                String InstructorName = resultSet.getString("InstructorName");
                int experience = resultSet.getInt("Experience");
                String role = resultSet.getString("Role");
                String email = resultSet.getString("Email");
                String password = resultSet.getString("Password");

                String studentAffiliated = resultSet.getString("StudentAffiliated");
                String studentBio = resultSet.getString("StudentBio");

                String instructorAffiliated = resultSet.getString("InstructorAffiliated");
                String instructorBio = resultSet.getString("InstructorBio");

                if (role.equals("student")) {
                    adminResponses.add(new AdminResponse(userID, StudentName, studentBio, studentAffiliated, experience, role, email, password));
                } else if (role.equals("instructor")) {
                    adminResponses.add(new AdminResponse(userID, InstructorName, instructorBio, instructorAffiliated, experience, role, email, password));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return adminResponses;
    }

    public boolean AdminDeleteUser(int userID) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBConnection.getConnection();
            String Sql = "DELETE FROM user WHERE UserID =?";
            preparedStatement = connection.prepareStatement(Sql);
            preparedStatement.setInt(1, userID);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return true;
    }
}
