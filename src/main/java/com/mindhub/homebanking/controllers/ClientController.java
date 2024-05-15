package com.mindhub.homebanking.controllers;
import com.mindhub.homebanking.dtos.ClientDTO;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.services.implementacion.ClientService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class ClientController {

    @Autowired
    private ClientService clientService;
    private ClientRepository clientRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ClientController(ClientService clientService, ClientRepository clientRepository, PasswordEncoder passwordEncoder) {
        this.clientService = clientService;
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;}

    @GetMapping("/clients")
    public Set<ClientDTO> getClients() {
        return clientService.findAll();}

    @GetMapping("/clients/{id}")
    public ClientDTO getClients(@PathVariable long id) {
        return clientService.findById(id);}

    @GetMapping("/clients/{current}")
    public Authentication getClients(ClientDTO clientDTO) {
        return Authentication.valueOf(clientDTO.getEmail());}

    @RequestMapping(path = "/clients", method = RequestMethod.POST)
    public ResponseEntity<Object> register(
            @RequestParam String firstName, @RequestParam String lastName,
            @RequestParam String email, @RequestParam String password) {
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            return new ResponseEntity<>("Faltan datos para la creacion de un Cliente", HttpStatus.FORBIDDEN);}

        if (clientRepository.findByEmail(email) !=  null) {
            return new ResponseEntity<>("Email ya se encuentra registrado", HttpStatus.FORBIDDEN);}

        clientRepository.save(new Client(firstName, lastName, email, passwordEncoder.encode(password)));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
