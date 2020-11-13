<?php

require_once "../vendor/autoload.php";

\App\Model\Session::startSession();

$data = $_POST['data'];
$idTutor = $_POST['idtutor'];
$idHorario = $_POST['idhorario'];
$idAluno = $_SESSION['idUsuario'];
print_r($idAluno);

$tutoria = new \App\Model\Tutoria($idAluno, $idTutor, $data, $idHorario);

\App\Model\TutoriaService::agendarTutoria($tutoria);
header('Location: ../agendarTutoria.php');
