package se.lexicon.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Transaction {
    private String id;
    private String transactionType;
    private String walletId;
    private String cryptoCurrency;
    private BigDecimal amount;
    private String description;
    private LocalDateTime timeStamp;

    public Transaction(String transactionType, String walletId, String cryptoCurrency, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("Amount cannot be negative.");
        this.id = UUID.randomUUID().toString();
        this.transactionType = transactionType;
        this.walletId = walletId;
        this.cryptoCurrency = cryptoCurrency;
        this.amount = amount;
        this.timeStamp = LocalDateTime.now();
    }

    public Transaction(String id, String transactionType, String walletId, String cryptoCurrency, BigDecimal amount, String description, LocalDateTime timeStamp) {
        this.id = id;
        this.transactionType = transactionType;
        this.walletId = walletId;
        this.cryptoCurrency = cryptoCurrency;
        this.amount = amount;
        this.description = description;
        this.timeStamp = timeStamp;
    }

    public String getId() {
        return id;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public String getWalletId() {
        return walletId;
    }

    public String getCryptoCurrency() {
        return cryptoCurrency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + id + '\'' +
                ", transactionType='" + transactionType + '\'' +
                ", walletId='" + walletId + '\'' +
                ", cryptoCurrency='" + cryptoCurrency + '\'' +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
