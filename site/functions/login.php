<?php
require_once "../vendor/autoload.php";

\App\Model\Session::startSession();

\App\Helper\LoginHelper::login($_POST['usuario'], $_POST['senha']);

header('Location: ../index.php');
