package com.mindhub.homebanking.services;

import com.mindhub.homebanking.dtos.TransactionDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Transaction;
import com.mindhub.homebanking.models.TransactionType;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.repositories.TransactionRepository;
import com.mindhub.homebanking.services.implementacion.TransactionService;
import com.mindhub.homebanking.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImp implements TransactionService {

    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;
    private final TransactionRepository transactionRepository;

    public TransactionServiceImp(AccountRepository accountRepository,ClientRepository clientRepository,TransactionRepository transactionRepository){
        this.accountRepository = accountRepository;
        this.clientRepository = clientRepository;
        this.transactionRepository = transactionRepository;}

    @Override
    public Set<TransactionDTO> findAll() {
        return this.transactionRepository.findAll().stream().map(TransactionDTO::new).collect(Collectors.toSet());}

    @Override
    public TransactionDTO findById(long id) {
        return this.transactionRepository.findById(id).map(TransactionDTO::new).orElse(null);}


    @Override
    public ResponseEntity<Object> transaction(org.springframework.security.core.Authentication authentication, double amount, String description,
                                              String sourceAccountNumber, String destinationAccountNumber){
        if(Utils.validTransaction(authentication, amount, description, sourceAccountNumber, destinationAccountNumber,
                                  accountRepository, clientRepository)){
            Account sourceAccount = accountRepository.findByNumber(sourceAccountNumber).get();
            Account destinationAccount = accountRepository.findByNumber(destinationAccountNumber).get();

            if (sourceAccount.getBalance() >= amount){
                Transaction debito = transactionRepository.save(new Transaction(TransactionType.DEBIT,
                        amount, description + " " + sourceAccountNumber, LocalDate.now()));

                Transaction credito = transactionRepository.save(new Transaction(TransactionType.CREDIT,
                        amount, description + " " + destinationAccountNumber, LocalDate.now()));

                debito.setAccount(sourceAccount);
                sourceAccount.setBalance(sourceAccount.getBalance() - amount);
                accountRepository.save(sourceAccount);

                credito.setAccount(destinationAccount);
                destinationAccount.setBalance(destinationAccount.getBalance() + amount);
                accountRepository.save(destinationAccount);

                return new ResponseEntity<>("Exito", HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("Forbidden", HttpStatus.FORBIDDEN);
            }
        }  else {
            return new ResponseEntity<>("Forbidden", HttpStatus.FORBIDDEN);
        }
    }

}
