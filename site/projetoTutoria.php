<?php require_once "vendor/autoload.php" ?>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, shrink-to-fit=no">
    <title>Cotil Amigável - Projeto Tutoria</title>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="css/style.min.css">

    <!-- My styles CSS -->
    <link href="css/style.scss">
    <link rel="stylesheet" href="css/nav.css"> 
    <link rel="stylesheet" href="css/imgEvents.css">   
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="css/navside.css">
    <link rel="stylesheet" href="css/notification.css">
    <link rel="stylesheet" href="css/cardsProjetoTutoria.css">
    <link rel="stylesheet" href="css/projetoTutoria.css">

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
    include("navside.php");
?>

<div class="img-events" >
        <div>
            <h1 style="position: relative;">Projeto Tutoria</h1> 
            <h2>Consulte os Horários e Marque uma Tutoria! </h2>
            <h3>Nossos Tutores são Professores do COTIL</h3>
    </div>
</div>

<section class="texto-projeto">
    <div class="img-ondas col-lg-12 col-md-12"></div>
    <div class="col-lg-12 flex-box">
            <div class="col-lg-6 col-md-12 column-one">
                <h1>O que é uma Tutoria?</h1>
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
    <div id="info-events" class="col-xl-10 col-lg-9 col-md-11 col-sm-12 col-12">
            <h2>Professores envolvidos</h2> 
            <h3>Esses são os professores do Colégio Técnico de Limeira que temos como tutores.</h3>
    </div>
</header>

<section class="envolvidos">
        <div class="cards">
            <?php \App\Model\TutorService::renderizarProfessoresProjetoTutoria(); ?>
        </div>
</section>

<footer>
    <div class="img-ondas2"></div>

    <div class="img-footer">
        <div id="conteudo">
            <h2>Queremos Ajudar!</h2> 
            <h3>Precisa de alguém pra conversar sobre qualquer coisa? Marque uma tutoria com um dos nossos tutores!</h3>

        <div id="links-footer" class="p-0">
            <ul class="p-0 list-links"> 
                <li><a class="actived" href="#">Como marcar uma Tutoria?</a></li>
                <li><a href="#">Desenvolvedores</a></li>
                <li><a href="https://www.cotil.unicamp.br/portal/">Colégio Técnico de Limeira</a></li>
                <li><a href="projetoTutoria.html">Projeto Tutoria</a></li>
                <li><a href="#">Esqueceu sua senha?</a></li>
            </ul>
        </div>

        </div>
        <div id="copyright" class="p-0">
            <span class="p-0">@Copyright | Todos os Direitos Reservados ao COTIL</span>
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
<script src='js/nav.js'></script>
<script src="js/navside.js"></script>
<script src="js/notification.js"></script>
<script src="js/classEdit.js"></script>
<script src='js/cardsProjetoTutoria.js'></script>

</body>
</html>