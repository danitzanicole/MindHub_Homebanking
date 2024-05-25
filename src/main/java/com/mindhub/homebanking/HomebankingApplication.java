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

	@Autowired
	private PasswordEncoder passwordEncode;
	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);}

	public HomebankingApplication(PasswordEncoder passwordEncode) {
		this.passwordEncode = passwordEncode;
	}

	@Bean
	public CommandLineRunner iniData(ClientRepository clientRepository, AccountRepository accountRepository,
									 TransactionRepository transactionRepository, LoanRepository loanRepository,
									 ClientLoanRepository clientLoanRepository, CardRepository cardRepository){
		return args -> {
			Client client1 = new Client("Melba", "Morel", "melba@mindhub.com", passwordEncode.encode("Melba1234"));
			Client client2 = new Client("Jose", "Salazar", "jose@gmail.com", passwordEncode.encode("Jose1234"));
			Client client3 = new Client("Danitza", "Alvarado", "danitza@gmail.com", passwordEncode.encode("Danitza1234"));
			clientRepository.save(client1);
			clientRepository.save(client2);
			clientRepository.save(client3);

			LocalDate fechaHoy = LocalDate.now();
			Account account1 = new Account("VIN001", fechaHoy, 5000);
			Account account2 = new Account("VIN002", fechaHoy.plusDays(1), 7500);
			Account account3 = new Account("VIN003", fechaHoy.plusDays(2), 10000);
			Account account4 = new Account("VIN004", fechaHoy.plusDays(3), 1000);

			account1.setClient(client1);
			accountRepository.save(account1);
			account2.setClient(client1);
			accountRepository.save(account2);
			account3.setClient(client2);
			accountRepository.save(account3);
			account4.setClient(client3);
			accountRepository.save(account4);

			Transaction transaction1 = new Transaction(TransactionType.CREDIT, 200000, "Abono", fechaHoy);
			Transaction transaction2 = new Transaction(TransactionType.DEBIT, -2000, "Cargo", fechaHoy);
			Transaction transaction3 = new Transaction(TransactionType.CREDIT, 1000, "Abono", fechaHoy);
			Transaction transaction4 = new Transaction(TransactionType.DEBIT, -300, "Cargo", fechaHoy);
			Transaction transaction5 = new Transaction(TransactionType.CREDIT, 500000, "Abono", fechaHoy);
			Transaction transaction6 = new Transaction(TransactionType.DEBIT, -1000, "Cargo", fechaHoy);
			Transaction transaction7 = new Transaction(TransactionType.CREDIT, 800000, "Abono", fechaHoy);
			Transaction transaction8 = new Transaction(TransactionType.DEBIT, -2000, "Cargo", fechaHoy);
			Transaction transaction9 = new Transaction(TransactionType.CREDIT, 6000, "Abono", fechaHoy);
			Transaction transaction10 = new Transaction(TransactionType.DEBIT, -20567, "Cargo", fechaHoy);

			transaction1.setAccount(account1);
			transactionRepository.save(transaction1);
			transaction2.setAccount(account2);
			transactionRepository.save(transaction2);
			transaction3.setAccount(account3);
			transactionRepository.save(transaction3);
			transaction4.setAccount(account4);
			transactionRepository.save(transaction4);
			transaction5.setAccount(account1);
			transactionRepository.save(transaction5);
			transaction6.setAccount(account2);
			transactionRepository.save(transaction6);
			transaction7.setAccount(account3);
			transactionRepository.save(transaction7);
			transaction8.setAccount(account4);
			transactionRepository.save(transaction8);
			transaction9.setAccount(account4);
			transactionRepository.save(transaction9);
			transaction10.setAccount(account4);
			transactionRepository.save(transaction10);

			Loan loan1 = new Loan("Hipotecario", 500000, Arrays.asList(12,24,36,48,60), fechaHoy);
			Loan loan2 = new Loan("Personal", 100000, Arrays.asList(6,12,24), fechaHoy);
			Loan loan3 = new Loan("Automotriz", 300000, Arrays.asList(6,12,24,36), fechaHoy);
			loanRepository.save(loan1);
			loanRepository.save(loan2);
			loanRepository.save(loan3);

			ClientLoan clientLoan1 = new ClientLoan (400000,60);
			ClientLoan clientLoan2 = new ClientLoan (50000,12);
			ClientLoan clientLoan3 = new ClientLoan (100000,24);
			ClientLoan clientLoan4 = new ClientLoan (200000,36);

			clientLoan1.setClient(client1);
			clientLoanRepository.save(clientLoan1);
			clientLoan2.setClient(client1);
			clientLoanRepository.save(clientLoan2);
			clientLoan3.setClient(client2);
			clientLoanRepository.save(clientLoan3);
			clientLoan4.setClient(client3);
			clientLoanRepository.save(clientLoan4);

			Card card1 = new Card(CardType.DEBIT, "123-456-789","Melba Morel", CardColor.GOLD, fechaHoy, fechaHoy.plusYears(5), 560);
			Card card2 = new Card(CardType.CREDIT, "123-455-432","Melba Morel", CardColor.TITANIUM, fechaHoy, fechaHoy.plusYears(5), 700);
			Card card3 = new Card(CardType.CREDIT, "987-654-321","Danitza Alvarado" ,CardColor.SILVER, fechaHoy, fechaHoy.plusYears(5), 666);
			Card card4 = new Card(CardType.DEBIT, "987-655-678", "Danitza Alvarado",CardColor.GOLD, fechaHoy, fechaHoy.plusYears(5), 133);

			card1.setClient(client1);
			cardRepository.save(card1);
			card2.setClient(client1);
			cardRepository.save(card2);
			card3.setClient(client3);
			cardRepository.save(card3);
			card4.setClient(client3);
			cardRepository.save(card4);
		};
	}

}
