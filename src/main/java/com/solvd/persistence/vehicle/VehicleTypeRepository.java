package com.solvd.persistence.vehicle;

import com.solvd.model.vehicle.VehicleType;
import com.solvd.persistence.utilities.Repositorable;

import java.util.List;
import java.util.Optional;

public interface VehicleTypeRepository extends Repositorable<VehicleType> {
    void create(VehicleType vehicleType);

    Optional<VehicleType> findById(Long id);

    Optional<VehicleType> findByVehicleId(Long id);

    List<VehicleType> loadAll();

    boolean existsById(Long id);

    void deleteByName(String name);

}
