package com.cardsofooo.capstone_project_backend.controller;

import com.cardsofooo.capstone_project_backend.model.Card;
import com.cardsofooo.capstone_project_backend.service.CardService;
import com.cardsofooo.capstone_project_backend.service.FileStorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/cards")
public class CardController {

    @Autowired
    private CardService cardService;
    @Autowired
    private FileStorageService fileStorageService;

    @GetMapping
    public List<Card> getAllCards() {
        List<Card> cards = cardService.getAllCards();
        log.info("retrieved {} cards from database", cards.size());
        cards.forEach(card -> log.debug("Card found: {}", card));
        return cards;
    }

    @GetMapping("/search")
    public List<Card> searchCards(@RequestParam String name){
        return cardService.searchCardsByName(name);
    }

    @PostMapping
    public Card createCard(@RequestBody Card card){
        return cardService.createCard(card);
    }

    @PostMapping("/upload")
  public Card createCardWithImage(@RequestParam("name") String name, @RequestParam("image")MultipartFile image){
        String fileName = fileStorageService.save(image);
        Card card = new Card();
        card.setName(name);
        card.setImageUrl("/uploads/" + fileName);
        return cardService.createCard(card);
    }

    @PutMapping("/{id}")
    public Card updateCard(@PathVariable Long id, @RequestBody Card card) {
        return cardService.updateCard(id, card);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCard(@PathVariable Long id){
        cardService.deleteCard(id);
        return ResponseEntity.ok().build();
    }


}
