<?php

require_once "../vendor/autoload.php";
\App\Model\Session::startSession();

$_POST = array_filter($_POST);
$tutorDao = new \App\Model\TutorDAO();

foreach ($_POST as $dia => $horarios) {
    $dia = array_filter(explode('dia-', $dia))[1];
    $horarios = array_filter(explode(';', $horarios));

    $tutorDao->deleteAllHorariosByDia($_SESSION['idUsuario'], $dia);

    foreach ($horarios as $horario) {
        $tutorDao->createHorario($_SESSION['idUsuario'], $horario, $dia);
    }
}

header('Location: ../index.php');
