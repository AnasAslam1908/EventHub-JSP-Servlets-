<%@ page import="java.util.List" %>
<%@ page import="com.classes.Event" %>
<%@ page import="com.classes.UserService" %>
<%@ page import="com.classes.Participant" %>
<%@ page import="java.sql.SQLException" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Admin Dashboard</title>

  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    .dashboard-container {
      margin-top: 30px;
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

  UserService userService = new UserService();
  List<Event> events = userService.getAllEvents();
  String message = (String) request.getAttribute("message");
%>


<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">EventHub - Admin Dashboard</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav ms-auto">
        <li class="nav-item">
          <a class="nav-link" href="logout.jsp">Logout</a>
        </li>
      </ul>
    </div>
  </div>
</nav>

<div class="container dashboard-container">
  <h1 class="text-center">Admin Dashboard</h1>

  <% if (message != null) { %>
  <div class="alert alert-info text-center">
    <%= message %>
  </div>
  <% } %>


  <section id="manage-events">
    <h3>Manage Events</h3>
    <table class="table table-striped">
      <thead>
      <tr>
        <th>Event Name</th>
        <th>Category</th>
        <th>Date</th>
        <th>Capacity</th>
        <th>Registered</th>
        <th>Status</th>
        <th>Actions</th>
      </tr>
      </thead>
      <tbody>
      <% for (Event event : events) { %>
      <tr>
        <td><%= event.getEventName() %></td>
        <td><%= event.getCategory() %></td>
        <td><%= event.getEventDate() %></td>
        <td><%= event.getCapacity() %></td>
        <td><%= event.getRegisteredCount() %></td>
        <td><%= event.getStatus()%></td>
        <td>
          <a href="updateEvent.jsp?event_Id=<%= event.getEventId() %>" class="btn btn-warning btn-sm">Update</a>
          <a href="deleteEvent?event_Id=<%= event.getEventId() %>" class="btn btn-danger btn-sm">Delete</a>
          <a href="cancelEvent?event_Id=<%= event.getEventId() %>" class="btn btn-secondary btn-sm">Cancel</a>
        </td>
      </tr>
      <% } %>
      </tbody>
    </table>
    <a href="addEvent.jsp" class="btn btn-primary">Add New Event</a>
  </section>


  <section id="manage-participants" class="mt-5">
    <h3>Manage Participants</h3>
    <p>Select an event to view participants:</p>
    <form action="viewParticipants.jsp" method="get">
      <div class="row">
        <div class="col-md-8">
          <select name="eventId" class="form-select" required>
            <option value="">Select an event</option>
            <% for (Event event : events) { %>
            <option value="<%= event.getEventId() %>"><%= event.getEventName() %></option>
            <% } %>
          </select>
        </div>
        <div class="col-md-4">
          <button type="submit" class="btn btn-success">View Participants</button>
        </div>
      </div>
    </form>
  </section>
</div>
<footer>
  <p class="mb-0"><strong>&copy; 2024 EventHub. All rights reserved.</strong></p>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
