package com.solvd.persistence.vehicle;

import com.solvd.model.vehicle.Vehicle;
import com.solvd.persistence.utilities.Repositorable;

import java.util.List;
import java.util.Optional;

public interface VehicleRepository extends Repositorable<Vehicle> {
    void create(Vehicle vehicle);

    Optional<Vehicle> findById(Long id);

    Optional<Vehicle> findByRelatedTableId(String table, Long id);

    List<Vehicle> loadAll();

    List<Vehicle> findAllByRentalDealId(Long id);

    void updateAvailableById(Long id, boolean available);

}
