package edu.iu.c322.finalproject.feedbackservice.model;

import edu.iu.c322.finalproject.feedbackservice.repository.SellerRepository;

public class ConcreteObserver implements Observer {

    public void update(SellerRepository sellerRepository, FeedbackSeller feedbackSeller, Feedback feedback) {
        Seller seller = sellerRepository.findById(feedback.getFeedbackSellerId()).get();
        seller.setFeedbackSeller(feedbackSeller);
        sellerRepository.save(seller);
    }
}
