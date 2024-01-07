package com.solvd.model.deal;

import com.solvd.model.persons.customer.Customer;
import com.solvd.model.persons.employee.Employee;
import com.solvd.model.vehicle.Vehicle;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class RentalDeal {
    private Long id;
    private Customer customer;
    private final LocalDateTime startRental;
    private final LocalDateTime endRental;
    private final BigDecimal totalCost;
    private Employee employee;
    private Status status;
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
}
