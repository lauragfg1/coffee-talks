package com.example.germanTalks.repository;

import com.example.germanTalks.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByEmail(String email);
    User findByEmail(String email);


    @Query("SELECT u FROM User u WHERE u.participation = :participation")
    List<User> findAllParticipants(@Param("participation") byte participation);
}
