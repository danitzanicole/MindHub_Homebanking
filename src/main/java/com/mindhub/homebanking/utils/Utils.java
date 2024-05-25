package com.mindhub.homebanking.utils;
import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import org.springframework.security.core.Authentication;

import java.util.Random;
import java.util.Set;
import java.util.stream.Stream;

public final class Utils {

    public static String generateAccountNumber(long max){
        String account = "VIN-";
        for (int i = 0; i < max; i++) {
            account = account.concat(Integer.toString(getRandomNumber()));}
        return account;}

    public static String generateCardNumber(){
        int contador = 0;
        StringBuilder number = new StringBuilder();
        for(int i = 0; i < 4; i++){
            while (contador < 4){
                number.append(getRandomNumber());
                contador++;}
            if (i != 3){
                number.append("-");}
            contador = 0;}
        return number.toString();}

    public static int getRandomNumber(){
        Random random = new Random();
        return random.nextInt(10);}

    public static long countCards(Set<Card> cards, CardType cardType){
        Stream<Card> cardStream = cards.stream().filter(c -> c.getType() == cardType);
        return cardStream.count();}

    public static long generateCvv(){
        StringBuilder cvv = new StringBuilder();
        for (int i = 0; i < 3; i++){
            cvv.append(getRandomNumber());}
        return Long.parseLong(cvv.toString());}

    private static boolean validateParameters(
            Authentication authentication, double amount, String description,
            String sourceAccountNumber, String destinationAccountNumber){
        if (authentication != null && amount != 0 && description != null && sourceAccountNumber != null && destinationAccountNumber != null){
            return true;
        } else {
            return  false;}}

    private static boolean clientExiste(Authentication authentication, ClientRepository clientRepository){
        if (clientRepository.findByEmail(authentication.getName()).isPresent()){
            return true;
        } else {
            return false;}}

    private static boolean AccountExiste(String accountNumber, AccountRepository accountRepository){
        if (accountRepository.findByNumber(accountNumber).isPresent()){
            return true;
        } else {
            return false;}}

    private static  boolean validAccountOwnerShip(Account account, ClientRepository clientRepository, Authentication authentication){
        Client client = clientRepository.findByEmail(authentication.getName()).get();
        if (client.getAccounts().contains(account)){
            return true;
        } else {
            return false;}}

    private static Account getAccounts(String accountNumber, AccountRepository accountRepository){
        if (accountRepository.findByNumber(accountNumber).isPresent()){
            return accountRepository.findByNumber(accountNumber).get();
        } else {
            return null;}}

    public static boolean validTransaction(Authentication authentication, double amount, String description,
                                           String sourceAccountNumber, String destinationAccountNumber,
                                           AccountRepository accountRepository, ClientRepository clientRepository){
        if (validateParameters(authentication,amount, description, sourceAccountNumber, destinationAccountNumber)
        && clientExiste(authentication, clientRepository)){
            if (AccountExiste(sourceAccountNumber, accountRepository) && AccountExiste(destinationAccountNumber, accountRepository)){
                Account sourceAccount = getAccounts(sourceAccountNumber, accountRepository);
                Account destinationAccount = getAccounts(destinationAccountNumber, accountRepository);

                return validAccountOwnerShip(sourceAccount, clientRepository, authentication) &&
                        validAccountOwnerShip(destinationAccount, clientRepository, authentication);
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
