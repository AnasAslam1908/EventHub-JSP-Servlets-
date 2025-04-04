<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>EventHub</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        html, body {
            height: 100%;
            margin: 0;
        }
        body {
            display: flex;
            flex-direction: column;
            background-color: #f4f4f9;
        }
        .content {
            flex: 1;
        }
        .hero-section {
            width: 100%;
            background: linear-gradient(to right, #4b79a1, #283e51);
            color: white;
            padding: 80px 20px;
            text-align: center;
            margin-top: 56px;
        }
        .hero-section h1 {
            font-size: 3rem;
            font-weight: bold;
        }
        .hero-section p {
            font-size: 1.2rem;
            margin-top: 10px;
        }
        .btn-custom-primary {
            background-color: #0069d9;
            border-color: #0069d9;
            color: white;
        }
        .btn-custom-secondary {
            background-color: #6c757d;
            border-color: #6c757d;
            color: white;
        }
        .btn:hover {
            opacity: 0.9;
        }
        footer {
            background-color: #f4f4f9;
            color: #283e51 ;
            padding: 10px 0;
            text-align: center;
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


<div class="content">
    <div class="hero-section">
        <h1>Welcome to EventHub</h1>
        <p>Your one-stop platform for discovering, managing, and participating in amazing events.</p>
        <div class="mt-4">
            <a href="login.jsp" class="btn btn-custom-primary btn-lg me-2">Login</a>
            <a href="register.jsp" class="btn btn-custom-secondary btn-lg">Register</a>
        </div>
    </div>
</div>


<footer>
    <p class="mb-0"><strong>&copy; 2024 EventHub. All rights reserved.</strong></p>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
