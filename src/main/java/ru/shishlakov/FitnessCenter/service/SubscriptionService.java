package ru.shishlakov.FitnessCenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.shishlakov.FitnessCenter.model.Payment;
import ru.shishlakov.FitnessCenter.model.Subscription;
import ru.shishlakov.FitnessCenter.model.User;
import ru.shishlakov.FitnessCenter.model.Visit;
import ru.shishlakov.FitnessCenter.model.enums.SubscriptionType;
import ru.shishlakov.FitnessCenter.repository.SubscriptionRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionService {
    @Autowired
    public SubscriptionService(SubscriptionRepository repository) {
        this.repository = repository;
    }

    private SubscriptionRepository repository;

    public List<Subscription> findAllInGymIsTrue() {
        return repository.findAllByInGymIsTrue();
    }

    public Subscription findById(String id) {
        Optional<Subscription> result = repository.findById(Long.parseLong(id));

        return result.isPresent() ? result.get() : null;
    }

    public void startEndTraining(Subscription subscription) {
        if (checkEndSubscription(subscription)) {
            subscription.setActive(false);
            subscription.setLeftVisits(0);
        }

        if (subscription.isActive()) {
            if (subscription.isInGym()) {
                subscription.setInGym(false);
                subscription.getVisits()
                        .get(subscription.getCountVisit() - 1)
                        .setEndTraining(LocalDateTime.now());

                if (subscription.getLeftVisits() == 0) {
                    subscription.setActive(false);
                }

            } else {
                subscription.getVisits().add(new Visit());
                subscription.setInGym(true);
                subscription.setCountVisit(subscription.getVisits().size());

                if (!subscription.getType().equals(SubscriptionType.UNLIMITED)) {
                    subscription.setLeftVisits(subscription.getLeftVisits() - 1);
                }
            }
        }

        checkDiscount(subscription);

        repository.save(subscription);
    }

    private boolean checkEndSubscription(Subscription subscription) {
        int w = LocalDate.now().compareTo(subscription.getEndSubscription());
        return (w > 0);
    }

    public void checkDiscount(Subscription subscription) {
        if (subscription.getDiscount() == 0.2)
            return;

        int years = Period.between(subscription.getStartSubscription(), LocalDate.now()).getYears();

        if (years >= 5 && subscription.getCountVisit() >= 1000 && subscription.getDiscount() != 0.2) {
            subscription.setDiscount(0.2);
        } else if (years >= 3 && subscription.getCountVisit() >= 500 && subscription.getDiscount() != 0.1) {
            subscription.setDiscount(0.1);
        } else
            return;
    }


    public void prolongationSubscription(User user, Subscription subscription, String type) {
        int leftVisits = subscription.getLeftVisits() == -1 ? 0 : subscription.getLeftVisits();

        subscription.setActive(true);
        subscription.setType(SubscriptionType.valueOf(type.toUpperCase()));

        if (checkEndSubscription(subscription)) {
            switch (subscription.getType()) {
                case SINGLE:
                    subscription.setLeftVisits(leftVisits + 1);
                    subscription.setEndSubscription(LocalDate.now().plusWeeks(1));
                    break;
                case UNLIMITED:
                    subscription.setLeftVisits(-1);
                    subscription.setEndSubscription(LocalDate.now().plusYears(1));
                    break;
                case STANDART:
                    subscription.setLeftVisits(leftVisits + 120);
                    subscription.setEndSubscription(LocalDate.now().plusMonths(6));
                    break;
            }
        } else {
            switch (subscription.getType()) {
                case SINGLE:
                    subscription.setLeftVisits(leftVisits + 1);
                    subscription.setEndSubscription(LocalDate.now().plusWeeks(1));
                    break;
                case UNLIMITED:
                    subscription.setLeftVisits(-1);
                    subscription.setEndSubscription(subscription.getEndSubscription().plusYears(1));
                    break;
                case STANDART:
                    subscription.setLeftVisits(leftVisits + 120);
                    subscription.setEndSubscription(subscription.getEndSubscription().plusMonths(6));
                    break;
            }
        }

        Payment p = new Payment();
        p.setEmployee(user.getEmployee());

        double beforeDiscount = subscription.getType().getPrice();
        double discount = 1.0 - subscription.getDiscount();
        double price = beforeDiscount * discount;
        p.setPrice(price);

        subscription.getPayments().add(p);

        repository.save(subscription);
    }
}
