package com.example.valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired private PollRepository pollRepo;

    // 1. USER DASHBOARD - View voted polls and own polls
    @GetMapping("/dashboard")
    public String userDashboard(Model model, Principal principal) {
        String username = principal.getName();
        
        model.addAttribute("username", username);
        model.addAttribute("allPolls", pollRepo.findAll());
        model.addAttribute("votedPolls", pollRepo.findPollsVotedByUser(username));
        model.addAttribute("myPolls", pollRepo.findByCreatedBy(username));
        model.addAttribute("newPoll", new Poll());
        
        return "user_dashboard_new";
    }

    // 2. CREATE POLL by User
    @PostMapping("/create-poll")
    public String createPoll(@ModelAttribute Poll poll, 
                            @RequestParam(required = false) List<String> pollOptions,
                            Principal principal) {
        poll.setOptions(new ArrayList<>());
        poll.setCreatedBy(principal.getName());

        if (pollOptions != null) {
            for (String opt : pollOptions) {
                if (opt != null && !opt.trim().isEmpty()) {
                    poll.addOption(opt.trim());
                }
            }
        }
        pollRepo.save(poll);
        return "redirect:/user/dashboard?success=poll";
    }

    // 3. EDIT POLL FORM (only if user is creator)
    @GetMapping("/edit-poll/{id}")
    public String editPollForm(@PathVariable Long id, Model model, Principal principal) {
        Poll poll = pollRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid poll Id:" + id));
        
        // Check if current user is the creator
        if (!poll.getCreatedBy().equals(principal.getName())) {
            return "redirect:/user/dashboard?error=unauthorized";
        }
        
        model.addAttribute("poll", poll);
        return "user_edit_poll";
    }

    // 4. UPDATE POLL (only if user is creator)
    @PostMapping("/update-poll")
    public String updatePoll(@ModelAttribute Poll poll, 
                            @RequestParam(required = false) List<String> pollOptions,
                            Principal principal) {
        Poll existingPoll = pollRepo.findById(poll.getId())
            .orElseThrow(() -> new IllegalArgumentException("Invalid poll Id:" + poll.getId()));
        
        // Check if current user is the creator
        if (!existingPoll.getCreatedBy().equals(principal.getName())) {
            return "redirect:/user/dashboard?error=unauthorized";
        }
        
        // Update poll
        existingPoll.setQuestion(poll.getQuestion());
        existingPoll.setOptions(new ArrayList<>());
        
        if (pollOptions != null) {
            for (String opt : pollOptions) {
                if (opt != null && !opt.trim().isEmpty()) {
                    existingPoll.addOption(opt.trim());
                }
            }
        }
        
        pollRepo.save(existingPoll);
        return "redirect:/user/dashboard?success=updated";
    }

    // 5. DELETE POLL (only if user is creator)
    @GetMapping("/delete-poll/{id}")
    public String deletePoll(@PathVariable Long id, Principal principal) {
        Poll poll = pollRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid poll Id:" + id));
        
        // Check if current user is the creator
        if (!poll.getCreatedBy().equals(principal.getName())) {
            return "redirect:/user/dashboard?error=unauthorized";
        }
        
        pollRepo.deleteById(id);
        return "redirect:/user/dashboard?deleted=true";
    }
}
