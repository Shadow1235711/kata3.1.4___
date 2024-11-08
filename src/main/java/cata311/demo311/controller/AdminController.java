package cata311.demo311.controller;

import cata311.demo311.model.User;
import cata311.demo311.service.RoleService;
import cata311.demo311.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private Long userAdminID;

    private final UserService userService;
    private final RoleService roleService;


    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;

    }


    @GetMapping("/users")
    public String allUsers(Model model, Principal principal) {
        if (userService.findByName(principal.getName()) != null) {
            userAdminID = userService.findByName(principal.getName()).getId();
            model.addAttribute("user", userService.findByName(principal.getName()));
        }
        if (userService.findByName(principal.getName()) == null) {
            model.addAttribute("user", userService.showUser(userAdminID));

        }
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
