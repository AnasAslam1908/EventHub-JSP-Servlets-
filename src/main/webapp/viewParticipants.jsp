<%@ page import="java.util.List" %>
<%@ page import="com.classes.Participant" %>
<%@ page import="com.classes.UserService" %>
<%@ page import="com.classes.User" %>
<%@ page import="com.classes.Event" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Participants</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .container {
            margin-top: 50px;
        }
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
<%
    session = request.getSession(false);
    if (session == null || session.getAttribute("loggedInUser") == null) {
        response.sendRedirect("login.jsp");
        return;
    }


    User loggedInUser = (User) session.getAttribute("loggedInUser");

    UserService userService = new UserService();

    List<Event> events = userService.getAllEvents();
    String eventId = request.getParameter("eventId");
    List<Participant> participants = null;

    if (eventId != null) {
        participants = userService.getParticipantsByEvent(eventId);
    }
    String message = (String) request.getAttribute("message");
%>

<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark sticky-top">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">EventHub Admin</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <a class="nav-link" href="admin-dashboard.jsp">Dashboard</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="logout.jsp">Logout</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container">
    <h2 class="text-center">View Participants</h2>
    <% if (message != null) { %>
    <div class="alert alert-info text-center">
        <%= message %>
    </div>
    <% } %>
    <!-- Event Selection -->
    <form method="get" action="viewParticipants.jsp">
        <div class="mb-3">
            <label for="eventId" class="form-label">Select Event:</label>
            <select name="eventId" id="eventId" class="form-select" required>
                <option value="">-- Select an Event --</option>
                <% for (Event event : events) { %>
                <option value="<%= event.getEventId() %>"
                        <%= eventId != null && eventId.equals(event.getEventId()) ? "selected" : "" %>>
                    <%= event.getEventName() %> (Date: <%= event.getEventDate() %>)
                </option>
                <% } %>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">View Participants</button>
    </form>

    <!-- Participant List -->
    <% if (eventId != null && participants != null && !participants.isEmpty()) { %>
    <table class="table table-striped mt-4">
        <thead>
        <tr>
            <th>#</th>
            <th>Participant ID</th>
            <th>Username</th>
            <th>Registration Time</th>
            <th>Registration Status</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <% int count = 1;
            for (Participant participant : participants) { %>
        <tr>
            <td><%= count++ %></td>
            <td><%= participant.getParticipantId() %></td>
            <td><%= participant.getUserId() %></td>
            <td><%= participant.getRegisteredAt() %></td>
            <td><%= participant.getRegistrationStatus()%></td>
            <td>
                <a href="approveRegistration?p_Id=<%= participant.getParticipantId() %>&action=approve" class="btn btn-success btn-sm">Approve</a>
                <a href="cancelRegistration?p_Id=<%= participant.getParticipantId() %>&action=cancel" class="btn btn-danger btn-sm">Cancel</a>
                <a href="pendingRegistration?p_Id=<%= participant.getParticipantId() %>&action=pending" class="btn btn-secondary btn-sm">Pending</a>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
    <% } else if (eventId != null) { %>
    <p class="text-danger mt-4">No participants registered for this event.</p>
    <% } %>
</div>
<footer>
    <p class="mb-0"><strong>&copy; 2024 EventHub. All rights reserved.</strong></p>
</footer>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
