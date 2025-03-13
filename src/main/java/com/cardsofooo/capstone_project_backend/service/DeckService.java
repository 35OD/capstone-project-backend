package com.cardsofooo.capstone_project_backend.service;

import com.cardsofooo.capstone_project_backend.model.Card;
import com.cardsofooo.capstone_project_backend.model.Deck;
import com.cardsofooo.capstone_project_backend.model.User;
import com.cardsofooo.capstone_project_backend.repository.CardRepository;
import com.cardsofooo.capstone_project_backend.repository.DeckRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class DeckService {
    @Autowired
    private DeckRepository deckRepository;
    @Autowired
    private CardRepository cardRepository;

    public Deck createDeck(Deck deck) {
        log.info("Creating deck: {}", deck);

        // Fetch complete card objects from database
        if (deck.getCards() != null && !deck.getCards().isEmpty()) {
            List<Card> completeCards = new ArrayList<>();
            for (Card card : deck.getCards()) {
                Card completeCard = cardRepository.findById(card.getId())
                        .orElseThrow(() -> new RuntimeException("Card not found with id: " + card.getId()));
                completeCards.add(completeCard);
            }
            deck.setCards(completeCards);
        }

        Deck savedDeck = deckRepository.save(deck);

        // Establish bidirectional relationship
        if (deck.getCards() != null) {
            deck.getCards().forEach(card -> card.setDeck(savedDeck));
            cardRepository.saveAll(deck.getCards());
        }


        return deckRepository.findById(savedDeck.getId())
                .orElseThrow(() -> new RuntimeException("Failed to retrieve saved deck"));
    }
  public List<Deck> getAllDecks(){
        return deckRepository.findAll();
  }

    public List<Deck> getDecksByName(String deckName) {
        return deckRepository.findByDeckNameContainingIgnoreCase(deckName);
    }

    public Deck updateDeck(Long id, Deck deck){
        deck.setId(id);
        return deckRepository.save(deck);
    }

    public void deleteDeck(Long id){
        deckRepository.deleteById(id);
    }

    public Deck addCardToDeck(Long deckId, List<Card> cards) {
        List<Card> savedCards = cardRepository.saveAll(cards);
        Deck deck = deckRepository.findById(deckId).orElseThrow();
        deck.getCards().addAll(savedCards);
        return deckRepository.save(deck);
    }


    public Deck removeCardFromDeck(Long deckId, Card card) {
        Deck deck = deckRepository.findById(deckId)
                .orElseThrow(() -> new RuntimeException("Deck not found"));
        deck.getCards().remove(card);
        return deckRepository.save(deck);
    }


    public List<Deck> getDecksByUserId(Long userId) {
        return deckRepository.findByUser_Id(userId);
    }
}
