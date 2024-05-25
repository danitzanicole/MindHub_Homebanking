package com.mindhub.homebanking.services.implementacion;
import com.mindhub.homebanking.dtos.AccountDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import java.time.LocalDate;
import java.util.Set;

@Service
public interface AccountService {
    Set<AccountDTO> findAll();

    AccountDTO findById(long id);
}
