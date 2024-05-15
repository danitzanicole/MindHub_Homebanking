package com.mindhub.homebanking.dtos;
import com.mindhub.homebanking.models.Transaction;
import com.mindhub.homebanking.models.TransactionType;
import java.time.LocalDate;


public class TransactionDTO {
    private long id;
    private TransactionType type;
    private double amount;
    private String description;
    private LocalDate creationDate;

    public TransactionDTO(Transaction transaction) {
        this.type = TransactionType.valueOf(transaction.getType().toString());
        this.id = transaction.getId();
        this.amount = transaction.getAmount();
        this.description = transaction.getDescription();
        this.creationDate = transaction.getCreationDate();}

    public long getId() {return id;}
    public void setId(long id) {this.id = id;}
    public TransactionType getType() {return type;}
    public void setType(TransactionType type) {this.type = type;}
    public double getAmount() {return amount;}
    public void setAmount(double amount) {this.amount = amount;}
    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}
    public LocalDate getCreationDate() {return creationDate;}
    public void setCreationDate(LocalDate creationDate) {this.creationDate = creationDate;}
}
