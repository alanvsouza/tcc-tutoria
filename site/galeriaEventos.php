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
            <a  href="index.php" id="close-galeria">Cotil Amigável</a>
            <!-- <span class="span-evento">Nome:</span>
            <input class="filtro-evento" id="filtro-evento-nome" type="text" name="filtro-nome" placeholder="Nome do evento">
            <span class="span-evento">Data:</span>
            <input class="filtro-evento" id="filtro-evento-data" type="date" name="filtro-data">
            <input id="btn-filtrar" type="button" value="Procurar"> -->
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
                <div class="modal-fotos">
                    <i id="close__galeria-fotos-1" class="fas fa-times close"></i>
                    <div class="arrows">
                        <a href="#" class="btn prev" id="galeria-fotos-1__prev">&#171;</a>
                        <a href="#" class="btn next" id="galeria-fotos-1__next">&#187;</a>
                        <span>01 / 05</span>
                    </div>
                    <div class="fotos-galeria" id="galeria-fotos-1__galeria">
                        <img src="img/evento1.jpg" class="fotos foto-visivel" id="galeria-fotos-1__foto-1" alt="">
                        <img src="img/evento2.jpg" class="fotos foto-invisivel" id="galeria-fotos-1__foto-2" alt="">
                    </div>
                </div>
            </div>

            <div class="card-novo">
                <a href="#" class="card-novo__card-link"></a>
                <img src="img/evento5.jpg" alt="" class="card-novo__image">
                <div class="card-novo__card-text">
                    <h2 class="card-novo__title">Evento 2</h2>
                    <div class="card-novo__card-date">21/02/2021</div>
                        <div class="card-novo__card-detalhes">
                            <p class="card-novo__card-descricao">Lorem ipsum dolor sit amet consectetur adipisicing elit. Est pariatur nemo tempore repellat? Ullam sed officia iure architecto deserunt distinctio, pariatur&hellip;</p>
                            <a href="#" id="galeria-fotos-2" class="card-novo__ver-fotos">Ver Fotos <i class="fas fa-long-arrow-alt-right"></i></a>
                        </div>
                </div>
            </div>

            <div class="container-fotos" id="modal-fotos__galeria-fotos-2">
                <div class="modal-fotos">
                    <i id="close__galeria-fotos-2" class="fas fa-times close"></i>
                    <div class="arrows">
                        <a href="" class="btn prev" id="prev">&#171;</a>
                        <a href="" class="btn next" id="next">&#187;</a>
                        <span>01 / 12</span>
                    </div>
                    <img src="img/evento3.jpg" class="fotos" id="img__galeria-fotos-3" alt="">
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
                <div class="modal-fotos">
                    <i id="close__galeria-fotos-3" class="fas fa-times close"></i>
                    <div class="arrows">
                        <a href="" class="btn prev" id="prev">&#171;</a>
                        <a href="" class="btn next" id="next">&#187;</a>
                        <span>01 / 05</span>
                    </div>
                    <img src="img/evento6.jpg" class="fotos" id="img__galeria-fotos-3" alt="">
                </div>
            </div>

            <div class="container-fotos" id="modal-fotos__galeria-fotos-4">
                <div class="modal-fotos">
                    <i id="close__galeria-fotos-4" class="fas fa-times close"></i>
                    <div class="arrows">
                        <a href="" class="btn prev" id="prev">&#171;</a>
                        <a href="" class="btn next" id="next">&#187;</a>
                        <span>01 / 05</span>
                    </div>
                    <img src="img/evento6.jpg" class="fotos" id="img__galeria-fotos-4" alt="">
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

            <div class="container-fotos" id="modal-fotos__galeria-fotos-5">
                <div class="modal-fotos">
                    <i id="close__galeria-fotos-5" class="fas fa-times close"></i>
                    <div class="arrows">
                        <a href="" class="btn prev" id="prev">&#171;</a>
                        <a href="" class="btn next" id="next">&#187;</a>
                        <span>01 / 05</span>
                    </div>
                    <img src="img/evento7.jpg" class="fotos" id="img__galeria-fotos-5" alt="">
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

            <div class="container-fotos" id="modal-fotos__galeria-fotos-6">
                <div class="modal-fotos">
                    <i id="close__galeria-fotos-6" class="fas fa-times close"></i>
                    <div class="arrows">
                        <a href="" class="btn prev" id="prev">&#171;</a>
                        <a href="" class="btn next" id="next">&#187;</a>
                        <span>01 / 05</span>
                    </div>
                    <img src="img/evento8.jpg" class="fotos" id="img__galeria-fotos-6" alt="">
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

            <div class="container-fotos" id="modal-fotos__galeria-fotos-7">
                <div class="modal-fotos">
                    <i id="close__galeria-fotos-7" class="fas fa-times close"></i>
                    <div class="arrows">
                        <a href="" class="btn prev" id="prev">&#171;</a>
                        <a href="" class="btn next" id="next">&#187;</a>
                        <span>01 / 05</span>
                    </div>
                    <img src="img/evento9.jpg" class="fotos" id="img__galeria-fotos-7" alt="">
                </div>
            </div>

    </div>
</div>
<script src="js/classEdit.js"></script>
<script src="js/galeriaEventos.js"></script>
</body>
</html>