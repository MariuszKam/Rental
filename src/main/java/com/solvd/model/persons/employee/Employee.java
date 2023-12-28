package com.solvd.model.persons.employee;

import com.solvd.model.persons.Person;

import java.util.Objects;

public class Employee extends Person {
    private String position;
    private Contract contract;

    public Employee(Long id, String firstName, String lastName, String position, Contract contract) {
        super(id, firstName, lastName);
        this.position = position;
        this.contract = contract;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Employee employee = (Employee) o;
        return Objects.equals(position, employee.position) && Objects.equals(contract, employee.contract);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), position, contract);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                " position='" + position + '\'' +
                ", contract=" + contract +
                '}';
    }
}
