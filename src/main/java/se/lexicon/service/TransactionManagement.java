package se.lexicon.service;

import se.lexicon.model.CryptoCurrency;
import se.lexicon.model.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionManagement {
    Transaction createDepositTransaction(String walletId, CryptoCurrency cryptocurrency, BigDecimal amount, String description);

    Transaction createWithdrawTransaction(String walletId, CryptoCurrency cryptocurrency, BigDecimal amount, String description);

    Transaction findById(String id);

    List<Transaction> getTransactionsByWalletId(String walletId);
}
