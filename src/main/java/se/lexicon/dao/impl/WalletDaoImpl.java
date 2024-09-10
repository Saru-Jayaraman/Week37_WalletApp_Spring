package se.lexicon.dao.impl;

import org.springframework.stereotype.Component;
import se.lexicon.dao.WalletDao;
import se.lexicon.exception.WalletNotFoundException;
import se.lexicon.model.Wallet;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class WalletDaoImpl implements WalletDao {

    private List<Wallet> walletStorage;

    public WalletDaoImpl() {
        System.out.println("Wallet List is initialized and ready to use...");
        this.walletStorage = new ArrayList<>();
    }

    @Override
    public Wallet createWallet(Wallet wallet) {
        walletStorage.add(wallet);
        return wallet;
    }

    @Override
    public Optional<Wallet> findById(String id) {
        return walletStorage.stream().filter(wallet -> wallet.getId().equals(id)).findFirst();
    }

    @Override
    public Optional<Wallet> findByWalletName(String walletName) {
        return walletStorage.stream().filter(wallet -> wallet.getWalletName().equalsIgnoreCase(walletName)).findFirst();
    }

    @Override
    public List<Wallet> findAll() {
        return new ArrayList<>(walletStorage);
    }

    @Override
    public boolean deleteWallet(String id) {
        Optional<Wallet> optionalWallet = walletStorage.stream().filter(wallet -> wallet.getId().equals(id)).findFirst();
        if(!optionalWallet.isPresent()) {
            throw new WalletNotFoundException("Wallet not found.");
        }
        walletStorage.remove(optionalWallet.get());
        return true;
    }
}
