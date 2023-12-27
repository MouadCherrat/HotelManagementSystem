<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Connexion & Inscription</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-image: url('images/login.jpeg');
            background-size: cover;
            background-position: center;
            margin: 0;
            padding: 0;
            display: flex;
            align-items: center;
            justify-content: center;
            min-height: 100vh;
        }

        .container {
            display: flex;
            max-width: 800px;
            width: 100%;
            background-color: #fff;
            border-radius: 10px;
            overflow: hidden;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
        }

        .form-container {
            flex: 1;
            padding: 40px;
            box-sizing: border-box;

        }

        h2 {
            color: #0b487a;
            margin-bottom: 30px;
            font-size: 26px;
            padding-left: 28%;
        }

        label {
            display: block;
            margin: 0 auto;
            color: #555;
            font-size: 14px;
            text-align: center;
            line-height: 1.5;
        }


        input,
        textarea {
            width: 100%;
            padding: 12px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 6px;
            box-sizing: border-box;
            transition: border-color 0.3s;
        }

        input[type="submit"] {
            background-color: #0b487a;
            color: #fff;
            cursor: pointer;
            font-size: 16px;
            padding: 14px;
            border: none;
            border-radius: 6px;
            transition: background-color 0.3s;
        }

        input[type="submit"]:hover {
            background-color: #0b487a;
        }

        p {
            margin-top: 20px;
            font-size: 14px;
            color: #555;
        }

        a {
            color: #3498db;
            text-decoration: none;
            font-weight: bold;
            transition: color 0.3s;
        }

        a:hover {
            color: #2075c7;
        }
    </style>
</head>

<body>

<div class="container">

    <div class="form-container">

        <form action="loginServlet-servlet" method="post">
            <h2>Connexion</h2>

            <label for="email">Adresse e-mail:</label>
            <input type="email" id="email" name="email" required>

            <label for="password">Mot de passe:</label>
            <input type="password" id="password" name="password" required>

            <input type="submit" value="Se connecter">

        </form>
    </div>

    <div class="form-container">
        <form action="RegistrationServlet-servlet" method="post">
            <h2>Inscription</h2>

            <label for="firstName">Prenom:</label>
            <input type="text" id="firstName" name="firstName" required>

            <label for="lastName">Nom de famille:</label>
            <input type="text" id="lastName" name="lastName" required>

            <label for="email">Adresse e-mail:</label>
            <input type="email" id="email1" name="email" required>

            <label for="password">Mot de passe:</label>
            <input type="password" id="password1" name="password" required>

            <input type="submit" value="S'inscrire">

        </form>
    </div>
</div>

</body>

</html>
