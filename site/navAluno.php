<?php
require_once "vendor/autoload.php";
\App\Model\Session::startSession();
?>

<nav class="type-position">
    <span class="icon-menu"></span>
    <!-- <div id="logo-site"></div> -->
    <div class="menu">
        <ul id="list-navbar">
            <?php $basename = basename($_SERVER['PHP_SELF'], '.php'); ?>
            <li class="li-nome-site"><h1><a href="index.php" class="name-site">Cotil Amigável</a></h1></li>
            <div class="links-menu">
                <li class="item-menu"><a <?php if ($basename == "index") echo "class = actived" ?> href="index.php">Eventos</a></li>
                <li class="item-menu"><a <?php if ($basename == "projetoTutoria") echo "class = actived" ?> href="projetoTutoria.php">Projeto Tutoria</a></li>
                <li class="item-menu"><a <?php if ($basename == "agendarTutoria") echo "class = actived" ?> href="agendarTutoria.php">Agendar Tutoria</a></li>
            </div>
            <?php
            if (!isset($_SESSION['usuario'])) {
                echo
                    '<li class="sumir"><a class="login" href="login.php">Login</a></li>
                    <li class="sumir mr-4"><a href="cadastro.php">Cadastro</a></li>';
            } else {
                $icon = $_SESSION['tipo'] == 'aluno'
                    ? 'img-alunos/' . \App\Model\AlunoService::getFotoPerfil()
                    : 'img-professores/' . \App\Model\TutorService::getFotoPerfil();

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
                                            <div class="logout">
                                                <a href="functions/logout.php" class="sair"><i class="fas fa-sign-out-alt"></i>Sair</a>
                                            </div>
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
                                            <span class="nome-usuario">' . $_SESSION['nome'] .'</span>
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