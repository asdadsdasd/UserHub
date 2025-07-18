package com.example.userhub.controller;

import com.example.userhub.entity.User;
import com.example.userhub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class ContentController {
    private final UserRepository userRepository;

    @Value("${app.upload.dir:/app/images/avatars}")
    private String uploadDir;

    public ContentController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/upload")
    public String uploadPage() {
        return "upload";
    }

    @PostMapping("/upload")
    public String handleUpload(@RequestParam("image") MultipartFile file, Principal principal, Model model) {
        if (file.isEmpty()) {
            model.addAttribute("error", "Empty file");
            return "upload";
        }

        try {
            String username = principal.getName();
            User user = userRepository.findByUsername(username).orElseThrow();

            String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();

            File dest = new File(uploadDir, filename);
            dest.getParentFile().mkdirs();
            file.transferTo(dest);

            user.setImagePath(filename);
            userRepository.save(user);

            return "redirect:/user/content";

        } catch (IOException e) {
            model.addAttribute("error", "Upload failed");
            return "upload";
        }
    }

    @GetMapping("/content")
    public String userContent(Model model, Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow();

        model.addAttribute("text", user.getUniqueText());
        model.addAttribute("imageUrl", "/images/" + user.getImagePath());
        return "content";
    }

    @GetMapping("/test")
    public String index() {
        return "Hello World";
    }
}
