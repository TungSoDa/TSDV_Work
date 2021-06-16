package com.ifi.servletwebsite.Servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;

//@WebServlet(name = "Home", urlPatterns = {"/Home"})
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("home.html").include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
