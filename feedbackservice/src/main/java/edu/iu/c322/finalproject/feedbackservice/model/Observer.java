package edu.iu.c322.finalproject.feedbackservice.model;

import edu.iu.c322.finalproject.feedbackservice.repository.SellerRepository;

public interface Observer {

    void update(SellerRepository sellerRepository, FeedbackSeller feedbackSeller, Feedback feedback);

}
