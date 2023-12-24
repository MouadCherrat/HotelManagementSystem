<%@ page import="login.Model.User" %>
<%@ page import="login.Model.Booking" %>
<%@ page import="login.Service.BookingService" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="login.Model.Facture" %>
<%--
  Created by IntelliJ IDEA.
  User: mouaad
  Date: 10/12/2023
  Time: 19:11
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Facture de Reservation</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }

        .invoice-container {
            background-color: #fff;
            border-radius: 8px;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 400px;
        }

        .header {
            text-align: center;
            margin-bottom: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        th, td {
            padding: 10px;
            border-bottom: 1px solid #ddd;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

        h2 {
            color: #007bff; /* Blue */
        }

        ul {
            list-style: none;
            padding: 0;
            margin: 0;
        }

        li {
            margin: 10px 0;
            color: #333;
        }
        .center-form {
            text-align: center;
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
    HttpSession session1 = request.getSession();



    Facture facture = (Facture) session1.getAttribute("facture");


    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    String checkInDateFormatted = dateFormat.format(facture.getBooking().getCheckInDate());
    String checkOutDateFormatted = dateFormat.format(facture.getBooking().getCheckOutDate());

%>

<div class="invoice-container">
    <div class="header">
        <h2>Facture de Reservation</h2>
    </div>
    <table>
        <tr>
            <th>Numero de chambre</th>
            <td><%=facture.getBooking().getRoom().getRoom_number()%></td>
        </tr>
        <tr>
            <th>Nombre de lits</th>
            <td><%=facture.getBooking().getRoom().getNombre_lits()%></td>
        </tr>
        <tr>
            <th>Date de depart</th>
            <td><%=checkInDateFormatted%></td>
        </tr>
        <tr>
            <th>Date de sortie</th>
            <td><%=checkOutDateFormatted%></td>
        </tr>
        <tr>
            <th>Prenom</th>
            <td><%=facture.getUser().getFirstName()%></td>
        </tr>
        <tr>
            <th>Nom</th>
            <td><%=facture.getUser().getLastName()%></td>
        </tr>
        <tr>
            <th>Adresse email</th>
            <td><%=facture.getUser().getEmail()%></td>
        </tr>
        <tr>
            <th>Montant a payer</th>
            <td> <%=facture.getAmount()%>$</td>
        </tr>
    </table>
    <form action="PdfServlet-servlet" method="post" class="center-form">
        <input type="submit" value="Valider Reservation">
    </form>
</div>



</body>
</html>
