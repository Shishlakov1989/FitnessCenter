package ru.shishlakov.FitnessCenter.model;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@MappedSuperclass
@Data
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Size(min = 2, max = 35)
    private String lastName;

    @NotNull
    @Size(min = 2, max = 20)
    private String firstName;

    @NotNull
    @Size(min = 2, max = 25)
    private String middleName;

    private char sex;
    private LocalDate birthday;

    @NotNull
    @Size(min = 10, max = 20)
    private String phoneNumber;

    public String getBirthdayString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        return birthday.format(formatter);
    }

    public String getSexString() {
        return sex == 'm' ? "мужской" : "женский";
    }

    public String getFio() {
        StringBuilder sb = new StringBuilder(lastName);
        sb.append(" ")
                .append(firstName)
                .append(" ")
                .append(middleName);


        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return Objects.equals(lastName, person.lastName) &&
                Objects.equals(firstName, person.firstName) &&
                Objects.equals(middleName, person.middleName) &&
                Objects.equals(birthday, person.birthday) &&
                Objects.equals(phoneNumber, person.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, firstName, middleName, birthday, phoneNumber);
    }
}