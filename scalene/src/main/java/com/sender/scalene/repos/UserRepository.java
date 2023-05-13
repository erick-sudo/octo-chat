package com.sender.scalene.repos;

import com.sender.scalene.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();

    @Query("SELECT u FROM User u WHERE u.id = ?1")
    User findUser(Long id);
}
