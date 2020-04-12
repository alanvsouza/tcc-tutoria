<!doctype html>
<html lang="pt-br">

<head>
    <title>Cotil Amigável</title>

    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="css/style.min.css">

    <!-- My styles CSS -->
    <link href="css/style.scss">
    <link rel="stylesheet" href="css/imgEvents.css">
    <link rel="stylesheet" href="css/events.css">
    <link rel="stylesheet" href="css/nav.css">    
    <link rel="stylesheet" href="css/login.css">
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="css/navside.css">

    <!-- Font Awesome -->
    <link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Berkshire+Swash|Josefin+Sans&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Josefin+Sans&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

</head>

<body> 
    
    <?php 
        include("nav.php");
    ?>

    <?php 
        include("navside.php");
    ?> 

    <?php 
        include("login.php");
    ?>

    <div class="img-events">
        <div>
            <h1 style="position: relative;">Cotil Amigável</h1> 
            <h2>Novos Eventos Toda Semana</h2>
            <h3>Roda de Conversa / Sessão Cinema e Muito Mais!</h3>
        </div>
    </div>

<section>
<div class="shape-fill"></div>

    <div class="col-lg-12 flex-box"  >

        <div class="col-lg-6 col-md-12 column-one">
            <h2>Bem-Vindo!</h2>
            <h3>Fique ligado nos novos eventos ativando as notificações!</h3>
            <p>Os eventos são organizados pela professora de enfermagem Daniele Zuza. Você pode acompanha-los consultado nosso calendário ou ver os próximos eventos que ocorrerão logo abaixo.</p>
            <button class="mt-3" id="btn-ver-calendario">Ver Calendário</button>
        </div>

    <div class="col-lg-6 col-md-12 column-two"></div>

    </div>

</section>

<section id="prox-events" class="col-lg-12" style="padding-bottom: 120px;">
    
    <header id="header-events" class="col-lg-12">
        <div class="info-box">
            <div class="direction">
                <div id="img-calendar" ></div>
                    <div class="" id="info-events">
                        <h2>Próximos Eventos</h2>
                        <h3>Esses são os próximos eventos que estão marcados para ocorrer.</h3>
                        <h4>Atualizado em 23 de março de 2020</h4>
                    </div>
            </div>
        </div>
    </header>

<article id="cards-events" class="col-lg-12">

    <div class="col-lg-4 col-md-6 col-sm-6 col-12" id="card1">
        <div class="card mb-3">
            <img src="img/fundoRosa.png" class="card-img-top" alt="...">
                <div class="card-body">
                    <h5 class="card-title">Roda de Conversa</h5>
                    <hr>
                    <p class="card-text card-content">This is a wider card with supporting text below as a natural lead-in to additional content. This content  is a little bit longer.</p>
                    <div class="row">
                        <span  class="card-text"><small class="details">Data: 03/05/2020 |</small></span>
                        <span  class="card-text"><small class="details">Hora: 12:05 - 12:50 |</small></span>
                        <span class="card-text"><small class="details">Local: Sala 14</small></span>
                    </div>
                </div>
        </div>
    </div>


    <div class="col-lg-4 col-md-6 col-sm-6 col-12" id="card2">
        <div class="card mb-3">
            <img src="img/fundoPreto.png" class="card-img-top" alt="...">
                <div class="card-body">
                    <h5 class="card-title">Sessão Cinema</h5>
                    <hr>
                    <p class="card-text card-content">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                    <div class="row">
                        <span  class="card-text"><small class="details">Data: 03/05/2020 |</small></span>
                        <span  class="card-text"><small class="details">Hora: 12:05 - 12:50 |</small></span>
                        <span class="card-text"><small class="details">Local: Sala 14</small></span>
                    </div>
                </div>
        </div>
    </div>


    <div class="col-lg-4 col-md-6 col-sm-6 col-12" id="card3">
        <div class="card mb-3">
            <img src="img/fundoLaranja.jpg" class="card-img-top" alt="...">
                <div class="card-body">
                    <h5 class="card-title">Desabafo Grupal</h5>
                    <hr>
                    <p class="card-text card-content">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                    <div class="row">
                        <span  class="card-text"><small class="details">Data: 03/05/2020 |</small></span>
                        <span  class="card-text"><small class="details">Hora: 12:05 - 12:50 |</small></span>
                        <span class="card-text"><small class="details">Local: Sala 14</small></span>
                    </div>
                </div>
        </div>
    </div>

</article>
</section>

<footer>
    <div class="shape-fill2"></div>
    <div class="img-footer">
        <div id="footer-content">
            <h1>Não Fique De Fora!</h1> 
            <h3>Experimente participar de um dos nossos eventos, garantimos uma experiência muito legal! </h3>
        </div>

        <div id="links-footer" >
            <ul class="m-auto list-links"> 
                <li><a class="actived" href="#">Como marcar uma Tutoria?</a></li>
                <li><a href="#">Desenvolvedores</a></li>
                <li><a href="https://www.cotil.unicamp.br/portal/">Colégio Técnico de Limeira</a></li>
                <li><a href="projetoTutoria.php">Projeto Tutoria</a></li>
                <li><a href="#">Esqueceu sua senha?</a></li>
            </ul>
        </div>

        <div id="copyright">
            <span>@Copyright | Todos os Direitos Reservados ao COTIL</span>
        </div>

    </div>
</footer>

    <!-- <notification id="notificacao-actived" class="notificacao noti-none">
        <span id="icone-notification"><i class="fa fa-bell"></i></span>
        <span id="status" class="font-weight-bold" >Notificações Ativadas</span>
    </notification>

    <notification id="notificacao-disabled" class="notificacao" >
        <span id="icone-notification-disabled"><i class="fas fa-bell-slash"></i></span>
        <span id="status" class="font-weight-bold">Notificações Desativadas</span>
    </notification> -->


<!-- My JS -->
<script src="js/nav.js"></script>
<script src="js/login.js"></script>
<script src="js/events.js"></script>
<script src="js/navside.js"></script>
<script type="text/javascrpit" src="node_modules/jquery/dist/jquery.slim.min.js"></script>
<script type="text/javascrpit" src="node_modules/popper.js/dist/umd/popper.min.js"></script>
<script type="text/javascrpit" src="node_modules/bootstrap/dist/js/bootstrap.min.js"></script>


</body>
</html>