package edu.iu.c322.finalproject.feedbackservice.repository;

import edu.iu.c322.finalproject.feedbackservice.model.SellerItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerItemRepository extends JpaRepository<SellerItem, Integer> {
}
