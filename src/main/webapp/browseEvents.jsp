<%@ page import="java.sql.*, com.classes.UserService, com.classes.Event" %>
<%@ page import="java.util.List" %>
<%@ page import="com.classes.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Browse Events</title>

  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    .event-container {
      margin-top: 50px;
    }
    .event-card {
      background: linear-gradient(to right, #4b79a1, #283e51);
      margin-bottom: 20px;
      color: #f4f4f9;
    }
    .card-title {
      color: #f4f4f9;
      font-size: 1.25rem;
    }
    body{

      background-color: #f4f4f9;
    }
    h2,h4{
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

  String categoryFilter = request.getParameter("category");
  String dateFilter = request.getParameter("eventDate");
  String keywordFilter = request.getParameter("keyword");
  String upcomingFilter = request.getParameter("upcoming");


  List<Event> events = userService.getFilteredEvents(categoryFilter, dateFilter, keywordFilter, Boolean.parseBoolean(upcomingFilter));

  if (events == null || events.isEmpty()) {
%>
<div class="alert alert-info text-center">
<p>No events found based on your filters.</p>
</div>
<% }%>


<nav class="navbar navbar-expand-lg navbar-dark bg-dark sticky-top">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">EventHub</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav ms-auto">
        <li class="nav-item">
          <a class="nav-link" href="user-dashboard.jsp">Dashboard</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="logout.jsp">Logout</a>
        </li>
      </ul>
    </div>
  </div>
</nav>


<div class="container">
  <h4>Filter Events</h4>
  <form method="get" action="browseEvents.jsp">
    <div class="row">
      <div class="col-md-3">
        <label for="category">Category</label>
        <input type="text" name="category" id="category" class="form-control" value="<%= categoryFilter != null ? categoryFilter : "" %>">
      </div>
      <div class="col-md-3">
        <label for="eventDate">Event Date</label>
        <input type="date" name="eventDate" id="eventDate" class="form-control" value="<%= dateFilter != null ? dateFilter : "" %>">
      </div>
      <div class="col-md-3">
        <label for="keyword">Keyword</label>
        <input type="text" name="keyword" id="keyword" class="form-control" value="<%= keywordFilter != null ? keywordFilter : "" %>">
      </div>
      <div class="col-md-3">
        <label>&nbsp;</label>
        <button type="submit" class="btn btn-primary form-control">Apply Filters</button>
      </div>
    </div>
  </form>

  <form method="get" action="browseEvents.jsp">
    <div class="row mt-3">
      <div class="col-md-3">
        <button type="submit" class="btn btn-info form-control" name="upcoming" value="true">Upcoming Events</button>
      </div>
    </div>
  </form>
</div>


<div class="container event-container">
  <h2 class="text-center">Browse Events</h2>
  <%
    String message = (String) request.getAttribute("message");
    if (message != null) {
  %>
  <div class="alert alert-info text-center">
    <%= message %>
  </div>
  <%
    }
  %>

  <div class="row">
    <%
      for (Event event : events) {
    %>
    <div class="col-md-4">
      <div class="card event-card">
        <div class="card-body">
          <h5 class="card-title"><%= event.getEventName() %></h5>
          <p class="card-text"><%= event.getDescription() %></p>
          <p><strong>Category:</strong> <%= event.getCategory() %></p>
          <p><strong>Date:</strong> <%= event.getEventDate() %></p>
          <p><strong>Capacity:</strong> <%= event.getCapacity() %></p>
          <p><strong>Registered:</strong> <%= event.getRegisteredCount() %></p>
          <a href="registerEvent.jsp?eventId=<%= event.getEventId() %>" class="btn btn-primary">Register</a>
        </div>
      </div>
    </div>
    <%
      }
    %>
  </div>
</div>
<footer>
  <p class="mb-0"><strong>&copy; 2024 EventHub. All rights reserved.</strong></p>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
