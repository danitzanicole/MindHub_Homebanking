package com.mindhub.homebanking.services.implementacion;
import com.mindhub.homebanking.dtos.TransactionDTO;
import com.mindhub.homebanking.models.TransactionType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import java.time.LocalDate;
import java.util.Set;

@Service
public interface TransactionService {
    Set<TransactionDTO> findAll();
    TransactionDTO findById(@PathVariable long id);

}
