package com.example.user_servicer.Services;

import com.example.user_servicer.DBConnection.DBConnection;
import com.example.user_servicer.Models.Instructor;
import com.example.user_servicer.Models.Student;
import com.example.user_servicer.Models.User;

import javax.ejb.Stateless;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Stateless
public class UserService {

    public UserService() {
    }

    public int UserSignUp(User user) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnection.getConnection();
            String sql = "SELECT COUNT(*) FROM user WHERE Email = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getEmail());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next() && resultSet.getInt(1) > 0) {
                return -1;
            }
            String insertSql = "INSERT INTO user (Email, Password, Role) VALUES (?,?,?)";
            preparedStatement = connection.prepareStatement(insertSql);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRole());
            preparedStatement.executeUpdate();
            String query = "SELECT * FROM user WHERE Email = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getEmail());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("UserID");
            }
            return -1;
        } catch (SQLException e) {
            throw e;
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
    }

    public int UserLogin(User user) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnection.getConnection();
            String sql = "SELECT UserID FROM user WHERE Email = ? AND Password = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("UserID");
            } else {
                return -1;
            }
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
            } catch (SQLException e) {
                throw e;
            }
        }
    }

    public int StudentSignUp(Student student) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBConnection.getConnection();
            String sql = "SELECT COUNT(*) FROM student WHERE UserID = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, student.getUserID());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next() && resultSet.getInt(1) > 0) {
                return -1;
            }
            String insertSql = "INSERT INTO student (Name, Affiliation, Bio, UserID) VALUES (?,?,?,?)";
            preparedStatement = connection.prepareStatement(insertSql);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getAffiliation());
            preparedStatement.setString(3, student.getBio());
            preparedStatement.setInt(4, student.getUserID());
            preparedStatement.executeUpdate();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
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
    }

    public int InstructorSignUp(Instructor instructor) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnection.getConnection();
            String sql = "SELECT COUNT(*) FROM instructor WHERE UserID = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, instructor.getUserID());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next() && resultSet.getInt(1) > 0) {
                return -1;
            }
            String insertSql = "INSERT INTO instructor (Name, Affiliation, YearsOfExperience, Bio, UserID) VALUES (?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(insertSql);
            preparedStatement.setString(1, instructor.getName());
            preparedStatement.setString(2, instructor.getAffiliation());
            preparedStatement.setInt(3, instructor.getYearsOfExperience());
            preparedStatement.setString(4, instructor.getBio());
            preparedStatement.setInt(5, instructor.getUserID());
            preparedStatement.executeUpdate();
            return 1;
        } catch (SQLException e) {
            throw e;
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
    }

}