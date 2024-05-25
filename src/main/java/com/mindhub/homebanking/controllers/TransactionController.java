package com.mindhub.homebanking.controllers;
import com.mindhub.homebanking.dtos.ClientDTO;
import com.mindhub.homebanking.dtos.TransactionDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.TransactionType;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.repositories.TransactionRepository;
import com.mindhub.homebanking.services.implementacion.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;};

    @RequestMapping("/transactions")
    public Set<TransactionDTO> getTransactions() {
        return this.transactionService.findAll();}

    @RequestMapping("/transactions/{id}")
    public TransactionDTO getTransactions(@PathVariable long id) {
        return transactionService.findById(id);}

    @Transactional
    @PostMapping(path = "/transactions")
    public ResponseEntity<Object> makeTransaction(
            @RequestParam String fromAccountNumber,
            @RequestParam String toAccountNumber,
            @RequestParam double amount,
            @RequestParam String description){
        return this.transactionService.transaction(SecurityContextHolder.getContext().getAuthentication(), amount,
                description, fromAccountNumber, toAccountNumber);
    }

}
