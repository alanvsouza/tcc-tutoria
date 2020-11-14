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
    <title>Cotíl Amigável - tutorias agendadas</title>

    <!-- Required meta tags -->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1,  minimun-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="../css/style.min.css">

    <!-- My styles CSS -->
    <link rel="stylesheet"  href="../css/style.scss">
    <link rel="stylesheet" href="../css/tutoriasAgendadas.css">

    <!-- Font Awesome -->
    <link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Berkshire+Swash|Josefin+Sans&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.0-2/css/all.min.css">

</head>
<body>
    
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
                <div class="table-body">
                    <div class="table-row">
                        <div class="tutoria-agendada">
                            <div class="tutoria-agendada-header">
                                <img src="" alt="">
                            </div>
                            <div class="tutoria-agendada-body"></div>
                        </div>
                    </div>
                    <div class="table-row">
                        <div class="tutoria-agendada">
                            <div class="tutoria-agendada-header">
                                <img src="" alt="">
                            </div>
                            <div class="tutoria-agendada-body"></div>
                        </div>
                    </div>
                    <div class="table-row">
                        <div class="tutoria-agendada">
                            <div class="tutoria-agendada-header">
                                <img src="" alt="">
                            </div>
                            <div class="tutoria-agendada-body"></div>
                        </div>
                    </div>
                    <div class="table-row">
                        <div class="tutoria-agendada">
                            <div class="tutoria-agendada-header">
                                <img src="" alt="">
                            </div>
                            <div class="tutoria-agendada-body"></div>
                        </div>
                    </div>
                    <div class="table-row">
                        <div class="tutoria-agendada">
                            <div class="tutoria-agendada-header">
                                <img src="" alt="">
                            </div>
                            <div class="tutoria-agendada-body"></div>
                        </div>
                    </div>
                    <div class="table-row the-end">
                        <div class="tutoria-agendada">
                            <div class="tutoria-agendada-header">
                                <img src="" alt="">
                            </div>
                            <div class="tutoria-agendada-body"></div>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    </div>

</body>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.11/jquery.mask.min.js"></script>
<!-- 
<script type="text/javascript">
        $('.input-date').mask('00/00/0000');
</script> -->

</html>