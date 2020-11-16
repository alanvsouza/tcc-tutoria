<?php
require_once "vendor/autoload.php";
\App\Model\Session::startSession();
?>

<nav class="type-position">
    <span class="icon-menu"></span>
    <h1><a href="index.php" class="name-site"> Cotil Amigável</a></h1>
    <!-- <div id="logo-site"></div> -->
    <div class="links-menu sumir">
        <ul id="list-navbar">
            <?php $basename = basename($_SERVER['PHP_SELF'], '.php'); ?>
            <li><a <?php if ($basename == "index") echo "class = actived" ?> href="index.php">Eventos</a></li>
            <li><a <?php if ($basename == "projetoTutoria") echo "class = actived" ?> href="projetoTutoria.php">Projeto Tutoria</a></li>
            <li><a <?php if ($basename == "horariosTutor") echo "class = actived" ?> href="horariosTutor.php">Tutor horários</a></li>
            <li><a <?php if ($basename == "tutoriasAgendadas") echo "class = actived" ?> href="tutoriasAgendadas.php">Tutorias Agendadas</a></li>
            <?php
            if (!isset($_SESSION['usuario'])) {
                echo
                    '<li class="sumir"><a class="login" href="login.php">Login</a></li>
                    <li class="sumir"><a href="cadastro.php">Cadastro</a></li>';
            } else {
                $icon = 'img-professores/' . \App\Model\TutorService::getFotoPerfil();

                if ($icon == null)
                    $icon = "<i id='icon-perfil' class='sem-foto' class='far fa-user-circle'></i>";
                else
                    $icon = "<img id='icon-perfil' class='com-foto' src='" . $icon . "' />";

                echo
                    '<div class="container-user">
                        <ul class="user-perfil">
                                <li class="ml-4 sobre"><a>' . $icon . '</a> </li>
                                <div class="items-sobre">
                                    <div class="triangulo-cima"></div>
                                    <div class="user-infos">
                                        <div class="row p-0 m-0">
                                            <span class="nome-usuario">' . $_SESSION['nome'] . '</span>
                                            <a href="#" class="ver-perfil">Ver Perfil</a>
                                            <a href="functions/logout.php" class="sair">Sair</a>
                                        </div>
                                    </div>
                                </div>
                        </ul>
                    </div>
                    
                    ';
            }
            ?>
        </ul>
    </div>
    <div class="links-menu aparecer">
        <?php
        if (isset($_SESSION['usuario'])) {
            $icon = \App\Model\AlunoService::getFotoPerfil();

            if ($icon == null)
                $icon = "<i class='sem-foto' class='far fa-user-circle'></i>";
            else
                $icon = "<img  class='com-foto' src='img-alunos/" . $icon . "' />";

            echo
                '
                    <div class="container-user">
                        <ul class="user-perfil">
                                <li class="ml-4 sobre2"><a>' . $icon . '</a> </li>
                                <div class="items-sobre2">
                                    <div class="triangulo-cima"></div>
                                    <div class="user-infos">
                                        <div class="row p-0 m-0">
                                            <span class="nome-usuario">Carla Pereira Costa</span>
                                            <a href="#" class="ver-perfil">Ver Perfil</a>
                                            <a href="functions/logout.php" class="sair">Sair</a>
                                        </div>
                                    </div>
                                </div>
                        </ul>
                    </div>';
        }
        ?>
    </div>
</nav>

<script src="js/nav.js"></script>