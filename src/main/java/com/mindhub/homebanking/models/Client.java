package com.mindhub.homebanking.models;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import java.util.HashSet;
import java.util.stream.Collectors;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @OneToMany (mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Set<Account> accounts = new HashSet<>();

    @OneToMany (mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Set<ClientLoan> clientLoans = new HashSet<>();

    @OneToMany (mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Set<Card> cards = new HashSet<>();

    public Client() { }
    public Client(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;}

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public Set<Account> getAccounts() { return accounts; }
    public void setAccounts(Set<Account> accounts) {this.accounts = accounts;}
    public void addAccount (Account account){
        account.setClient(this);
        this.accounts.add(account);}
    public Set<ClientLoan> getClientLoans() {return clientLoans;}
    public void setClientLoans(Set<ClientLoan> clientLoans) {this.clientLoans = clientLoans;}
    public Set<Card> getCards() {return cards;}
    public void setCards(Set<Card> cards) {this.cards = cards;}
    public void addCard (Card card){
        card.setClient(this);
        this.cards.add(card);}
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", accounts=" + accounts +
                '}';}

    @JsonIgnore
    private List<Loan> getLoans(){
        //retornar un listado de prestamos de un cliente.
        return clientLoans.stream().map(sub -> sub.getLoan()).collect(Collectors.toList());}
}
