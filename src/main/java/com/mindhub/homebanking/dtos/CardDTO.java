package com.mindhub.homebanking.dtos;
import com.mindhub.homebanking.models.*;
import java.time.LocalDate;

public class CardDTO {
    private long id;
    private CardType type;
    private int number;
    private String cardHolder;
    private CardColor color;
    private LocalDate fromDate;
    private LocalDate thruDate;
    private int cvv;

    public CardDTO(Card card) {
        this.id = card.getId();
        this.type = card.getType();
        this.number = card.getNumber();
        this.cardHolder = card.getCardHolder();
        this.color = card.getColor();
        this.fromDate = card.getFromDate();
        this.thruDate = card.getThruDate();
        this.cvv = card.getCvv();}

    public long getId() {return id;}
    public void setId(long id) {this.id = id;}
    public CardType getType() {return type;}
    public void setType(CardType type) {this.type = type;}
    public int getNumber() {return number;}
    public void setNumber(int number) {this.number = number;}
    public CardColor getColor() {return color;}
    public void setColor(CardColor color) {this.color = color;}
    public LocalDate getFromDate() {return fromDate;}
    public void setFromDate(LocalDate fromDate) {this.fromDate = fromDate;}
    public LocalDate getThruDate() {return thruDate;}
    public void setThruDate(LocalDate thruDate) {this.thruDate = thruDate;}
    public int getCvv() {return cvv;}
    public void setCvv(int cvv) {this.cvv = cvv;}
    public String getCardHolder() {return cardHolder;}
    public void setCardHolder(String cardHolder) {this.cardHolder = cardHolder;}
}
