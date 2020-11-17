<?php require_once('vendor/autoload.php') ?>

<!DOCTYPE html>
<html lang="pt-BR">

<head>
    <title>Galeria de Eventos</title>

    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="css/style.min.css">

    <!-- My CSS  -->
    <link rel="stylesheet" href="css/galeriaEventos.css">

    <!-- Font Awesome -->
    <link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.0-2/css/all.min.css">
</head>

<body>

    <div id="galeria-eventos" class="col-lg-12 visibilidade-modal">
        <div class="header-modal col-lg-12 row">
            <div class="col-lg-12 filtro">
                <a href="index.php" id="nome-site">Cotil Amigável</a>
                <!-- <span class="span-evento">Nome:</span>
            <input class="filtro-evento" id="filtro-evento-nome" type="text" name="filtro-nome" placeholder="Nome do evento">
            <span class="span-evento">Data:</span>
            <input class="filtro-evento" id="filtro-evento-data" type="date" name="filtro-data">
            <input id="btn-filtrar" type="button" value="Procurar"> -->
            </div>

        </div>

        <div class="content-wrapper">
            <?php \App\Model\EventoService::renderizarGaleriaEventos() ?>



        </div>
    </div>
    <script src="js/classEdit.js"></script>
    <script src="js/galeriaEventos.js"></script>
</body>

</html>