package ru.shishlakov.FitnessCenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.shishlakov.FitnessCenter.model.Employee;
import ru.shishlakov.FitnessCenter.model.User;
import ru.shishlakov.FitnessCenter.model.enums.Privileges;
import ru.shishlakov.FitnessCenter.repository.UserRepository;
import ru.shishlakov.FitnessCenter.util.Util;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = encoder;
    }

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s).get();
    }

    public boolean hasUsers() {
        return userRepository.findAll().size() > 0;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User addUser(Map<String, String> param) {
        User user = new User();
        user.setEmployee(new Employee());

        return editUser(user, param);
    }

    public User editUser(User user, Map<String, String> param) {
        Employee employee = user.getEmployee();
        user.getPrivileges().removeAll(user.getPrivileges());
        user.getPrivileges().add(Privileges.USER);

        for (String k : param.keySet()) {
            switch (k) {
                case "username":
                    user.setUsername(param.get(k));
                    break;
                case "password":
                    user.setPassword(passwordEncoder.encode(param.get(k)));
                    break;
                case "admin":
                    user.getPrivileges().add(Privileges.ADMIN);
                    break;
                case "lastName":
                    employee.setLastName(param.get(k));
                    break;
                case "firstName":
                    employee.setFirstName(param.get(k));
                    break;
                case "middleName":
                    employee.setMiddleName(param.get(k));
                    break;
                case "birthday":
                    employee.setBirthday(Util.getDate(param.get(k)));
                    break;
                case "phoneNumber":
                    employee.setPhoneNumber(param.get(k));
                    break;
                case "sex":
                    employee.setSex(param.get(k).charAt(0));
                    break;
                case "position":
                    employee.setPosition(param.get(k));
                    break;
                case "startWork":
                    employee.setStartWork(Util.getDate(param.get(k)));
                    break;
            }
        }

        return userRepository.save(user);
    }

    public void fire(User user) {
        user.getEmployee().setEndWork(LocalDate.now());
        userRepository.delete(user);
    }
}
