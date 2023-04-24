package edu.iu.c322.finalproject.feedbackservice.repository;

import edu.iu.c322.finalproject.feedbackservice.model.FeedbackSeller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<FeedbackSeller, Integer> {

    @Query("SELECT DISTINCT e.feedbackSellerId FROM FeedbackSeller e")
    List<Integer> findAllIds();

}
