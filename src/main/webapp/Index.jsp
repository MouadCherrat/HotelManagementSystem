<%@ page import="login.Model.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reservation</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }
        /* Ajoutez ce style à votre fichier CSS existant */

        /* Style du label */
        label {
            display: block;
            margin: 10px 0 5px;
            color: #333;
        }


        select {
            width: 100%;
            padding: 8px;
            margin: 5px 0 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }


        form {
            background-color: #fff;
            border-radius: 8px;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 300px;
            text-align: center;
            margin-top: 20px;
        }

        h2 {
            color: #007bff;
            margin-bottom: 20px;
        }


        label {
            display: block;
            margin: 10px 0 5px;
            color: #333;
        }

        input {
            width: 100%;
            padding: 8px;
            margin: 5px 0 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }

        input[type="date"] {
            padding: 8px;
        }

        input[type="submit"] {
            background-color: #007bff;
            color: #fff;
            cursor: pointer;
            font-size: 16px;
            padding: 10px;
            border: none;
            border-radius: 4px;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
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
<h2>Welcome <%= user.getFirstName() + " " + user.getLastName()%> </h2>


<form action="BookingServlet-servlet" method="post">
    <h2>Reservation</h2>
    <label for="room_number">Numero de Chambre:</label>
    <select id="room_number" name="room_number" required>

        <option value="1">Chambre 1</option>
        <option value="2">Chambre 2</option>
        <option value="3">Chambre 3</option>
        <option value="4">Chambre 4</option>
        <option value="5">Chambre 5</option>
        <option value="6">Chambre 6</option>
        <option value="7">Chambre 7</option>
        <option value="8">Chambre 8</option>
        <option value="9">Chambre 9</option>
        <option value="10">Chambre 10</option>
       </select>




        <label for="nombre_beds">Nombre de Lits:</label>
    <input type="number" id="nombre_beds" name="nombre_lits" required min="0">

    <label for="checkInDate">Date d'arrivee:</label>
    <input type="date" id="checkInDate" name="checkInDate" required>

    <label for="checkOutDate">Date de depart:</label>
    <input type="date" id="checkOutDate" name="checkOutDate" required>

    <input type="submit" value="Reserver">
</form>

</body>
</html>
