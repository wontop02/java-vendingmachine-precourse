package vendingmachine.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import vendingmachine.domain.Product;

public class ProductRepository {
    private static final String NOT_FOUND_PRODUCT = "해당 상품을 찾을 수 없습니다.";

    private static final List<Product> products = new ArrayList<>();

    public static List<Product> products() {
        return Collections.unmodifiableList(products);
    }

    public static void addProduct(Product product) {
        products.add(product);
    }

    public static Product findByName(String name) {
        return products.stream()
                .filter(p -> p.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_PRODUCT));
    }

    public static boolean allSoldOut() {
        return products.stream()
                .allMatch(p -> p.getQuantity() < 0);
    }

    public static int minPrice() {
        return products.stream()
                .mapToInt(Product::getPrice)
                .min()
                .orElse(0);
    }
}
