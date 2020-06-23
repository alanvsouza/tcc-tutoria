<?php

    require_once 'vendor/autoload.php';

    $usuario = new \App\Model\Usuario();

    $usuario->setNome($_POST['nome']);
    $usuario->setEmail($_POST['email']);
    $usuario->setCurso($_POST['curso']);
    $usuario->setAno($_POST['ano']);
    $usuario->setLogin($_POST['login']);
    $usuario->setSenha(md5($_POST['senha']));

    var_dump($usuario);

    $usuariodao = new \App\Model\UsuarioDao();

    if($usuariodao->cadastrar($usuario)) {
        header("Location: projetoTutoria.php");
    }
