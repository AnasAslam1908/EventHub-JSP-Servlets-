<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Event</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .form-container {
            margin-top: 50px;
        }
        .form-title {
            text-align: center;
            margin-bottom: 20px;
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
    if (session == null || session.getAttribute("loggedInUser") == null ||
            !"admin".equals(session.getAttribute("role"))) {
        response.sendRedirect("login.jsp");
        return;
    }


    String message = (String) request.getAttribute("message");
%>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark sticky-top">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">EventHub</a>
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

<div class="container form-container">
    <h2 class="form-title">Add Event</h2>
    <% if (message != null) { %>
    <div class="alert alert-info text-center">
        <%= message %>
    </div>
    <% } %>
    <form action="AddEventServlet" method="post">
        <div class="row mb-3">
            <label for="eventName" class="col-sm-2 col-form-label">Event Name</label>
            <div class="col-sm-10">
                <input type="text" name="eventName" id="eventName" class="form-control" required>
            </div>
        </div>
        <div class="row mb-3">
            <label for="category" class="col-sm-2 col-form-label">Category</label>
            <div class="col-sm-10">
                <input type="text" name="category" id="category" class="form-control" required>
            </div>
        </div>
        <div class="row mb-3">
            <label for="eventDate" class="col-sm-2 col-form-label">Event Date</label>
            <div class="col-sm-10">
                <input type="date" name="eventDate" id="eventDate" class="form-control" required>
            </div>
        </div>
        <div class="row mb-3">
            <label for="capacity" class="col-sm-2 col-form-label">Capacity</label>
            <div class="col-sm-10">
                <input type="number" name="capacity" id="capacity" class="form-control" required>
            </div>
        </div>
        <div class="row mb-3">
            <label for="description" class="col-sm-2 col-form-label">Description</label>
            <div class="col-sm-10">
                <textarea name="description" id="description" rows="4" class="form-control" required></textarea>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-10 offset-sm-2">
                <button type="submit" class="btn btn-primary">Add Event</button>
                <a href="admin-dashboard.jsp" class="btn btn-secondary">Cancel</a>
            </div>
        </div>
    </form>
</div>
<footer>
    <p class="mb-0"><strong>&copy; 2024 EventHub. All rights reserved.</strong></p>
</footer>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
