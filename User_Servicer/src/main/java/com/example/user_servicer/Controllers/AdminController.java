package com.example.user_servicer.Controllers;

import com.example.user_servicer.Models.AdminResponse;
import com.example.user_servicer.Models.User;
import com.example.user_servicer.Services.AdminService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;


@Path("/admin")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AdminController {

    AdminService adminService = new AdminService();


    public AdminController() {

    }

    @POST
    @Path("/AdminGenerateCenterCredentials")
    public int AdminGenerateCenterCredentials(@QueryParam("CenterName") String CenterName) throws SQLException {
        return adminService.AdminGenerateCenterCredentials(CenterName);
    }

    @GET
    @Path("/AdminViewAllUsers")
    public List<AdminResponse> AdminViewAllUsers() throws SQLException {
        return adminService.AdminViewAllUsers();
    }

    @DELETE
    @Path("/AdminDeleteUser")
    public boolean AdminDeleteUser(@QueryParam("userID") int userID) throws SQLException {
        return adminService.AdminDeleteUser(userID);
    }
}
