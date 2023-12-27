package com.solvd.model.deal;

import com.solvd.model.vehicle.Vehicle;

import java.util.Objects;

public class RentalDealHasVehicle {
    private Long id;
    private final RentalDeal rentalDeal;
    private final Vehicle vehicle;

    public RentalDealHasVehicle(Long id, RentalDeal rentalDeal, Vehicle vehicle) {
        this.id = id;
        this.rentalDeal = rentalDeal;
        this.vehicle = vehicle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RentalDeal getRentalDeal() {
        return rentalDeal;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RentalDealHasVehicle that = (RentalDealHasVehicle) o;
        return Objects.equals(id, that.id) && Objects.equals(rentalDeal, that.rentalDeal) && Objects.equals(vehicle, that.vehicle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rentalDeal, vehicle);
    }

    @Override
    public String toString() {
        return "RentalDealHasVehicle{" +
                "id=" + id +
                ", rentalDeal=" + rentalDeal +
                ", vehicle=" + vehicle +
                '}';
    }
}
