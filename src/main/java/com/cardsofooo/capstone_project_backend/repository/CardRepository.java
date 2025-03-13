package com.cardsofooo.capstone_project_backend.repository;

import com.cardsofooo.capstone_project_backend.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findByNameContainingIgnoreCase(String name);

    List<Card> findAllByOrderByNameAsc();
}
