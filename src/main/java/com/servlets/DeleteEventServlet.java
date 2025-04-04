package com.servlets;
import com.classes.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
@WebServlet("/deleteEvent")
public class DeleteEventServlet extends HttpServlet {
    private  UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String eventId = request.getParameter("event_Id");
        boolean isDeleted = userService.deleteEvent(eventId);

        if (isDeleted) {
            userService.unregisterOnCancelStatus(eventId);
            request.setAttribute("message", "Event deleted successfully.");
        } else {
            request.setAttribute("error", "Error deleting event. Please try again.");
        }

        request.getRequestDispatcher("admin-dashboard.jsp").forward(request, response);
    }
}
