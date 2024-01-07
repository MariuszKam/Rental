package com.solvd.persistence.vehicle;

import com.solvd.model.vehicle.Vehicle;
import com.solvd.persistence.utilities.Repositorable;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

public interface VehicleRepository extends Repositorable<Vehicle> {
    void create(Vehicle vehicle);

    Optional<Vehicle> findById(Long id);

    Optional<Vehicle> findByRelatedTableId(@Param("table") String table, @Param("id") Long id);

    List<Vehicle> loadAll();

    List<Vehicle> findAllByRentalDealId(Long id);

    void updateAvailableById(@Param("id") Long id, @Param("available") boolean available);

}
