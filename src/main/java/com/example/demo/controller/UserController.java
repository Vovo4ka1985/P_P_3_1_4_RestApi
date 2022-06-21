package com.example.demo.controller;

import com.example.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserDetailsService userDetailsService;

    @Autowired
    public UserController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @GetMapping()
    public String index(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        return "user";
    }
}

    /*
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //@RequestMapping(value = { "/usersList" }, method = RequestMethod.GET)
    @GetMapping("/users")
    //Возвращаем список из людей
    public String allUsers (Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }
    @GetMapping("/{id}")
    // Получим одного человека из ДАО и передадим в отображение
    public String showById (@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "users";
    }
    @GetMapping("/create")
    public String newUserForm (@ModelAttribute("user") User user, Model model) {
        //model.addAttribute("user", new User());
        return "create";
    }
    @PostMapping("/create")
    public String create(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/users";
    }
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
    @GetMapping("/update/{id}")
    public String edit (@PathVariable("id") long id,Model model) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return "update";
    }
    @PostMapping ("/update")
    public String editUsers(User user) {
        userService.addUser(user);//addUser(user)
        return "redirect:/users";
    }

     */
