package com.mindhub.homebanking.services;

import com.mindhub.homebanking.dtos.TransactionDTO;
import com.mindhub.homebanking.models.Transaction;
import com.mindhub.homebanking.models.TransactionType;
import com.mindhub.homebanking.repositories.TransactionRepository;
import com.mindhub.homebanking.services.implementacion.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImp implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public TransactionServiceImp(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;}

    @Override
    public Set<TransactionDTO> findAll() {
        return this.transactionRepository.findAll().stream().map(TransactionDTO::new).collect(Collectors.toSet());}

    @Override
    public TransactionDTO findById(long id) {
        return this.transactionRepository.findById(id).map(TransactionDTO::new).orElse(null);}

}
