package vendingmachine.domain;


import java.util.HashMap;
import java.util.Map;

public class VendingMachine {
    public static final int MIN_AMOUNT = 10;

    private static final String INVALID_AMOUNT_REMAINDER = MIN_AMOUNT + "원으로 나누어 떨어져야 합니다.";
    private static final String INVALID_RANGE = String.format("금액은 %d원 이상이어야 합니다.", MIN_AMOUNT);

    private final Map<Coin, Integer> coins = new HashMap<>();

    public VendingMachine(int amount) {
        validate(amount);
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

    private void validate(int amount) {
        validateRange(amount);
        validateRemainder(amount);
    }

    private static void validateRange(int amount) {
        if (amount < MIN_AMOUNT) {
            throw new IllegalArgumentException(INVALID_RANGE);
        }
    }

    private static void validateRemainder(int amount) {
        if (amount % MIN_AMOUNT != 0) {
            throw new IllegalArgumentException(INVALID_AMOUNT_REMAINDER);
        }
    }

    public int getCoinCount(Coin coin) {
        return coins.getOrDefault(coin, 0);
    }

    public Map<Coin, Integer> calculateChange(int amount) {
        Map<Coin, Integer> result = new HashMap<>();
        for (Coin coin : Coin.values()) {
            if (amount < Coin.COIN_10.getAmount()) {
                return result;
            }
            if (getCoinCount(coin) <= 0) {
                continue;
            }
            if ((coin.getAmount() * coins.get(coin)) <= amount) {
                result.put(coin, coins.get(coin));
                amount -= coin.getAmount() * coins.get(coin);
                continue;
            }
            result.put(coin, (amount / coin.getAmount()));
            amount %= coin.getAmount();
        }
        return result;
    }
}
