package com.example.enrollmentservice.Controller;

import com.example.enrollmentservice.Model.EnrollmentRequest;
import com.example.enrollmentservice.Model.Notification;
import com.example.enrollmentservice.Service.StudentService;

import javax.annotation.Resource;
import javax.jms.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

@Path("/student")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StudentController {
    @Resource(mappedName = "java:/ConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(mappedName = "java:/queue/queuename")
    private Queue queue;

    StudentService studentService = new StudentService();

    public StudentController(){

    }

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @POST
    @Path("/send")
    public Response SendMessage(EnrollmentRequest enrollmentRequest) {
        try (Connection connection = connectionFactory.createConnection()) {
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = queue;
            MessageProducer messageProducer = session.createProducer(destination);

            ObjectMessage message = session.createObjectMessage(enrollmentRequest);

            messageProducer.send(message);
            return Response.ok("Enrollment request sent successfully!").build();
        } catch (JMSException e) {
            throw new RuntimeException("Failed to send enrollment request: " + e.getMessage(), e);
        }
    }


    @DELETE
    @Path("/DeleteEnrollRequest")
    public Response DeleteEnrollRequest(@QueryParam("studentName") String StudentName,@QueryParam("courseName") String courseName) throws SQLException {
        return studentService.deleteMessage(StudentName, courseName);
    }



    @GET
    @Path("/GetEnrollments")
    public List<EnrollmentRequest> GetEnrollments(@QueryParam("studentId") int studentID) throws SQLException {
        return studentService.showStudentEnrollments(studentID);
    }
    @GET
    @Path("/GetNotifications")
    public List<Notification> GetNotifications(@QueryParam("studentId") int studentId) throws SQLException {
        return studentService.getNotifications(studentId);
    }

}
