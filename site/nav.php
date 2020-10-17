<?php    
    error_reporting(0);
    session_start();
    require_once("functions/pegarInfosAluno.php");
?>

<nav>
    <h1><a href="index.php" class="name-site"> Cotil Amigável</a></h1>
    <!-- <div id="logo-site"></div> -->
    <div class="links-menu sumir">
        <ul id="list-navbar">
            <li><a class="actived" href="index.php">Eventos</a></li>
            <li id="sobre" class="tutoria">
                <a>Tutoria</a>
                <ul id="items-sobre">
                    <li><a href="projetoTutoria.php">Projeto Tutoria</li></a>
                    <li style="border-top: 1px solid rgba(143, 143, 143, 0.568);"><a href="agendarTutoria.php">Agendar Tutoria</li></a>
                </ul>
            </li> 
            <?php
                if (!isset($_SESSION['usuario'])) {
                    echo 
                    '<li><a class="login" href="login.php">Login</a></li>
                    <li><a href="cadastro.php">Cadastro</a></li>';
                }
            ?>
            <?php                
                if(isset($_SESSION['usuario'])){
                    $icon = getFotoAluno();

                    if($icon == null)
                        $icon = "<i style='margin-top: 10px; font-size: 45px; text-align: center;' class='far fa-user-circle'></i>";
                    else
                        $icon = "<img style='width: 50px; border-radius: 50%; margin-top: 7px;' src='img-alunos/" . $icon . "' />";

                    echo 
                    '<li><a href="functions/logout.php">Logout</a></li>
                    <li><a href="cadastro.php">' . $icon . '</a></li>'
                    ;
                }
            ?>
        </ul> 
    </div> 
    <span class="icon-menu"></span>
</nav>