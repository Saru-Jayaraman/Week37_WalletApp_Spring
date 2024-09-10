package se.lexicon;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import se.lexicon.config.AppConfig;

import se.lexicon.model.CryptoCurrency;
import se.lexicon.model.Transaction;
import se.lexicon.model.Wallet;
import se.lexicon.service.TransactionManagement;
import se.lexicon.service.WalletManagement;

//import se.lexicon.dao.TransactionDao;
//import se.lexicon.dao.WalletDao;
//import se.lexicon.dao.impl.TransactionDaoImpl;
//import se.lexicon.dao.impl.WalletDaoImpl;
//import se.lexicon.service.impl.TransactionManagementImpl;
//import se.lexicon.service.impl.WalletManagementImpl;

import java.math.BigDecimal;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//        WalletDao walletDao = new WalletDaoImpl();
//        WalletManagement walletManagement = new WalletManagementImpl(walletDao);
//        TransactionDao transactionDao = new TransactionDaoImpl();
//        TransactionManagement transactionManagement = new TransactionManagementImpl(transactionDao, walletDao);

        //Spring Core
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        WalletManagement walletManagement = context.getBean(WalletManagement.class);
        TransactionManagement transactionManagement = context.getBean(TransactionManagement.class);

        System.out.println("--------------------------------CREATE--------------------------------");
        Wallet createdWallet1 = walletManagement.create("Wallet1");
        System.out.println("Created Wallet: " + createdWallet1);
        Wallet createdWallet2 = walletManagement.create("Wallet2");
        System.out.println("Created Wallet: " + createdWallet2);
        System.out.println();

        System.out.println("-------------------------------FIND ALL-------------------------------");
        System.out.println("Wallets list:");
        walletManagement.findAll().forEach(System.out::println);
        System.out.println();

        System.out.println("------------------------------FIND BY ID------------------------------");
        System.out.println("Find the wallet id: " + createdWallet1.getId());
        Wallet foundWalletById = walletManagement.findById(createdWallet1.getId());
        System.out.println("Found Wallet: " + foundWalletById);
        System.out.println();

        System.out.println("-----------------------------FIND BY NAME-----------------------------");
        String findName = "Wallet2";
        System.out.println("Find the wallet name: " + findName);
        Wallet foundWalletByName = walletManagement.findByName("Wallet2");
        System.out.println("Found Wallet: " + foundWalletByName);
        System.out.println();

        System.out.println("--------------------------------DELETE--------------------------------");
        boolean isDeleted = walletManagement.delete(createdWallet2.getId());
        System.out.println("Is Wallet id " + createdWallet2.getId() + " deleted: " + isDeleted);
        System.out.println("After deleting:");
        walletManagement.findAll().forEach(System.out::println);
        System.out.println();

        System.out.println("***********************************************************************");
        System.out.println("--------------------------------DEPOSIT---------------------------------");
        Transaction depositTransaction1 = transactionManagement.createDepositTransaction(createdWallet1.getId(), CryptoCurrency.BTC, BigDecimal.valueOf(100000), "Deposit in Wallet1");
        System.out.println("Created Transaction: " + depositTransaction1);
        Transaction depositTransaction2 = transactionManagement.createDepositTransaction(createdWallet1.getId(), CryptoCurrency.BTC, BigDecimal.valueOf(10), "Deposit in Wallet1");
        System.out.println("Created Transaction: " + depositTransaction2);
        Transaction depositTransaction3 = transactionManagement.createDepositTransaction(createdWallet1.getId(), CryptoCurrency.ETH, BigDecimal.valueOf(20000), "Deposit in Wallet1");
        System.out.println("Created Transaction: " + depositTransaction3);
        Transaction depositTransaction4 = transactionManagement.createDepositTransaction(createdWallet1.getId(), CryptoCurrency.BNB, BigDecimal.valueOf(10), "Deposit in Wallet1");
        System.out.println("Created Transaction: " + depositTransaction4);
        System.out.println();

        System.out.println("-------------------------------WITHDRAW--------------------------------");
        Transaction withdrawTransaction1 = transactionManagement.createWithdrawTransaction(createdWallet1.getId(), CryptoCurrency.BTC, BigDecimal.valueOf(1), "Withdraw in Wallet1");
        System.out.println("Created Transaction: " + withdrawTransaction1);
        Transaction withdrawTransaction2 = transactionManagement.createWithdrawTransaction(createdWallet1.getId(), CryptoCurrency.ETH, BigDecimal.valueOf(1), "Withdraw in Wallet1");
        System.out.println("Created Transaction: " + withdrawTransaction2);
        System.out.println();

        System.out.println("-------------------------FIND BY TRANSACTION ID------------------------");
        System.out.println("Transaction details of the id: " + depositTransaction3.getId());
        Transaction foundTransaction = transactionManagement.findById(depositTransaction3.getId());
        System.out.println("Found Transaction: " + foundTransaction);
        System.out.println();

        System.out.println("---------------------------FIND BY WALLET ID---------------------------");
        System.out.println("Transaction list for Wallet id: " + createdWallet1.getId());
        transactionManagement.getTransactionsByWalletId(createdWallet1.getId()).forEach(transaction -> System.out.println(transaction.toString()));
        System.out.println();

        System.out.println("***********************************************************************");
        System.out.println("-------------WALLET DETAILS AFTER PERFORMING TRANSACTIONS--------------");
        System.out.println(createdWallet1.getWalletName() + " details:");
        System.out.println(walletManagement.findById(createdWallet1.getId()));
    }
}
