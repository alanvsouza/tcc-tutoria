<?php

require_once "../vendor/autoload.php";

$idAluno = $_GET['idaluno'];
$data = $_GET['data'];
$idTutor = $_GET['idtutor'];
$idHorario = $_GET['idhorario'];
print_r($idAluno);

$tutoria = new \App\Model\Tutoria($idAluno, $idTutor, $data, $idHorario);
$tutoriaDao = new \App\Model\TutoriaDAO();

try {
    $tutoriaDao->create($tutoria);
    header('Location: ../agendarTutoria.php');
} catch (PDOException $e) {
    echo "Erro ao salvar tutoria!";
}
