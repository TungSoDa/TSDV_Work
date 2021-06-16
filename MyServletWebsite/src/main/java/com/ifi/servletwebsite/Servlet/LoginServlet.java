package com.ifi.servletwebsite.Servlet;

import com.ifi.servletwebsite.Connection.DatabaseConnector;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

//@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.html").include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Connection connection = DatabaseConnector.initializeDatabase();
            ResultSet result = connection.createStatement().executeQuery("SELECT username, password FROM servlet.account");
            boolean check = false;
            while(result.next()) {
                if( request.getParameter("username").equals(result.getString("username"))
                        && request.getParameter("password").equals(result.getString("password")) ) {
                    check = true;
                    break;
                }
                else {
                    check = false;
                }
            }
            if (check) {
                new HomeServlet().doGet(request, response);
            }
            else {
                doGet(request, response);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }
    }
}
