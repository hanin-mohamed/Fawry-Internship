package com.example.jakarta.hello;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/products")
public class ProductController {
    private final ProductService productService = new ProductService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProducts() {
        return Response.ok(productService.getProducts()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addNewProduct(Product product) {
        String response = productService.addProduct(product.getName(), product.getPrice());
        if (response.equals("Product added successfully")) {
            return Response.status(Response.Status.CREATED).entity(response).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity(response).build();
    }

    @DELETE
    @Path("/delete/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProductByName(@PathParam("name") String name) {
        String response = productService.deleteProductByName(name);
        if (response.equals("Product deleted successfully")) {
            return Response.ok(response).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity(response).build();
    }

    @PUT
    @Path("/update}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProduct(Product product) {
        String response = productService.updateProduct(product);
        if (response.equals("Product updated successfully")) {
            return Response.ok(response).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity(response).build();
    }

    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchProduct(@PathParam("name") String name) {
        Product product = productService.findProductByName(name);
        if (product != null) {
            return Response.ok(product).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Product not found").build();
    }
}
