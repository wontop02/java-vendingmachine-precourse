package vendingmachine.domain;

import static camp.nextstep.edu.missionutils.Randoms.pickNumberInList;

import java.util.List;

public class CoinGenerator {
    public static int generate() {
        return pickNumberInList(
                List.of(Coin.COIN_500.getAmount(), Coin.COIN_100.getAmount(), Coin.COIN_50.getAmount(),
                        Coin.COIN_10.getAmount()));
    }
}
