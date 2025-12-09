package com.example.valid;

import com.example.valid.Poll;
import com.example.valid.User;
import com.example.valid.PollRepository;
import com.example.valid.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired private UserRepository userRepo;
    @Autowired private PollRepository pollRepo;
    @Autowired private PasswordEncoder passwordEncoder;

    // 1. DASHBOARD VIEW
    @GetMapping("/dashboard")
    public String adminDashboard(Model model) {
        model.addAttribute("newUser", new User());
        model.addAttribute("newPoll", new Poll());
        // Load lists for display
        model.addAttribute("allUsers", userRepo.findAll());
        model.addAttribute("allPolls", pollRepo.findAll());
        return "admin_dashboard";
    }

    // 2. CREATE USER
    @PostMapping("/create-user")
    public String createUser(@ModelAttribute User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER"); 
        userRepo.save(user);
        return "redirect:/admin/dashboard?success=user";
    }

    // 3. CREATE POLL (FIXED: Prevents Double Options)
    @PostMapping("/create-poll")
    public String createPoll(@ModelAttribute Poll poll, @RequestParam(required = false) List<String> pollOptions) {
        // Safety: Ensure we start with an empty list so Spring doesn't auto-bind duplicates
        poll.setOptions(new ArrayList<>());
        poll.setCreatedBy("ADMIN"); // Mark as created by admin

        if (pollOptions != null) {
            for (String opt : pollOptions) {
                if (opt != null && !opt.trim().isEmpty()) {
                    poll.addOption(opt.trim());
                }
            }
        }
        pollRepo.save(poll);
        return "redirect:/admin/dashboard?success=poll";
    }

    // 4. DELETE POLL
    @GetMapping("/delete-poll/{id}")
    public String deletePoll(@PathVariable Long id) {
        pollRepo.deleteById(id);
        return "redirect:/admin/dashboard?deleted=true";
    }
    
    // 5. DELETE USER
    @GetMapping("/delete-user/{id}")
    public String deleteUser(@PathVariable Long id) {
        userRepo.deleteById(id);
        return "redirect:/admin/dashboard?deletedUser=true";
    }

    // 6. EDIT POLL FORM
    @GetMapping("/edit-poll/{id}")
    public String editPollForm(@PathVariable Long id, Model model) {
        Poll poll = pollRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid poll Id:" + id));
        model.addAttribute("poll", poll);
        return "edit_poll"; 
    }

    // 7. UPDATE POLL
    @PostMapping("/update-poll")
    public String updatePoll(@ModelAttribute Poll poll) {
        pollRepo.save(poll);
        return "redirect:/admin/dashboard?success=updated";
    }
}