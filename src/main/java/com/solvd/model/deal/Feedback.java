package com.solvd.model.deal;

import com.solvd.model.persons.customer.Customer;

import java.util.Objects;

public class Feedback {
    private Long id;
    private final int rate;
    private final String description;
    private RentalDeal rentalDeal;
    private Customer customer;

    public Feedback(Long id, int rate, String description, RentalDeal rentalDeal, Customer customer) {
        this.id = id;
        this.rate = rate;
        this.description = description;
        this.rentalDeal = rentalDeal;
        this.customer = customer;
    }

    public Feedback() {
        this.rate = 0;
        this.description = "";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRate() {
        return rate;
    }

    public String getDescription() {
        return description;
    }

    public RentalDeal getRentalDeal() {
        return rentalDeal;
    }

    public void setRentalDeal(RentalDeal rentalDeal) {
        this.rentalDeal = rentalDeal;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feedback feedback = (Feedback) o;
        return rate == feedback.rate && Objects.equals(id, feedback.id) && Objects.equals(description, feedback.description) && Objects.equals(rentalDeal, feedback.rentalDeal) && Objects.equals(customer, feedback.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rate, description, rentalDeal, customer);
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "id=" + id +
                ", rate=" + rate +
                ", description='" + description + '\'' +
                ", rentalDeal=" + rentalDeal +
                ", customer=" + customer +
                '}';
    }
}
