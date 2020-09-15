package ru.shishlakov.FitnessCenter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.shishlakov.FitnessCenter.model.Subscription;
import ru.shishlakov.FitnessCenter.service.SubscriptionService;
import ru.shishlakov.FitnessCenter.service.UserService;
import ru.shishlakov.FitnessCenter.util.Util;

import java.util.List;
import java.util.Map;

@Controller
public class MainController {
    @Autowired
    public MainController(SubscriptionService service, UserService userService) {
        this.subscriptionService = service;
        this.userService = userService;
    }

    private SubscriptionService subscriptionService;
    private UserService userService;

    @GetMapping("/")
    public String getMainPage(@RequestParam(required = false, defaultValue = "") String subscription,
                              Model model) {
        if (subscription != null && !subscription.isEmpty()) {
            model.addAttribute("subscription", subscriptionService.findById(subscription));
        }

        List<Subscription> inGym = subscriptionService.findAllInGymIsTrue();

        model.addAttribute("inGym", inGym);
        model.addAttribute("name", "Алахвердиев Равиль Тристанович");

        return "index";
    }

    @GetMapping("/error")
    public String getErrorPage(Model model) {
        return "error";
    }

    @GetMapping("/init")
    public String getInitPage(Model model) {
        return userService.hasUsers() ? "redirect:/" : "initPage";
    }

    @PostMapping("/init")
    public String addFirstEmployee(@RequestParam Map<String, String> param,
                                   Model model) {
        param.put("admin", "on");

        param.remove("_csrf");

        Map<String, String> errors = Util.getPersonErrors(param);
        errors.putAll(Util.getUserErrors(param));

        if (!errors.isEmpty()) {
            model.mergeAttributes(errors);
            model.mergeAttributes(param);

            return "initPage";
        }

        userService.addUser(param);

        return "redirect:/login";
    }
}