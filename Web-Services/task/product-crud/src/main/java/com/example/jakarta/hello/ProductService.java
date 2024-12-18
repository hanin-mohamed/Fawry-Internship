package com.example.jakarta.hello;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductService {
    private static List<Product> products = new ArrayList<>();
    {
        products.add(new Product("Phone", 100.0));
        products.add(new Product("Laptop", 450.0));
        products.add(new Product("Tablet", 1200.0));
    }

    // All Products
    public List<Product> getProducts() {
        return products;
    }

    // Add Product
    public String addProduct(String name, double  price) {
        if (findProductByName(name)==null) {
            products.add(new Product(name, price));
            return "Product added successfully";
        }
        return "Product already exists";
    }

    // Delete Product
    public String deleteProductByName(String name) {
        Product product = findProductByName(name);
        if (product != null) {
            products.remove(product);
            return "Product deleted successfully";
        }
        return "Product not found";
    }

    // Search for Product
    public Product findProductByName(String name) {
        return products.stream()
                .filter(product -> product.getName().equals(name))
                .findFirst().orElse(null);
    }

    public String updateProduct(Product product) {
        Product exproduct = findProductByName(product.getName());
        if (exproduct != null) {
            exproduct.setPrice(product.getPrice());
            return "Product updated successfully";
        }
        return "Product not found";
    }

}
