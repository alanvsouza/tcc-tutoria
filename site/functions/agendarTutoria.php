<?php

require_once "../vendor/autoload.php";

\App\Model\Session::startSession();

$data = $_GET['data'];
$idTutor = $_GET['idtutor'];
$idHorario = $_GET['idhorario'];
$idAluno = $_SESSION['idUsuario'];
print_r($idAluno);

$tutoria = new \App\Model\Tutoria($idAluno, $idTutor, $data, $idHorario);

\App\Model\TutoriaService::agendarTutoria($tutoria);
header('Location: ../agendarTutoria.php');
