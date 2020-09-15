package ru.shishlakov.FitnessCenter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.shishlakov.FitnessCenter.model.Subscription;
import ru.shishlakov.FitnessCenter.model.User;
import ru.shishlakov.FitnessCenter.service.SubscriptionService;

@Controller
@RequestMapping("/subscription")
public class SubscriptionController {
    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    private SubscriptionService subscriptionService;

    @GetMapping("{subscription}/training")
    public String startEndTraining(@PathVariable Subscription subscription,
                                   Model model) {
        subscriptionService.startEndTraining(subscription);

        return "redirect:/";
    }

    @GetMapping("/prolongation/{subscription}")
    public String getProlongationPage(@AuthenticationPrincipal User user,
                                      @PathVariable Subscription subscription,
                                      @RequestParam String type,
                                      Model model) {
        subscriptionService.prolongationSubscription(user, subscription, type);

        return "redirect:/customer/edit/" + subscription.getCustomer().getId();
    }
}
