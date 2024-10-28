package cata311.demo311.service;


import cata311.demo311.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> getUsers();

    User showUser(Long id);

    void updateUser(Long id, User user);

    void deleteUser(Long id);

    User findByName(String name);
}
