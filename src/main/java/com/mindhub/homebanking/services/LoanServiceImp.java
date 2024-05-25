package com.mindhub.homebanking.services;

import com.mindhub.homebanking.dtos.LoanApplicationDTO;
import com.mindhub.homebanking.dtos.LoanDTO;
import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.*;
import com.mindhub.homebanking.services.implementacion.LoanService;
import com.mindhub.homebanking.utils.Utils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class LoanServiceImp implements LoanService {

    private final ClientRepository clientRepository;
    private final LoanRepository loanRepository;
    private final AccountRepository accountRepository;
    private final ClientLoanRepository clientLoanRepository;
    private final TransactionRepository transactionRepository;

    public LoanServiceImp(ClientRepository clientRepository,LoanRepository loanRepository,AccountRepository accountRepository,
                          ClientLoanRepository clientLoanRepository, TransactionRepository transactionRepository){
        this.clientRepository = clientRepository;
        this.loanRepository = loanRepository;
        this.accountRepository = accountRepository;
        this.clientLoanRepository = clientLoanRepository;
        this.transactionRepository = transactionRepository;}

    @Override
    public Set<LoanDTO> getLoans(){
        return loanRepository.findAll().stream().map(LoanDTO::new).collect(Collectors.toSet());}

    @Override
    public ResponseEntity<Object> crearLoan(Authentication authentication, LoanApplicationDTO applicationDTO){
        if (Utils.validarAppliedLoan(applicationDTO, loanRepository, accountRepository, clientRepository, authentication)){
            double amount = applicationDTO.getAmount() + applicationDTO.getAmount() * 0.2;
            ClientLoan clientLoan = clientLoanRepository.save(new ClientLoan(amount, applicationDTO.getPayments()));
            Client client = clientRepository.findByEmail(authentication.getName()).get();
            Loan loan = loanRepository.getById(applicationDTO.getLoanId());
            clientLoan.setLoan(loan);
            clientLoan.setClient(client);
            clientLoanRepository.save(clientLoan);
            Account loanAccount = accountRepository.findByNumber(applicationDTO.getToAccountNumber()).get();
            String descripcion = loan.getName() + " loan approved";
            LocalDate fechaHoy = LocalDate.now();
            Transaction transaction = transactionRepository.save(new Transaction(TransactionType.CREDIT, amount, descripcion, fechaHoy));
            loanAccount.setBalance(loanAccount.getBalance() + applicationDTO.getAmount());
            accountRepository.save(loanAccount);
            return new ResponseEntity<>("Exito", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Forbidden", HttpStatus.FORBIDDEN);}}

}
