package vendingmachine.domain;

import java.util.Arrays;

public enum Coin {
    COIN_500(500),
    COIN_100(100),
    COIN_50(50),
    COIN_10(10);
    
    private static final String INVALID_COIN = "코인을 찾을 수 없습니다.";

    private final int amount;

    Coin(final int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public static Coin valueOfAmount(int amount) {
        return Arrays.stream(values())
                .filter(c -> c.getAmount() == amount)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_COIN));
    }
}
