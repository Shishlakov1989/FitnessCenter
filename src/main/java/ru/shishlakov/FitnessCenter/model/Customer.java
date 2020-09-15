package ru.shishlakov.FitnessCenter.model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@Entity
@Data
public class Customer extends Person {
    public Customer() {
    }

    private String photo;

    @OneToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.LAZY)
    private Subscription subscription;
}
