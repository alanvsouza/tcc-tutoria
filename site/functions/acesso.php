<?php
// Verificando se foi informado algum usuario e senha
if (!isset($_SESSION["usuario"])) :
    $_SESSION["nao_autenticado_tutoria"] = true;
    header('Location: login.php');
endif;
