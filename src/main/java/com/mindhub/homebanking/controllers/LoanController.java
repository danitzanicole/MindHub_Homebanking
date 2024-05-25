package com.mindhub.homebanking.controllers;
import com.mindhub.homebanking.dtos.LoanApplicationDTO;
import com.mindhub.homebanking.dtos.LoanDTO;
import com.mindhub.homebanking.repositories.LoanRepository;
import com.mindhub.homebanking.services.implementacion.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class LoanController {

    private final LoanService loanService;

    private LoanController(LoanService loanService) {
        this.loanService = loanService;};

    @GetMapping("/loans")
    public Set<LoanDTO> getCurrentClientLoans() {
        return loanService.getLoans();}

    @Transactional
    @PostMapping(path = "/loans")
    public ResponseEntity<Object> applyForLoan(@RequestBody LoanApplicationDTO appliedLoan){
        return loanService.crearLoan(SecurityContextHolder.getContext().getAuthentication(), appliedLoan);}
}
