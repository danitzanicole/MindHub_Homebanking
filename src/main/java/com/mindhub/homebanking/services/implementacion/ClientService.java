package com.mindhub.homebanking.services.implementacion;
import com.mindhub.homebanking.dtos.ClientDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Set;

@Service
public interface ClientService {
    Set<ClientDTO> findAll();
    ClientDTO findById(@PathVariable long id);
}
