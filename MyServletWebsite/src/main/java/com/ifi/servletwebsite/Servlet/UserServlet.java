package com.ifi.servletwebsite.Servlet;

import com.ifi.servletwebsite.Connection.DatabaseConnector;
import com.ifi.servletwebsite.Model.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//@WebServlet(name = "User", value = "/User")
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Connection connection = DatabaseConnector.initializeDatabase();
            ResultSet result = connection.createStatement().executeQuery("SELECT * FROM servlet.user");
            ArrayList<User> user = new ArrayList();
            while(result.next()) {
                int id = result.getInt("user_id");
                String username = result.getString("username");
                Date birthday =  result.getDate("birthday");
                String email = result.getString("email");
                String company = result.getString("company");
                String homeTown = result.getString("home_town");
                user.add(new User(id, username, birthday, email, company, homeTown));
            }
            request.setAttribute("userList", user);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }
        request.getRequestDispatcher("/resources/pages/User/userInfo.jsp").include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
