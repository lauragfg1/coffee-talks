package com.example.germanTalks.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.germanTalks.model.User;
import com.example.germanTalks.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void getAuthUser(HttpServletResponse response, @AuthenticationPrincipal OidcUser principal) throws IOException, IOException {
        if (principal != null) {
            String email = principal.getAttribute("email");

            if (!userService.userExistsByEmail(email)) {
                User user = new User();
                user.setName(principal.getAttribute("name"));
                user.setEmail(email);
                userService.saveUser(user);
            }
            response.sendRedirect("http://localhost:3000/home");
        } else {
            response.sendRedirect("http://localhost:3000/");
        }
    }

    @GetMapping("/check-auth")
    public Boolean checkAuth(@AuthenticationPrincipal OidcUser principal) {
        return (principal != null);
    }

}
