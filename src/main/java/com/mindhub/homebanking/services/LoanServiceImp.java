package com.mindhub.homebanking.services;

import com.mindhub.homebanking.dtos.LoanDTO;
import com.mindhub.homebanking.repositories.LoanRepository;
import com.mindhub.homebanking.services.implementacion.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class LoanServiceImp implements LoanService {

    private final LoanRepository loanRepository;

    public LoanServiceImp(LoanRepository loanRepository){
        this.loanRepository = loanRepository;}

    @Override
    public Set<LoanDTO> findAll(){
        return this.loanRepository.findAll().stream().map(LoanDTO::new).collect(Collectors.toSet());}

    @Override
    public LoanDTO findById(@PathVariable long id) {
        return this.loanRepository.findById(id).map(LoanDTO::new).orElse(null);}


}
