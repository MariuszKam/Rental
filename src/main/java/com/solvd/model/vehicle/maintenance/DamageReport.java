package com.solvd.model.vehicle.maintenance;

import com.solvd.model.deal.RentalDeal;
import com.solvd.model.vehicle.Vehicle;

import java.time.LocalDateTime;
import java.util.Objects;

public class DamageReport {
    private Long id;
    private final LocalDateTime reportDate;
    private final String description;
    private Vehicle vehicle;
    private RentalDeal rentalDeal;

    public DamageReport(Long id, LocalDateTime reportDate, String description, Vehicle vehicle, RentalDeal rentalDeal) {
        this.id = id;
        this.reportDate = reportDate;
        this.description = description;
        this.vehicle = vehicle;
        this.rentalDeal = rentalDeal;
    }

    public DamageReport() {
        this.reportDate = null;
        this.description = "";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getReportDate() {
        return reportDate;
    }

    public String getDescription() {
        return description;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
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
        DamageReport that = (DamageReport) o;
        return Objects.equals(id, that.id) && Objects.equals(reportDate, that.reportDate) && Objects.equals(description, that.description) && Objects.equals(vehicle, that.vehicle) && Objects.equals(rentalDeal, that.rentalDeal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, reportDate, description, vehicle, rentalDeal);
    }

    @Override
    public String toString() {
        return "DamageReport{" +
                "id=" + id +
                ", reportDate=" + reportDate +
                ", description='" + description + '\'' +
                ", vehicle=" + vehicle +
                ", rentalDeal=" + rentalDeal +
                '}';
    }
}
