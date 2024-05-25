package com.mindhub.homebanking.services.implementacion;
import com.mindhub.homebanking.dtos.LoanApplicationDTO;
import com.mindhub.homebanking.dtos.LoanDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Set;

@Service
public interface LoanService {
    Set<LoanDTO> getLoans();
    ResponseEntity<Object> crearLoan(Authentication authentication, LoanApplicationDTO applicationDTO);

}
