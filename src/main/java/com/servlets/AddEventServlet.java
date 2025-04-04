package com.servlets;

import com.classes.User;
import com.classes.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/AddEventServlet")
public class AddEventServlet extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String eventName = request.getParameter("eventName");
        String category = request.getParameter("category");
        String eventDate = request.getParameter("eventDate");
        String capacityStr = request.getParameter("capacity");
        String description = request.getParameter("description");
        User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
        String userId = loggedInUser.getUserId();


        int capacity;
        try {
            capacity = Integer.parseInt(capacityStr);
        } catch (NumberFormatException e) {
            request.setAttribute("message", "Invalid capacity. Please enter a valid number.");
            request.getRequestDispatcher("addEvent.jsp").forward(request, response);
            return;
        }
        boolean success = userService.addEvent(eventName, category, eventDate, capacity, description,userId);


        if (success) {
            request.setAttribute("message", "Event added successfully.");
        } else {
            request.setAttribute("message", "Failed to add the event. Please try again.");
        }
        request.getRequestDispatcher("addEvent.jsp").forward(request, response);
    }
}
