<?php

require_once "../vendor/autoload.php";

$tutoriaDao = new \App\Model\TutoriaDAO();

switch ($_POST['tipo-tutoria']) {
    case "fixo":
        $tutoriaDao->confirmarTutoria($_POST['id-tutoria'], $_POST['local']);
        break;

    case "dinamico":
        $tutoriaDao->confirmarTutoriaDinamica($_POST['id-tutoria'], $_POST['local']);
        break;
}

header('Location: ../tutoriasAgendadas.php');
