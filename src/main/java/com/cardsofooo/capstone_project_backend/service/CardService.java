package com.cardsofooo.capstone_project_backend.service;

import com.cardsofooo.capstone_project_backend.model.Card;
import com.cardsofooo.capstone_project_backend.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    public List<Card> getAllCards() {
        return cardRepository.findAllByOrderByNameAsc();
    }
    public List<Card> searchCardsByName(String name){
        return cardRepository.findByNameContainingIgnoreCase(name);
    }

    public Card createCard(Card card){
        return cardRepository.save(card);
    }

    public Card updateCard(Long id, Card card) {
        card.setId(id);
        return cardRepository.save(card);
    }

    public void deleteCard(Long id){
        cardRepository.deleteById(id);
    }

}
