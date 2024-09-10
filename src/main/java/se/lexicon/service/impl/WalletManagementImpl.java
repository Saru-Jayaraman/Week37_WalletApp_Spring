package se.lexicon.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.lexicon.dao.WalletDao;
import se.lexicon.exception.WalletNotFoundException;
import se.lexicon.exception.WalletValidationException;
import se.lexicon.model.Wallet;
import se.lexicon.service.WalletManagement;

import java.util.List;
import java.util.Optional;

@Component
public class WalletManagementImpl implements WalletManagement {

    WalletDao walletDao;

    @Autowired
    public WalletManagementImpl(WalletDao walletDao) {
        this.walletDao = walletDao;
    }

    @Override
    public Wallet create(String walletName) {
        if(walletName == null || walletName.trim().isEmpty())
            throw new WalletValidationException("Wallet Name is null/empty.");
        if(walletDao.findByWalletName(walletName).isPresent())
            throw new WalletValidationException("Wallet Name already exists: ", walletName);
        Wallet walletObj = new Wallet(walletName);
        return walletDao.createWallet(walletObj);
    }

    @Override
    public Wallet findById(String id) {
        if(id == null || id.trim().isEmpty())
            throw new WalletValidationException("Wallet Id is null/empty.");
        Optional<Wallet> optionalWallet = walletDao.findById(id);
        if(!optionalWallet.isPresent())
            throw new WalletNotFoundException("Wallet Id not found: ", id);
        return optionalWallet.get();
    }

    @Override
    public Wallet findByName(String walletName) {
        if(walletName == null || walletName.trim().isEmpty())
            throw new WalletValidationException("Wallet Name is null/empty.");
        Optional<Wallet> optionalWallet = walletDao.findByWalletName(walletName);
        if(!optionalWallet.isPresent())
            throw new WalletNotFoundException("Wallet Name not found: ", walletName);
        return optionalWallet.get();
    }

    @Override
    public List<Wallet> findAll() {
        return walletDao.findAll();
    }

    @Override
    public boolean delete(String id) {
        if(id == null || id.trim().isEmpty())
            throw new WalletValidationException("Wallet Id is null/empty.");
        Optional<Wallet> optionalWallet = walletDao.findById(id);
        if(!optionalWallet.isPresent())
            throw new WalletNotFoundException("Wallet Id not found: ", id);
        return walletDao.deleteWallet(id);
    }
}
