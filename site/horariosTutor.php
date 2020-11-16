<?php
require_once "vendor/autoload.php";
\App\Model\Session::startSession();
\App\Helper\AcessoHelper::nivelAcesso($_SESSION['nivelAcesso'], __FILE__);

include("functions/acesso.php");
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
    <link rel="stylesheet" href="css/style.min.css">

    <!-- My styles CSS -->
    <link href="css/style.scss">
    <link rel="stylesheet" href="css/horariosTutor.css">

    <!-- Font Awesome -->
    <link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Berkshire+Swash|Josefin+Sans&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Josefin+Sans&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.0-2/css/all.min.css">

</head>

<body>
    <div class="content-header">
        <a href="index.php" class="logo-site">Cotil Amigável</a>
    </div>
    <div class="content-body">
        <form method="POST" id="form-horarios-fixos" action="#" class="tabela-horarios-tutor">
            <div class="header-horarios">
                <span class="title-horarios">Horários fixos</span>
            </div>
            <?php
            \App\Model\TutorService::renderizarTabelaHorariosFixosTutor();
            ?>
        </form>
        <form method='POST' action="functions/atualizarHorariosFixosTutor.php" class="atualizar-horarios">
            <input type='hidden' id='dia-1' name='dia-1' class='input-horarios-fixos' value='' />
            <input type='hidden' id='dia-2' name='dia-2' class='input-horarios-fixos' value='' />
            <input type='hidden' id='dia-3' name='dia-3' class='input-horarios-fixos' value='' />
            <input type='hidden' id='dia-4' name='dia-4' class='input-horarios-fixos' value='' />
            <input type='hidden' id='dia-5' name='dia-5' class='input-horarios-fixos' value='' />
            <input type='hidden' id='dia-6' name='dia-6' class='input-horarios-fixos' value='' />

            <input id="btn-horarios-fixos" class="btn-submeter" type="button" value="Atualizar" />
            <input id="btn-submit-horarios-fixos" hidden type="submit" />
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
                    <input class="input-hora" disabled type="text" />
                    <input class="input-hora" disabled type="text" />
                    <input class="input-hora" disabled type="text" />
                    <input class="input-hora" disabled type="text" />
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
    <script src="js/horariosFixosProfessor.js"></script>

    <script type="text/javascript">
        $('.input-hora').mask('00:00 - 00:00');
        $('.input-date').mask('00/00/0000');
    </script>

</body>

</html>