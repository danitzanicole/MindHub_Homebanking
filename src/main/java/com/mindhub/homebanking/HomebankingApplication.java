package com.mindhub.homebanking;
import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.*;

@SpringBootApplication
public class HomebankingApplication {

	public static void main(String[] args) { SpringApplication.run(HomebankingApplication.class, args);}

	@Autowired
	private PasswordEncoder passwordEncode;

	public HomebankingApplication(PasswordEncoder passwordEncode) {
		this.passwordEncode = passwordEncode;
	}

	@Bean
	public CommandLineRunner iniData(ClientRepository clientRepository, AccountRepository accountRepository,
									 TransactionRepository transactionRepository, LoanRepository loanRepository,
									 ClientLoanRepository clientLoanRepository, CardRepository cardRepository){
		return (args -> {
			Client client1 = new Client("Melba", "Morel", "melba@mindhub.com", passwordEncode.encode("Melba1234"));
			Client client2 = new Client("Jose", "Salazar", "jose@gmail.com", passwordEncode.encode("Jose1234"));
			Client client3 = new Client("Danitza", "Alvarado", "danitza@gmail.com", passwordEncode.encode("Danitza1234"));
			clientRepository.save(client1);
			clientRepository.save(client2);
			clientRepository.save(client3);

			LocalDate fechaHoy = LocalDate.now();
			Account account1 = new Account("VIN001", fechaHoy, 5000, client1);
			Account account2 = new Account("VIN002", fechaHoy.plusDays(1), 7500,client1);
			Account account3 = new Account("VIN003", fechaHoy.plusDays(2), 10000,client2);
			Account account4 = new Account("VIN004", fechaHoy.plusDays(3), 1000,client3);
			accountRepository.save(account1);
			accountRepository.save(account2);
			accountRepository.save(account3);
			accountRepository.save(account4);

			Transaction transaction1 = new Transaction(TransactionType.CREDIT, 200000, "Abono", fechaHoy, account1);
			Transaction transaction2 = new Transaction(TransactionType.DEBIT, -2000, "Cargo", fechaHoy, account1);
			Transaction transaction3 = new Transaction(TransactionType.CREDIT, 1000, "Abono", fechaHoy, account2);
			Transaction transaction4 = new Transaction(TransactionType.DEBIT, -300, "Cargo", fechaHoy, account2);
			Transaction transaction5 = new Transaction(TransactionType.CREDIT, 500000, "Abono", fechaHoy, account3);
			Transaction transaction6 = new Transaction(TransactionType.DEBIT, -1000, "Cargo", fechaHoy, account3);
			Transaction transaction7 = new Transaction(TransactionType.CREDIT, 800000, "Abono", fechaHoy, account4);
			Transaction transaction8 = new Transaction(TransactionType.DEBIT, -2000, "Cargo", fechaHoy, account4);
			Transaction transaction9 = new Transaction(TransactionType.CREDIT, 6000, "Abono", fechaHoy, account4);
			Transaction transaction10 = new Transaction(TransactionType.DEBIT, -20567, "Cargo", fechaHoy, account4);
			transactionRepository.save(transaction1);
			transactionRepository.save(transaction2);
			transactionRepository.save(transaction3);
			transactionRepository.save(transaction4);
			transactionRepository.save(transaction5);
			transactionRepository.save(transaction6);
			transactionRepository.save(transaction7);
			transactionRepository.save(transaction8);
			transactionRepository.save(transaction9);
			transactionRepository.save(transaction10);

			Loan loan1 = new Loan("Hipotecario", 500000, Arrays.asList(12,24,36,48,60));
			Loan loan2 = new Loan("Personal", 100000, Arrays.asList(6,12,24));
			Loan loan3 = new Loan("Automotriz", 300000, Arrays.asList(6,12,24,36));
			loanRepository.save(loan1);
			loanRepository.save(loan2);
			loanRepository.save(loan3);

			ClientLoan clientLoan1 = new ClientLoan (400000,60, client1, loan1);
			ClientLoan clientLoan2 = new ClientLoan (50000,12, client1, loan2);
			ClientLoan clientLoan3 = new ClientLoan (100000,24, client3, loan2);
			ClientLoan clientLoan4 = new ClientLoan (200000,36, client3, loan3);
			clientLoanRepository.save(clientLoan1);
			clientLoanRepository.save(clientLoan2);
			clientLoanRepository.save(clientLoan3);
			clientLoanRepository.save(clientLoan4);

			Card card1 = new Card(CardType.DEBIT, 123456789, client1, CardColor.GOLD, fechaHoy, fechaHoy.plusYears(5), 56);
			Card card2 = new Card(CardType.CREDIT, 123455432, client1, CardColor.TITANIUM, fechaHoy, fechaHoy.plusYears(5), 7000);
			Card card3 = new Card(CardType.CREDIT, 987654321, client3, CardColor.SILVER, fechaHoy, fechaHoy.plusYears(5), 666);
			Card card4 = new Card(CardType.DEBIT, 987655678, client3, CardColor.GOLD, fechaHoy, fechaHoy.plusYears(5), 133);
			cardRepository.save(card1);
			cardRepository.save(card2);
			cardRepository.save(card3);
			cardRepository.save(card4);
		});
	}

}
