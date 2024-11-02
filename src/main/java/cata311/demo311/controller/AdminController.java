package cata311.demo311.controller;

import cata311.demo311.model.User;
import cata311.demo311.service.RoleService;
import cata311.demo311.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;


    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;

    }


    @GetMapping("/users")
    public String allUsers(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "allUsers";
    }

    @PostMapping("/create")
    public String creatUser(@Valid @RequestBody @ModelAttribute("user") User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "newUser";
        }
        userService.updateUser(user.getId(), user);
        return "redirect:users";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", roleService.getAllRoles());
        return "newUser";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:users";
    }

    @GetMapping("/edit")
    public String editUser(@RequestParam("id") Long id, ModelMap model) {
        model.addAttribute("user", userService.showUser(id));
        model.addAttribute("allRoles", roleService.getAllRoles());
        return "editUser";
    }

    @PostMapping("/update")
    public String updateUser(@Valid @RequestBody @ModelAttribute("user") User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "editUser";
        }
        userService.updateUser(user.getId(), user);
        return "redirect:/admin/users";
    }

    @GetMapping("/showUser")
    public String userDetails(@RequestParam("id") Long id, Model model) {
        model.addAttribute("users", userService.showUser(id));
        return "userDetails";
    }
}
