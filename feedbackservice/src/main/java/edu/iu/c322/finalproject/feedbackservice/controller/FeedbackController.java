package edu.iu.c322.finalproject.feedbackservice.controller;

import edu.iu.c322.finalproject.feedbackservice.model.Feedback;
import edu.iu.c322.finalproject.feedbackservice.model.FeedbackSeller;
import edu.iu.c322.finalproject.feedbackservice.repository.FeedbackRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    private FeedbackRepository repository;

    public FeedbackController(FeedbackRepository repository) {
        this.repository = repository;
    }

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

    @PostMapping("/create")
    public void create(@RequestBody Feedback feedback) {
        Optional<FeedbackSeller> feedbackById = repository.findById(feedback.getFeedbackSellerId());
        if (feedbackById.isEmpty()) {
            FeedbackSeller feedbackSeller = new FeedbackSeller();
            feedbackSeller.setFeedbackSellerId(feedback.getFeedbackSellerId());
            feedbackSeller.setNumOfSellerScores(1);
            feedbackSeller.setSumOfSellerScores(feedback.getRating());
            repository.save(feedbackSeller);
        }
        else {
            FeedbackSeller sellerById = repository.findById(feedback.getFeedbackSellerId()).get();
            sellerById.setNumOfSellerScores(sellerById.getNumOfSellerScores() + 1);
            sellerById.setSumOfSellerScores(sellerById.getSumOfSellerScores() + feedback.getRating());
            repository.save(sellerById);
        }
    }

}
