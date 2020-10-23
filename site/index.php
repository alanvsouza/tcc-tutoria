<?php        
    require_once "vendor/autoload.php";
    \App\Model\Session::startSession();
?>

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
    <link rel="stylesheet" href="css/galeriaEventos.css">
    <link rel="stylesheet" href="css/cardsEventos.css">

    <!-- Font Awesome -->
    <link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Berkshire+Swash|Josefin+Sans&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Josefin+Sans&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.0-2/css/all.min.css">

    <!-- FavIcon -->
    <link rel="icon" href="img/logoSite.jpg">
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
            <p>Os even&shy;tos são or&shy;ga&shy;ni&shy;za&shy;dos pela pro&shy;fe&shy;sso&shy;ra de en&shy;fer&shy;ma&shy;gem Daniele Zuza. Você pode acom&shy;panha-los ati&shy;van&shy;do as noti&shy;fica&shy;ções e con&shy;fer&shy;indo os pró&shy;xi&shy;mos even&shy;tos que ocorre&shy;rão logo abaixo.</p>
            <button class="mt-2" id="btn-ver-eventos">Ver Eventos</button>
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
        </div>
    </header>

<div class="container">
    <?php 
        error_reporting(6143);
    
        \App\Model\EventoService::renderizarEventos();
        $eventoDao = new \App\Model\EventoDAO();

    ?>
</div>

<button id="btn-ver-galeriaEventos">Galeria de Eventos</button>

