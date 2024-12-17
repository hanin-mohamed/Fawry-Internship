package com.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {
    public static List<Product> products = new ArrayList<>();


    // intial data
    @Override
    public void init() throws ServletException {
        super.init();
        products.add(new Product("Apple", 120));
        products.add(new Product("Phone", 8000));
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        out.println("<h2>Product List</h2>");
        out.println("<ul>");
        for (Product product : products) {
            out.println("<li>" + product.getName() + " - $" + product.getPrice()
                    + " | <a href='delete-product'>Delete</a>"
                    + " | <a href='update-product'>Update Price</a></li>");
        }
        out.println("</ul>");

        out.println("<h2><a href='add-product'>Add New Product</a></h2>");
    }
}
