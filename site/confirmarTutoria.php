<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Confirmar tutoria</title>
</head>

<body>
    <form method="POST" action="functions/enviarEmail.php">
        Local para a tutoria: <br />
        <input type="text" name="local" />

        <br /><br />

        Email para onde enviar: <br />
        <input type="text" name="email" />

        <br /><br />

        <button type="submit">Confirmar</button>
    </form>
</body>

</html>