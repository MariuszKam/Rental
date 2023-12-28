package com.solvd.model.vehicle.maintenance;

import com.solvd.model.vehicle.Vehicle;

import java.math.BigDecimal;
import java.util.Objects;

public class Insurance {
    private Long id;
    private Vehicle vehicle;
    private final Integer policyNumber;
    private final BigDecimal cost;
    private InsuranceCompany insuranceCompany;

    public Insurance(Long id, Vehicle vehicle, Integer policyNumber, BigDecimal cost, InsuranceCompany insuranceCompany) {
        this.id = id;
        this.vehicle = vehicle;
        this.policyNumber = policyNumber;
        this.cost = cost;
        this.insuranceCompany = insuranceCompany;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Integer getPolicyNumber() {
        return policyNumber;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public InsuranceCompany getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(InsuranceCompany insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Insurance insurance = (Insurance) o;
        return Objects.equals(id, insurance.id) && Objects.equals(vehicle, insurance.vehicle) && Objects.equals(policyNumber, insurance.policyNumber) && Objects.equals(cost, insurance.cost) && Objects.equals(insuranceCompany, insurance.insuranceCompany);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, vehicle, policyNumber, cost, insuranceCompany);
    }

    @Override
    public String toString() {
        return "Insurance{" +
                "id=" + id +
                ", vehicle=" + vehicle +
                ", policyNumber=" + policyNumber +
                ", cost=" + cost +
                ", insuranceCompany=" + insuranceCompany +
                '}';
    }
}
