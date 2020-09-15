package ru.shishlakov.FitnessCenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.shishlakov.FitnessCenter.model.Customer;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findAll();

    Optional<Customer> findByLastNameAndFirstNameAndMiddleNameAndBirthdayAndPhoneNumber(String lastName,
                                                                                        String firstName,
                                                                                        String middleName,
                                                                                        LocalDate birthday,
                                                                                        String phoneNumber);
}
