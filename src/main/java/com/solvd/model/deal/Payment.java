package com.solvd.model.deal;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Payment {
    private Long id;
    private final BigDecimal amount;
    private final LocalDateTime paymentDate;
    private RentalDeal rentalDeal;

    public Payment(Long id, BigDecimal amount, LocalDateTime paymentDate, RentalDeal rentalDeal) {
        this.id = id;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.rentalDeal = rentalDeal;
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
        return Objects.equals(id, payment.id) && Objects.equals(amount, payment.amount) && Objects.equals(paymentDate, payment.paymentDate) && Objects.equals(rentalDeal, payment.rentalDeal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, paymentDate, rentalDeal);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", amount=" + amount +
                ", paymentDate=" + paymentDate +
                ", rentalDeal=" + rentalDeal +
                '}';
    }
}
