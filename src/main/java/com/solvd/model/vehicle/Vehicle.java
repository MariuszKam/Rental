package com.solvd.model.vehicle;

import java.util.Objects;

public class Vehicle {
    private Long id;
    private VehicleType vehicleType;
    private final String model;
    private final String registrationNumber;
    private Long currentKilometers;
    private boolean available;

    public Vehicle(Long id, VehicleType vehicleType, String model, String registrationNumber, Long currentKilometers, boolean available) {
        this.id = id;
        this.vehicleType = vehicleType;
        this.model = model;
        this.registrationNumber = registrationNumber;
        this.currentKilometers = currentKilometers;
        this.available = available;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getModel() {
        return model;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public Long getCurrentKilometers() {
        return currentKilometers;
    }

    public void setCurrentKilometers(Long currentKilometers) {
        this.currentKilometers = currentKilometers;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return available == vehicle.available && Objects.equals(id, vehicle.id) && Objects.equals(vehicleType, vehicle.vehicleType) && Objects.equals(model, vehicle.model) && Objects.equals(registrationNumber, vehicle.registrationNumber) && Objects.equals(currentKilometers, vehicle.currentKilometers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, vehicleType, model, registrationNumber, currentKilometers, available);
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", vehicleType=" + vehicleType +
                ", model='" + model + '\'' +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", currentKilometers=" + currentKilometers +
                ", status=" + available +
                '}';
    }
}
