package com.sender.scalene.repos;

import com.sender.scalene.models.Channel;
import com.sender.scalene.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, Long> {

    @Query("SELECT m FROM Message m WHERE m.channel.id = :channelId")
    List<Message> findAllMessagesByChannelId(Long channelId);

    @Query("SELECT c FROM Channel c WHERE c.id = :id")
    Optional<Channel> findById(@Param("id") Long id);
}
