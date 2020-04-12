<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cotil Amigável - Projeto Tutoria</title>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="css/style.min.css">

    <!-- My styles CSS -->
    <link href="css/style.scss">
    <link rel="stylesheet" href="css/nav.css"> 
    <link rel="stylesheet" href="css/imgEvents.css">   
    <link rel="stylesheet" href="css/login.css">
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="css/projetoTutoria.css">
    <link rel="stylesheet" href="css/navside.css">
    <link rel="stylesheet" href="css/style_Envolvidos.css">

    <!-- Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.4.1.js"></script> 

    <!-- Font Awesome -->
    <link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Berkshire+Swash|Josefin+Sans&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Josefin+Sans&display=swap" rel="stylesheet">
    <script src="https://kit.fontawesome.com/bc2be06805.js"></script>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

    <!-- Lodash -->
    <script src="https://cdn.jsdelivr.net/npm/lodash@4.17.15/lodash.min.js"></script>
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

        <div class="img-events" >
            <div>
                <h1 style="position: relative;">Projeto Tutoria</h1> 
                <h2>Consulte os Horários e Marque uma Tutoria! </h2>
                <h3>Nossos Tutores são Professores do COTIL</h3>
            </div>
        </div>

<section>
    <div class="shape-fill"></div>
        <div class="col-lg-12 flex-box"  >
            <div class="col-lg-6 col-md-12 column-one">
                <h3>O que é uma Tutoria?</h3>
                <p> Segundo Argüís “A Tutoria é uma atividade inerente à função do professor, que se
                    realiza individual e coletivamente com os alunos de uma sala de aula, a fim de facilitar a
                    integração pessoal nos processos de aprendizagem.”</p>

                <p>O autor continua definindo a ação da tutoria: “como a ação de ajuda ou orientação ao aluno
                    que o professor pode realizar além de sua própria ação docente e paralelamente a ela”.</p>

                <p>Neste sentido, a tutoria consiste em um processo de acompanhamento durante a formação
                    dos estudantes, que se concretiza mediante a atenção personalizada a um aluno ou a um
                    grupo reduzido de alunos, acompanhados por professores nomeados para tal atividade do
                    curso ou disciplina em questão, apoiando-se nas teorias de aprendizagem.</p>
            </div>

            <div class="col-lg-6 col-md-12 column-two"></div>
        </div>

        <div class="col-lg-12 col-md-12 column-three">
            <p style="margin-top:  10px;">Portanto, considera-se uma modalidade da atividade docente, que compreende um conjunto
                sistematizado de ações educativas centradas no aluno envolvido no programa, a ser
                desenvolvido em espaços e tempos diferentes do horário de aula.
                A Tutoria pretende orientar e dar segmento ao desenvolvimento dos alunos, apoiando-os nos
                aspectos cognitivos e afetivos da relação ensino / aprendizagem. Buscará fomentar sua
                capacidade crítica e criadora em seu rendimento acadêmico, bem como, aperfeiçoar sua
                evolução social e pessoal.</p>
        </div>
</section>

<header id="header-events" class="col-lg-12 envolvidos">
    <div class="info-box">
        <div class="direction">
            <div id="img-tutoria"></div>
                <div  id="info-events">
                    <h2>Professores envolvidos</h2>
                    <h3 style="margin-bottom: 0px;">Esses são os professores do Colégio Técnico de Limeira que temos como tutores.</h3>
                </div>
        </div>
    </div>
