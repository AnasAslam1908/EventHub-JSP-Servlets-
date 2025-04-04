package com.servlets;

import com.classes.UserService;
import com.classes.Event;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/UpdateEventServlet")
public class UpdateEventServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String eventId = request.getParameter("event_id");
        String eventName = request.getParameter("event_name");
        String category = request.getParameter("category");
        String eventDate = request.getParameter("event_date");
        int capacity = Integer.parseInt(request.getParameter("capacity"));
        String description = request.getParameter("description");
        String status = request.getParameter("status");

        UserService userService = new UserService();
        boolean isUpdated = userService.updateEvent(eventId,eventName,category,java.sql.Date.valueOf(eventDate),capacity,description,status);

        if (isUpdated) {
            response.sendRedirect("admin-dashboard.jsp");
        } else {
            response.sendRedirect("error.jsp");
        }
    }
}
