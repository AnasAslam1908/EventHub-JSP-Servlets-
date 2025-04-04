package com.servlets;

import java.io.*;

import com.classes.User;
import com.classes.UserService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserService userService = new UserService();
        if (userService.authenticateUser(username, password)) {
            HttpSession session = request.getSession();
            User user = userService.getUserByUsername(username);
            session.setAttribute("loggedInUser", user);
            session.setAttribute("role", user.getRole());

            if ("admin".equals(user.getRole())) {
                response.sendRedirect("admin-dashboard.jsp");
                System.out.println("logged in admin");
            } else {
                response.sendRedirect("user-dashboard.jsp");
                System.out.println("logged in user");
            }
        } else {
            request.setAttribute("message", "Invalid username or password");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}