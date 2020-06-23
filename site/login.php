<?php
    require_once 'vendor/autoload.php';
    session_start();    
?>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, shrink-to-fit=no">
    <title>Login 2</title>
    <link rel="stylesheet" href="css/login.css">
</head>
<body>

    <form action="acesso.php" method="post">
        <div id="header">
            <span>Sistema de Login</span>
            <p id="nome_site"><a href="index.php">Cotil Amigável</a></p>
        </div>
        <?php
        if (isset($_SESSION['nao_autenticado'])):
        ?>
        <div id="erro">
            <span>ERRO: Usuário ou senha incorreto!</span>
        </div>
        <?php
        endif;
        unset($_SESSION['nao_autenticado']);
        ?>

        <?php
        if (isset($_SESSION['nao_autenticado_tutoria'])):
        ?>
        <div id="erro_tutoria">
            <span> AVISO: Efetue o login para continuar!</span>
        </div>
        <?php
        endif;
        unset($_SESSION['nao_autenticado_tutoria']);
        ?>
        <div id="body">
            <input type="text" name="usuario" id="usuario" placeholder="Informe seu usuário">
            <input type="password" name="senha" id="senha" placeholder="Informe sua senha" maxlength="15" minlength="1">
            <input type="submit" id="btn-entrar" value="Entrar">
            <p id="cadastre_se">Ainda não possui uma conta?<a href="cadastro.php"> Cadastre-se aqui!</a></p>
        </div>
    </form>
    
</body>
</html>