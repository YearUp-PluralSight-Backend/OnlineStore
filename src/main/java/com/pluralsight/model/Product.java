package com.pluralsight.model;

import java.util.Objects;

public class Product {

    private String SKU;
    private String productName;
    private double price;
    private String department;

    public Product() {
    }

    public Product(String SKU, String productName, double price, String department) {
        this.SKU = SKU;
        this.productName = productName;
        this.price = price;
        this.department = department;
    }

    public String getSKU() {
        return SKU;
    }

    public void setSKU(String SKU) {
        this.SKU = SKU;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(getPrice(), product.getPrice()) == 0 && Objects.equals(getSKU(), product.getSKU()) && Objects.equals(getProductName(), product.getProductName()) && Objects.equals(getDepartment(), product.getDepartment());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSKU(), getProductName(), getPrice(), getDepartment());
    }

    @Override
    public String toString() {
        return "Product{" +
                "SKU='" + SKU + '\'' +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", department='" + department + '\'' +
                '}';
    }


}
