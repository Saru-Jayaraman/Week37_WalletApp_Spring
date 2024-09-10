package se.lexicon.service;

import se.lexicon.model.Wallet;

import java.util.List;
import java.util.Optional;

public interface WalletManagement {
    Wallet create(String walletName);

    Wallet findById(String id);

    Wallet findByName(String walletName);

    List<Wallet> findAll();

    boolean delete(String id);
}
