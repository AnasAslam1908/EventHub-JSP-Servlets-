<%@ page import="java.sql.*, com.classes.Event, com.classes.User" %>
<%
    session = request.getSession(false);
    if (session == null || session.getAttribute("loggedInUser") == null) {

        response.sendRedirect("login.jsp");
        return;
    }


    String eventId = request.getParameter("eventId");
    User loggedInUser = (User) session.getAttribute("loggedInUser");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register for Event</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body{
            background-color: #f4f4f9;
            color: #283e51;
        }

        footer {
            background-color: #f4f4f9;
            color: #283e51;
            padding: 10px 0;
            text-align: center;
            position: fixed;
            width: 100%;
            bottom: 0;
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <h2>Register for Event</h2>
    <form action="RegisterEventServlet" method="post">
        <input type="hidden" name="eventId" value="<%= eventId %>">
        <input type="hidden" name="userId" value="<%= loggedInUser.getUserId() %>">
        <p>Are you sure you want to register for this event?</p>
        <button type="submit" class="btn btn-primary">Confirm Registration</button>
        <a href="browseEvents.jsp" class="btn btn-secondary">Cancel</a>
    </form>
    <footer>
        <p class="mb-0"><strong>&copy; 2024 EventHub. All rights reserved.</strong></p>
    </footer>
</div>
</body>
</html>
