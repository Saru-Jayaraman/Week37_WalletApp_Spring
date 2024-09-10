package se.lexicon.model;

import se.lexicon.exception.InSufficientBalanceException;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Wallet {
    private String id;
    private String walletName;
    Map<CryptoCurrency, BigDecimal> cryptoCurrencies;

    public Wallet(String walletName) {
        this.id = UUID.randomUUID().toString();
        this.walletName = walletName;
        this.cryptoCurrencies = new HashMap<>();
    }

    public Wallet(String id, String walletName, Map<CryptoCurrency, BigDecimal> cryptoCurrencies) {
        this.id = id;
        this.walletName = walletName;
        this.cryptoCurrencies = cryptoCurrencies;
    }

    public String getId() {
        return id;
    }

    public String getWalletName() {
        return walletName;
    }

    public Map<CryptoCurrency, BigDecimal> getCryptoCurrencies() {
        return cryptoCurrencies;
    }

    public void deposit(CryptoCurrency cryptoCurrency, BigDecimal amount) {
        if(amount.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Deposit amount must be greater than zero.");
        BigDecimal currentBalance = getBalance(cryptoCurrency);
        BigDecimal newBalance = currentBalance.add(amount);
        cryptoCurrencies.put(cryptoCurrency, newBalance);
    }

    public BigDecimal getBalance(CryptoCurrency currency) {
        return cryptoCurrencies.getOrDefault(currency, BigDecimal.ZERO);
    }

    public void withdraw(CryptoCurrency cryptoCurrency, BigDecimal amount) {
        if(amount.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Withdrawal amount must be greater than zero.");
        BigDecimal currentBalance = cryptoCurrencies.getOrDefault(cryptoCurrency, BigDecimal.ZERO);
        if(amount.compareTo(currentBalance) >= 0)
            throw new InSufficientBalanceException("InSufficientBalanceException: Withdrawal amount must be greater than currentBalance: " + currentBalance);
        BigDecimal newBalance = currentBalance.subtract(amount);
        cryptoCurrencies.put(cryptoCurrency, newBalance);
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "id='" + id + '\'' +
                ", walletName='" + walletName + '\'' +
                ", cryptoCurrencies=" + cryptoCurrencies +
                '}';
    }
}
