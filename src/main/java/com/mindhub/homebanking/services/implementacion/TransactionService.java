package com.mindhub.homebanking.services.implementacion;
import com.mindhub.homebanking.dtos.TransactionDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.TransactionType;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import java.time.LocalDate;
import java.util.Set;

@Service
public interface TransactionService {
    Set<TransactionDTO> findAll();

    TransactionDTO findById(long id);

    ResponseEntity<Object> transaction(org.springframework.security.core.Authentication authentication, double amount, String description,
                                       String fromAccountNumber, String toAccountNumber);

}
