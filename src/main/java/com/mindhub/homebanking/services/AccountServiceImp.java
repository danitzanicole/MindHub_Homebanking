package com.mindhub.homebanking.services;
import com.mindhub.homebanking.dtos.AccountDTO;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.services.implementacion.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class AccountServiceImp implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImp (AccountRepository accountRepository){
        this.accountRepository = accountRepository;}

    @Override
    public Set<AccountDTO> findAll(){
        return this.accountRepository.findAll().stream().map(AccountDTO::new).collect(Collectors.toSet());}

    @Override
    public AccountDTO findById(long id) {
        return this.accountRepository.findById(id).map(AccountDTO::new).orElse(null);}

}
