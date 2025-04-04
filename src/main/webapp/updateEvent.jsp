<%@ page import="com.classes.Event" %>
<%@ page import="com.classes.UserService" %>
<%@ page import="java.sql.Date" %>
<%
  String eventId = request.getParameter("event_Id");
  System.out.println(eventId);
  UserService userService = new UserService();
  Event event = userService.getEventById(eventId);

  if (event == null) {
    System.out.println("Event not found.");
    return;
  }
%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Update Event</title>

  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    body{

      background-color: #f4f4f9;
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


<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">EventHub</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav ms-auto">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="admin-dashboard.jsp">Dashboard</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="logout.jsp">Logout</a>
        </li>
      </ul>
    </div>
  </div>
</nav>

<div class="container mt-5">
  <h2 class="mb-4">Update Event</h2>

  <form action="UpdateEventServlet" method="post">
    <input type="hidden" name="event_id" value="<%= event.getEventId() %>">

    <div class="mb-3">
      <label for="event_name" class="form-label">Event Name:</label>
      <input type="text" id="event_name" name="event_name" class="form-control" value="<%= event.getEventName() %>" required>
    </div>

    <div class="mb-3">
      <label for="category" class="form-label">Category:</label>
      <input type="text" id="category" name="category" class="form-control" value="<%= event.getCategory() %>" required>
    </div>

    <div class="mb-3">
      <label for="event_date" class="form-label">Event Date:</label>
      <input type="date" id="event_date" name="event_date" class="form-control" value="<%= event.getEventDate() != null ? event.getEventDate().toString().substring(0, 10) : "" %>" required>
    </div>

    <div class="mb-3">
      <label for="capacity" class="form-label">Capacity:</label>
      <input type="number" id="capacity" name="capacity" class="form-control" value="<%= event.getCapacity() %>" required>
    </div>

    <div class="mb-3">
      <label for="status" class="form-label">Status:</label>
      <select id="status" name="status" class="form-select" required>
        <option value="Active" <%= "Active".equals(event.getStatus()) ? "selected" : "" %>>Active</option>
        <option value="Cancelled" <%= "Cancelled".equals(event.getStatus()) ? "selected" : "" %>>Cancelled</option>
      </select>
    </div>
    <div class="mb-3">
      <label for="description" class="form-label">Description:</label>
      <textarea id="description" name="description" class="form-control" rows="4" required><%= event.getDescription() %></textarea>
      <button type="submit" class="btn btn-primary">Updatee Event</button>
    </div>

  </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
