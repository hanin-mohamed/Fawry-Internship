package com.example;

import java.io.*;
import java.util.List;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/add-product")
public class AddProductServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h1>Add New Product</h1>");
        out.println("<form method='post'>"
                + "Product Name: <input type='text' name='name' maxlength='100' required><br>"
                + "Product Price: <input type='number' name='price' min='0' step='0.01' required><br>"
                + "<input type='submit' value='Add Product'>"
                + "</form>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));

        List<Product> productList = ProductServlet.products;

        boolean exists = productList.stream().anyMatch(p -> p.getName().equalsIgnoreCase(name));

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (exists) {
            out.println("<h3 style='color:red;'>Product name already exists! Try another name.</h3>");
            out.println("<a href='add-product'>Go Back</a>");
        } else {
            Product product = new Product(name, price);
            productList.add(product);
            response.sendRedirect("products");
        }
    }
}