</header>

    <section class="envolvidos" class="col-lg-12">

            <div class="cards col-lg-12 col-sm-12">
                <article class="card-prof col-xl-5 col-lg-9 col-md-9 col-sm-11 col-11">
                    <div class="imagem  col-lg-5 col-md-5 col-sm-5 col-12">
                        <img src="img/pessoa1.png" alt="">
                    </div>
                    <div class="col-lg-7 col-md-7 col-sm-7 col-12 information-main">
                        <header>
                            <h1>José Alberto Tamioli</h1>
                        </header>
                        <span>
                            Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem k a galley of type and scrambled it to make a type specimen 
                        </span>
                        <ul class="rede-social">
                            <li><a href="#"><i class="fab fa-facebook-square"></i></a></li>
                            <li><a href="#"><i class="fab fa-twitter-square"></i></a></li>
                            <li><a href="#"><i class="fab fa-google-plus-square"></i></a></li>
                        </ul>
                    </div>
                </article>
        
                <article class="card-prof col-xl-5 col-lg-9 col-md-9 col-sm-11 col-11">
                    <div class="imagem col-lg-5 col-md-5 col-sm-5 col-12">
                        <img src="img/pessoa2.png" alt="">
                    </div>
                    <div class="col-lg-7 col-md-7 col-sm-7 col-12 information-main">
                        <header>
                            <h1>Carlos Alberto</h1>
                        </header>
                        <span>
                            Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem k a galley of type and scrambled it to make a type specimen Lorem Ipsum is simply dummy text of the printing and typesetting industry and scrambled it. 
                        </span>
                        <ul class="rede-social">
                            <li><a href="#"><i class="fab fa-facebook-square"></i></a></li>
                            <li><a href="#"><i class="fab fa-twitter-square"></i></a></li>
                            <li><a href="#"><i class="fab fa-google-plus-square"></i></a></li>
                        </ul>
                    </div>
                </article>
        
                <article class="card-prof col-xl-5 col-lg-9 col-md-9 col-sm-11 col-11" >
                    <div class="imagem col-lg-5 col-md-5 col-sm-5 col-12">
                        <img src="img/pessoa3.png" alt="">
                    </div>
                    <div class="col-lg-7 col-md-7 col-sm-7 col-12 information-main">
                        <header>
                            <h1>Maria Joaquina</h1>
                        </header>
                        <span>
                            Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem k a galley of type and scrambled it to make a type specimen Lorem Ipsum is simply dummy text of the printing and typesetting.
                        </span>
                        <ul class="rede-social">
                            <li><a href="#"><i class="fab fa-facebook-square"></i></a></li>
                            <li><a href="#"><i class="fab fa-twitter-square"></i></a></li>
                            <li><a href="#"><i class="fab fa-google-plus-square"></i></a></li>
                        </ul>
                    </div>
                </article>
        
                <article class="card-prof col-xl-5 col-lg-9 col-md-9 col-sm-11 col-11">
                    <div class="imagem col-lg-5 col-md-5 col-sm-5 col-12">
                        <img src="img/pessoa4.png" alt="">
                    </div>
                    <div class="col-lg-7 col-md-7 col-sm-7 col-12 information-main">
                        <header>
                            <h1>Alberto Machado</h1>
                        </header>
                        <span>
                            Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem k a galley of type and scrambled it to make a type specimen Lorem Ipsum is simply dummy text of industry. And scrambled it to make a type
                        </span>
                        <ul class="rede-social">
                            <li><a href="#"><i class="fab fa-facebook-square"></i></a></li>
                            <li><a href="#"><i class="fab fa-twitter-square"></i></a></li>
                            <li><a href="#"><i class="fab fa-google-plus-square"></i></a></li>
                        </ul>
                    </div>
                </article>
        
                
                <article class="card-prof col-xl-5 col-lg-9 col-md-9 col-sm-11 col-11">
                    <div class="imagem col-lg-5 col-md-5 col-sm-5 col-12">
                        <img src="img/pessoa5.png" alt="">
                    </div>
                    <div class="col-lg-7 col-md-7 col-sm-7 col-12 information-main">
                        <header>
                            <h1>Gustavo Coelho</h1>
                        </header>
                        <span>
                            Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem k a galley of type and scrambled it to make a type specimen Lorem Ipsum is simply .
                        </span>
                        <ul class="rede-social">
                            <li><a href="#"><i class="fab fa-facebook-square"></i></a></li>
                            <li><a href="#"><i class="fab fa-twitter-square"></i></a></li>
                            <li><a href="#"><i class="fab fa-google-plus-square"></i></a></li>
                        </ul>
                    </div>
                </article>
        
                
                <article class="card-prof col-xl-5 col-lg-9 col-md-9 col-sm-11 col-11">
                    <div class="imagem col-lg-5 col-md-5 col-sm-5 col-12">
                        <img src="img/pessoa6.png" alt="">
                    </div>
                    <div class="col-lg-7 col-md-7 col-sm-7 col-12 information-main">
                        <header>
                            <h1>Jessica Camargo</h1>
                        </header>
                        <span>
                            Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem k a galley of type and scrambled it to make a type specimen Lorem Ipsum is simply dummy text of the printing and typesetting industry. 
                        </span>
                        <ul class="rede-social">
                            <li><a href="#"><i class="fab fa-facebook-square"></i></a></li>
                            <li><a href="#"><i class="fab fa-twitter-square"></i></a></li>
                            <li><a href="#"><i class="fab fa-google-plus-square"></i></a></li>
                        </ul>
                    </div>
                </article>
        
                <article class="card-prof col-xl-5 col-lg-9 col-md-9 col-sm-11 col-11">
                    <div class="imagem col-lg-5 col-md-5 col-sm-5 col-12">
                        <img src="img/pessoa7.png" alt="">
                    </div>
                    <div class="col-lg-7 col-md-7 col-sm-7 col-12 information-main">
                        <header>
                            <h1>Pedro Andrade</h1>
                        </header>
                        <span>
                            Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem k a galley of type and scrambled it to make a type specimen Lorem Ipsum is simply dummy text of the printing and typesetting industry. And scrambled it to make a
                        </span>
                        <ul class="rede-social">
                            <li><a href="#"><i class="fab fa-facebook-square"></i></a></li>
                            <li><a href="#"><i class="fab fa-twitter-square"></i></a></li>             
                            <li><a href="#"><i class="fab fa-google-plus-square"></i></a></li>
                        </ul>
                    </div>
                </article>
            </div>
        
        </section>

<footer>
    <div class="shape-fill2"></div>
    <div class="img-footer">
        <div id="footer-content">
            <h1>Queremos Ajudar!</h1> 
            <h3>Precisa de alguém pra conversar sobre qualquer coisa? Marque uma tutoria com um dos nossos tutores!</h3>
        </div>

        <div id="links-footer" >
            <ul class="m-auto list-links"> 
                <li><a class="actived" href="#">Como marcar uma Tutoria?</a></li>
                <li><a href="#">Desenvolvedores</a></li>
                <li><a href="https://www.cotil.unicamp.br/portal/">Colégio Técnico de Limeira</a></li>
                <li><a href="projetoTutoria.html">Projeto Tutoria</a></li>
                <li><a href="#">Esqueceu sua senha?</a></li>
            </ul>
        </div>

        <div id="copyright">
            <span>@Copyright | Todos os Direitos Reservados ao COTIL</span>
        </div>

    </div>
</footer>


<!-- My JS -->
<script src="js/nav.js"></script>
<script src="js/login.js"></script>
<script src="js/navside.js"></script>
<script src="js/envolvidos.js"></script>


</body>
</html>