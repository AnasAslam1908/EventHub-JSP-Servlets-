package com.servlets;
import com.classes.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
@WebServlet("/cancelEvent")
public class CancelEventServlet extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String eventId = request.getParameter("event_Id");
        boolean isCanceled = userService.cancelEvent(eventId);
        boolean usersUnregistered =userService.unregisterOnCancelStatus(eventId);
        if (isCanceled && usersUnregistered) {
            request.setAttribute("message", "Event canceled successfully and Users are unregistered.");
        } else {
            request.setAttribute("error", "Error canceling event. Event not found or an error occurred while unregistering Users.");
        }

        request.getRequestDispatcher("admin-dashboard.jsp").forward(request, response);
    }
}
