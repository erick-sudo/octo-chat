package com.sender.scalene.models;

public class Message {
    private String message;
    private String sender;
    public String getMessage() {
        return this.message;
    }

    public String getSender() {
        return this.sender;
    }

    @Override
    public String toString() {
        return sender+"|"+message;
    }
}
