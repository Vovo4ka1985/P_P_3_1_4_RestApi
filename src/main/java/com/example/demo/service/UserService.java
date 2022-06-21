package com.example.demo.service;

import com.example.demo.models.Role;
import com.example.demo.models.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    void addUser(User user);

    List<User> getAllUsers();

    void deleteUser(long id);

    User getUser (long id);

    //User findByUsername(String username);
    Set<Role> getAllRoles();
    void updateUser(User updatedUser);
}
