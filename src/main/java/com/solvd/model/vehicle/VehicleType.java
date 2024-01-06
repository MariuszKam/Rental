package com.solvd.model.vehicle;

import java.util.Objects;

public class VehicleType {
    private Long id;
    private final String typeName;

    public VehicleType(Long id, String typeName) {
        this.id = id;
        this.typeName = typeName;
    }

    public VehicleType() {
        this.typeName = "";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VehicleType that = (VehicleType) o;
        return Objects.equals(id, that.id) && Objects.equals(typeName, that.typeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, typeName);
    }

    @Override
    public String toString() {
        return "Vehicle_Type{" +
                "id=" + id +
                ", typeName='" + typeName + '\'' +
                '}';
    }
}
