package com.servlets;
import com.classes.User;
import com.classes.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/CancelRegistrationByUserServlet")
public class CancelRegistrationServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        String eventId = request.getParameter("eventId");

        UserService userService = new UserService();
      boolean flag=  userService.cancelRegistration(loggedInUser.getUserId(), eventId);
        if(flag){
            request.setAttribute("message", "Registration Cancelled.");
        }else {
            request.setAttribute("message", "Canceling Registration Failed.");
        }
        request.getRequestDispatcher("userUpcomingEvents.jsp").forward(request, response);


    }
}
