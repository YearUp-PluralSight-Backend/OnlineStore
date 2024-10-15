package com.pluralsight.utils;

import com.pluralsight.model.Product;

import java.util.Map;
import java.util.Scanner;

public class InputUtil {


    private static Scanner scanner = new Scanner(System.in);

    /**
     *
     * @param prompt for remind the user
     * @return string value
     */
    public static String promptForString(String prompt) {
        System.out.println(prompt);
        String value;
        try {
            value = scanner.nextLine().trim();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return value;
    }

    public static String promptForString() {
        String value;
        try {
            value = scanner.nextLine().trim();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return value;
    }

    public static int promptForInteger(String prompt) {
        System.out.println(prompt);
        int value;
        try {
            value = Integer.parseInt(scanner.nextLine().trim());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return value;
    }

    public static int promptForInteger() {
        int value;
        try {
            value = Integer.parseInt(scanner.nextLine().trim());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return value;
    }

    public static Double promptForDouble(String prompt) {
        System.out.println(prompt);
        double value;
        try {
            value = Double.parseDouble(scanner.nextLine().trim());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return value;
    }

    public static Double promptForDouble() {
        double value;
        try {
            value = Double.parseDouble(scanner.nextLine().trim());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return value;
    }

    public static short promptForShort(String prompt) {
        System.out.println(prompt);
        short value;
        try {
            value = Short.parseShort(scanner.nextLine().trim());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return value;
    }


    public static boolean checkId(String id, Map<String, Product> inventory) {

        if (id == null) {
            return false;  // Handle null ID if necessary
        }

        return  inventory.values().parallelStream().allMatch(product -> product.getSKU().equalsIgnoreCase(id));
    }

    public static String format(Product product) {
        String content =
                """
                |     %s     |     %s     |     %.2f     |     %s     |
                """.formatted(product.getSKU(), product.getProductName(), product.getPrice(), product.getDepartment());
        return content;
    }

    public static void lineFormat(){
        System.out.println("-----".repeat(20));
    }


    public static void listProducts(Map<String, Product> allProducts) {

        System.out.println("----".repeat(10) + "Inventory" + "----".repeat(10));
        System.out.println("|     SKU     |     ProductName     |     Price     |     Department     |");
        for(Product product: allProducts.values()) {
            InputUtil.format(product);
        }
        lineFormat();
    }
}