package com.solvd.model.deal;

import com.solvd.model.persons.customer.Customer;
import com.solvd.model.persons.employee.Employee;
import com.solvd.model.vehicle.Vehicle;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class RentalDeal {
    private Long id;
    private Customer customer;
    private final LocalDateTime startRental;
    private final LocalDateTime endRental;
    private final BigDecimal totalCost;
    private Employee employee;
    private final Status status;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
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

    public Status getStatus() {
        return status;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
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
}
