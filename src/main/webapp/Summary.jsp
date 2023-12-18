<%@ page import="login.Model.User" %>
<%@ page import="login.Model.Booking" %>
<%@ page import="login.Service.BookingService" %>
<%@ page import="java.text.SimpleDateFormat" %>
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
    </style>
</head>
<body>
<%
    HttpSession session1 = request.getSession();
    HttpSession session2 = request.getSession();
    HttpSession session3 = request.getSession();


    User user = (User) session1.getAttribute("user");
    Booking booking = (Booking) session2.getAttribute("booking");
    double totalAmount = (double) session3.getAttribute("totalAmount");
    BookingService bookingService = new BookingService();

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    String checkInDateFormatted = dateFormat.format(booking.getCheckInDate());
    String checkOutDateFormatted = dateFormat.format(booking.getCheckOutDate());

%>

<div class="invoice-container">
    <div class="header">
        <h2>Facture de Reservation</h2>
    </div>
    <table>
        <tr>
            <th>Numero de chambre</th>
            <td><%=booking.getRoom_number()%></td>
        </tr>
        <tr>
            <th>Nombre de lits</th>
            <td><%=booking.getNombre_beds()%></td>
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
            <td><%=user.getFirstName()%></td>
        </tr>
        <tr>
            <th>Nom</th>
            <td><%=user.getLastName()%></td>
        </tr>
        <tr>
            <th>Adresse email</th>
            <td><%=user.getEmail()%></td>
        </tr>
        <tr>
            <th>Montant a payer</th>
            <td> <%=bookingService.calculateAmount(booking)%> </td>
        </tr>
    </table>
</div>

</body>
</html>
