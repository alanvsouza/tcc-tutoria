<?php

require_once "../vendor/autoload.php";

$tutoriaDao = new \App\Model\TutoriaDAO();
$tutorDao = new \App\Model\TutorDAO();
$alunoDao = new \App\Model\AlunoDAO();

switch ($_POST['tipo-tutoria']) {
    case "fixo":
        $tutoriaInfos = $tutoriaDao->readById($_POST['id-tutoria']);
        $tutoriaDao->cancelarTutoria($_POST['id-tutoria']);

        $alunoInfos = $alunoDao->readById($tutoriaInfos['idaluno']);
        $tutorInfos = $tutorDao->readById($tutoriaInfos['idtutor']);
        $horarioInfos = $tutorDao->readHorarioById($tutoriaInfos['idhorario']);
        $horarios = explode('-', $horarioInfos['horarios']);

        \App\Helper\RequestHelper::makeRequest(
            'mail-sender/sendEmail.php',
            'POST',
            array(
                'subject' => 'Tutoria confirmada!',
                'from' => 'cotiltutoria@gmail.com',
                'to' => $alunoInfos['email'],
                'model' => 'tutoria-cancelada',
                'replace' => array(
                    'nome' => $alunoInfos['nomealuno'],
                    'tutor' => $tutorInfos['nometutor']
                )
            )
        );

        break;

    case "dinamico":
        $tutoriaInfos = $tutoriaDao->readDinamicaById($_POST['id-tutoria']);
        $tutoriaDao->cancelarTutoriaDinamica($_POST['id-tutoria']);

        $alunoInfos = $alunoDao->readById($tutoriaInfos['idaluno']);
        $tutorInfos = $tutorDao->readById($tutoriaInfos['idtutor']);
        $horarioInfos = $tutorDao->readHorarioById($tutoriaInfos['idhorario']);
        $horarios = explode('-', $horarioInfos['horarios']);

        \App\Helper\RequestHelper::makeRequest(
            'mail-sender/sendEmail.php',
            'POST',
            array(
                'subject' => 'Tutoria cancelada!',
                'from' => 'cotiltutoria@gmail.com',
                'to' => $alunoInfos['email'],
                'model' => 'tutoria-cancelada',
                'replace' => array(
                    'nome' => $alunoInfos['nomealuno'],
                    'tutor' => $tutorInfos['nometutor']
                )
            )
        );
        break;
        break;
}

header('Location: ../tutoriasAgendadas.php');
