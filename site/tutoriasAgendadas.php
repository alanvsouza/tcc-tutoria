<?php
error_reporting(0);
require_once "vendor/autoload.php";
\App\Model\Session::startSession();

// $nivelAcesso = isset($_SESSION['nivelAcesso']) ? $_SESSION['nivelAcesso'] : NULL;
// \App\Helper\AcessoHelper::nivelAcesso($nivelAcesso, dirname(__FILE__), '/index.php');

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
                        <input type="date" value="dd/MM/yyyy" class="input-date" name="" id="">
                        <form>
                            <button type="submit"><i class="fas fa-search"></i></button>
                        </form>
                    </form>
                </div>
                <!--inicio do card -->
                <div class="table-body">
                    <div class="table-row">
                        <div class="tutoria-agendada">
                            <div class="tutoria-agendada-header">
                                <img src="img-professores/1.png" class="img-usuario" alt="">
                                <div class="column-header">
                                    <span class="nome-usuario-tutoria">José Tamióli de Andrade </span>
                                    <div class="line">
                                            <i class="far fa-envelope"></i>
                                            <span class="info email">josetamiolia@gmail.com</span>
                                        </div>
                                </div>
                            </div>
                            <div class="tutoria-agendada-body">
                                <div class="infos-tutoria-agendada">
                                    <div class="column">
                                        <div class="line">
                                            <i class="fas fa-calendar-alt"></i>
                                            <span class="info"> 17/10/2020</span>
                                        </div>
                                        <div class="line">
                                            <i class="far fa-clock"></i>
                                            <span class="info"> 12:30 - 13:00</span>
                                        </div>
                                    </div>
                                    <div class="column">
                                        <div class="line local">
                                            <i class="fas fa-map-marker-alt"></i>
                                            <span class="info">Sala 24</span>
                                        </div>
                                        <div class="line local">
                                        <i class="far fa-star"></i>
                                            <span class="info">Confirmada</span>
                                        </div>
                                    </div>
                                    <form class="form-btn-tutoria">
                                        <div class="line">
                                            <button type="submit" class="btn-cancelar-tutoria">Cancelar Tutoria</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!--fim do card-->
                <div class="table-body">
                    <div class="table-row">
                        <div class="tutoria-agendada">
                            <div class="tutoria-agendada-header">
                                <img src="img-alunos/1.jpeg" class="img-usuario" alt="">
                                <div class="column-header">
                                    <span class="nome-usuario-tutoria">Maria Pereira Costa Da Silva </span>
                                    <div class="row-header mt-1">
                                        <div class="line mr-5">
                                            <i class="fas fa-street-view"></i><span class="info ml-1">2° Ano</span>
                                        </div>
                                        <div class="line">
                                            <i class="far fa-address-card"></i><span class="info ml-1">Informática</span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="tutoria-agendada-body">
                                <div class="infos-tutoria-agendada">
                                    <div class="column">
                                        <div class="line">
                                            <i class="fas fa-calendar-alt"></i>
                                            <span class="info"> 17/10/2020</span>
                                        </div>
                                        <div class="line">
                                            <i class="far fa-clock"></i>
                                            <span class="info"> 12:30 - 13:00</span>
                                        </div>
                                    </div>
                                    <div class="column">
                                        <div class="line local">
                                            <i class="fas fa-map-marker-alt"></i>
                                            <input type="text" class="info input-local"></input>
                                        </div>
                                    </div>
                                    <form class="form-btn-tutoria">
                                        <div class="line">
                                            <button type="submit" class="btn-cancelar-tutoria">Confirmar Tutoria</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!--fim -->
                <div class="table-body">
                    <div class="table-row">
                        <div class="tutoria-agendada">
                            <div class="tutoria-agendada-header">
                                <img src="img-professores/1.png" class="img-usuario" alt="">
                                <div class="column-header">
                                    <span class="nome-usuario-tutoria">José Tamióli de Andrade </span>
                                    <div class="line">
                                        <i class="far fa-envelope"></i>
                                        <span class="info email">josetamiolia@gmail.com</span>
                                    </div>
                                </div>
                            </div>
                            <div class="tutoria-agendada-body">
                                <div class="infos-tutoria-agendada">
                                    <div class="column">
                                        <div class="line">
                                            <i class="fas fa-calendar-alt"></i>
                                            <span class="info"> 17/10/2020</span>
                                        </div>
                                        <div class="line">
                                            <i class="far fa-clock"></i>
                                            <span class="info"> 12:30 - 13:00</span>
                                        </div>
                                    </div>
                                    <div class="column">
                                        <div class="line local">
                                            <i class="fas fa-map-marker-alt"></i>
                                            <span class="info">Indefinido</span>
                                        </div>
                                    </div>
                                    <form class="form-btn-tutoria">
                                        <div class="line">
                                            <button type="submit" class="btn-cancelar-tutoria">Cancelar Tutoria</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!--fim do card-->
            </section>
        </div>
    </div>

</body>

<!-- My JS -->

<script src="js/navside.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.11/jquery.mask.min.js"></script>
<!-- 
<script type="text/javascript">
        $('.input-date').mask('00/00/0000');
</script> -->

</html>