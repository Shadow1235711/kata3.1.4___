package cata311.demo311.controller;

import cata311.demo311.model.Role;
import cata311.demo311.model.User;
import cata311.demo311.service.RoleService;
import cata311.demo311.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Collection;
import java.util.List;


@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/showAccount")
    public ResponseEntity<User> showInfoUser(Principal principal) {
        return ResponseEntity.ok(userService.findByName(principal.getName()));
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @GetMapping(value = "/roles")
    public ResponseEntity<Collection<Role>> getAllRoles() {
        return new ResponseEntity<>(roleService.getAllRoles(), HttpStatus.OK);
    }

    @GetMapping("/roles/{id}")
    public ResponseEntity<Collection<Role>> getRole(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.showUser(id).getRoles(), HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        User user = userService.showUser(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<User> addNewUser(@RequestBody  User newUser) {
        userService.add(newUser);
        return new ResponseEntity<>(newUser, HttpStatus.OK);

    }

    @PatchMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User userFromWebPage, @PathVariable("id") Long id) {
        userService.updateUser(id,userFromWebPage);
        return new ResponseEntity<>(userFromWebPage, HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") Long id) {
        User user = userService.showUser(id);
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
