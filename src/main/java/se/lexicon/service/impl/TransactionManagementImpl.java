package se.lexicon.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.lexicon.dao.TransactionDao;
import se.lexicon.dao.WalletDao;
import se.lexicon.exception.WalletNotFoundException;
import se.lexicon.exception.WalletValidationException;
import se.lexicon.model.CryptoCurrency;
import se.lexicon.model.Transaction;
import se.lexicon.model.Wallet;
import se.lexicon.service.TransactionManagement;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Component
public class TransactionManagementImpl implements TransactionManagement {

    private TransactionDao transactionDao;
    private WalletDao walletDao;

    @Autowired
    public TransactionManagementImpl(TransactionDao transactionDao, WalletDao walletDao) {
        this.transactionDao = transactionDao;
        this.walletDao = walletDao;
    }

    @Override
    public Transaction createDepositTransaction(String walletId, CryptoCurrency cryptocurrency, BigDecimal amount, String description) {
        if (walletId == null || cryptocurrency == null || amount == null)
            throw new IllegalArgumentException("Transaction parameters are null.");
        Optional<Wallet> walletOptional = walletDao.findById(walletId);
        if(!walletOptional.isPresent())
            throw new WalletNotFoundException("Wallet not found. " + walletId);
        Wallet wallet = walletOptional.get();
        wallet.deposit(cryptocurrency, amount);
        Transaction transaction = new Transaction("DEPOSIT", walletId, cryptocurrency.getName(), amount);
        transaction.setDescription(description);
        return transactionDao.createTransaction(transaction);
    }

    @Override
    public Transaction createWithdrawTransaction(String walletId, CryptoCurrency cryptocurrency, BigDecimal amount, String description) {
        if (walletId == null || cryptocurrency == null || amount == null)
            throw new IllegalArgumentException("Transaction parameters are null.");
        Optional<Wallet> walletOptional = walletDao.findById(walletId);
        if(!walletOptional.isPresent())
            throw new WalletNotFoundException("Wallet not found. " + walletId);
        Wallet wallet = walletOptional.get();
        wallet.withdraw(cryptocurrency, amount);
        Transaction transaction = new Transaction("WITHDRAW", walletId, cryptocurrency.getName(), amount);
        transaction.setDescription(description);
        return transactionDao.createTransaction(transaction);
    }

    @Override
    public Transaction findById(String id) {
        if(id == null || id.trim().isEmpty())
            throw new WalletValidationException("Transaction Id is null/empty.");
        Optional<Transaction> optionalTransaction = transactionDao.findById(id);
        if(!optionalTransaction.isPresent())
            throw new WalletNotFoundException("Transaction Id not found: ", id);
        return optionalTransaction.get();
    }

    @Override
    public List<Transaction> getTransactionsByWalletId(String walletId) {
        if (walletId == null)
            throw new WalletNotFoundException("Wallet not found.");
        Optional<Wallet> walletOptional = walletDao.findById(walletId);
        if(!walletOptional.isPresent())
            throw new WalletNotFoundException("Wallet not found. " + walletId);
        return transactionDao.findTransactionByWalletId(walletId);
    }
}
