<?php

require_once "../vendor/autoload.php";


$tutoriaDao = new \App\Model\TutoriaDAO();
$tutorDao = new \App\Model\TutorDAO();
$alunoDao = new \App\Model\AlunoDAO();

switch ($_POST['tipo-tutoria']) {
    case "fixo":
        $tutoriaDao->confirmarTutoria($_POST['id-tutoria'], $_POST['local']);
        $tutoriaInfos = $tutoriaDao->readById($_POST['id-tutoria']);

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
                'model' => 'tutoria-confirmada-aluno',
                'replace' => array(
                    'nome' => $alunoInfos['nomealuno'],
                    'tutor' => $tutorInfos['nometutor'],
                    'data' => DateTime::createFromFormat('Y-m-d', $tutoriaInfos['datatutoria'])->format('d/m/Y'),
                    'inicio' => $horarios[0],
                    'termino' => $horarios[1],
                    'local' => $tutoriaInfos['localtutoria']
                )
            )
        );

        break;

    case "dinamico":
        $tutoriaDao->confirmarTutoriaDinamica($_POST['id-tutoria'], $_POST['local']);
        $tutoriaInfos = $tutoriaDao->readDinamicaById($_POST['id-tutoria']);

        $alunoInfos = $alunoDao->readById($tutoriaInfos['idaluno']);
        $tutorInfos = $tutorDao->readById($tutoriaInfos['idtutor']);
        $horarioInfos = $tutorDao->readHorarioDinamicoById($tutoriaInfos['idhorario']);
        $horarios = explode('-', $horarioInfos['horarios']);

        \App\Helper\RequestHelper::makeRequest(
            'mail-sender/sendEmail.php',
            'POST',
            array(
                'subject' => 'Tutoria confirmada!',
                'from' => 'cotiltutoria@gmail.com',
                'to' => $alunoInfos['email'],
                'model' => 'tutoria-confirmada-aluno',
                'replace' => array(
                    'nome' => $alunoInfos['nomealuno'],
                    'tutor' => $tutorInfos['nometutor'],
                    'data' => DateTime::createFromFormat('Y-m-d', $tutoriaInfos['data'])->format('d/m/Y'),
                    'inicio' => $horarios[0],
                    'termino' => $horarios[1],
                    'local' => $tutoriaInfos['local']
                )
            )
        );
        break;
}



header('Location: ../tutoriasAgendadas.php');
