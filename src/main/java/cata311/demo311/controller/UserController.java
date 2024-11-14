package cata311.demo311.controller;

import cata311.demo311.model.User;
import cata311.demo311.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.security.Principal;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/showAccount")
    public ResponseEntity<User> showUserAccount(Principal principal) {
        User user = userService.findByName(principal.getName());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
