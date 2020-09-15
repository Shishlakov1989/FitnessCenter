package ru.shishlakov.FitnessCenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.shishlakov.FitnessCenter.model.Customer;
import ru.shishlakov.FitnessCenter.model.Subscription;
import ru.shishlakov.FitnessCenter.model.User;
import ru.shishlakov.FitnessCenter.repository.CustomerRepository;
import ru.shishlakov.FitnessCenter.util.Util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    private CustomerRepository repository;

    public synchronized Map<String, String> getErrorsNewCustomer(Map<String, String> fields) {
        Map<String, String> errors = new HashMap<>();

        /*if (!CheckInput.checkPhoneNumber(fields.get("phoneNumber")))
            errors.put("phoneNumberErr", "Неверный формат номера. <br/>Правильный формат: 79012345678");

        if (!CheckInput.checkBirthday(fields.get("birthday")))
            errors.put("birthdayErr", "Введена некорректная дата");

        if (!CheckInput.checkLastName(fields.get("lastName")))
            errors.put("lastNameErr", "Некорректная фамилия");

        if (!CheckInput.checkFirstName(fields.get("firstName")))
            errors.put("firstNameErr", "Некорректное имя");

        if (!CheckInput.checkMiddleName(fields.get("middleName")))
            errors.put("middleNameErr", "Некорректное отчество");*/

        return errors;
    }

    public synchronized Customer addCustomer(User user, Map<String, String> fields) {
        Optional<Customer> opt = hasCustomer(fields);

        if (opt.isPresent())
            return opt.get();

        Customer customer = new Customer();

        customer.setLastName(Util.getString(fields.get("lastName")));
        customer.setFirstName(Util.getString(fields.get("firstName")));
        customer.setMiddleName(Util.getString(fields.get("middleName")));
        customer.setBirthday(Util.getDate(fields.get("birthday")));
        customer.setPhoneNumber(Util.getPhone(fields.get("phoneNumber")));
        customer.setSex(Util.getSex(fields.get("sex")));
        customer.setSubscription(new Subscription(fields.get("subscription"), user, customer));

        return repository.save(customer);
    }

    public synchronized void editCustomer(Customer customer, Map<String, String> fields) {
        customer.setLastName(Util.getString(fields.get("lastName")));
        customer.setFirstName(Util.getString(fields.get("firstName")));
        customer.setMiddleName(Util.getString(fields.get("middleName")));
        customer.setBirthday(Util.getDate(fields.get("birthday")));
        customer.setPhoneNumber(Util.getPhone(fields.get("phoneNumber")));

        repository.save(customer);
    }

    public List<Customer> findAll(String filter) {
        List<Customer> list = repository.findAll();

        if (filter != null && !filter.isEmpty()) {
            List<Customer> filteredList = list.stream()
                    .filter(customer -> customer.getFirstName().contains(filter) ||
                            customer.getMiddleName().contains(filter) ||
                            customer.getLastName().contains(filter))
                    .collect(Collectors.toList());

            if (filteredList.size() > 0) {
                return filteredList;
            }
        }

        return list.size() > 0 ? list : null;
    }

    public synchronized boolean addCustomerPhoto(MultipartFile file, Customer customer) {
        try {
            if (customer.getPhoto() != null) {
                Files.delete(Paths.get(Util.getUploadPath() + "/" + customer.getPhoto()));
            }

            if (!file.getOriginalFilename().isEmpty()) {
                String fileName = UUID.randomUUID().toString() +
                        "." + file.getOriginalFilename();

                Path uploadFile = Paths.get(Util.getUploadPath() + "/" + fileName);
                file.transferTo(uploadFile);

                customer.setPhoto(fileName);
                repository.save(customer);
            }

            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private Optional<Customer> hasCustomer(Map<String, String> fields) {
        String ln = Util.getString(fields.get("lastName"));
        String fn = Util.getString(fields.get("firstName"));
        String mn = Util.getString(fields.get("middleName"));
        LocalDate bd = Util.getDate(fields.get("birthday"));
        String pn = Util.getPhone(fields.get("phoneNumber"));

        Optional<Customer> opt = repository.findByLastNameAndFirstNameAndMiddleNameAndBirthdayAndPhoneNumber(ln, fn, mn, bd, pn);

        return opt;
    }
}