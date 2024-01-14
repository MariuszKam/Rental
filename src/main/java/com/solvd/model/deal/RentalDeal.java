package com.solvd.model.deal;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.solvd.jackson.LocalDateTimeDeserializer;
import com.solvd.jackson.LocalDateTimeSerializer;
import com.solvd.jaxb.LocalDateTimeAdapter;
import com.solvd.model.persons.customer.Customer;
import com.solvd.model.persons.employee.Employee;
import com.solvd.model.vehicle.Vehicle;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@XmlRootElement(name = "RentalDeal")
@XmlAccessorType(XmlAccessType.FIELD)
public class RentalDeal {

    @XmlElement
    private Long id;
    private Customer customer;
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private final LocalDateTime startRental;
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private final LocalDateTime endRental;
    private final BigDecimal totalCost;
    private Employee employee;
    private Status status;
    @XmlElementWrapper(name = "vehicles")
    @XmlElement(name = "vehicle")
    private List<Vehicle> vehicles;

    public RentalDeal(Long id, Customer customer, LocalDateTime startRental, LocalDateTime endRental, BigDecimal totalCost, Employee employee, Status status, List<Vehicle> vehicles) {
        this.id = id;
        this.customer = customer;
        this.startRental = startRental;
        this.endRental = endRental;
        this.totalCost = totalCost;
        this.employee = employee;
        this.status = status;
        this.vehicles = vehicles;
    }

    public RentalDeal() {
        this.startRental = null;
        this.endRental = null;
        this.totalCost = null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDateTime getStartRental() {
        return startRental;
    }

    public LocalDateTime getEndRental() {
        return endRental;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RentalDeal that = (RentalDeal) o;
        return Objects.equals(id, that.id) && Objects.equals(customer, that.customer) && Objects.equals(startRental, that.startRental) && Objects.equals(endRental, that.endRental) && Objects.equals(totalCost, that.totalCost) && Objects.equals(employee, that.employee) && Objects.equals(status, that.status) && Objects.equals(vehicles, that.vehicles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customer, startRental, endRental, totalCost, employee, status, vehicles);
    }

    @Override
    public String toString() {
        return "RentalDeal{" +
                "id=" + id +
                ", customer=" + customer +
                ", startRental=" + startRental +
                ", endRental=" + endRental +
                ", totalCost=" + totalCost +
                ", employee=" + employee +
                ", status=" + status +
                ", vehicles=" + vehicles +
                '}';
    }

    //Bulider desing
    private RentalDeal(Builder builder) {
        this.id = builder.id;
        this.customer = builder.customer;
        this.startRental = builder.startRental;
        this.endRental = builder.endRental;
        this.totalCost = builder.totalCost;
        this.employee = builder.employee;
        this.status = builder.status;
        this.vehicles = builder.vehicles;
    }

    public static class Builder {
        private Long id;
        private Customer customer;
        private LocalDateTime startRental;
        private LocalDateTime endRental;
        private BigDecimal totalCost;
        private Employee employee;
        private Status status;
        private List<Vehicle> vehicles;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder customer(Customer customer) {
            this.customer = customer;
            return this;
        }

        public Builder startRental(LocalDateTime startRental) {
            this.startRental = startRental;
            return this;
        }

        public Builder endRental(LocalDateTime endRental) {
            this.endRental = endRental;
            return this;
        }

        public Builder totalCost(BigDecimal totalCost) {
            this.totalCost = totalCost;
            return this;
        }

        public Builder employee(Employee employee) {
            this.employee = employee;
            return this;
        }

        public Builder status(Status status) {
            this.status = status;
            return this;
        }

        public Builder vehicles(List<Vehicle> vehicles) {
            this.vehicles = vehicles;
            return this;
        }

        public RentalDeal build() {
            return new RentalDeal(this);
        }
    }
}
