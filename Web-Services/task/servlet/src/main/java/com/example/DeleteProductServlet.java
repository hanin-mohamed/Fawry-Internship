package com.example;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
@WebServlet("/delete-product")
public class DeleteProductServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<h1>Delete Product</h1>");
        out.println("<form method='post'>"
                + "Product Name: <input type='text' name='name' required><br>"
                + "<input type='submit' value='Delete Product'>"
                + "</form>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (ProductServlet.products.removeIf(product -> product.getName().equals(name))) {
            out.println("<h3>Product deleted successfully!</h3>");
        } else {
            out.println("<h3>Product not found.</h3>");
        }

        out.println("<a href='delete-product'>Delete Another Product</a>");
    }
}
