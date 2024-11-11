package cata311.demo311.controller;

import cata311.demo311.model.User;
import cata311.demo311.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {


    private final UserService userService;


    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/users")
    public String allUsers(Model model) {
        model.addAttribute("user", userService.getAuthUser());
        List<User> listOfUsers = userService.getUsers();
        model.addAttribute("userList", listOfUsers);

        return "MainAdminPage";
    }

    @PutMapping("/create")
    public String creatUser(@ModelAttribute("user") User user) {

        userService.add(user);
        return "redirect:/admin/users";
    }


    @DeleteMapping("/delete{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }


    @PatchMapping("/update{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userService.updateUser(id, user);
        return "redirect:/admin/users";
    }


}
