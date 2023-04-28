package edu.iu.c322.finalproject.feedbackservice.model;

import edu.iu.c322.finalproject.feedbackservice.repository.SellerRepository;

public interface Subject {

    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers(SellerRepository sellerRepository, FeedbackSeller feedbackSeller, Feedback feedback);

}
