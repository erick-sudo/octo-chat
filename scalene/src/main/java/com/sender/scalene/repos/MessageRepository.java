package com.sender.scalene.repos;

import com.sender.scalene.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAll();


    @Query("SELECT m FROM Message m WHERE (m.sender.id = ?1 AND m.receiver.id = ?2) OR (m.sender.id = ?2 AND m.receiver.id = ?1) ORDER BY m.id ASC")
    List<Message> findMessages(Long senderId, Long receiverId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO message (message, sender_id, receiver_id) VALUES (:message, :senderId, :receiverId)", nativeQuery = true)
    void insertMessage(@Param("message") String message, @Param("senderId") Long senderId, @Param("receiverId") Long receiverId);
}
