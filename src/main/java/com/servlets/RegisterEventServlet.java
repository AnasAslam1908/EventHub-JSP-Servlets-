package com.servlets;
import com.classes.User;
import com.classes.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet("/RegisterEventServlet")
public class RegisterEventServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String eventId = request.getParameter("eventId");
        User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
        String userId = loggedInUser.getUserId();

        UserService userService = new UserService();

        if (userService.isUserAlreadyRegistered(eventId, userId)) {
            request.setAttribute("message", "You are already registered for this event.");
            request.getRequestDispatcher("browseEvents.jsp").forward(request, response);
            return;
        }
        if (userService.datePassed(eventId)){
            request.setAttribute("message", "Cannot register. Event has ended.");
            request.getRequestDispatcher("browseEvents.jsp").forward(request, response);
            return;
        }
        if(userService.checkCapacity(eventId)){
            request.setAttribute("message", "Cannot register. Capacity exceeded.");
            request.getRequestDispatcher("browseEvents.jsp").forward(request, response);
            return;
        }
        if (userService.checkStatus(eventId)){
            request.setAttribute("message", "Event is canceled. Cannot Register");
            request.getRequestDispatcher("browseEvents.jsp").forward(request, response);
            return;
        }



        boolean registrationSuccess = userService.registerUserForEvent(eventId, userId);
        if (registrationSuccess) {
            request.setAttribute("message", "Successfully registered for the event!");
        } else {
            request.setAttribute("message", "Failed to register for the event. Please try again.");
        }
        request.getRequestDispatcher("browseEvents.jsp").forward(request, response);
    }
}
