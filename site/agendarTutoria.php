<?php
include 'calendario.php';
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

</head>
<body>


<?php 
    include("nav.php");
    include("navside.php");
?>

<div id="content">
    <div id="sidebar">
        <ul id="list-profs">
            <li> 
                <div class="col-lg-12 p-0 m-0 row"  id="info-prof">
                <img src="img/pessoa1.png" class="image col-lg-5" alt="">
                    <div id="dados" class="col-lg-7" >
                        <div id="nome-professor">José Tamioli</div>
                        <button id="btn-colsultar-horarios"> Consultar horários </button>
                    </div>
                </div>
            </li>
            <li>
                <div class="col-lg-12 p-0 m-0 row"  id="info-prof">
                <img src="img/pessoa2.png" class="image col-lg-5" alt="">
                    <div id="dados" class="col-lg-7" >
                        <div id="nome-professor">Carlos Alberto</div>
                        <button id="btn-colsultar-horarios"> Consultar horários </button>
                    </div>
                </div>
            </li>
            <li>
            <div class="col-lg-12 p-0 m-0 row"  id="info-prof">
                <img src="img/pessoa3.png" class="image col-lg-5" alt="">
                    <div id="dados" class="col-lg-7" >
                        <div id="nome-professor">Maria Joaquina</div>
                        <button id="btn-colsultar-horarios"> Consultar horários </button>
                    </div>
                </div>
            </li>
            <li>
            <div class="col-lg-12 p-0 m-0 row"  id="info-prof">
                <img src="img/pessoa4.png" class="image col-lg-5" alt="">
                    <div id="dados" class="col-lg-7" >
                        <div id="nome-professor">Alberto Machado</div>
                        <button id="btn-colsultar-horarios"> Consultar horários </button>
                    </div>
                </div>
            </li>
            <li>
            <div class="col-lg-12 p-0 m-0 row"  id="info-prof">
                <img src="img/pessoa5.png" class="image col-lg-5" alt="">
                    <div id="dados" class="col-lg-7" >
                        <div id="nome-professor">Gustavo Coelho</div>
                        <button id="btn-colsultar-horarios"> Consultar horários </button>
                    </div>
                </div>
            </li>
            <li>
            <div class="col-lg-12 p-0 m-0 row"  id="info-prof">
                <img src="img/pessoa6.png" class="image col-lg-5" alt="">
                    <div id="dados" class="col-lg-7" >
                        <div id="nome-professor">Jessica Costa</div>
                        <button id="btn-colsultar-horarios"> Consultar horários </button>
                    </div>
                </div>
            </li>
            <li>
            <div class="col-lg-12 p-0 m-0 row"  id="info-prof">
                <img src="img/pessoa7.png" class="image col-lg-5" alt="">
                    <div id="dados" class="col-lg-7" >
                        <div id="nome-professor">Pedro Andrade</div>
                        <button id="btn-colsultar-horarios"> Consultar horários </button>
                    </div>
                </div>
            </li>
        </ul>
    </div>
    <div id="body">
        <div id="professor" class="m-0 p-0">
            <div class="col-lg-12 m-0" id="description">
                <div id="header-description">
                    <h3>José Tamioli</h3>
                    <a id="ver-perfil" href="#">Ver Perfil</a>
                </div>
                <div id="body-description"> 
                    <span>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.</span>
                </div>
            </div>
        </div>

        <div class="col-lg-12">
            <div id="agendar-tutoria"  class="col-lg-12" >
                <div class="calendario" class="col-lg-4 col-md-4 col-sm-7 col-11">
                    <?php montaCalendario();?>
                </div>

                <div class="col-lg-7 col-md-7 col-sm-11 col-11 column" id="horarios-professor">
                        <span style="font-size:17px;font-weight: 600;">Data selecionada:</span> <input type="text" disabled value="20/09/2020" id="data-selecionada">
                        <table id="table-professor">
                            <tr bgcolor="#f2f2f2"><th >Inicio</><th>Término</th><th>Local</th></tr>
                            <tr class="ativado"><td >12:00</td><td>12:45</td><td>Sala 10</td></tr>
                            <tr class="ativado"><td>13:00</td><td>13:40</td><td>Sala 11</td></tr>
                            <tr class="desativado"><td>15:30</td><td>15:45</td><td>Sala 4</td></tr>
                            <tr class="ativado"><td >14:00</td><td>15:00</td><td>Sala 7</td></tr>
                        </table>
                </div>
            </div>
            <div class="col-lg-12 column"><input type="button" id="btn-agendar" value="Agendar Tutoria"></div>
        </div>

        
    </div>
</div>

<script type="text/javascript" src="js/nav.js"></script>
<script type="text/javascript" src="js/navside.js"></script>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/calendario.js"></script>

<?php
echo '<script  type="text/javascript">function teste(teste){console.log(teste)}</script>';
?>

<!-- <script type="text/javascrpit" src="node_modules/jquery/dist/jquery.slim.min.js"></script>
<script type="text/javascrpit" src="node_modules/popper.js/dist/umd/popper.min.js"></script>
<script type="text/javascrpit" src="node_modules/bootstrap/dist/js/bootstrap.min.js"></script> -->
</body>
</html>