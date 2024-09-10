package se.lexicon.model;

public enum CryptoCurrency {
    BTC("Bitcoin"), ETH("Ethereum"), USDT("TetherUS"), BNB("BNB");

    private final String name;

    CryptoCurrency(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}