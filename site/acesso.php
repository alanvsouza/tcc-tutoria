<?php

    require_once 'vendor/autoload.php';

    session_start();    

    $login = $_POST['usuario'];
    $senha = $_POST['senha'];

    $usuariodao = new \App\Model\UsuarioDao();    

    $logou = $usuariodao->logar($login, md5($senha));    

    if($logou) {
        $_SESSION['usuario'] = $login;
        $_SESSION['senha'] = $senha;

        echo "<br>logado como " . $_SESSION['usuario'];
    }
