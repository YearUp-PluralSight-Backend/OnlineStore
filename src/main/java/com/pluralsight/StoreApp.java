package com.pluralsight;

import com.pluralsight.utils.InputUtil;
import com.pluralsight.model.Product;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * read the files and display the data
 * one static variable and three static methods.
 */
public class StoreApp {

    final static String FILENAME = "inventory.csv";
    static Map<String, Product> inventory = getInventory();

    public static void main(String[] args) {

        homeScreen();
    }

    /**
     * start the program from here
     * a while loop to give user options
     */
    private static void homeScreen() {

        try {
            boolean flag = true;
            while (flag) {
                PrintMenu();
                short command = InputUtil.promptForShort("Enter your command: ");
                switch (command) {
                    case 1 -> dislpayProducts(inventory);
                    case 2 -> loopUpById();
                    case 3 -> lookUpByPriceRange();
                    case 4 -> addNewProduct();
                    case 5 -> deleteProductById();
                    case 6 -> updateProductById();
                    case 7 -> {
                        flag = false;
                        System.out.println("Thank you for using our application! have a great day.");
                    }
                    default -> System.out.println("Invalid option! Please try it again.");
                }
                format();
            }
        } catch (Exception e) {

        }

    }

    /**
     * print the menu option
     */
    private static void PrintMenu() {
        String info =
                """
                        1 - Display the products
                        2 - Look up product by id
                        3 - Look up product by price range
                        4 - Add new product
                        5 - Delete product By Id
                        6 - Update product by Id
                        7 - Exit
                        """;
        System.out.println(info);
    }

    /**
     * lookup the product based on the price range.
     */
    private static void lookUpByPriceRange() {

        HashMap<String, Product> productList = new HashMap<>();
        double startPrice = InputUtil.promptForDouble("Enter your start price : ");
        double endPrice = InputUtil.promptForDouble("Enter your end price : ");

        for (String key: inventory.keySet()) {
            Product product = inventory.get(key);

            if (product == null) {
                System.out.println("NullPointerException! ");
                return;
            }
            if (product.getPrice() >= startPrice && product.getPrice() < endPrice) {

                productList.put(product.getSKU(), product);
            }
        }

        dislpayProducts(productList);
    }

    /**
     * ask user id and print out the product based on the id.
     */
    private static void loopUpById() {
        String id = InputUtil.promptForString("Enter the product Id: ");

        Product product = inventory.get(id);
        if (product.getSKU().equalsIgnoreCase(id)) {
            System.out.println(product.toString());
        } else {
            System.out.println("Not found!");
        }

    }

    /**
     * add new product and then write the product into csv
     */
    private static void addNewProduct() {

        String[] productData = productDataInput();
        String id = productData[0];
        String name =  productData[1];
        double price = Double.parseDouble(productData[2]);
        String department = productData[3];

        Product product = new Product(id, name, price, department);
        inventory.put(id, product);
        updateCSV();

        System.out.println("You have successfully added new product: " + product.toString());
    }

    /**
     * update the product information by id
     */
    private static void updateProductById() {

        int id = InputUtil.promptForInteger("Enter the product id: ");
        Product product = inventory.get(id);
        if (product == null) {
            System.out.println("ID is not found!");
            return;
        }
        String[] productData = productDataInput();
        String newId =  productData[0];
        String newName = (String) productData[1];
        double newPrice = Double.parseDouble(productData[2]);
        String department = productData[3];

        product.setSKU(newId);
        product.setProductName(newName);
        product.setPrice(newPrice);
        product.setDepartment(department);
        updateCSV();

        System.out.println("You have updated the product: " + product.toString());
    }

    /**
     * delete the product by product id
     *
     * @return true if it found by id and deleted.
     */
    private static boolean deleteProductById() {
        int id = InputUtil.promptForInteger("Enter the product id: ");
        Product product = inventory.get(id);
        if (product != null) {
            System.out.println("Not found!");
            return false;
        }
        product = inventory.get(id);
        System.out.println(product.toString());

        updateCSV();
        return true;
    }

    /**
     * ask user to input the product information, id name, price
     *
     * @return id, name, price
     */
    private static String[] productDataInput() {
        String id = InputUtil.promptForString("Enter product id: ");

        boolean valid = checkId(id);
        while (!valid) {
            System.out.println("You have entered the duplicate ID.");
            id = InputUtil.promptForString("Enter product id: ");
            valid = checkId(id);
        }

        String name = InputUtil.promptForString("Enter product name: ");
        String price = InputUtil.promptForString("Enter the product price: ");
        String department = InputUtil.promptForString("Enter the department: ");
        return new String[]{id, name, price, department};
    }

    /**
     * give a new line with 100 -
     */
    private static void format() {
        System.out.println("-".repeat(100));
    }
    /**
     * write the product to csv file
     *
     * @param product new product
     * @return true or false depends on whether it write successful or not.
     */
    private static void writeProductToCSV(Product product, boolean overWrite) {

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("inventory.csv", overWrite))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line = stringBuilder.append("\n").append(product.getSKU()).append("|").append(product.getProductName()).append("|").append(product.getPrice()).append("|" ).append(product.getDepartment()).toString();
            bufferedWriter.write(line);
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * check the id in the inventory
     *
     * @param id product id
     * @return return true if the id is not in the inventory.
     */
    private static boolean checkId(String id) {
        Product product = inventory.get(id);
        if (product == null) {
            return false;
        }
        return true;
    }

    /**
     * update the csv file
     */
    private static void updateCSV() {
        for(String key: inventory.keySet()) {
            Product newProduct = inventory.get(key);

            if (newProduct == null) {
                System.out.println("Failed to update the csv file due to NullPointException");
                return;
            }
            writeProductToCSV(newProduct, true);
        }

        System.out.println("You have successfully update the inventory.csv");
    }

    /**
     * start point of the program!
     * get the inventory and loop it to display them.
     */
    private static void dislpayProducts(Map<String, Product> inventory) {
        try {
            System.out.println("We carry the following inventory: ");

            for (String key : inventory.keySet()) {

                Product p = inventory.get(key);
                if (p == null) {
                    System.out.println("NullPointException! ");
                    return;
                }
                System.out.printf("id: %d - name: %s - Price: $%.2f  -  Department: %s\n",
                        p.getSKU(), p.getProductName(), p.getPrice(), p.getDepartment());
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * read the file inventory.csv
     * get the content and split them by |.
     * create a {@code Product} to store the data
     * add the {@code Product} to the inventory list
     *
     * @return
     */
    private static Map<String, Product> getInventory() {
        Map<String, Product> inventory = new HashMap<>(); // create a list to store the products

        // read the file and add the content to the inventory list.  (try-catch resource release)
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {

                String[] lineContent = line.split("\\|");
                inventory.putIfAbsent(lineContent[0], new Product(lineContent[0], lineContent[1], Double.parseDouble(lineContent[2]), lineContent[3]));

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return inventory;
    }
}