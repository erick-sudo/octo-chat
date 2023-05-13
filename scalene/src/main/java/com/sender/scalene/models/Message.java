package com.sender.scalene.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String message;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    public User sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    public User receiver;

    public String getMessage() {
        return this.message;
    }

    public User getReceiver() {
        return this.receiver;
    }
    public User getSender() {
        return this.sender;
    }
}
