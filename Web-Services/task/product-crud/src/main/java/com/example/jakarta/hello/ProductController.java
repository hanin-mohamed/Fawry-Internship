package com.example.jakarta.hello;


import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/products")
public class ProductController {
    private final ProductRepository productRepository = new ProductRepository();


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getProducts() {
        return productRepository.getProducts();
    }

    @POST
    @Path("/addProduct")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void addNewProduct(Product product) {
        productRepository.addProduct(product);
    }


    @DELETE
    @Path("/delete/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteProductByName(@PathParam("name") String name) {
        Product product = productRepository.getProducts().stream().filter(p -> p.getName() == name).findFirst().orElse(null);
        if (product != null) {
            productRepository.deleteProductByName(name);
        }
    }
    @POST
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String updateProduct(Product product) {
        return productRepository.updateProduct(product);
    }

    @GET
    @Path("/find/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Product searchProduct(@PathParam("name") String name) {
        return productRepository.findProductByName(name);
    }
}

