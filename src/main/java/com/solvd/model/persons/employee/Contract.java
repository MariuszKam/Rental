package com.solvd.model.persons.employee;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.solvd.jackson.LocalDateTimeDeserializer;
import com.solvd.jackson.LocalDateTimeSerializer;
import com.solvd.jaxb.LocalDateTimeAdapter;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Contract {
    @XmlElement
    private Long id;
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime startContract;
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime endContract;
    @XmlElement
    private BigDecimal salary;

    public Contract(Long id, LocalDateTime startContract, LocalDateTime endContract, BigDecimal salary) {
        this.id = id;
        this.startContract = startContract;
        this.endContract = endContract;
        this.salary = salary;
    }

    public Contract() {
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartContract() {
        return startContract;
    }

    public void setStartContract(LocalDateTime startContract) {
        this.startContract = startContract;
    }

    public LocalDateTime getEndContract() {
        return endContract;
    }

    public void setEndContract(LocalDateTime endContract) {
        this.endContract = endContract;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contract contract = (Contract) o;
        return Objects.equals(id, contract.id) && Objects.equals(startContract, contract.startContract) && Objects.equals(endContract, contract.endContract) && Objects.equals(salary, contract.salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startContract, endContract, salary);
    }

    @Override
    public String toString() {
        return "Contract{" +
                "id=" + id +
                ", startContract=" + startContract +
                ", endContract=" + endContract +
                ", salary=" + salary +
                '}';
    }
}
