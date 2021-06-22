package com.ifi.servletwebsite.Servlet;

import com.ifi.servletwebsite.Connection.DatabaseConnector;
import com.ifi.servletwebsite.Model.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

//@WebServlet(name = "User", value = "/User")
public class UserServlet extends HttpServlet {

    protected void showDataTable(HttpServletRequest request) {
        try {
            Connection connection = DatabaseConnector.initializeDatabase();
            ResultSet result = connection.createStatement().executeQuery("SELECT * FROM servlet.user");

            ArrayList<User> user = new ArrayList();

            while (result.next()) {
                int id = result.getInt("user_id");
                String username = result.getString("username");
                Date birthday = result.getDate("birthday");
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
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        showDataTable(request);
        request.getRequestDispatcher("/resources/pages/User/userInfo.jsp").include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String userFunction = request.getParameter("userFunction");
            Connection connection = DatabaseConnector.initializeDatabase();
            PreparedStatement preparedStatement;
            switch (userFunction) {
                case "Add":
                    preparedStatement = connection.prepareStatement("INSERT INTO servlet.user VALUES (default, ?, ?, ?, ?, ?)");

                    preparedStatement.setString(1, request.getParameter("addUsername"));
                    preparedStatement.setDate(2, Date.valueOf(request.getParameter("addBirthday")));
                    preparedStatement.setString(3, request.getParameter("addEmail"));
                    preparedStatement.setString(4, request.getParameter("addHomeTown"));
                    preparedStatement.setString(5, request.getParameter("addCompany"));
                    preparedStatement.executeUpdate();

                    preparedStatement.close();
                    connection.close();
                    doGet(request, response);
                    break;
                case "Save":
                    preparedStatement = connection.prepareStatement("UPDATE servlet.user SET username = ?, birthday = ?, email = ?, home_town = ?, company = ? WHERE (user_id = ?);");

                    preparedStatement.setString(1, request.getParameter("editUsername"));
                    preparedStatement.setDate(2, Date.valueOf(request.getParameter("editBirthday")));
                    preparedStatement.setString(3, request.getParameter("editEmail"));
                    preparedStatement.setString(4, request.getParameter("editHomeTown"));
                    preparedStatement.setString(5, request.getParameter("editCompany"));
                    preparedStatement.setInt(6, Integer.valueOf(request.getParameter("editID")));
                    break;
                case "Delete":
                    preparedStatement = connection.prepareStatement("DELETE from servlet.user where user_id=?");
                    preparedStatement.setString(1, request.getParameter("deleteUserID"));
                    preparedStatement.executeUpdate();

                    preparedStatement.close();
                    connection.close();
                    doGet(request, response);
                    break;
                case "DeleteAll":
                    System.out.println("Delete all success");
                    break;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }
    }
}
