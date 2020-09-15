package ru.shishlakov.FitnessCenter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.shishlakov.FitnessCenter.model.Customer;
import ru.shishlakov.FitnessCenter.service.CustomerService;

@Controller
@RequestMapping("/upload")
public class FileUploadController {
    @Autowired
    public FileUploadController(CustomerService customerService) {
        this.customerService = customerService;
    }

    private CustomerService customerService;

    @PostMapping("{customer}")
    public String uploadFile(@PathVariable Customer customer,
                             @RequestParam MultipartFile file,
                             Model model) {
        if (customerService.addCustomerPhoto(file, customer))
            model.addAttribute("message", "Файл(ы) загружен(ы)");
        else
            model.addAttribute("message", "Ошибка загрузки файла");

        return "redirect:/customer/edit/" + customer.getId();
    }
}
