<%@ page import="login.Model.User" %>
<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reservation</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            background-image: url('images/home.jpg');
            background-size: cover;
            background-position: center;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
            color: #333;
        }

        h1{
            color: #fff6c4;
        }
        h2 {
            color: #090707;
        }

        .container {
            display: flex;
            justify-content: space-between;
            width: 80%;
            margin-top: 20px;
            background-color: rgba(255, 255, 255, 0.9);
            padding: 20px;
            border-radius: 12px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.2);
        }

        form,
        .room-rates {
            margin: 0px 25px;
        }

        label {
            display: block;
            margin-bottom: 15px;
            color: #555;
            font-size: 14px;
        }

        select,
        input[type="date"],
        input[type="submit"] {
            width: 100%;
            padding: 15px;
            margin-bottom: 20px;
            box-sizing: border-box;
            border: 1px solid #e0e0e0;
            border-radius: 6px;
            background-color: #f9f9f9;
            color: #555;
            font-size: 16px;
        }

        input[type="submit"] {
            background-color: #ffcc00;
            color: #333;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        input[type="submit"]:hover {
            background-color: #ffdb4d;
        }

        footer {
            margin-top: 20px;
            text-align: center;
            color: #ffedae;
        }
    </style>
</head>

<body>
<%
    String erreurMessage = (String) request.getAttribute("erreurMessage");

    if (erreurMessage != null) {
%>

<p style="color: red;"><%= erreurMessage %></p>
<%
    }
%>
<%
    HttpSession session1 = request.getSession();
    User user = (User) session1.getAttribute("user");

    if (user == null) {
        response.sendRedirect("Login.jsp");
    }
%>
<h1>Bienvenue <b> <%= user.getFirstName() + " " + user.getLastName() %> </b></h1>

<div class="container">
    <form action="BookingServlet-servlet" method="post">
        <h2>Reservation</h2>
        <label for="nombre_lits">Nombre de lits :</label>
        <select id="nombre_lits" name="nombre_lits" required>
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
        </select>

        <label for="checkInDate">Date d'arrivee :</label>
        <input type="date" id="checkInDate" name="checkInDate" required placeholder="<%=java.time.LocalDate.now()%>">


        <label for="checkOutDate">Date de depart :</label>
        <input type="date" id="checkOutDate" name="checkOutDate" requiredplaceholder="<%=java.time.LocalDate.now()%>">

        <input type="submit" value="Reserver maintenant">
    </form>

    <div class="room-rates">
        <h2>Tarif </h2>
        <p>Decouvrez du luxe dans nos chambres et suites . Chaque chambre est  concue pour vous offrir un  confort et de sophistication.</p>

        <ul>
            <li><p>  Chambre Simple : <b>150 $ </b>par nuit</p></li>
            <li><p>  Chambre Double : <b>200 $ </b>par nuit</p></li>
            <li><p>  Chambre Triple : <b>300 $ </b>par nuit</p></li>
            <li><p> Chambre a Quatre : <b>350 $ </b>par nuit</p></li>
            <i class="fa-solid fa-bed"></i>

        </ul>
        <p> Rendez votre sejour inoubliable.<b> Reservez maintenant !</b> </p>


    </div>
</div>

<footer>
    Contactez-nous au +212 64513 1252 | hmsemsi@gmail.com
</footer>
<script>
    document.getElementById('checkInDate').valueAsDate = new Date();
    document.getElementById('checkOutDate').valueAsDate = new Date();
</script>
</body>

</html>
