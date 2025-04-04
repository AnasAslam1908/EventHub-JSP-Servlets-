<%@ page import="com.classes.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Dashboard</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f4f4f9;
            margin: 0;
        }


        .navbar {
            position: sticky;
            top: 0;
            z-index: 1000;
        }

        .dashboard-container {
            text-align: center;
            margin-top: 100px;
        }

        .welcome-message {
            font-size: 2rem;
            font-weight: bold;
            margin-bottom: 30px;
            color: #283e51;
        }

        .card-link {
            text-decoration: none;
        }

        .row {
            justify-content: center;
        }
        .card{
            background: linear-gradient(to right, #4b79a1, #283e51);
            color: #f4f4f9;
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
                    <a class="nav-link" href="logout.jsp">Logout</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container dashboard-container">
    <p class="welcome-message">Welcome, <strong><%= loggedInUser.getUsername() %></strong>!</p>
    <div class="row">
        <!-- Browse Events Card -->
        <div class="col-md-4">
            <div class="card">
                <div class="card-body text-center">
                    <h5 class="card-title">Browse Events</h5>
                    <p class="card-text">Discover and register for exciting events!</p>
                    <a href="browseEvents.jsp" class="btn btn-primary card-link">Browse Events</a>
                </div>
            </div>
        </div>

        <!-- Attended Events -->
        <div class="col-md-4">
            <div class="card">
                <div class="card-body text-center">
                    <h5 class="card-title">Your Attended Events</h5>
                    <p class="card-text">View the events you have attended so far.</p>
                    <a href="attendedEvents.jsp" class="btn btn-primary card-link">Attended Events</a>
                </div>
            </div>
        </div>
       <!-- Upcoming Events -->
        <div class="col-md-4">
            <div class="card">
                <div class="card-body text-center">
                    <h5 class="card-title">Your Upcoming Events</h5>
                    <p class="card-text">View Your Upcoming Events.</p>
                    <a href="userUpcomingEvents.jsp" class="btn btn-primary card-link">Upcoming Events</a>
                </div>
            </div>
        </div>
    </div>
</div>
<footer>
    <p class="mb-0"><strong>&copy; 2024 EventHub. All rights reserved.</strong></p>
</footer>
<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
