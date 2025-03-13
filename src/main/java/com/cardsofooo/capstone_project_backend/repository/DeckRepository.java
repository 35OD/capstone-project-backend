package com.cardsofooo.capstone_project_backend.repository;


import com.cardsofooo.capstone_project_backend.model.Deck;
import com.cardsofooo.capstone_project_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeckRepository extends JpaRepository<Deck, Long> {
    List<Deck> findByUser_Id(Long userId);
    List<Deck> findByDeckNameContainingIgnoreCase(String deckName);
}
