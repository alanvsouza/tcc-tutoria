<?php

require_once "../vendor/autoload.php";

$tutoriaDao = new \App\Model\TutoriaDAO();
var_dump($_POST);

switch ($_POST['tipo-tutoria']) {
    case "fixo":
        $tutoriaDao->cancelarTutoria($_POST['id-tutoria']);

        break;

    case "dinamico":
        $tutoriaDao->cancelarTutoriaDinamica($_POST['id-tutoria']);
        break;
}

header('Location: ../tutoriasAgendadas.php');
