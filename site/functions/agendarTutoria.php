<?php

require_once "../vendor/autoload.php";

\App\Model\Session::startSession();

$data = DateTime::createFromFormat('d/m/Y', $_POST['data'])->format('Y-m-d');
$idTutor = $_POST['idtutor'];
$idHorario = $_POST['idhorario'];
$idAluno = $_SESSION['idUsuario'];
$tipoHorario = $_POST['tipo'];

$tutoria = new \App\Model\Tutoria($idAluno, $idTutor, $data, $idHorario, $tipoHorario);

if (\App\Model\TutoriaService::agendarTutoria($tutoria)) {
    $_SESSION['tutoria_agendada'] = true;
} else {
    $_SESSION['tutoria_agendada'] = false;
}

header('Location: ../agendarTutoria.php');
