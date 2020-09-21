<!doctype html>
<html lang="pt-br">

<head>
    <title>Cotil Amigável</title>

    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="css/style.min.css">

    <!-- My styles CSS -->
    <link href="css/style.scss">
    <link rel="stylesheet" href="css/imgEvents.css">
    <link rel="stylesheet" href="css/events.css">
    <link rel="stylesheet" href="css/nav.css">    
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="css/notification.css">
    <link rel="stylesheet" href="css/navside.css">

    <!-- Font Awesome -->
    <link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Berkshire+Swash|Josefin+Sans&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Josefin+Sans&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.0-2/css/all.min.css">

</head>

<body> 
    
    <?php 
        include("nav.php");
        include("navside.php");
    ?>

    <div class="img-events">
        <div>
            <h1>Cotil Amigável</h1> 
            <h2>Novos Eventos Toda Semana</h2>
            <h3>Roda de Conversa / Sessão Cinema e Muito Mais!</h3>
        </div>
    </div>

<section class="m-0">
<div class="shape-fill"></div>

    <div class="col-lg-12 flex-box" id="bem-vindo">

        <div class="col-lg-6 col-md-12 column-one">
            <h2>Bem-Vindo!</h2>
            <h3>Fique li&shy;ga&shy;do nos no&shy;vos even&shy;tos ati&shy;van&shy;do as no&shy;ti&shy;fi&shy;ca&shy;ções!</h3>
            <p>Os even&shy;tos são or&shy;ga&shy;ni&shy;za&shy;dos pela pro&shy;fe&shy;sso&shy;ra de en&shy;fer&shy;ma&shy;gem Daniele Zuza. Você pode acom&shy;panha-los consul&shy;tando nosso calen&shy;dário ou ver os pró&shy;xi&shy;mos even&shy;tos que ocorre&shy;rão logo abaixo.</p>
            <button class="mt-3" id="btn-ver-calendario">Ver Calendário</button>
        </div>

    <div class="col-lg-6 col-md-11 column-two"></div>
    </div>

</section>

<section id="prox-events" class="col-lg-12 col-12" style="border-top: 1px solid rgba(150, 150, 151,0.3);" >
    
    <header id="header-events" class="col-xl-10 col-lg-10 col-md-10 col-sm-11 col-11">
        <div class="info-box">
            <div class="direction">
                <div id="img-calendar"></div>
                    <div id="info-events">
                        <h2>Próximos Eventos</h2>
                        <h3>Esses são os pró&shy;xi&shy;mos even&shy;tos que es&shy;tão mar&shy;ca&shy;dos pa&shy;ra oco&shy;rrer.</h3>
                        <h4>Atualizado em 17 de novembro de 2021</h4>
                    </div>
            </div>
        </div>
    </header>

<article id="cards-events"  class="col-lg-12 col-12 ">

    <?php
    include("db/credentials.php");
    include("functions/eventos.php");
    ?>

</article>
<button id="btn-ver-galeriaEventos">Galeria de Eventos</button>
</section>

<div class="shape-fill2"></div>

<footer>
    <div class="img-footer">
            <div id="conteudo">
                <h2>Não Fique De Fora!</h2> 
                <h3>Experimente participar de um dos nossos eventos, garantimos uma experiência muito legal! </h3>
                
                <div id="links-footer p-0 m-auto" >
                <ul class="m-auto list-links p-0 m-auto"> 
                    <li><a class="actived" href="#">Como marcar uma Tutoria?</a></li>
                    <li><a href="#">Desenvolvedores</a></li>
                    <li><a href="https://www.cotil.unicamp.br/portal/">Colégio Técnico de Limeira</a></li>
                    <li><a href="projetoTutoria.php">Projeto Tutoria</a></li>
                    <li><a href="#">Esqueceu sua senha?</a></li>
                </ul>
            </div>

            <div id="copyright" class="p-0">
                <span class="p-0">@Copyright | Todos os Direitos Reservados ao COTIL</span>
            </div>
        </div>
    </div>
</footer>

    <notification id="notificacao-actived" class="notificacao noti-none">
        <span id="icone-notification"><i class="fa fa-bell"></i></span>
        <span id="status" class="font-weight-bold">Notificações Ativadas</span>
    </notification>

    <notification id="notificacao-disabled" class="notificacao" >
        <span id="icone-notification-disabled"><i class="fas fa-bell-slash"></i></span>
        <span id="status" class="font-weight-bold">Notificações Desativadas</span>
    </notification>


<!-- My JS -->
<script src="js/nav.js"></script>
<script src="js/navside.js"></script>
<script src="js/notification.js"></script>
<script src="js/classEdit.js"></script>
<script src="js/createCards.js"></script>

<!-- 
<script type="text/javascrpit" src="node_modules/jquery/dist/jquery.slim.min.js"></script>
<script type="text/javascrpit" src="node_modules/popper.js/dist/umd/popper.min.js"></script>
<script type="text/javascrpit" src="node_modules/bootstrap/dist/js/bootstrap.min.js"></script> -->


</body>
</html>