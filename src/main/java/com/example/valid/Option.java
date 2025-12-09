package com.example.valid;

import jakarta.persistence.*;

@Entity
@Table(name = "poll_options")
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private int voteCount = 0;

    // --- CONSTRUCTORS ---
    public Option() {}
    
    public Option(String description) { 
        this.description = description; 
    }
    
    // --- METHOD TO INCREMENT VOTE ---
    public void incrementVote() { 
        this.voteCount++; 
    }

    // --- GETTERS AND SETTERS (REQUIRED) ---
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }
}