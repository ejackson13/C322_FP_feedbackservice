package edu.iu.c322.finalproject.feedbackservice.model;

public class Feedback {

    private int feedbackSellerId;

    private float rating;

    public int getFeedbackSellerId() {
        return feedbackSellerId;
    }

    public void setFeedbackSellerId(int feedbackSellerId) {
        this.feedbackSellerId = feedbackSellerId;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
