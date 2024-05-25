package com.mindhub.homebanking.services.implementacion;
import com.mindhub.homebanking.dtos.AccountDTO;
import com.mindhub.homebanking.dtos.ClientDTO;
import com.mindhub.homebanking.models.CardColor;
import com.mindhub.homebanking.models.CardType;
import com.mindhub.homebanking.models.Client;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface ClientService {
    Set<ClientDTO> findAll();

    ClientDTO findById(long id);
    ClientDTO findCurrentClient(Authentication authentication);

    List<AccountDTO> findCurrentAccount(Authentication authentication);

    ResponseEntity<Object> crearAccount(Authentication authentication);
    ResponseEntity<Object> autoCrearAccount(String email);
    ResponseEntity<Object> crearCard(Authentication authentication, CardColor cardColor, CardType cardType);


}