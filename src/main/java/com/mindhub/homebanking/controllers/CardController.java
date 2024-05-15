package com.mindhub.homebanking.controllers;
import com.mindhub.homebanking.dtos.CardDTO;
import com.mindhub.homebanking.models.CardColor;
import com.mindhub.homebanking.models.CardType;
import com.mindhub.homebanking.services.implementacion.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class CardController {

    @Autowired
    private CardService cardService;

    @Autowired
    private CardController(CardService cardService) {this.cardService = cardService;};

    @GetMapping("/cards")
    public Set<CardDTO> getCards() {
        return this.cardService.findAll();}

    @GetMapping("/cards/{id}")
    public CardDTO getCards(@PathVariable long id) {
        return cardService.findById(id);}

}
