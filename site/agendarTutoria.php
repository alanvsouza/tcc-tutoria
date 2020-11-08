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
    <link rel="stylesheet" href="css/agTutoria.css">
    <link rel="stylesheet" href="css/calendario.css">

    <!-- Font Awesome -->
    <link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Berkshire+Swash|Josefin+Sans&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Josefin+Sans&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.0-2/css/all.min.css">
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

    <div class="row container-body">
        <div class="content-tutor p-0 m-0 col-xl-6">
            <div class="header-tutor">
                <div class="row content-header-tutor">
                    <div class="row informacoes-tutor">
                        <div class="img-tutor"></div>
                        <div class="column reposicionar">
                            <span class="nome-tutor">José Tamióli</span>
                            <div class="informacoes row">
                                <span id="disciplinas">Física, Matemática, Mecânica e Química</span>
                            </div>
                        </div>
                    </div>
                    <div class="p-0 m-auto content-selecionar">
                        <select name="selecionar-tutor" id="selecionar-tutor">
                            <option value="volvo">Selecione um tutor</option>
                            <option value="saab">José Tamióli</option>
                            <option value="mercedes">Carlos Andrade</option>
                            <option value="audi">Jéssica Costa</option>
                        </select>
                    </div>
                </div>
            </div>

            <div class="content-agentar-tutoria mb-2">
                <div class="calendario">
                    <?php montaCalendario(); ?>
                </div>
            </div>
        </div>
        <div class="tabela-horarios-tutor col-xl-6">
            <ul class="responsive-table">
            <div id="data-tutoria" class="col">12 de outubro de 2020</div>
                <li class="table-header">
                    <div>Início</div>
                    <div>Término</div>
                    <div>Disponibilidade</div>
                    <div>Agendar</div>
                </li>
                <li class="table-row">
                    <div class="" data-label="10:00">10:00</div>
                    <div data-label="10:20">10:20</div>
                    <div data-label="Disponível">Disponível</div>
                    <form class="d-flex align-items-center"><input class="btn-selecionar disponivel" type="button" value="Selecionar"></form>
                </li>
                <li class="table-row">
                    <div data-label="12:00">12:00</div>
                    <div data-label="12:30">12:30</div>
                    <div data-label="Disponível">Disponível</div>
                    <form class="d-flex align-items-center"><input class="btn-selecionar disponivel" type="button" value="Selecionar"></form>
                </li>
                <li class="table-row">
                    <div data-label="12:35">12:35</div>
                    <div data-label="13:05">13:05</div>
                    <div data-label="Indiponível" style="cursor: none;">Indisponível</div>
                    <form class="d-flex align-items-center"><input class="btn-selecionar indisponivel" type="button" value="Selecionar"></form>
                </li>
                <li class="table-row">
                    <div data-label="15:00">15:00</div>
                    <div data-label="15:20">15:20</div>
                    <div data-label="Disponível">Disponível</div>
                    <form class="d-flex align-items-center"><input class="btn-selecionar disponivel" type="button" value="Selecionar"></form>
                </li>
            </ul>
            <div class="footer-tabela-horarios">
                <div class="col-xl-7 col-lg-7 col-md-7 col-sm-7 col-12 observacao">O local da tutoria será definido pelo tutor após este confirmar o agendamento da mesma.</div>
                <form action=""  class="col-xl-5 col-lg-5 col-md-5 col-sm-5 col-12">
                    <input type="button" id="btn-agendar" value="Agendar Tutoria">
                </form>
            </div>
        </div>
    </div>



    <!-- <div id="content">
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
    </div> -->

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