package com.example.valid;

import com.example.valid.Poll;
import com.example.valid.Option;
import com.example.valid.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@Controller
public class PollController {

    @Autowired private PollRepository pollRepository;

    @GetMapping("/login")
    public String loginPage() { return "login"; }

    @GetMapping("/")
    public String userDashboard(Model model, Principal principal) {
        return "redirect:/user/dashboard";
    }

    @PostMapping("/vote/{id}")
    public String vote(@PathVariable Long id, @RequestParam Long optionId, Principal principal) {
        Poll poll = pollRepository.findById(id).orElseThrow();
        String username = principal.getName();

        // Check if user already voted
        if (poll.getVotedUsernames().contains(username)) {
            return "redirect:/?error=already_voted";
        }

        // Increment vote
        for (Option opt : poll.getOptions()) {
            if (opt.getId().equals(optionId)) {
                opt.incrementVote();
                break;
            }
        }

        // Record that user voted
        poll.getVotedUsernames().add(username);
        pollRepository.save(poll);
        return "redirect:/?voted=true";
    }
}