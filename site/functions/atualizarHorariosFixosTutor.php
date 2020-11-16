<?php

require_once "../vendor/autoload.php";
\App\Model\Session::startSession();

$tutorDao = new \App\Model\TutorDAO();

// echo "<pre>" . var_export($_POST, true) . "</pre>";

foreach ($_POST as $dia => $horarios) {
    $dia = array_filter(explode('dia-', $dia))[1];
    $tutorDao->deleteAllHorariosByDia($_SESSION['idUsuario'], $dia);
    // echo "deleteAllHorariosByDia({$_SESSION['idUsuario']}, {$dia})<br>";

    if ($horarios == '')
        continue;

    $horarios = array_filter(explode(';', $horarios));

    foreach ($horarios as $horario) {
        $tutorDao->createHorario($_SESSION['idUsuario'], $horario, $dia);
        // echo "createHorario({$_SESSION['idUsuario']}, {$horario}, {$dia})<br>";
    }
}

header('Location: ../horariosTutor.php');
