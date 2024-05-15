package com.mindhub.homebanking.controllers;
import com.mindhub.homebanking.dtos.ClientLoanDTO;
import com.mindhub.homebanking.repositories.ClientLoanRepository;
import com.mindhub.homebanking.services.implementacion.ClientLoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ClientLoanController {

    @Autowired
    private ClientLoanService clientLoanService;

    @Autowired
    private ClientLoanController(ClientLoanService clientLoanService) {this.clientLoanService = clientLoanService;};

    @GetMapping("/clientLoans")
    public Set<ClientLoanDTO> getClientLoans() {
        return this.clientLoanService.findAll();}

    @GetMapping("/clientLoans/{id}")
    public ClientLoanDTO getClientLoans(@PathVariable long id) {
        return clientLoanService.findById(id);}

}
