package com.solvd.model.vehicle;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class VehicleType {
    @XmlElement
    private Long id;
    @XmlElement
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
