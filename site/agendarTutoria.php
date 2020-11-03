<?php
require_once "vendor/autoload.php";
\App\Model\Session::startSession();
include("functions/acesso.php");
?>

<!DOCTYPE html>
<html lang="pt-BR">

<head>
    <title>Agendamento de tutoria</title>

    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="css/style.min.css">

    <!-- My styles CSS -->
    <link href="css/style.scss">
    <link rel="stylesheet" href="css/nav.css">
    <link rel="stylesheet" href="css/navside.css">
    <link rel="stylesheet" href="css/agendarTutorias.css">

    <!-- Font Awesome -->
    <link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Berkshire+Swash|Josefin+Sans&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Josefin+Sans&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

    <style>
        table#horarios-professor,
        td#horarios-professor,
        th#horarios-professor {
            border: 1px solid black;
            border-collapse: collapse;
        }

        table#horarios-professor {
            text-align: center;
        }

        td#horarios-professor {
            padding: 0;
        }

        table#horarios-professor button[type="submit"] {
            width: 100%;
            height: 100%;
            border: none;
            background-color: orange;
            color: white;
        }
    </style>

</head>

<body>

    <?php
    error_reporting(6143);

    include("nav.php");
    include("navside.php");
    include('calendario.php');
    //    include("db/credentials.php");
    //    include("db/db_connect.php");
    //    include("functions/agendarTutoria.php");
    ?>

    <div id="content">
        <div id="sidebar">
            <ul id="list-profs">
                <?php \App\Model\TutorService::renderizarProfessoresAgendamentoTutoria(); ?>
            </ul>
        </div>
        <div id="body">
            <?php
            if (isset($_GET['id']))
                \App\Model\TutorService::renderizarInformacoes($_GET['id']);
            ?>
        </div>
    </div>

    <script type="text/javascript" src="js/nav.js"></script>
    <script type="text/javascript" src="js/navside.js"></script>
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/calendario.js"></script>
    <script type="text/javascript" src="js/selecionarPrimeiroProf.js"></script>

    <script type="text/javascript">
        function dataTutoria(data) {
            let dataFormatada = data.split("/");
            data = dataFormatada[1] + "/" + dataFormatada[0] + "/" + dataFormatada[2];

            let inputData = document.querySelector("input#data-selecionada");
            let fakeData = document.querySelector("#fakeData");
            let submitFakeData = document.querySelector("#submitFakeData");
            let idProf = document.querySelector("form input#idProf");

            inputData.value = data;
            fakeData.value = data;
            idProf.value = <?= $_GET["id"] ?>;

            submitFakeData.click();
        }
    </script>

    <!-- <script type="text/javascrpit" src="node_modules/jquery/dist/jquery.slim.min.js"></script>
<script type="text/javascrpit" src="node_modules/popper.js/dist/umd/popper.min.js"></script>
<script type="text/javascrpit" src="node_modules/bootstrap/dist/js/bootstrap.min.js"></script> -->
</body>

</html>