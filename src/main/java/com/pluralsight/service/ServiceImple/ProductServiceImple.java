package com.pluralsight.service.ServiceImple;

import com.pluralsight.model.Product;
import com.pluralsight.repository.ProductRepository;
import com.pluralsight.repository.RepositoryImple.ProductRepositoryImple;
import com.pluralsight.service.ProductService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ProductServiceImple implements ProductService {


    private ProductRepository productRepository = new ProductRepositoryImple();

    private ProductServiceImple() {}

    public static ProductServiceImple getInstance() {
        return  new ProductServiceImple();
    }

    /**
     * @return
     */
    @Override
    public Map<String, Product> getAllProducts() {
        Map<String, Product> allProducts = productRepository.getAllProducts();
        if (allProducts.isEmpty()) {
            return null;
        }
        return allProducts;
    }

    /**
     * @param name
     * @return
     */
    @Override
    public Product getProductByName(Optional<String> name) {
        Optional<Product> productByName = productRepository.getProductByName(name);
        if (!productByName.isPresent()) return null;

        return productByName.get();

    }

    /**
     * @param name
     * @return
     */
    @Override
    public Product getProductByDepartment(Optional<String> name) {
        Optional<Product> productByDepartment = productRepository.getProductByDepartment(name);
        if (!productByDepartment.isPresent()) return null;
        return productByDepartment.get();
    }

    /**
     * @param min
     * @param max
     * @return
     */
    @Override
    public HashMap<String, Product> getProductByPriceRange(Optional<Double> min, Optional<Double> max) {
        Optional<HashMap<String, Product>> productByPriceRange = productRepository.getProductByPriceRange(min, max);
        if (!productByPriceRange.isPresent()) return null;
        return productByPriceRange.get();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Product getProductById(Optional<String> id) {
        Optional<Product> productById = productRepository.getProductById(id);
        if (!productById.isPresent()) return null;
        return productById.get();
    }

    /**
     * @param product
     * @return
     */
    @Override
    public Product addNewProduct(Optional<Product> product) {
        Optional<Product> newProduct = productRepository.addNewProduct(product);
        if (!newProduct.isPresent()) return null;
        return newProduct.get();
    }

    /**
     * @param id
     * @param newProduct
     * @return
     */
    @Override
    public Product updateProduct(Optional<String> id, Optional<Product> newProduct) {
        Optional<Product> product = productRepository.updateProduct(id, newProduct);
        if (!product.isPresent()) return null;
        return product.get();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public boolean deleteProductById(Optional<String> id) {
        return productRepository.deleteProductById(id);
    }
}
