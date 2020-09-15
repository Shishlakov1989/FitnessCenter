package ru.shishlakov.FitnessCenter.model;

import lombok.Data;
import ru.shishlakov.FitnessCenter.model.enums.SubscriptionType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Subscription {
    public Subscription() {

    }

    public Subscription(String type, User user, Customer customer) {
        startSubscription = LocalDate.now();
        visits = new ArrayList<>();
        payments = new ArrayList<>();
        isActive = true;
        inGym = false;
        this.type = SubscriptionType.valueOf(type.toUpperCase());

        switch (this.type) {
            case SINGLE:
                leftVisits = 1;
                endSubscription = startSubscription.plusWeeks(1);
                break;
            case UNLIMITED:
                leftVisits = -1;
                endSubscription = startSubscription.plusYears(1);
                break;
            case STANDART:
                leftVisits = 120;
                endSubscription = startSubscription.plusMonths(6);
                break;
        }

        this.customer = customer;

        Payment payment = new Payment(user.getEmployee(), this.type.getPrice());

        payments.add(payment);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate startSubscription;
    private LocalDate endSubscription;
    private int countVisit;
    private int leftVisits;
    private double discount;
    private boolean isActive;
    private boolean inGym;

    @OneToOne
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Visit> visits;

    @Enumerated(EnumType.STRING)
    private SubscriptionType type;

    @NotNull
    @OneToMany(cascade = CascadeType.ALL)
    private List<Payment> payments;

    public int getCurrentDiscount() {
        return (int) (discount * 100);
    }
}
