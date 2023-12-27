package com.solvd.model.vehicle.maintenance;

import com.solvd.model.vehicle.Vehicle;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Maintenance {
    private Long id;
    private final Vehicle vehicle;
    private final LocalDateTime date;
    private final String description;
    private final BigDecimal cost;

    public Maintenance(Long id, Vehicle vehicle, LocalDateTime date, String description, BigDecimal cost) {
        this.id = id;
        this.vehicle = vehicle;
        this.date = date;
        this.description = description;
        this.cost = cost;
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

    public LocalDateTime getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getCost() {
        return cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Maintenance that = (Maintenance) o;
        return Objects.equals(id, that.id) && Objects.equals(vehicle, that.vehicle) && Objects.equals(date, that.date) && Objects.equals(description, that.description) && Objects.equals(cost, that.cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, vehicle, date, description, cost);
    }

    @Override
    public String toString() {
        return "Maintenance{" +
                "id=" + id +
                ", vehicle=" + vehicle +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", cost=" + cost +
                '}';
    }
}
