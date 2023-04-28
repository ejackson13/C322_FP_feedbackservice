package edu.iu.c322.finalproject.feedbackservice.model;

import jakarta.persistence.*;

@Entity
public class Seller {

    @Id
    private int sellerId;

    private String venmoId;

    @OneToOne
    @JoinColumn(name = "sellerShipping_id")
    private SellerShipping sellerShipping;

    private String sellerName;

    private String sellerEmail;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "feedbackSeller_id")
    private FeedbackSeller feedbackSeller;

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public SellerShipping getSellerShipping() {
        return sellerShipping;
    }

    public void setSellerShipping(SellerShipping sellerShipping) {
        this.sellerShipping = sellerShipping;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSellerEmail() {
        return sellerEmail;
    }

    public void setSellerEmail(String sellerEmail) {
        this.sellerEmail = sellerEmail;
    }

    public String getVenmoId() {
        return venmoId;
    }

    public void setVenmoId(String venmoId) {
        this.venmoId = venmoId;
    }

    public FeedbackSeller getFeedbackSeller() {
        return feedbackSeller;
    }

    public void setFeedbackSeller(FeedbackSeller feedbackSeller) {
        this.feedbackSeller = feedbackSeller;
    }

}
