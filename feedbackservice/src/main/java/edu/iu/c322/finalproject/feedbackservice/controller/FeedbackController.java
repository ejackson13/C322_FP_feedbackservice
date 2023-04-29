package edu.iu.c322.finalproject.feedbackservice.controller;

import edu.iu.c322.finalproject.feedbackservice.model.*;
import edu.iu.c322.finalproject.feedbackservice.repository.FeedbackRepository;
import edu.iu.c322.finalproject.feedbackservice.repository.SellerRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    private FeedbackRepository repository;

    private SellerRepository sellerRepository;

    public FeedbackController(FeedbackRepository repository, SellerRepository sellerRepository) {
        this.repository = repository;
        this.sellerRepository = sellerRepository;
    }

    @CrossOrigin()
    @GetMapping("/{id}")
    public Feedback find(@PathVariable int id) {
        Feedback feedback = new Feedback();
        FeedbackSeller feedbackSeller = repository.findById(id).get();
        feedback.setFeedbackSellerId(feedbackSeller.getFeedbackSellerId());
        feedback.setRating(feedbackSeller.getSumOfSellerScores() / feedbackSeller.getNumOfSellerScores());
        return feedback;
    }

    @CrossOrigin()
    @GetMapping
    public List<Feedback> getAll() {
        List<Feedback> allFeedback = new ArrayList<>();
        List<Integer> ids = repository.findAllIds();
        for (Integer id : ids) {
            Feedback feedback = new Feedback();
            FeedbackSeller feedbackSeller = repository.findById(id).get();
            feedback.setFeedbackSellerId(feedbackSeller.getFeedbackSellerId());
            feedback.setRating(feedbackSeller.getSumOfSellerScores() / feedbackSeller.getNumOfSellerScores());
            allFeedback.add(feedback);
        }
        return allFeedback;
    }

    @CrossOrigin()
    @PostMapping("/create")
    public void create(@RequestBody Feedback feedback) {
        ConcreteObserver observer = new ConcreteObserver();
        ConcreteSubject subject = new ConcreteSubject();
        subject.registerObserver(observer);
        Optional<FeedbackSeller> feedbackById = repository.findById(feedback.getFeedbackSellerId());
        if (feedbackById.isEmpty()) {
            FeedbackSeller feedbackSeller = new FeedbackSeller();
            feedbackSeller.setFeedbackSellerId(feedback.getFeedbackSellerId());
            feedbackSeller.setNumOfSellerScores(1);
            feedbackSeller.setSumOfSellerScores(feedback.getRating());
            System.out.println("feedbackSeller rating is :" + feedbackSeller.getSumOfSellerScores());
            repository.save(feedbackSeller);
            subject.notifyObservers(sellerRepository, feedbackSeller, feedback);
            /*
            Seller seller = sellerRepository.findById(feedback.getFeedbackSellerId()).get();
            seller.setFeedbackSeller(feedbackSeller);
            sellerRepository.save(seller);
            */
        }
        else {
            FeedbackSeller sellerById = repository.findById(feedback.getFeedbackSellerId()).get();
            sellerById.setNumOfSellerScores(sellerById.getNumOfSellerScores() + 1);
            sellerById.setSumOfSellerScores(sellerById.getSumOfSellerScores() + feedback.getRating());
            repository.save(sellerById);
            /*
            Seller seller = sellerRepository.findById(feedback.getFeedbackSellerId()).get();
            seller.setFeedbackSeller(sellerById);
            sellerRepository.save(seller);
            */
            subject.notifyObservers(sellerRepository, sellerById, feedback);
        }
    }

}
