package com.example.user_servicer.Controllers;

import com.example.user_servicer.Models.Instructor;
import com.example.user_servicer.Models.Student;
import com.example.user_servicer.Models.User;
import com.example.user_servicer.Services.UserService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;

@Path("/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserController {

    UserService userService = new UserService();

    public UserController() {

    }

    UserController(UserService userService) {
        this.userService = userService;
    }

    @POST
    @Path("/UserSignUp")
    public int UserSignUp(User user) throws SQLException {
        return userService.UserSignUp(user);
    }

    @POST
    @Path("/UserLogin")
    public int UserSignIn(User user) throws SQLException {
        return userService.UserLogin(user);
    }

    @POST
    @Path("/StudentSignUp")
    public int StudentSignUp(Student student) throws SQLException {
        return userService.StudentSignUp(student);
    }

    @POST
    @Path("/InstructorSignUp")
    public int InstructorSignUp(Instructor instructor) throws SQLException {
        return userService.InstructorSignUp(instructor);
    }



}