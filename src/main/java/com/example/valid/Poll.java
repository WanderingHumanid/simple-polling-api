package com.example.valid;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "polls")
public class Poll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Question cannot be empty")
    private String question;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Option> options = new ArrayList<>();

    // Tracks users who have voted to prevent duplicates
    @ElementCollection
    private Set<String> votedUsernames = new HashSet<>();

    // Track who created this poll
    private String createdBy;

    public Poll() {}

    // Helper: Add Option
    public void addOption(String text) {
        this.options.add(new Option(text));
    }

    // Helper: Calculate Total Votes for percentages
    public int getTotalVotes() {
        int total = 0;
        if (options != null) {
            for (Option opt : options) {
                total += opt.getVoteCount();
            }
        }
        return total;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getQuestion() { return question; }
    public void setQuestion(String question) { this.question = question; }
    public List<Option> getOptions() { return options; }
    public void setOptions(List<Option> options) { this.options = options; }
    public Set<String> getVotedUsernames() { return votedUsernames; }
    public void setVotedUsernames(Set<String> votedUsernames) { this.votedUsernames = votedUsernames; }
    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }
}