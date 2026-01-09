package vendingmachine.service;

import java.util.Arrays;
import java.util.List;
import vendingmachine.domain.Product;
import vendingmachine.domain.VendingMachine;
import vendingmachine.repository.ProductRepository;

public class VendingMachineService {
    private static final String ELIMINATE_FORMAT = "[\\[\\]]";
    private static final String PRODUCT_SEPARATOR = ";";
    private static final String PRODUCT_INNER_SEPARATOR = ",";

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
}
