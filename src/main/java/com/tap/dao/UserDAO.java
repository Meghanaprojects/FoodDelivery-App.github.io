package com.tap.dao;

import com.tap.model.User;
import java.util.List;

public interface UserDAO {
    void addUser(User user);
    User getUser(int userId);
    void updateUser(User user);
    void deleteUser(int userId);
    List<User> getAllUsers();
    User authenticateUser(String email, String password);
}
