package com.mindhub.homebanking.services;
import com.mindhub.homebanking.dtos.AccountDTO;
import com.mindhub.homebanking.dtos.ClientDTO;
import com.mindhub.homebanking.models.CardColor;
import com.mindhub.homebanking.models.CardType;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Card;
import com.mindhub.homebanking.utils.Utils;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.CardRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.services.implementacion.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ClientServiceImp implements ClientService {

    private final ClientRepository clientRepository;
    private final AccountRepository accountRepository;
    private final CardRepository cardRepository;

    private ClientServiceImp(ClientRepository clientRepository, AccountRepository accountRepository, CardRepository cardRepository){
        this.clientRepository = clientRepository;
        this.accountRepository = accountRepository;
        this.cardRepository = cardRepository; }

    @Override
    public Set<ClientDTO> findAll(){
        return clientRepository.findAll().stream().map(ClientDTO::new).collect(Collectors.toSet());}

    @Override
    public ClientDTO findById(long id) {
        return clientRepository.findById(id).map(ClientDTO::new).orElse(null);}

    @Override
    public ClientDTO findCurrentClient(Authentication authentication){
        return clientRepository.findByEmail(authentication.getName()).map(ClientDTO::new).orElse(null);}

    @Override
    public List<AccountDTO> findCurrentAccount(Authentication authentication){
        ClientDTO client = this.findCurrentClient(authentication);
        return client.getAccounts();}


    @Override
    public ResponseEntity<Object> crearAccount(Authentication authentication){
        Optional<Client> clientOptional = clientRepository.findByEmail(authentication.getName());
        if (clientOptional.isPresent()){
            if (clientOptional.get().getAccounts().isEmpty() || clientOptional.get().getAccounts().size() < 3 || clientOptional.get().getClientLoans().isEmpty()){
                LocalDate fechaHoy = LocalDate.now();
                Account account = accountRepository.save(new Account(Utils.generateAccountNumber(8), fechaHoy, 0));
                account.setClient(clientOptional.get());
                accountRepository.save(account);
                return new ResponseEntity<>("Creada", HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("Prohibido", HttpStatus.FORBIDDEN);
            }
        } else {
            return new ResponseEntity<>("Cliente no existe", HttpStatus.FORBIDDEN);
        }
    }

    @Override
    public ResponseEntity<Object> autoCrearAccount(String email){
        Optional<Client> optionalClient = clientRepository.findByEmail(email);
        if (optionalClient.isPresent()){
            if (optionalClient.get().getAccounts().isEmpty() || optionalClient.get().getAccounts().size() < 3){
                LocalDate fechaHoy = LocalDate.now();
                Account account = accountRepository.save(new Account(Utils.generateAccountNumber(8), fechaHoy, 0));
                account.setClient(optionalClient.get());
                accountRepository.save(account);
                return new ResponseEntity<>("Creada", HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("Prohibido", HttpStatus.FORBIDDEN);
            }
        } else {
            return new ResponseEntity<>("Cliente no existe", HttpStatus.FORBIDDEN);
        }
    }

    @Override
    public ResponseEntity<Object> crearCard(Authentication authentication, CardColor cardColor, CardType cardType){
        Optional<Client> optionalClient = clientRepository.findByEmail(authentication.getName());
        if (optionalClient.isPresent()){
            if (Utils.countCards(optionalClient.get().getCards(), cardType) < 3) {
                LocalDate fromDate = LocalDate.now();
                LocalDate thruDate = fromDate.plusYears(5);
                String cardHolder = optionalClient.get().getFirstName() + " " + optionalClient.get().getLastName();
                String number = Utils.generateCardNumber();
                int cvv = (int) Utils.generateCvv();
                Card card = cardRepository.save(new Card (
                        cardType, number, cardHolder, cardColor, fromDate, thruDate, cvv));
                card.setClient(optionalClient.get());
                cardRepository.save(card);
                return new ResponseEntity<>("Creada", HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("Prohibido", HttpStatus.FORBIDDEN);
            }
        } else {
            return  new ResponseEntity<>("Cliente no existe", HttpStatus.FORBIDDEN);
        }
    }

}
