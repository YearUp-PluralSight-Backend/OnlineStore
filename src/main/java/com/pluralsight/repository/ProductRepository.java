package com.pluralsight.repository;

import com.pluralsight.model.Product;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public interface ProductRepository {

    String FILENAME = "inventory.csv";
    Map<String, Product> getAllProducts();
    Optional<Product> getProductByName(Optional<String> name);
    Optional<Product> getProductByDepartment(Optional<String> name);
    Optional<HashMap<String, Product>> getProductByPriceRange(Optional<Double> min, Optional<Double> max);
    Optional<Product> getProductById(Optional<String> id);
    Optional<Product> addNewProduct(Optional<Product> product);
    Optional<Product> updateProduct(Optional<String> id, Optional<Product> newProduct);
    boolean  deleteProductById(Optional<String> id);

    /**
     * read the file inventory.csv
     * get the content and split them by |.
     * create a {@code Product} to store the data
     * add the {@code Product} to the inventory list
     *
     * @return
     */
    default Map<String, Product>  getInventory() {
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

    default void UpdatetheCSVFile(Map<String, Product> inventory) {

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("inventory.csv", true))) {

            for (Product product: inventory.values()) {
                StringBuilder stringBuilder = new StringBuilder();
                String line = stringBuilder.append("\n")
                        .append(product.getSKU())
                        .append("|")
                        .append(product.getProductName())
                        .append("|")
                        .append(product.getPrice())
                        .append("|")
                        .append(product.getDepartment())
                        .toString();
                bufferedWriter.write(line);
                bufferedWriter.flush();

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
