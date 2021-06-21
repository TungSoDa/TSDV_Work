package com.ifi.servletwebsite.Servlet;

import com.ifi.servletwebsite.Connection.DatabaseConnector;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//@WebServlet(name = "Registration", urlPatterns = {"/Registration"})
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/resources/pages/Login/registration.jsp").include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String createUsername = request.getParameter("createUsername");
            String createPassword = request.getParameter("createPassword");
            String confirmPassword = request.getParameter("confirmPassword");

            Connection connection = DatabaseConnector.initializeDatabase();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO servlet.account VALUES (default, ?, ?)");

            if (createPassword.equals(confirmPassword)) {
                preparedStatement.setString(1, createUsername);
                preparedStatement.setString(2, createPassword);
                preparedStatement.executeUpdate();

                preparedStatement.close();
                connection.close();

                new LoginServlet().doGet(request, response);
            }
            else {
                preparedStatement.close();
                connection.close();

                doGet(request, response);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }
    }
}
