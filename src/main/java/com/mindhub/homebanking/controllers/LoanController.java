package com.mindhub.homebanking.controllers;
import com.mindhub.homebanking.dtos.LoanDTO;
import com.mindhub.homebanking.repositories.LoanRepository;
import com.mindhub.homebanking.services.implementacion.LoanService;
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
public class LoanController {

    private final LoanService loanService;
    private LoanController(LoanService loanService) {
        this.loanService = loanService;};

    @RequestMapping("/loans")
    public Set<LoanDTO> getLoans() {
        return this.loanService.findAll();}

    @RequestMapping("/loans/{id}")
    public LoanDTO getLoans(@PathVariable long id) {
        return loanService.findById(id);}


}
