package edu.iu.c322.finalproject.feedbackservice.model;

import jakarta.persistence.*;

@Entity
public class SellerItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int itemId;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;

    private String name;

    private int inventory;

    private float price;

    private String description;

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
