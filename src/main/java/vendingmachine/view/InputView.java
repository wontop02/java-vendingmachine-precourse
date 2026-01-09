package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static final String REQUEST_AMOUNT = "자판기가 보유하고 있는 금액을 입력해 주세요.";
    private static final String REQUEST_PRODUCTS = "상품명과 가격, 수량을 입력해 주세요.";
    private static final String REQUEST_INPUT_AMOUNT = "투입 금액을 입력해 주세요.";

    public String readAmount() {
        System.out.println(REQUEST_AMOUNT);
        return Console.readLine();
    }

    public String readProducts() {
        System.out.println(REQUEST_PRODUCTS);
        return Console.readLine();
    }

    public String readInputAmount() {
        System.out.println(REQUEST_INPUT_AMOUNT);
        return Console.readLine();
    }
}
