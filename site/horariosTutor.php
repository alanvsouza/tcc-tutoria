<?php
require_once "vendor/autoload.php";
\App\Model\Session::startSession();
\App\Helper\AcessoHelper::nivelAcesso($_SESSION['nivelAcesso'], __FILE__);

include("functions/acesso.php");

$diasSemanaNomes = array(
    1 => 'SEGUNDA',
    2 => 'TERÇA',
    3 => 'QUARTA',
    4 => 'QUINTA',
    5 => 'SEXTA',
    6 => 'SÁBADO',
    7 => 'DOMINGO'
);

if ($_GET['data-horario-dinamico'] == "" || !isset($_GET['data-horario-dinamico']))
    $_GET['data-horario-dinamico'] = date('Y-m-d', time());

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
        <!-- DFJSOIFJSDOIFJSOIDFJSOIDFJ -->
        <form method="POST" id="form-horarios-fixos" action="#" class="tabela-horarios-tutor">
            <div class="header-horarios">
                <span class="title-horarios">Horários fixos</span>
            </div>
            <?php
            \App\Model\TutorService::renderizarTabelaHorariosFixosTutor();
            ?>
        </form>
        <!-- SDKFHSIADFHIUSDFHUISDHF -->
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
        <form id='form-horarios-dinamicos' class="tabela-horarios-tutor">
            <div class="header-horarios">
                <span class="title-horarios">Horários dinâmicos</span>
                <div class="div-horarios-dinamicos">
                    <form>
                        <input type="date" id="input-horarios-dinamico" <?php
                                                                        if (isset($_GET['data-horario-dinamico']))
                                                                            echo "value='{$_GET['data-horario-dinamico']}'";
                                                                        ?>>
                        <button type="button" id="btn-consultar-horarios"><i class="fas fa-search"></i></button>
                    </form>
                </div>
            </div>
            <ul class="responsive-table">
                <li class="table-row" id="dia-semana">

                    <?php
                    if (isset($_GET['data-horario-dinamico'])) :
                        $dataSelecionada = $_GET['data-horario-dinamico']; ?>

                        <div class="dia-semana">
                            <?= $diasSemanaNomes[DateTime::createFromFormat('Y-m-d', $_GET['data-horario-dinamico'])->format('N')] ?>
                        </div>

                    <?php
                        \App\Model\TutorService::renderizarInputsHorarioDinamico($_GET['data-horario-dinamico']);
                    else :
                        $dataSelecionada = ""; ?>

                        <div class="dia-semana">SELECIONE UMA DATA!</div>

                    <?php endif; ?>
                </li>
            </ul>
        </form>
        <form id="form-consultar-data" action="<?= htmlspecialchars($_SERVER['PHP_SELF']) ?>" method="get">
            <input id="input-horarios-dinamico-hidden" type="hidden" name="data-horario-dinamico" />
        </form>
        <form method="POST" action="functions/atualizarHorariosDinamicosTutor.php" class="atualizar-horarios">
            <input name="data" type="hidden" value="<?= $dataSelecionada ?>">
            <input id="input-atualizar-horarios-dinamicos" name="horarios" type="hidden" value="">
            <input id="btn-atualizar-horarios-dinamicos" class="btn-submeter" type="button" value="Atualizar">
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
    <script src="js/horariosDinamicosProfessor.js"></script>
    <script src="js/consultarDataHorariosDinamicos.js"></script>

    <script type="text/javascript">
        $('.input-hora').mask('00:00 - 00:00');
        $('.input-date').mask('00/00/0000');
    </script>

</body>

</html>