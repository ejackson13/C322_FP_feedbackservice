package edu.iu.c322.finalproject.feedbackservice.model;

import edu.iu.c322.finalproject.feedbackservice.repository.SellerRepository;

import java.util.ArrayList;
import java.util.List;

public class ConcreteSubject implements Subject{

    List<Observer> observers;

    public ConcreteSubject() {
        this.observers = new ArrayList<>();
    }

    public void registerObserver(Observer o) {
        this.observers.add(o);
    }

    public void removeObserver(Observer o) {
        this.observers.remove(o);
    }

    public void notifyObservers(SellerRepository sellerRepository, FeedbackSeller feedbackSeller, Feedback feedback) {
        for (Observer o : this.observers) {
            o.update(sellerRepository, feedbackSeller, feedback);
        }
    }
}
