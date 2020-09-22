<?php    
    error_reporting(0);
    session_start();
?>

<nav>
    <h1><a href="index.php" class="name-site"> Cotil Amigável</a></h1>
    <!-- <div id="logo-site"></div> -->
    <div class="links-menu sumir">
        <ul id="list-navbar">
            <li><a class="actived" href="index.php">Eventos</a></li>
            <li><a href="#">Calendário</a></li>

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
                    "<li><a class='login' href='login.php'>Login</a></li>
                    <li><a href='cadastro.php'>Cadastro</a></li>";
                }
            ?>                
            <?php                
                if(isset($_SESSION['usuario'])){
                    echo 
                    '<li><a href="cadastro.php">Icone</a></li>
                    <li><a href="functions/logout.php">Logout</a></li>';
                }
            ?>
        </ul> 
    </div> 
    <span class="icon-menu"></span>
</nav>