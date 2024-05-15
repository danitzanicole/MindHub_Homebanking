package com.mindhub.homebanking.services.implementacion;
import com.mindhub.homebanking.dtos.ClientLoanDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Set;

@Service
public interface ClientLoanService{
    Set<ClientLoanDTO> findAll();
    ClientLoanDTO findById(@PathVariable long id);

}
