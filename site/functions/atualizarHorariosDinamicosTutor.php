<?php

require_once "../vendor/autoload.php";

\App\Model\Session::startSession();

$horarios = array_filter(explode(';', $_POST['horarios']));
$data = $_POST['data'];

$tutorDao = new \App\Model\TutorDAO();
$tutorDao->deleteAllHorariosDinamicosByData($_SESSION['idUsuario'], $data);

foreach ($horarios as $horario) {
    $tutorDao->createHorarioDinamico($_SESSION['idUsuario'], $horario, $data);
}

header("Location: ../horariosTutor.php?data-horario-dinamico={$data}");
