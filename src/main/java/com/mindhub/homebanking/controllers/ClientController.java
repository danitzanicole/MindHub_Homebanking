package com.mindhub.homebanking.controllers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mindhub.homebanking.dtos.ClientDTO;
import com.mindhub.homebanking.dtos.AccountDTO;
import com.mindhub.homebanking.models.CardColor;
import com.mindhub.homebanking.models.CardType;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.services.implementacion.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;}

    @RequestMapping("/clients")
    @JsonIgnore
    public Set<ClientDTO> getClients() {
        return clientService.findAll();}

    @RequestMapping("/clients/{id}")
    public ClientDTO getClients(@PathVariable long id) {
        return clientService.findById(id);}

    @RequestMapping("clients/current")
    public ClientDTO getCurrentClient() {
        return clientService.findCurrentClient(SecurityContextHolder.getContext().getAuthentication());}

    @GetMapping("clients/current/accounts")
    public List<AccountDTO> getCurrentClients() {
        return clientService.findCurrentAccount(SecurityContextHolder.getContext().getAuthentication());}

    @PostMapping("clients/current/accounts")
    public ResponseEntity<Object> crearAccount(){
        return clientService.crearAccount(SecurityContextHolder.getContext().getAuthentication());}

    @PostMapping("clients/current/cards")
    public ResponseEntity<Object> crearCard(@RequestParam CardColor cardColor, @RequestParam CardType cardType){
        return clientService.crearCard(SecurityContextHolder.getContext().getAuthentication(), cardColor, cardType);
    }

}
