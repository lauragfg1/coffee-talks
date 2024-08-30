package com.example.germanTalks.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.germanTalks.model.Language;
import com.example.germanTalks.model.User;
import com.example.germanTalks.service.LanguageServiceImpl;
import com.example.germanTalks.service.UserService;
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
    @Autowired
    private LanguageServiceImpl languageServiceImpl;

    @GetMapping("/getAll")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/getParticipants")
    public List<User> getParticipants() {
        return userService.getAllParticipants();
    }

    @GetMapping("/loginAndRedirect")
    public ResponseEntity<Void> loginAndRedirect(@AuthenticationPrincipal OidcUser principal) {
        if (principal != null) {
            String email = principal.getAttribute("email");
            String name = principal.getAttribute("name");

            if (!userService.userExistsByEmail(email)) {
                User user = new User();
                user.setName(name);
                user.setEmail(email);
                user.setParticipation((byte) 0);
                userService.saveUser(user);
            }

            return ResponseEntity.status(HttpStatus.FOUND)
                    .location(URI.create("http://localhost:3000/home"))
                    .build();
        } else {
            return ResponseEntity.status(HttpStatus.FOUND)
                    .location(URI.create("http://localhost:3000/login"))
                    .build();
        }
    }

    @GetMapping("/getAuthUser")
    @ResponseBody
    public ResponseEntity<Map<String, String>> getAuthUserDetails(@AuthenticationPrincipal OidcUser principal) {
        Map<String, String> response = new HashMap<>();
        if (principal != null) {
            String email = principal.getAttribute("email");
            String name = principal.getAttribute("name");
            Byte participation = userService.findByEmail(email).getParticipation();

            response.put("name", name);
            response.put("email", email);
            response.put("participation", participation.toString());
            return ResponseEntity.ok(response);
        } else {
            response.put("error", "User not authenticated");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }


    @PutMapping("/updateParticipation")
    public ResponseEntity<String> updateParticipation(@AuthenticationPrincipal OidcUser principal, @RequestParam("participation") Byte participation) {
        if (principal != null) {
            String email = principal.getAttribute("email");
            User user = userService.findByEmail(email);

            if (user != null) {
                user.setParticipation(participation);
                userService.saveUser(user);
                return ResponseEntity.ok("Participation updated successfully");
            } else {
                return ResponseEntity.status(404).body("User not found");
            }
        } else {
            return ResponseEntity.status(401).body("User not authenticated");
        }
    }


    @PutMapping("/updateLevel")
    public ResponseEntity<String> updateLevel(@AuthenticationPrincipal OidcUser principal, @RequestParam("level") String level) {
        if (principal != null) {
            String email = principal.getAttribute("email");
            User user = userService.findByEmail(email);

            if (user != null) {
                user.setLevelGerman(level);
                userService.saveUser(user);
                return ResponseEntity.ok("Level updated successfully");
            } else {
                return ResponseEntity.status(404).body("User not found");
            }
        } else {
            return ResponseEntity.status(401).body("User not authenticated");
        }
    }

    @PutMapping("/updateMotherTongue")
    public ResponseEntity<String> updateMotherTongue(@AuthenticationPrincipal OidcUser principal, @RequestParam("motherTongue") Integer motherTongueId) {
        if (principal != null) {
            String email = principal.getAttribute("email");
            User user = userService.findByEmail(email);

            if (user != null) {
                Language motherTongue = languageServiceImpl.findById(motherTongueId);
                if (motherTongue != null) {
                    user.setMotherTongue(motherTongue);
                    userService.saveUser(user);
                    return ResponseEntity.ok("Mother tongue updated successfully");
                } else {
                    return ResponseEntity.status(404).body("Language not found");
                }
            } else {
                return ResponseEntity.status(404).body("User not found");
            }
        } else {
            return ResponseEntity.status(401).body("User not authenticated");
        }
    }

    @GetMapping("/getInitialValues")
    public ResponseEntity<Map<String, Object>> getInitialValues(@AuthenticationPrincipal OidcUser principal) {
        if (principal != null) {
            String email = principal.getAttribute("email");
            User user = userService.findByEmail(email);

            if (user != null) {
                Map<String, Object> initialValues = new HashMap<>();
                initialValues.put("level", user.getLevelGerman());
                initialValues.put("participation", user.getParticipation());
                initialValues.put("motherTongue", user.getMotherTongue() != null ? user.getMotherTongue().getName() : null);
                return ResponseEntity.ok(initialValues);
            } else {
                return ResponseEntity.status(404).body(null);
            }
        } else {
            return ResponseEntity.status(401).body(null);
        }
    }



}

