package com.servlets;

import com.classes.Feedback;
import com.classes.User;
import com.classes.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/submitFeedbackServlet")
public class SubmitFeedbackServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String eventId = request.getParameter("eventId");
        String ratingStr = request.getParameter("rating");
        String comments = request.getParameter("comments");
        String userId=((User) request.getSession().getAttribute("loggedInUser")).getUserId();
        if (eventId != null && !eventId.isEmpty() && ratingStr != null && !ratingStr.isEmpty()) {
            int rating = Integer.parseInt(ratingStr);

            Feedback feedback = new Feedback(eventId,userId,rating,comments);

            UserService userService = new UserService();
            boolean success = userService.submitFeedback(feedback);

            if (success) {
                response.sendRedirect("attendedEvents.jsp");
            } else {
                response.sendRedirect("submitFeedback.jsp?eventId=" + eventId + "&error=Unable to submit feedback");
            }
        }
    }
}
