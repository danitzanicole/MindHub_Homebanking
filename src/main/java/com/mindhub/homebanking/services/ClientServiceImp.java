package com.mindhub.homebanking.services;
import com.mindhub.homebanking.dtos.ClientDTO;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.services.implementacion.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ClientServiceImp implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public ClientServiceImp(ClientRepository clientRepository){
        this.clientRepository = clientRepository;}

    @Override
    public Set<ClientDTO> findAll(){
        return this.clientRepository.findAll().stream().map(ClientDTO::new).collect(Collectors.toSet());}

    @Override
    public ClientDTO findById(long id) {
        return this.clientRepository.findById(id).map(ClientDTO::new).orElse(null);}

}
