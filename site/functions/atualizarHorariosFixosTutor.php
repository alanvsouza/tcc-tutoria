<?php

require_once "../vendor/autoload.php";
\App\Model\Session::startSession();

$tutorDao = new \App\Model\TutorDAO();

foreach ($_POST as $dia => $horarios) {
    $dia = array_filter(explode('dia-', $dia))[1];
    $tutorDao->deleteAllHorariosByDia($_SESSION['idUsuario'], $dia);

    if ($horarios == '')
        continue;

    $horarios = array_filter(explode(';', $horarios));

    foreach ($horarios as $horario) {
        $tutorDao->createHorario($_SESSION['idUsuario'], $horario, $dia);
    }
}

header('Location: ../horariosTutor.php');
