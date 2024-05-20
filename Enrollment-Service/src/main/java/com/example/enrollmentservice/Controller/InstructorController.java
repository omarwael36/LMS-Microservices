package com.example.enrollmentservice.Controller;


import com.example.enrollmentservice.Model.EnrollmentRequest;
import com.example.enrollmentservice.Service.InstructorService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path("/instructor")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class InstructorController {
    InstructorService instructorService = new InstructorService();

    public InstructorController() {
    }

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @PUT
    @Path("/ManageEnrollments")
    public Response ManageEnrollments(@QueryParam("EnrollmentID") int EnrollmentID,@QueryParam("action") String action) throws SQLException {
        return instructorService.ManageEnrollments(EnrollmentID, action);
    }

    @GET
    @Path("/GetEnrollRequests")
    public List<EnrollmentRequest> GetEnrollRequests(@QueryParam("InstructorID") int InstructorID) throws SQLException {
        return instructorService.getEnrollRequests(InstructorID);
    }
}