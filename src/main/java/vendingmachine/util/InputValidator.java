package vendingmachine.util;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class InputValidator {
    private static final String ONLY_DIGIT_REGEX = "^[0-9]+$";
    private static final String ELIMINATE_FORMAT = "[\\[\\]]";
    private static final String PRODUCT_REGEX = "^[가-힣,A-Z,a-z]+,\\d+,\\d+";
    private static final String PRODUCT_SEPARATOR = ";";
    private static final String PRODUCT_INNER_SEPARATOR = ",";

    private static final String BLANK_INPUT = "빈 문자열이 입력되었습니다.";
    private static final String NOT_ONLY_DIGIT = "숫자를 제외한 문자가 포함되어 있습니다.";
    private static final String INVALID_INT_RANGE = "입력된 숫자가 범위를 초과했습니다.";
    private static final String INVALID_FORMAT = "입력 형식이 올바르지 않습니다.";
    private static final String DUPLICATED_PRODUCT_NAME = "중복된 상품명이 존재합니다.";

    private InputValidator() {
    }

    public static void validateAmount(String input) {
        validateNotBlank(input);
        validateOnlyDigit(input);
        validateWithinIntRange(input);
    }

    private static void validateNotBlank(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(BLANK_INPUT);
        }
    }

    private static void validateOnlyDigit(String input) {
        if (!input.matches(ONLY_DIGIT_REGEX)) {
            throw new IllegalArgumentException(NOT_ONLY_DIGIT);
        }
    }

    private static void validateWithinIntRange(String input) {
        BigInteger value = new BigInteger(input);
        if (value.compareTo(BigInteger.valueOf(Integer.MIN_VALUE)) < 0
                || value.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) > 0) {
            throw new IllegalArgumentException(INVALID_INT_RANGE);
        }
    }

    public static void validateProducts(String input) {
        validateNotBlank(input);
        List<String> products = Arrays.asList(input.split(PRODUCT_SEPARATOR, -1));
        List<String> names = new ArrayList<>();
        for (String product : products) {
            validateProduct(product, names);
        }
        validateNotDuplicate(names);
    }

    private static void validateProduct(String input, List<String> names) {
        input = input.replaceAll(ELIMINATE_FORMAT, "");
        validateProductFormat(input);
        List<String> inputs = Arrays.asList(input.split(PRODUCT_INNER_SEPARATOR, -1));
        names.add(inputs.get(0));
        validateWithinIntRange(inputs.get(1));
        validateWithinIntRange(inputs.get(2));
    }

    private static void validateProductFormat(String input) {
        if (!input.matches(PRODUCT_REGEX)) {
            throw new IllegalArgumentException(INVALID_FORMAT);
        }
    }

    private static void validateNotDuplicate(List<String> inputs) {
        if (new HashSet<>(inputs).size() != inputs.size()) {
            throw new IllegalArgumentException(DUPLICATED_PRODUCT_NAME);
        }
    }
}
