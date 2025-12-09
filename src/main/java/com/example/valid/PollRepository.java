package com.example.valid;

import com.example.valid.Poll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface PollRepository extends JpaRepository<Poll, Long> {
    // Find polls created by a specific user
    List<Poll> findByCreatedBy(String username);
    
    // Find polls where a user has voted
    @Query("SELECT p FROM Poll p WHERE :username MEMBER OF p.votedUsernames")
    List<Poll> findPollsVotedByUser(@Param("username") String username);
}