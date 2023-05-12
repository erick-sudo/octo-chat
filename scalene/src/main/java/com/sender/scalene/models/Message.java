package com.sender.scalene.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

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

    @ManyToOne
    @JoinColumn(name = "channel_id")
    public Channel channel;

    public String getMessage() {
        return this.message;
    }

    public User getReceiver() {
        return this.receiver;
    }
    public User getSender() {
        return this.sender;
    }

    public Channel getChannel() {
        return this.channel;
    }
}
