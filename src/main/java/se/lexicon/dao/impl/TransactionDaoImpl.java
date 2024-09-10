package se.lexicon.dao.impl;

import org.springframework.stereotype.Component;
import se.lexicon.dao.TransactionDao;
import se.lexicon.model.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TransactionDaoImpl implements TransactionDao {

    private List<Transaction> transactionStorage;

    public TransactionDaoImpl() {
        System.out.println("Transaction List is initialized and ready to use...");
        this.transactionStorage = new ArrayList<>();
    }

    @Override
    public Transaction createTransaction(Transaction transaction) {
        transactionStorage.add(transaction);
        return transaction;
    }

    @Override
    public Optional<Transaction> findById(String id) {
        return transactionStorage.stream().filter(transaction -> transaction.getId().equals(id)).findFirst();
    }

    @Override
    public List<Transaction> findTransactionByWalletId(String walletId) {
        return transactionStorage.stream()
                .filter(transaction -> transaction.getWalletId().equals(walletId))
                .collect(Collectors.toList());
    }
}
