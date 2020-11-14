<?php
require_once "../vendor/autoload.php";
\App\Model\Session::startSession();

$nivelAcesso = isset($_SESSION['nivelAcesso']) ? $_SESSION['nivelAcesso'] : NULL;
\App\Helper\AcessoHelper::nivelAcesso($nivelAcesso, dirname(__FILE__), '../index.php');

include("../functions/acesso.php");
?>

<!DOCTYPE html>
<html lang="pt-BR">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cotil Amigável - Definir Horários</title>

    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="../css/style.min.css">

    <!-- My styles CSS -->
    <link href="../css/style.scss">
    <link rel="stylesheet" href="../css/horariosTutor.css">

    <!-- Font Awesome -->
    <link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Berkshire+Swash|Josefin+Sans&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Josefin+Sans&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.0-2/css/all.min.css">

</head>

<body>

    <?php
    // include("../navTutor.php");
    // include("../navside.php");
    ?>

    <div class="content-header">
        <a href="../index.php" class="logo-site">Cotil Amigável</a>
    </div>
    <div class="content-body">
        <form method="GET" action="#" class="tabela-horarios-tutor">
            <div class="header-horarios">
                <span class="title-horarios">Horários dinâmicos</span>
            </div>
            <ul class="responsive-table more-width">
                <li class="table-row" id="segunda">
                    <div class="dia-semana">SEGUNDA</div>
                    <input class="input-hora" value="07:00 - 07:30" type="text"></input>
                    <input class="input-hora" type="text"></input>
                    <input class="input-hora" type="text"></input>
                    <input class="input-hora" type="text"></input>
                </li>
                <li class="table-row" id="terca">
                    <div class="dia-semana">TERÇA</div>
                    <input class="input-hora" type="text"></input>
                    <input class="input-hora" type="text"></input>
                    <input class="input-hora" type="text"></input>
                    <input class="input-hora" type="text"></input>
                </li>
                <li class="table-row" id="quarta">
                    <div class="dia-semana">QUARTA</div>
                    <input class="input-hora" type="text"></input>
                    <input class="input-hora" type="text"></input>
                    <input class="input-hora" type="text"></input>
                    <input class="input-hora" type="text"></input>
                </li>
                <li class="table-row" id="quinta">
                    <div class="dia-semana">QUINTA</div>
                    <input class="input-hora" type="text"></input>
                    <input class="input-hora" type="text"></input>
                    <input class="input-hora" type="text"></input>
                    <input class="input-hora" type="text"></input>
                </li>
                <li class="table-row" id="sexta">
                    <div class="dia-semana">SEXTA</div>
                    <input class="input-hora" type="text"></input>
                    <input class="input-hora" type="text"></input>
                    <input class="input-hora" type="text"></input>
                    <input class="input-hora" type="text"></input>
                </li>
                <li class="table-row" id="sabado">
                    <div class="dia-semana">SÁBADO</div>
                    <input class="input-hora" type="text"></input>
                    <input class="input-hora" type="text"></input>
                    <input class="input-hora" type="text"></input>
                    <input class="input-hora" type="text"></input>
                </li>
            </ul>
        </form>
        <form action="" class="atualizar-horarios">
            <input id="btn-horarios-dinamicos" class="btn-submeter" type="button" value="Atualizar">
        </form>
    </div>

    <div class="content-body">
        <form method="GET" action="#" class="tabela-horarios-tutor">
            <div class="header-horarios">
                <span class="title-horarios">Horários dinâmicos</span>
                <div class="div-horarios-dinamicos">
                    <input type="date" name="input-horarios-dinamico" id="input-horarios-dinamico">
                    <form>
                        <button type="submit" id="btn-consultar-horarios"><i class="fas fa-search"></i></button>
                    </form>
                </div>
            </div>
            <ul class="responsive-table">
                <li class="table-row" id="dia-semana">
                    <div class="dia-semana">Day of Week</div>
                    <input class="input-hora" disabled type="text"></input>
                    <input class="input-hora" disabled type="text"></input>
                    <input class="input-hora" disabled type="text"></input>
                    <input class="input-hora" disabled type="text"></input>
                </li>
            </ul>
        </form>
        <form action="" class="atualizar-horarios">
            <input id="btn-horarios-dinamicos" class="btn-submeter" type="button" value="Atualizar">
        </form>
    </div>
    <div class="content-header2"></div>


    <!-- <div class="content-footer">
                    <span class="title-horarios-dinamicos">Horário dinâmico:</span>
                    <input class="input-date" value="10/12/2020" type="text"></input>
                    <input class="input-hora" value="10/12/2020" type="text"></input>
        </div> -->


    <!-- 
<script src="js/navside.js"></script>  -->

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.11/jquery.mask.min.js"></script>

    <script type="text/javascript">
        $('.input-hora').mask('00:00 - 00:00');
        $('.input-date').mask('00/00/0000');
    </script>

</body>

</html>