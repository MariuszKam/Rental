package com.solvd.model.deal;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Payment {
    private Long id;
    private final BigDecimal amount;
    private final LocalDateTime paymentDate;
    private final String paymentMethod;
    private RentalDeal rentalDeal;

    public Payment(Long id, BigDecimal amount, LocalDateTime paymentDate, String paymentMethod, RentalDeal rentalDeal) {
        this.id = id;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.rentalDeal = rentalDeal;
    }

    public Payment() {
        this.amount = null;
        this.paymentDate = null;
        this.paymentMethod = null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public RentalDeal getRentalDeal() {
        return rentalDeal;
    }

    public void setRentalDeal(RentalDeal rentalDeal) {
        this.rentalDeal = rentalDeal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(id, payment.id) && Objects.equals(amount, payment.amount) && Objects.equals(paymentDate, payment.paymentDate) && Objects.equals(paymentMethod, payment.paymentMethod) && Objects.equals(rentalDeal, payment.rentalDeal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, paymentDate, paymentMethod, rentalDeal);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", amount=" + amount +
                ", paymentDate=" + paymentDate +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", rentalDeal=" + rentalDeal +
                '}';
    }
}
