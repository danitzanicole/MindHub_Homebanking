package com.mindhub.homebanking.controllers;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.services.implementacion.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AppController {

    private final PasswordEncoder passwordEncoder;
    private final ClientRepository clientRepository;
    private final ClientService clientService;

    public AppController (PasswordEncoder passwordEncoder, ClientRepository clientRepository,
                          ClientService clientService){
        this.passwordEncoder = passwordEncoder;
        this.clientRepository = clientRepository;
        this.clientService = clientService; }

    @PostMapping(path = "/clients")
    public ResponseEntity<Object> register(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String email,
            @RequestParam String password){
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty() ){
            return new ResponseEntity<>("Faltan datos.", HttpStatus.FORBIDDEN);}

        if (clientRepository.findByEmail(email).isPresent()) {
            return new ResponseEntity<>("Nombre ya esta en uso.", HttpStatus.FORBIDDEN);}

        clientRepository.save(new Client(firstName, lastName, email, passwordEncoder.encode(password)));
        clientService.autoCrearAccount(email);
        return  new ResponseEntity<>(HttpStatus.CREATED);
    }




}
