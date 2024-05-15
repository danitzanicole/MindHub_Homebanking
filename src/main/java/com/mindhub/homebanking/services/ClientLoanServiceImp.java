package com.mindhub.homebanking.services;

import com.mindhub.homebanking.dtos.ClientLoanDTO;
import com.mindhub.homebanking.repositories.ClientLoanRepository;
import com.mindhub.homebanking.services.implementacion.ClientLoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ClientLoanServiceImp implements ClientLoanService {

    @Autowired
    private ClientLoanRepository clientLoanRepository;

    public ClientLoanServiceImp(ClientLoanRepository clientLoanRepository){
        this.clientLoanRepository = clientLoanRepository;}

    @Override
    public Set<ClientLoanDTO> findAll(){
        return this.clientLoanRepository.findAll().stream().map(ClientLoanDTO::new).collect(Collectors.toSet());}

    @Override
    public ClientLoanDTO findById(@PathVariable long id) {
        return this.clientLoanRepository.findById(id).map(ClientLoanDTO::new).orElse(null);}


}
