package com.solvd.model.persons.customer;

import com.solvd.model.persons.Person;

import java.util.Objects;

public class Customer extends Person {
    private String phoneNumber;
    private String email;
    private String address;

    public Customer(long id, String firstName, String lastName, String phoneNumber, String email, String address) {
        super(id, firstName, lastName);
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(phoneNumber, customer.phoneNumber) && Objects.equals(email, customer.email) && Objects.equals(address, customer.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), phoneNumber, email, address);
    }
}
