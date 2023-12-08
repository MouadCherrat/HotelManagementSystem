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
            align-items: center;
            justify-content: center;
            height: 100vh;
        }

        form {
            background-color: #fff;
            border-radius: 8px;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 300px;
            text-align: center;
        }

        h2 {
            color: #007bff; /* Blue */
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
            /* Additional styling for date input */
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
            background-color: #0056b3; /* Darker blue on hover */
        }
    </style>
</head>
<body>

<form action="BookingServlet-servlet" method="post">
    <h2>Reservation</h2>

    <label for="roomNumber">Numero de chambre:</label>
    <input type="text" id="roomNumber" name="roomNumber" required>

    <label for="checkInDate">Date d'arrivee:</label>
    <input type="date" id="checkInDate" name="checkInDate" required>

    <label for="checkOutDate">Date de depart:</label>
    <input type="date" id="checkOutDate" name="checkOutDate" required>

    <input type="submit" value="Reserver">
</form>

</body>
</html>
