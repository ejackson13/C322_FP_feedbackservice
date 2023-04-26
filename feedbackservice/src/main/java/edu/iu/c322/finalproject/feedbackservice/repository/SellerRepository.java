package edu.iu.c322.finalproject.feedbackservice.repository;

import edu.iu.c322.finalproject.feedbackservice.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Integer> {
}
