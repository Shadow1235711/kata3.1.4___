package cata311.demo311.Dao;


import cata311.demo311.model.User;

import java.util.List;

public interface UserDao {
    List<User> getUsers();

    public User showUser(Long id);

    public void saveUser(User user);

    public void updateUser(Long id, User user);

    public void deleteUser(Long id);
}
