package com.pluralsight.controller;

import com.pluralsight.model.Product;
import com.pluralsight.service.ProductService;
import com.pluralsight.service.ServiceImple.ProductServiceImple;
import com.pluralsight.utils.InputUtil;

import java.util.Map;
import java.util.Optional;

import static com.pluralsight.utils.InputUtil.*;


public class ProductController {


    private ProductService productService = ProductServiceImple.getInstance();

    /**
     * display all the Products
     */
    void displayAllProducts() {
        Map<String, Product> allProducts = productService.getAllProducts();
        listProducts(allProducts);
    }

    /**
     * search product by product's name
     */
    void getProductByName() {

        String command = "";
        while (!command.equalsIgnoreCase("E")) {
            command = InputUtil.promptForString("Enter the product name: ");
            Product productByName = productService.getProductByName(Optional.of(command));
            if (productByName != null) {
                InputUtil.format(productByName);
                lineFormat();
                return;
            } else {
                System.out.println("Invalid Name! Please try it again.");
            }
        }

    }

    /**
     * search product by product's departmentName
     */
    void getProductByDepartment() {

        String command = "";
        while (!command.equalsIgnoreCase("E")) {
            command = InputUtil.promptForString("Enter the department name: ");
            Product departmentByName = productService.getProductByDepartment(Optional.of(command));
            if (departmentByName != null) {
                InputUtil.format(departmentByName);
                lineFormat();
                return;
            } else {
                System.out.println("Invalid Name! Please try it again.");
            }
        }

    }

    /**
     * search the product price range
     */
    void getProductByPriceRange() {

        Double min = promptForDouble("Enter the min value: ");
        Double max = promptForDouble("Enter the max value: ");


        Map<String, Product> allProducts = productService.getProductByPriceRange(Optional.of(min), Optional.of(max));
        listProducts(allProducts);
    }

    /**
     * search product by id
     */
    void getProductById() {

        String command = "";
        while (!command.equalsIgnoreCase("E")) {
            command = InputUtil.promptForString("Enter the product SKU: ");
            Product departmentById = productService.getProductById(Optional.of(command));
            if (departmentById != null) {
                InputUtil.format(departmentById);
                lineFormat();
                return;
            } else  {
                System.out.println("Invalid ID! Please try it again.");
            }
        }
    }

    /**
     * add new product
     */
    void addNewProduct() {

        String command = "";
        while (!command.equalsIgnoreCase("E")) {

            String id = promptForString("Enter the product SKU: ");
            String productName = promptForString("Enter the product name: ");
            String departmentName = promptForString("Enter the product department: ");
            Double price = promptForDouble("Enter the price: ");
            Optional<Product> product = Optional.of(new Product(id, productName, price, departmentName));
            Product newProduct = productService.addNewProduct(product);
            if (newProduct != null) {
                System.out.println("You have successfully added the " + productService.getProductById(Optional.of(id)));
                lineFormat();
                return;
            } else  {
                System.out.println("Invalid ID! Please try it again.");
            }
            command = InputUtil.promptForString("Would you like to exit or not (E == Exit): ");

        }
    }

    /**
     * update product by ID
     */
    void updateProductById() {
        String command = "";
        while (!command.equalsIgnoreCase("E")) {

            String oldId = promptForString("Enter the old product ID: ");
            String id = promptForString("Enter the product SKU: ");
            String productName = promptForString("Enter the product name: ");
            String departmentName = promptForString("Enter the product department: ");
            Double price = promptForDouble("Enter the price: ");
            Optional<Product> product = Optional.of(new Product(id, productName, price, departmentName));

            command = InputUtil.promptForString("Enter the product SKU: ");
            Product updateProduct = productService.updateProduct(Optional.of(oldId), product);
            if (updateProduct != null) {
                System.out.println("You have successfully updated the " + productService.getProductById(Optional.of(command)));
                lineFormat();
                return;
            } else  {
                System.out.println("Invalid ID! Please try it again.");
            }
        }
    }

    /**
     * delete product by ID
     */
    void deleteProductById() {
        String command = "";
        while (!command.equalsIgnoreCase("E")) {
            command = InputUtil.promptForString("Enter the product SKU: ");
            Boolean deleteProductById = productService.deleteProductById(Optional.of(command));
            if (deleteProductById != false) {
                System.out.println("You have successfully deleted the " + productService.getProductById(Optional.of(command)));
                lineFormat();
                return;
            } else  {
                System.out.println("Invalid ID! Please try it again.");
            }
        }
    }

}