<div id="modal-galeria-eventos" class="col-lg-12 visibilidade-modal">
    <div class="header-modal col-lg-12 row">
        <div class="col-lg-12 filtro">
            <!-- <span class="span-evento">Nome:</span>
            <input class="filtro-evento" id="filtro-evento-nome" type="text" name="filtro-nome" placeholder="Nome do evento">
            <span class="span-evento">Data:</span>
            <input class="filtro-evento" id="filtro-evento-data" type="date" name="filtro-data">
            <input id="btn-filtrar" type="button" value="Procurar"> -->
            <i id="close-galeria" class="fas fa-times"></i>
        </div>

    </div>

    <div class="content-wrapper">
            <div class="card-novo">
                <a href="#" class="card-novo__card-link"></a>
                <img src="img/evento4.jpg" class="card-novo__image">
                <div class="card-novo__card-text">
                    <h2 class="card-novo__title">Evento 1</h2>
                    <div class="card-novo__card-date">29/02/2021</div>
                        <div class="card-novo__card-detalhes">
                            <p class="card-novo__card-descricao">Lorem ipsum dolor sit amet consectetur adipisicing elit. Est pariatur nemo tempore repellat? Ullam sed officia iure architecto deserunt distinctio, pariatur&hellip;</p>
                            <a href="#" id="galeria-fotos-1" class="card-novo__ver-fotos">Ver Fotos <i class="fas fa-long-arrow-alt-right"></i></a>
                        </div>
                </div>
            </div>

            <div class="container-fotos" id="modal-fotos__galeria-fotos-1">
                <div class="modal-fotos">Galeria 1
                    <i id="close__galeria-fotos-1" class="fas fa-times close"></i>
                </div>
            </div>

            <div class="card-novo">
                <a href="#" class="card-novo__card-link"></a>
                <img src="img/evento5.jpg" alt="" class="card-novo__image">
                <div class="card-novo__card-text">
                    <h2 class="card-novo__title">Evento 2</h2>
                    <div class="card-novo__card-date">22/02/2021</div>
                        <div class="card-novo__card-detalhes">
                            <p class="card-novo__card-descricao">Lorem ipsum dolor sit amet consectetur adipisicing elit. Est pariatur nemo tempore repellat? Ullam sed officia iure architecto deserunt distinctio, pariatur&hellip;</p>
                            <a href="#"  id="galeria-fotos-2" class="card-novo__ver-fotos">Ver Fotos <i class="fas fa-long-arrow-alt-right"></i></a>
                        </div>
                </div>
            </div>

            <div class="container-fotos" id="modal-fotos__galeria-fotos-2">
                <div class="modal-fotos">Galeria 2
                    <i id="close__galeria-fotos-2" class="fas fa-times close"></i>
                </div>
            </div>

            <div class="card-novo">
                <a href="#" class="card-novo__card-link"></a>
                <img src="img/evento6.jpg" alt="" class="card-novo__image">
                <div class="card-novo__card-text">
                    <h2 class="card-novo__title">Evento 3</h2>
                    <div class="card-novo__card-date">15/02/2021</div>
                        <div class="card-novo__card-detalhes">
                            <p class="card-novo__card-descricao">Lorem ipsum dolor sit amet consectetur adipisicing elit. Est pariatur nemo tempore repellat? Ullam sed officia iure architecto deserunt distinctio, pariatur&hellip;</p>
                            <a href="#" id="galeria-fotos-3" class="card-novo__ver-fotos">Ver Fotos <i class="fas fa-long-arrow-alt-right"></i></a>
                        </div>
                </div>
            </div>

             <div class="container-fotos" id="modal-fotos__galeria-fotos-3">
                <div class="modal-fotos">Galeria 3
                    <i id="close__galeria-fotos-3" class="fas fa-times close"></i>
                </div>
            </div>


            <div class="card-novo">
                <a href="#" class="card-novo__card-link"></a>
                <img src="img/evento7.jpg" alt="" class="card-novo__image">
                <div class="card-novo__card-text">
                    <h2 class="card-novo__title">Evento 4</h2>
                    <div class="card-novo__card-date">09/02/2021</div>
                        <div class="card-novo__card-detalhes">
                            <p class="card-novo__card-descricao">Lorem ipsum dolor sit amet consectetur adipisicing elit. Est pariatur nemo tempore repellat? Ullam sed officia iure architecto deserunt distinctio, pariatur&hellip;</p>
                            <a href="#"  id="galeria-fotos-4"  class="card-novo__ver-fotos">Ver Fotos <i class="fas fa-long-arrow-alt-right"></i></a>
                        </div>
                </div>
            </div>

            <div class="container-fotos" id="modal-fotos__galeria-fotos-4">
                <div class="modal-fotos">Galeria 4
                    <i id="close__galeria-fotos-4" class="fas fa-times close"></i>
                </div>
            </div>


            <div class="card-novo">
                <a href="#" class="card-novo__card-link"></a>
                <img src="img/evento8.jpg" alt="" class="card-novo__image">
                <div class="card-novo__card-text">
                    <h2 class="card-novo__title">Evento 5</h2>
                    <div class="card-novo__card-date">02/02/2021</div>
                        <div class="card-novo__card-detalhes">
                            <p class="card-novo__card-descricao">Lorem ipsum dolor sit amet consectetur adipisicing elit. Est pariatur nemo tempore repellat? Ullam sed officia iure architecto deserunt distinctio, pariatur&hellip;</p>
                            <a href="#"  id="galeria-fotos-5"  class="card-novo__ver-fotos">Ver Fotos <i class="fas fa-long-arrow-alt-right"></i></a>
                        </div>
                </div>
            </div>

             <div class="container-fotos" id="modal-fotos__galeria-fotos-5">
                <div class="modal-fotos">Galeria 5
                    <i id="close__galeria-fotos-5" class="fas fa-times close"></i>
                </div>
            </div>

            <div class="card-novo">
                <a href="#" class="card-novo__card-link"></a>
                <img src="img/evento9.jpg" alt="" class="card-novo__image">
                <div class="card-novo__card-text">
                    <h2 class="card-novo__title">Evento 6</h2>
                    <div class="card-novo__card-date">10/12/2020</div>
                        <div class="card-novo__card-detalhes">
                            <p class="card-novo__card-descricao">Lorem ipsum dolor sit amet consectetur adipisicing elit. Est pariatur nemo tempore repellat? Ullam sed officia iure architecto deserunt distinctio, pariatur&hellip;</p>
                            <a href="#" id="galeria-fotos-6" class="card-novo__ver-fotos">Ver Fotos <i class="fas fa-long-arrow-alt-right"></i></a>
                        </div>
                </div>
            </div>

             <div class="container-fotos" id="modal-fotos__galeria-fotos-6">
                    <div class="modal-fotos">Galeria 6
                        <i id="close__galeria-fotos-6" class="fas fa-times close"></i>
                    </div>
            </div>
    </div>
</div>

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
<!-- <script src="js/nav.js"></script> -->
<script src="js/navside.js"></script>
<script src="js/notification.js"></script>
<script src="js/classEdit.js"></script>
<script src="js/galeriaEventos.js"></script>

<!-- 
<script type="text/javascrpit" src="node_modules/jquery/dist/jquery.slim.min.js"></script>
<script type="text/javascrpit" src="node_modules/popper.js/dist/umd/popper.min.js"></script>
<script type="text/javascrpit" src="node_modules/bootstrap/dist/js/bootstrap.min.js"></script> -->


</body>
</html>