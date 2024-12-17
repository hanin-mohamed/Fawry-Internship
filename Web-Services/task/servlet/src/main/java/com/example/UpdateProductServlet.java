package com.example;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/update-product")
public class UpdateProductServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h1>Update Product Price</h1>");
        out.println("<form method='post'>"
                + "Product Name: <input type='text' name='name' required><br>"
                + "New Price: <input type='number' name='newPrice' min='0' step='0.01' required><br>"
                + "<input type='submit' value='Update Price'>"
                + "</form>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        double newPrice = Double.parseDouble(request.getParameter("newPrice"));

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        for (Product product : ProductServlet.products) {
            if (product.getName().equals(name)) {
                product.setPrice(newPrice);
                out.println("<h3>Product price updated successfully!</h3>");
                return;
            }
        }
        out.println("<h3>Product not found.</h3>");
        out.println("<a href='update-product'>Try Again</a>");
    }
}
