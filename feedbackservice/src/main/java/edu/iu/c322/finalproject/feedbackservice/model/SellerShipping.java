package edu.iu.c322.finalproject.feedbackservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class SellerShipping {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int shippingId;

    private String street;

    private String city;

    private String zipCode;

    public int getShippingId() {
        return shippingId;
    }

    public void setShippingId(int shippingId) {
        this.shippingId = shippingId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

}
