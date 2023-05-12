package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Car {
    private Long id;
    private String model;
    private String rental_date;
    private String receiving_date;
    private String client_name;


    public Car() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getRental_date() {
        return rental_date;
    }

    public void setRental_date(String rental_date) {
        this.rental_date = rental_date;
    }

    public String getReceiving_date() {
        return receiving_date;
    }

    public void setReceiving_date(String receiving_date) {
        this.receiving_date = receiving_date;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    @Override
    public String toString() {
        return "ca [id=" + id + ", model=" + model + ", rentalDate=" + rental_date  + ", receivingDate=" + receiving_date + ", clientName=" + client_name + "]";
    }
}
