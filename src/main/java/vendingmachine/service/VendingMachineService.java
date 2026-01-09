package vendingmachine.service;

import java.util.Arrays;
import java.util.List;
import vendingmachine.domain.InputAmount;
import vendingmachine.domain.Product;
import vendingmachine.domain.VendingMachine;
import vendingmachine.repository.ProductRepository;

public class VendingMachineService {
    private static final String ELIMINATE_FORMAT = "[\\[\\]]";
    private static final String PRODUCT_SEPARATOR = ";";
    private static final String PRODUCT_INNER_SEPARATOR = ",";

    private static final String NOT_ENOUGH_INPUT_AMOUNT = "돈이 충분하지 않습니다.";
    private static final String NOT_ENOUGH_QUANTITY = "재고가 품절되었습니다.";

    public VendingMachine makeVendingMachine(String input) {
        return new VendingMachine(Integer.parseInt(input));
    }

    public void addProducts(String input) {
        List<String> products = Arrays.asList(input.split(PRODUCT_SEPARATOR, -1));
        for (String product : products) {
            product = product.replaceAll(ELIMINATE_FORMAT, "");
            List<String> tokens = Arrays.asList(product.split(PRODUCT_INNER_SEPARATOR, -1));
            String name = tokens.get(0);
            int price = Integer.parseInt(tokens.get(1));
            int quantity = Integer.parseInt(tokens.get(2));
            ProductRepository.addProduct(new Product(name, price, quantity));
        }
    }

    public InputAmount makeInputAmount(String input) {
        return new InputAmount(Integer.parseInt(input));
    }

    public void buyProduct(String name, InputAmount inputAmount) {
        Product product = ProductRepository.findByName(name);
        if (product.getPrice() > inputAmount.getAmount()) {
            throw new IllegalArgumentException(NOT_ENOUGH_INPUT_AMOUNT);
        }
        if (product.getQuantity() < 1) {
            throw new IllegalArgumentException(NOT_ENOUGH_QUANTITY);
        }
        product.minusQuantity();
        inputAmount.minusAmount(product.getPrice());
    }

    public boolean isEnd(InputAmount inputAmount) {
        if (ProductRepository.allSoldOut()) {
            return true;
        }
        return ProductRepository.minPrice() > inputAmount.getAmount();
    }
}
