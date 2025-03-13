package com.cardsofooo.capstone_project_backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    private String description;
    private String imageUrl;
    private String cardSetName;
    private int cardCost;
    private int cardAttack;
    private int cardDefense;
    private String cardLandscape;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "deck_id")
    private Deck deck;

    @ManyToMany(mappedBy = "cards")
    @JsonBackReference
    private List<Deck> decks = new ArrayList<>();

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getImageUrl() {
//        return imageUrl;
//    }
//
//    public void setImageUrl(String imageUrl) {
//        this.imageUrl = imageUrl;
//    }
//
//    public String getCardSetName() {
//        return cardSetName;
//    }
//
//    public void setCardSetName(String cardSetName) {
//        this.cardSetName = cardSetName;
//    }
//
//    public int getCardCost() {
//        return cardCost;
//    }
//
//    public void setCardCost(int cardCost) {
//        this.cardCost = cardCost;
//    }
//
//    public int getCardAttack() {
//        return cardAttack;
//    }
//
//    public void setCardAttack(int cardAttack) {
//        this.cardAttack = cardAttack;
//    }
//
//    public int getCardDefense() {
//        return cardDefense;
//    }
//
//    public void setCardDefense(int cardDefense) {
//        this.cardDefense = cardDefense;
//    }
//
//    public String getCardLandscape() {
//        return cardLandscape;
//    }
//
//    public void setCardLandscape(String cardLandscape) {
//        this.cardLandscape = cardLandscape;
//    }
}


