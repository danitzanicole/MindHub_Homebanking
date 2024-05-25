package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.services.implementacion.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/api")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;};

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
