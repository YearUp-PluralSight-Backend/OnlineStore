package com.pluralsight.service;

import com.pluralsight.model.Product;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public interface ProductService {


    Map<String, Product> getAllProducts();
    Product getProductByName(Optional<String> name);
    Product getProductByDepartment(Optional<String> name);
    HashMap<String, Product> getProductByPriceRange(Optional<Double> min, Optional<Double> max);
    Product getProductById(Optional<String> id);
    Product addNewProduct(Optional<Product> product);
    Product updateProduct(Optional<String> id, Optional<Product> newProduct);
    boolean  deleteProductById(Optional<String> id);
}
