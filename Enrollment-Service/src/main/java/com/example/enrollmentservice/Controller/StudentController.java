package com.example.enrollmentservice.Controller;

import javax.annotation.Resource;
import javax.jms.*;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/student")
public class StudentController {

    @Resource(mappedName = "java:/ConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(mappedName = "java:/queue/queuename")
    private Queue queue;

    @POST
    @Path("/send")
    public Response SendMessage() {
        try (Connection connection = connectionFactory.createConnection()) {
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = queue;
            MessageProducer messageProducer = session.createProducer(destination);
            TextMessage message = session.createTextMessage("Hello, World!");
            messageProducer.send(message);
            return Response.ok("Message sent successfully!").build();
        } catch (JMSException e) {
            throw new RuntimeException("Failed to send message: " + e.getMessage(), e);
        }
    }
}
