<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - EventHub</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

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

<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">EventHub</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
    </div>
</nav>


<div class="container" style="padding-top: 80px;">
    <h2 class="my-4">Login to EventHub</h2>


    <%
        String message = (String) request.getAttribute("message");
        if (message != null) {
    %>
    <div class="alert alert-success" role="alert">
        <%= message %>
    </div>
    <%
        }
    %>


    <form action="login" method="post">
        <div class="form-group">
            <label for="username">Username</label>
            <input type="text" class="form-control" id="username" name="username" required>
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" class="form-control" id="password" name="password" required>
        </div>
        <button type="submit" class="btn btn-primary mt-3">Login</button>
    </form>

    <div class="mt-3">
        <p>Don't have an account? <a href="register.jsp">Register here</a></p>
    </div>
</div>


<footer>
    <p class="mb-0"><strong>&copy; 2024 EventHub. All rights reserved.</strong></p>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
