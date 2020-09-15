package ru.shishlakov.FitnessCenter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.shishlakov.FitnessCenter.model.User;
import ru.shishlakov.FitnessCenter.service.UserService;
import ru.shishlakov.FitnessCenter.util.Util;

import java.util.Map;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    private UserService userService;

    @GetMapping
    public String getUserListPage(@RequestParam(required = false, defaultValue = "") String filter,
                                  Model model) {
        if (filter != null || !filter.isEmpty())
            model.addAttribute("filter", filter);

        model.addAttribute("userList", userService.findAll());

        return "userList";
    }

    @GetMapping("/add")
    public String getRegisterPage(Model model) {
        return "register";
    }

    @PostMapping("/add")
    public String addNewUser(@RequestParam Map<String, String> param,
                             Model model) {
        param.remove("_csrf");
        Map<String, String> errors = Util.getPersonErrors(param);
        errors.putAll(Util.getUserErrors(param));
        User user;

        if (errors.isEmpty()) {
            user = userService.addUser(param);
        } else {
            model.mergeAttributes(errors);
            model.mergeAttributes(param);

            return "register";
        }

        return "redirect:/user/edit/" + user.getId();
    }

    @GetMapping("/edit/{user}")
    public String getUserPage(@PathVariable User user,
                              Model model) {
        model.addAttribute("user", user);

        return "userInfo";
    }

    @PostMapping("/edit/{user}")
    public String editUser(@PathVariable User user,
                           @RequestParam Map<String, String> param,
                           Model model) {
        param.remove("_csrf");

        Map<String, String> errors = Util.getPersonErrors(param);
        errors.putAll(Util.getUserErrors(param));

        if (errors.isEmpty()) {
            userService.editUser(user, param);
        } else {
            model.mergeAttributes(errors);
            model.mergeAttributes(param);

            return "userInfo";
        }

        return "redirect:/user/edit/" + user.getId();
    }

    @GetMapping("/fire/{user}")
    public String fireUser(@PathVariable User user) {
        userService.fire(user);

        return "redirect:/user";
    }
}
