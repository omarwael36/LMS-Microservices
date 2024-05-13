package com.example.enrollmentservice.Controller;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(name = "InstructorController", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/queue/queuename"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")
})
public class InstructorController implements MessageListener {

    @Override
    public void onMessage(Message message) {
        // Handle incoming messages here
        try {
            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                String text = textMessage.getText();
                System.out.println("Received message: " + text);
                // Process the message as needed
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to process message: " + e.getMessage(), e);
        }
    }
}