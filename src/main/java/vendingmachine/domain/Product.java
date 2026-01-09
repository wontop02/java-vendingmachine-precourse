package vendingmachine.domain;

import static vendingmachine.constant.VendingMachineConstant.MIN_PRODUCT_PRICE;
import static vendingmachine.constant.VendingMachineConstant.MIN_PRODUCT_QUANTITY;
import static vendingmachine.domain.VendingMachine.MIN_AMOUNT;

public class Product {
    private static final String INVALID_AMOUNT_REMAINDER = MIN_AMOUNT + "원으로 나누어 떨어져야 합니다.";
    private static final String INVALID_RANGE = String.format("상품 금액은 %d원 이상이어야 합니다.", MIN_PRODUCT_PRICE);
    private static final String INVALID_QUANTITY = String.format("상품 수량은 %d개 이상이어야 합니다.", MIN_PRODUCT_QUANTITY);

    private final String name;
    private final int price;
    private int quantity;

    public Product(String name, int price, int quantity) {
        validate(price, quantity);
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    private void validate(int price, int quantity) {
        validatePrice(price);
        validateQuantity(quantity);
    }

    private void validatePrice(int price) {
        if (price < MIN_PRODUCT_PRICE) {
            throw new IllegalArgumentException(INVALID_RANGE);
        }
        if (price % MIN_AMOUNT != 0) {
            throw new IllegalArgumentException(INVALID_AMOUNT_REMAINDER);
        }
    }

    private void validateQuantity(int quantity) {
        if (quantity < MIN_PRODUCT_QUANTITY) {
            throw new IllegalArgumentException(INVALID_QUANTITY);
        }
    }
}
