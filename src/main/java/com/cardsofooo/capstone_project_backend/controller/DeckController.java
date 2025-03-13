package com.cardsofooo.capstone_project_backend.controller;

import com.cardsofooo.capstone_project_backend.model.Card;
import com.cardsofooo.capstone_project_backend.model.Deck;
import com.cardsofooo.capstone_project_backend.model.User;
import com.cardsofooo.capstone_project_backend.service.DeckService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/decks")
@CrossOrigin(origins = "http://localhost:4200")
public class DeckController {
    @Autowired
    private DeckService deckService;

    @PostMapping
    public Deck createDeck(@RequestBody Map<String, Object> requestData) {
        System.out.println("Raw request data: " + requestData);

        Deck deck = new Deck();
        deck.setDeckName((String) requestData.get("deckName"));

        Map<String, Object> userData = (Map<String, Object>) requestData.get("user");
        User user = new User();
        user.setId(Long.valueOf(userData.get("id").toString()));
        deck.setUser(user);

        List<Map<String, Object>> cardsData = (List<Map<String, Object>>) requestData.get("cards");
        List<Card> cards = new ArrayList<>();
        if (cardsData != null) {
            for (Map<String, Object> cardData : cardsData) {
                Card card = new Card();
                card.setId(Long.valueOf(cardData.get("id").toString()));
                cards.add(card);
            }
            deck.setCards(cards);
        }

        return deckService.createDeck(deck);
    }
    @GetMapping
    public List<Deck> getAllDecks() {
        return deckService.getAllDecks();
    }

    @GetMapping("/search")
    public List<Deck> searchDecksByName(@RequestParam String name) {
        return deckService.getDecksByName(name);
    }

    @PutMapping("/{id}")
    public Deck updateDeck(@PathVariable Long id, @RequestBody Deck deck) {
        return deckService.updateDeck(id, deck);
    }

    @DeleteMapping("/{id}")
    public void deleteDeck(@PathVariable Long id) {
        deckService.deleteDeck(id);
    }

    @PostMapping("/{deckId}/cards")
    @PreAuthorize("isAuthenticated()")  // or specific role like "hasRole('USER')"
    public Deck addCardToDeck(@PathVariable Long deckId, @RequestBody List<Card> cards) {
        return deckService.addCardToDeck(deckId, cards);
    }

    @DeleteMapping("/{deckId}/cards")
    public Deck removeCardFromDeck(@PathVariable Long deckId, @RequestBody Card card) {
        return deckService.removeCardFromDeck(deckId, card);
    }

    @GetMapping("/user/{userId}")
    public List<Deck> getUserDecks(@PathVariable Long userId) {
        return deckService.getDecksByUserId(userId);
    }
}