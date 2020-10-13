package com.mspproject.backendmspapp.model;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name="students")
public class Student {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String name;
    private String surname;
    private int payment_rate;
    private boolean isActive;

    public Student() {
    }

    public Student(
            @JsonProperty("name") String name,
            @JsonProperty("surname") String surname,
            @JsonProperty("paymentRate") int paymentRate,
            @JsonProperty("isActive") boolean isActive) {

        this.name = name;
        this.surname = surname;
        this.payment_rate = paymentRate;
        this.isActive = isActive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getPayment_rate() {
        return payment_rate;
    }

    public void setPaymentRate(int payment_rate) {
        this.payment_rate = payment_rate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
