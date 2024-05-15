package com.mindhub.homebanking.services;
import com.mindhub.homebanking.dtos.CardDTO;
import com.mindhub.homebanking.models.CardColor;
import com.mindhub.homebanking.models.CardType;
import com.mindhub.homebanking.repositories.CardRepository;
import com.mindhub.homebanking.services.implementacion.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CardServiceImp implements CardService {

    @Autowired
    private CardRepository cardRepository;

    public CardServiceImp(CardRepository cardRepository){
        this.cardRepository = cardRepository;}

    @Override
    public Set<CardDTO> findAll() {
        return this.cardRepository.findAll().stream().map(CardDTO::new).collect(Collectors.toSet());}

    @Override
    public CardDTO findById(@PathVariable long id) {
        return this.cardRepository.findById(id).map(CardDTO::new).orElse(null);}


}
