package vendingmachine.view;

public class OutputView {
    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String PRINT_COIN_START = "자판기가 보유한 동전";
    private static final String PRINT_COIN = "%d원 - %d개";
    private static final String PRINT_INPUT_AMOUNT = "투입 금액: %d원";

    public void printErrorMessage(String message) {
        System.out.println(ERROR_PREFIX + message);
        System.out.println();
    }

    public void printCoinStart() {
        System.out.println(PRINT_COIN_START);
    }

    public void printCoin(int amount, int count) {
        System.out.printf(PRINT_COIN, amount, count);
        System.out.println();
    }

    public void printInputAmount(int amount) {
        System.out.printf(PRINT_INPUT_AMOUNT, amount);
        System.out.println();
    }
}
