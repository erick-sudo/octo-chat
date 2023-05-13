package com.sender.scalene;

import com.sender.scalene.repos.MessageRepository;
import com.sender.scalene.service.MessageSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


@Service
public class ScaleneWebSocketHandler extends TextWebSocketHandler {

    List<SessionHash> sessions = new CopyOnWriteArrayList<>();

    private class SessionHash {

        public WebSocketSession session;
        public Long sender;
        public Long receiver;
        SessionHash(WebSocketSession session, Long sender, Long receiver) {
            this.session = session;
            this.sender = sender;
            this.receiver = receiver;
        }
    }

    private MessageRepository messageRepository;

    @Autowired
    ScaleneWebSocketHandler(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception{
        // Superclass method
        super.afterConnectionEstablished(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // Superclass method
        super.afterConnectionClosed(session, status);
    }

    @Transactional
    public void saveMessage(String[] fields) {
        messageRepository.insertMessage(fields[0], Long.parseLong(fields[1]), Long.parseLong(fields[2]));
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // Incoming client text message
        String payload = message.getPayload();
        String[] fields = payload.split("\\|");

        // saving the message
        saveMessage(fields);

        MessageSendService.sendMessage("scalene", payload);
    }
}