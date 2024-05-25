package com.mindhub.homebanking.models;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private CardType type;
    private String number;
    private String cardHolder;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    private CardColor color;
    private LocalDate fromDate;
    private LocalDate thruDate;
    private int cvv;

    public Card() {}
    public Card(CardType type, String number, String cardHolder, CardColor color, LocalDate fromDate, LocalDate thruDate, int cvv) {
        this.type = type;
        this.number = number;
        this.cardHolder = cardHolder;
        this.color = color;
        this.fromDate = fromDate;
        this.thruDate = thruDate;
        this.cvv = cvv;}


    public long getId() {return id;}
    public void setId(long id) {this.id = id;}
    public CardType getType() {return type;}
    public void setType(CardType type) {this.type = type;}
    public String getNumber() {return number;}
    public void setNumber(String number) {this.number = number;}
    public CardColor getColor() {return color;}
    public void setColor(CardColor color) {this.color = color;}
    public LocalDate getFromDate() {return fromDate;}
    public void setFromDate(LocalDate fromDate) {this.fromDate = fromDate;}
    public LocalDate getThruDate() {return thruDate;}
    public void setThruDate(LocalDate thruDate) {this.thruDate = thruDate;}
    public int getCvv() {return cvv;}
    public void setCvv(int cvv) {this.cvv = cvv;}
    public Client getClient() {return client;}
    public void setClient(Client client) {this.client = client;}
    public String getCardHolder() {
        cardHolder = client.getFirstName()+" "+client.getLastName();
        return cardHolder;}
    public void setCardHolder(String cardHolder) {this.cardHolder = cardHolder;}

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", type=" + type +
                ", number=" + number +
                ", cardHolder='" + cardHolder + '\'' +
                ", color=" + color +
                ", fromDate=" + fromDate +
                ", thruDate=" + thruDate +
                ", cvv=" + cvv +
                '}';}
}
