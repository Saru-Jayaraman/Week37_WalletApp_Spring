package se.lexicon.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Bean;
//import se.lexicon.dao.impl.TransactionDaoImpl;
//import se.lexicon.dao.impl.WalletDaoImpl;
//import se.lexicon.service.impl.TransactionManagementImpl;
//import se.lexicon.service.impl.WalletManagementImpl;

@Configuration
@ComponentScan(basePackages = "se.lexicon")
public class AppConfig {
//    @Bean
//    public TransactionDaoImpl transactionDao() {
//        return new TransactionDaoImpl();
//    }
//
//    @Bean
//    public WalletDaoImpl walletDao() {
//        return new WalletDaoImpl();
//    }
//
//    @Bean
//    public TransactionManagementImpl transactionManagement() {
//        return new TransactionManagementImpl(transactionDao(), walletDao());
//    }
//
//    @Bean
//    public WalletManagementImpl walletManagement() {
//        return new WalletManagementImpl(walletDao());
//    }
}
