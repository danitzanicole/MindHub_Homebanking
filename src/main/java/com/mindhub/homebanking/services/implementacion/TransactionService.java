package com.mindhub.homebanking.services.implementacion;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface TransactionService {

    ResponseEntity<Object> transaction(org.springframework.security.core.Authentication authentication, double amount, String description, String fromAccountNumber, String toAccountNumber);
}
