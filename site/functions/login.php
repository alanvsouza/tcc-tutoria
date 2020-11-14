<?php
require_once "../vendor/autoload.php";

\App\Model\Session::startSession();

if (\App\Helper\LoginHelper::login($_POST['usuario'], $_POST['senha']))
    header('Location: ../index.php');
else {
    $_SESSION['nao_autenticado'] = true;
    header('Location: ../login.php');
}
