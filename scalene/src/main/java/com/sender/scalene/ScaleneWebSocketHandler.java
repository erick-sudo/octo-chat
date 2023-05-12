package com.sender.scalene;

import com.sender.scalene.models.Channel;
import com.sender.scalene.repos.ChannelRepository;
import com.sender.scalene.repos.MessageRepository;
import com.sender.scalene.repos.UserRepository;
import com.sender.scalene.service.MessageConsumerService;
import com.sender.scalene.service.MessageSendService;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Optional;

@Service
public class ScaleneWebSocketHandler extends TextWebSocketHandler {
    private MessageRepository messageRepository;
    private UserRepository userRepository;
    private ChannelRepository channelRepository;

    // Active MQ services
    private MessageSendService sendService;
    private MessageConsumerService consumerService;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception{
        // Creating new instances of the sender and receiver services
        MessageSendService senderService = new MessageSendService(new JmsTemplate());
        MessageConsumerService receiverService = new MessageConsumerService();

        // Storing the sender and the receiver services in the WebSocketSession
        session.getAttributes().put("senderService", senderService);
        session.getAttributes().put("receiverService", receiverService);

        Long channelId = (Long) session.getAttributes().get("channel");
        Optional<Channel> optionalChannel = channelRepository.findById(channelId);
        Channel channel = optionalChannel.orElseThrow(() -> new RuntimeException("Channel not found"));


        // Initializing services
        receiverService.setChannel(channel);

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

        System.out.println("Received message: " + payload);
    }
}
