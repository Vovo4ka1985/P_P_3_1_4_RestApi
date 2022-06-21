package com.example.demo.controller;

import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
@RestController
@RequestMapping("/admin/api/users")
public class ControllerApi {
    private final UserService userService;
    @Autowired
    public ControllerApi(UserService userService, UserRepository userRepository) {
        this.userService = userService;
    }

    //@RequestMapping(value = { "/usersList" }, method = RequestMethod.GET)
    @GetMapping()
    //Возвращаем список из людей
    public List<User> showAllUsers () {
        List<User> allUsers = userService.getAllUsers();
        return allUsers;
    }
    @GetMapping("{id}")
    // Получим одного человека из ДАО и передадим в отображение
    public User showById (@PathVariable("id") long id) {
       return userService.getUser(id);
    }

    @PostMapping()
    public User createUser(@RequestBody User user) { //@ModelAttribute("user")
        userService.addUser(user);
        return user;
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("{id}")
    public User updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return user;
    }
}
