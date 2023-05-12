package com.sender.scalene;

import com.sender.scalene.repos.ChannelRepository;
import com.sender.scalene.service.MessageConsumerService;
import com.sender.scalene.service.MessageSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Service
public class ScaleneWebSocketHandler extends TextWebSocketHandler {
    // Active MQ services
    private MessageSendService senderService;
    private MessageConsumerService consumerService;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception{
        // Creating new instances of the sender and receiver services
        this.senderService = new MessageSendService(new JmsTemplate());
        this.consumerService = new MessageConsumerService();

        // Storing the sender and the receiver services in the WebSocketSession
        session.getAttributes().put("senderService", senderService);
        session.getAttributes().put("receiverService", consumerService);

        // Initializing services
        //receiverService

        // Superclass method
        super.afterConnectionEstablished(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // Get the sender and receiver services from the WebSocketSession
        MessageSendService senderService = (MessageSendService) session.getAttributes().get("senderService");
        MessageConsumerService receiverService = (MessageConsumerService) session.getAttributes().get("receiverService");


        // Superclass method
        super.afterConnectionClosed(session, status);
    }
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // Incoming client text message
        String payload = message.getPayload();
        senderService.sendMessage("scalene", payload);
        System.out.println("Received message: " + payload);
    }
}