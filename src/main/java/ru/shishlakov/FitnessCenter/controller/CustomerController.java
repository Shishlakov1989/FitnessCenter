package ru.shishlakov.FitnessCenter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.shishlakov.FitnessCenter.model.Customer;
import ru.shishlakov.FitnessCenter.model.User;
import ru.shishlakov.FitnessCenter.model.enums.SubscriptionType;
import ru.shishlakov.FitnessCenter.service.CustomerService;
import ru.shishlakov.FitnessCenter.service.SubscriptionService;
import ru.shishlakov.FitnessCenter.util.Util;

import java.util.Map;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    public CustomerController(CustomerService customerService, SubscriptionService subscriptionService) {
        this.customerService = customerService;
        this.subscriptionService = subscriptionService;
    }

    private CustomerService customerService;
    private SubscriptionService subscriptionService;

    @GetMapping
    public String getCustomerListPage(@RequestParam(required = false, defaultValue = "") String filter,
                                      Model model) {
        model.addAttribute("customerList", customerService.findAll(filter.trim()));

        if (filter != null || !filter.isEmpty())
            model.addAttribute("filter", filter);

        return "customerList";
    }

    @GetMapping("/add")
    public String getNewCustomerPage(Model model) {
        return "newCustomer";
    }

    @PostMapping("/add")
    public String addCustomer(@AuthenticationPrincipal User user,
                              @RequestParam Map<String, String> param,
                              Model model) {
        param.remove("_csrf");
        Map<String, String> errors = Util.getPersonErrors(param);
        Customer c = null;

        if (errors.isEmpty()) {
            c = customerService.addCustomer(user, param);
        } else {
            model.mergeAttributes(errors);
            model.mergeAttributes(param);
        }

        return c == null ? "redirect:/customer/add" : "redirect:/customer/edit/" + c.getId();
    }

    @GetMapping("/edit/{customer}")
    public String getCustomerInfoPage(@PathVariable Customer customer,
                                      Model model) {
        subscriptionService.checkDiscount(customer.getSubscription());
        model.addAttribute("customer", customer);
        model.addAttribute("typesSubscr", SubscriptionType.values());

        return "customerInfo";
    }

    @PostMapping("/edit/{customer}")
    public String changeCustomerInfo(@PathVariable Customer customer,
                                     @RequestParam Map<String, String> fields,
                                     Model model) {
        Map<String, String> errors = Util.getPersonErrors(fields);

        if (errors.isEmpty()) {
            customerService.editCustomer(customer, fields);
        }

        return "redirect:/customer/edit/" + customer.getId();
    }


}
