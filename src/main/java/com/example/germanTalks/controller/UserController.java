package com.example.germanTalks.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.germanTalks.model.User;
import com.example.germanTalks.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getAll")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }


    @GetMapping("/getAuthUser")
    @ResponseBody
    public ResponseEntity<Map<String, String>> getAuthUser(@AuthenticationPrincipal OidcUser principal) {
        Map<String, String> response = new HashMap<>();
        if (principal != null) {
            String email = principal.getAttribute("email");
            String name = principal.getAttribute("name");

            if (!userService.userExistsByEmail(email)) {
                User user = new User();
                user.setName(name);
                user.setEmail(email);
                userService.saveUser(user);
            }
            response.put("name", name);
            response.put("email", email);
            return ResponseEntity.ok(response);
        } else {
            response.put("error", "User not authenticated");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    @GetMapping("/check-auth")
    public Boolean checkAuth(@AuthenticationPrincipal OidcUser principal) {
        return (principal != null);
    }

    // In UserController.java
    @PostMapping("/updateParticipation")
    public ResponseEntity<String> updateParticipation(@AuthenticationPrincipal OidcUser principal, @RequestParam("participation") Byte participation) {
        if (principal != null) {
            String email = principal.getAttribute("email");
            User user = userService.findByEmail(email);
            if (user != null) {
                user.setParticipation(participation);
                userService.saveUser(user);
                return ResponseEntity.ok("Participation updated");
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
    }

}
