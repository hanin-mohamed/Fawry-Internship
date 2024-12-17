package com.example.jakarta.hello;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductRepository {
    private static List<Product> products = new ArrayList<>();


    // All Products
    public List<Product> getProducts() {
        return products;
    }
    // Add Product
    public String addProduct(Product product) {
        if (findProductByName(product.getName())==null) {
            products.add(product);
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
                .filter(product -> product.getName() == name)
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
