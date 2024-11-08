package cata311.demo311.controller;

import cata311.demo311.model.User;
import cata311.demo311.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private Long userAdminID;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping()
    public String user(Principal principal, Model model) {
        if (userService.findByName(principal.getName()) != null) {
            userAdminID = userService.findByName(principal.getName()).getId();
            model.addAttribute("user", userService.findByName(principal.getName()));
        }
        if (userService.findByName(principal.getName()) == null) {
            model.addAttribute("user", userService.showUser(userAdminID));

        }
        List<User> listOfUsers = userService.getUsers();
        model.addAttribute("userList", listOfUsers);
        return "user";
    }


}
