package com.mindhub.homebanking.services.implementacion;
import com.mindhub.homebanking.dtos.LoanDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Set;

@Service
public interface LoanService {
    Set<LoanDTO> findAll();

    LoanDTO findById( long id);

}
