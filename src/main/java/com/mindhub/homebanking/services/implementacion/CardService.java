package com.mindhub.homebanking.services.implementacion;
import com.mindhub.homebanking.dtos.CardDTO;
import com.mindhub.homebanking.models.CardColor;
import com.mindhub.homebanking.models.CardType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import java.time.LocalDate;
import java.util.Set;

@Service
public interface CardService {
    Set<CardDTO> findAll();

    CardDTO findById(long id);

}
