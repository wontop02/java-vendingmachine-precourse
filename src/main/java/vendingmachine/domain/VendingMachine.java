package vendingmachine.domain;

import java.util.HashMap;
import java.util.Map;

public class VendingMachine {
    private final Map<Coin, Integer> coins = new HashMap<>();

    public VendingMachine(int amount) {
        generateCoins(amount);
    }

    private void generateCoins(int amount) {
        while (amount > 0) {
            int coin = CoinGenerator.generate();
            if (coin > amount) {
                continue;
            }
            coins.put(Coin.valueOfAmount(coin), coins.getOrDefault(Coin.valueOfAmount(coin), 0) + 1);
            amount -= coin;
        }
    }

    public int getCoinCount(Coin coin) {
        return coins.getOrDefault(coin, 0);
    }
}
