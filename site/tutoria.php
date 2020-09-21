<?php
session_start();
if (!$_SESSION['usuario']) {
    $_SESSION['nao_autenticado_tutoria'] = true;
    header('Location: login.php');
    exit();
}
?>