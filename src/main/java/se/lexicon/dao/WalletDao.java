package se.lexicon.dao;

import se.lexicon.model.Wallet;

import java.util.List;
import java.util.Optional;

public interface WalletDao {
    Wallet createWallet(Wallet wallet);

    Optional<Wallet> findById(String id);

    Optional<Wallet> findByWalletName(String walletName);

    List<Wallet> findAll();

    boolean deleteWallet(String id);
}
