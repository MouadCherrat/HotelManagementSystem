<%--
  Created by IntelliJ IDEA.
  User: mouaad
  Date: 23/12/2023
  Time: 19:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Gestion d'Hotel</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">

  <style>
    body {
      font-family: 'Arial', sans-serif;
    }
    .navbar-brand {
      font-size: 1.5rem;
      font-weight: bold;
    }
    .navbar-nav .nav-link {
      font-size: 1.2rem;
    }
    .hero-section {
      background: url('images/slide2.jpg') center/cover no-repeat;
      color: #fff;
      padding: 100px 0;
      text-align: center;
    }
    .hero-section h1 {
      font-size: 3rem;
    }
    .hero-section p {
      font-size: 1.25rem;
      margin-bottom: 30px;
    }
    .btn-primary {
      font-size: 1.25rem;
      padding: 10px 20px;
    }
    .features-section {
      padding: 50px 0;
    }
    .features-section h2 {
      font-size: 2rem;
      margin-bottom: 20px;
    }
    .features-section p {
      font-size: 1.1rem;
    }
    footer {
      background-color: #343a40;
      color: #fff;
      padding: 15px 0;
    }
  </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="#">HMS Hotel</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav ml-auto">
      <li class="nav-item active">
        <a class="nav-link" href="Home.jsp">Accueil</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="Login.jsp">Se connecter</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">Contactez Nous</a>
      </li>
    </ul>
  </div>
</nav>

<div class="hero-section">
  <div class="container text-center">
    <h1 class="display-4">Bienvenue a Notre Hotel</h1>
    <p class="lead">Reservez votre sejour des maintenant pour une experience inoubliable.</p>
    <a href="Login.jsp" class="btn btn-primary btn-lg">Reserver maintenant</a>
  </div>
</div>

<div class="container features-section">
  <div class="row">
    <div class="col-lg-4">
      <h2>Chambres Luxueuses</h2>
      <img src="images/slide4.jpg" alt="Chambre Luxueuse" class="img-fluid">
      <p>Decouvrez nos chambres elegantes et confortables, concues pour repondre a toutes vos attentes.</p>
    </div>
    <div class="col-lg-4">
      <h2>Service Exceptionnel</h2>
      <img src="images/slide1.jpg" alt="Service Exceptionnel" class="img-fluid">
      <p>Profitez d'un service client exceptionnel et de l'attention aux details pour rendre votre sejour memorable.</p>
    </div>
    <div class="col-lg-4">
      <h2>Piscine et Spa</h2>
      <img src="images/slide3.jpg" alt="Piscine et Spa" class="img-fluid">
      <p>Relaxez-vous dans notre piscine et spa de luxe apres une journee bien remplie.</p>
    </div>
  </div>
</div>
<footer class="bg-dark text-white text-center py-3">
  <p>&copy; 2023 HMS Hotel</p>
</footer>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>


