package ru.shishlakov.FitnessCenter.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Data
public class Employee extends Person {
    public Employee() {
        startWork = LocalDate.now();
    }

    @NotNull
    @Pattern(regexp = "^[а-яА-Я\\sa-zA-Z]+$")
    @Size(min = 4, max = 50)
    private String position;

    @NotNull
    private LocalDate startWork;
    private LocalDate endWork;

    public boolean isActive() {
        return endWork == null;
    }
}
