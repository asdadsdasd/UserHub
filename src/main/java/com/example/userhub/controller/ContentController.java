package com.example.userhub.controller;

import com.example.userhub.entity.User;
import com.example.userhub.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class ContentController {
    private final UserRepository userRepository;

    public ContentController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity<?> getContent(Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow();
        return ResponseEntity.ok(Map.of(
                "text", user.getUniqueText(),
                "imageUrl", "/images/" + user.getImagePath()
        ));
    }
}
