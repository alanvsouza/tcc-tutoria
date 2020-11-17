<?php
error_reporting(0);
require_once "vendor/autoload.php";
\App\Model\Session::startSession();

date_default_timezone_set('America/Sao_Paulo');

$nivelAcesso = isset($_SESSION['nivelAcesso']) ? $_SESSION['nivelAcesso'] : NULL;
\App\Helper\AcessoHelper::nivelAcesso($nivelAcesso, __FILE__);

if (!isset($_GET['data']) || $_GET['data'] == '')
    $_GET['data'] = date('Y-m-d', time());

include("functions/acesso.php");
?>

<!DOCTYPE html>
<html lang="pt-BR">

<head>
    <title>Cotíl Amigável - Tutorias Agendadas</title>

    <!-- Required meta tags -->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="css/style.min.css">

    <!-- My styles CSS -->
    <link rel="stylesheet" href="css/style.scss">
    <link rel="stylesheet" href="css/tutoriasAgendadas.css">
    <link rel="stylesheet" href="css/nav.css">
    <link rel="stylesheet" href="css/navside.css">


    <!-- Font Awesome -->
    <link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Berkshire+Swash|Josefin+Sans&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.0-2/css/all.min.css">

</head>

<body>
    <?php
    include("navTutor.php");
    include("navside.php");
    ?>

    <div class="content-body">
        <div class="content-header"></div>
        <div class="hidden-scrool">
            <section class="content-tutorias-agendadas">
                <div class="table-header">
                    <div class="title">Tutorias Agendadas</div>
                    <form class="filtrar">
                        <form action="<?= htmlspecialchars($_SERVER['PHP_SELF']) ?>">
                            <input type="date" value="<?= $_GET['data'] ?>" class="input-date" name="data" id="">
                            <button type="submit"><i class="fas fa-search"></i></button>
                        </form>
                    </form>
                </div>

                <div class='header-horarios'>
                    <span class='title-horarios'>Horários fixos</span>
                </div>

                <?php
                \App\Model\TutoriaService::renderizarTutoriasAgendadasTutor($_SESSION['idUsuario'], $_GET['data']);
                ?>

                <div class='header-horarios'>
                    <span class='title-horarios'>Horários dinâmicos</span>
                </div>

                <?php
                \App\Model\TutoriaService::renderizarTutoriasDinamicasAgendadasTutor($_SESSION['idUsuario'], $_GET['data']);
                ?>
            </section>
        </div>
    </div>

</body>

<!-- My JS -->

<script src="js/navside.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.11/jquery.mask.min.js"></script>
<script src="js/tutoriasAgendadas.js"></script>
<!-- 
<script type="text/javascript">
        $('.input-date').mask('00/00/0000');
</script> -->

</html>