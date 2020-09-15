package ru.shishlakov.FitnessCenter.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Data
public class Payment {
    public Payment() {
        date = LocalDate.now();
    }

    public Payment(Employee employee, double price) {
        this();
        this.employee = employee;
        this.price = price;
    }

    @Id
    @GeneratedValue
    private long id;

    private LocalDate date;
    private double price;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
