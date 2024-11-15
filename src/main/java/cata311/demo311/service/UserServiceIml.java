package cata311.demo311.service;

import cata311.demo311.Repository.UserRepository;
import cata311.demo311.model.User;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceIml implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public UserServiceIml(UserRepository userRepository, @Lazy PasswordEncoder encoder) {
        this.encoder = encoder;
        this.userRepository = userRepository;
    }


    @Transactional(readOnly = true)
    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public void add(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
    }


    @Override
    public User showUser(Long id) {
        return userRepository.findById(id).get();
    }

    @Transactional
    @Override
    public void updateUser(Long id, User user) {
        if (!user.getPassword().equals(showUser(user.getId()).getPassword())) {
            user.setId(id);
            user.setPassword(encoder.encode(user.getPassword()));
        }
        userRepository.save(user);
    }


    @Override
    public boolean deleteUser(Long id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public User getAuthUser() {
        UserDetails userDetails =
                (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long idOfAuthUser = Long.valueOf(1);
        if (findByName(userDetails.getUsername()) != null) {
            idOfAuthUser = findByName(userDetails.getUsername()).getId();
            return findByName(userDetails.getUsername());
        } else {
            return showUser(idOfAuthUser);
        }
    }

    @Transactional
    @Override
    public User findByName(String name) {
        return userRepository.findByName(name);
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByName(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found", username));
        }
        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), user.getAuthorities());
    }
}
