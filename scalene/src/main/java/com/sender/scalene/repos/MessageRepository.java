package com.sender.scalene.repos;

import com.sender.scalene.models.Message;
import com.sender.scalene.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAll();

    findMessagesBy
}
