<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inscription</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #e6f7ff;
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
            color: #008ae6;
        }

        label {
            display: block;
            margin: 10px 0 5px;
            color: #006bb3;
        }

        input, textarea {
            width: 100%;
            padding: 8px;
            margin: 5px 0 15px;
            border: 1px solid #66a3ff;
            border-radius: 4px;
            box-sizing: border-box;
        }

        input[type="submit"] {
            background-color: #005bb5;
            color: #fff;
            cursor: pointer;
            font-size: 16px;
            padding: 10px;
            border: none;
            border-radius: 4px;
        }

        input[type="submit"]:hover {
            background-color: #004080;
        }

        p {
            margin-top: 10px;
            font-size: 14px;
            color: #333;
        }

        a {
            color: #005bb5;
            text-decoration: none;
        }

        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

<form action="RegistrationServlet-servlet" method="post">
    <h2>Inscription</h2>

    <label for="firstName">Prenom:</label>
    <input type="text" id="firstName" name="firstName" required>

    <label for="lastName">Nom de famille:</label>
    <input type="text" id="lastName" name="lastName" required>

    <label for="email">Adresse e-mail:</label>
    <input type="email" id="email" name="email" required>

    <label for="password">Mot de passe:</label>
    <input type="password" id="password" name="password" required>

    <label for="address">Adresse:</label>
    <textarea id="address" name="address" rows="3" required></textarea>

    <input type="submit" value="S'inscrire">
    <p>Deja inscrit ? <a href="Login.jsp">Se connecter</a></p>
</form>

</body>
</html>
