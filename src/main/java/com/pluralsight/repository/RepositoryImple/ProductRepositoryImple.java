package com.pluralsight.repository.RepositoryImple;

import com.pluralsight.model.Product;
import com.pluralsight.repository.ProductRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.pluralsight.utils.InputUtil.checkId;

public class ProductRepositoryImple implements ProductRepository {

    private Map<String, Product> inventory = getInventory();

    /**
     * @return
     */
    @Override
    public Map<String, Product> getAllProducts() {
        return inventory;
    }

    /**
     * @param name
     * @return
     */
    @Override
    public Optional<Product> getProductByName(Optional<String> name) {

            if (!name.isPresent()) return Optional.empty();

            for (Product product: inventory.values()) {
                if (product.getProductName().equalsIgnoreCase(name.get())) {
                    return Optional.of(product);
                }
            }

        return Optional.empty();
    }

    /**
     * @param name
     * @return
     */
    @Override
    public Optional<Product> getProductByDepartment(Optional<String> name) {

        if (!name.isPresent()) return Optional.empty();

        for (Product product: inventory.values()) {
            if (product.getDepartment().equalsIgnoreCase(name.get())) {
                return Optional.of(product);
            }
        }

        return Optional.empty();
    }

    /**
     * @param min
     * @param max
     * @return
     */
    @Override
    public Optional<HashMap<String, Product>> getProductByPriceRange(Optional<Double> min, Optional<Double> max) {

        HashMap<String, Product> newInventory = new HashMap<>();

        for (Product product: inventory.values()) {

            if (product.getPrice() > min.get() && product.getPrice() < max.get()) {
                newInventory.put(product.getSKU(), product);
            }
        }
        return Optional.ofNullable(newInventory);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Optional<Product> getProductById(Optional<String> id) {

        boolean validId = checkId(id.get(), inventory);
        if (!validId) return Optional.empty();

        Product product = inventory.get(id.get());
        return Optional.of(product);
    }

    /**
     * @param product
     * @return
     */
    @Override
    public Optional<Product> addNewProduct(Optional<Product> product) {

        boolean validId = checkId(product.get().getSKU(), inventory);
        if (!validId) {
            return Optional.empty();

        }

        inventory.put(product.get().getSKU(), product.get());
        UpdatetheCSVFile(inventory);
        return product;


    }

    /**
     * @param id
     * @param newProduct
     * @return
     */
    @Override
    public Optional<Product> updateProduct(Optional<String> id, Optional<Product> newProduct) {

        boolean validId = checkId(id.get(), inventory);

        if (!validId) return  Optional.empty();
        Product oldProduct = inventory.get(id.get());
        oldProduct.setProductName(newProduct.get().getProductName());
        oldProduct.setPrice(newProduct.get().getPrice());
        oldProduct.setDepartment(newProduct.get().getDepartment());
        UpdatetheCSVFile(inventory);
        return newProduct;
    }

    /**
     * @param id
     */
    @Override
    public boolean deleteProductById(Optional<String> id) {

        boolean validId = checkId(id.get(), inventory);
        if (!validId) return false;
        inventory.remove(id.get());
        UpdatetheCSVFile(inventory);
        return true;

    }

}
