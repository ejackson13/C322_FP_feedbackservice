package edu.iu.c322.finalproject.feedbackservice.controller;

import edu.iu.c322.finalproject.feedbackservice.model.Feedback;
import edu.iu.c322.finalproject.feedbackservice.model.FeedbackSeller;
import edu.iu.c322.finalproject.feedbackservice.model.Seller;
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

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/{id}")
    public Feedback find(@PathVariable int id) {
        Feedback feedback = new Feedback();
        FeedbackSeller feedbackSeller = repository.findById(id).get();
        feedback.setFeedbackSellerId(feedbackSeller.getFeedbackSellerId());
        feedback.setRating(feedbackSeller.getSumOfSellerScores() / feedbackSeller.getNumOfSellerScores());
        return feedback;
    }

    @CrossOrigin(origins = "http://localhost:3000")
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

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/create")
    public void create(@RequestBody Feedback feedback) {
        System.out.println("the id is: " + feedback.getFeedbackSellerId());
        System.out.println("the rating is: " + feedback.getRating());
        Optional<FeedbackSeller> feedbackById = repository.findById(feedback.getFeedbackSellerId());
        if (feedbackById.isEmpty()) {
            FeedbackSeller feedbackSeller = new FeedbackSeller();
            feedbackSeller.setFeedbackSellerId(feedback.getFeedbackSellerId());
            feedbackSeller.setNumOfSellerScores(1);
            feedbackSeller.setSumOfSellerScores(feedback.getRating());
            System.out.println("feedbackSeller rating is :" + feedbackSeller.getSumOfSellerScores());
            repository.save(feedbackSeller);
            Seller seller = sellerRepository.findById(feedback.getFeedbackSellerId()).get();
            seller.setFeedbackSeller(feedbackSeller);
            sellerRepository.save(seller);
        }
        else {
            FeedbackSeller sellerById = repository.findById(feedback.getFeedbackSellerId()).get();
            sellerById.setNumOfSellerScores(sellerById.getNumOfSellerScores() + 1);
            sellerById.setSumOfSellerScores(sellerById.getSumOfSellerScores() + feedback.getRating());
            repository.save(sellerById);
            Seller seller = sellerRepository.findById(feedback.getFeedbackSellerId()).get();
            seller.setFeedbackSeller(sellerById);
            sellerRepository.save(seller);
        }
    }

}
