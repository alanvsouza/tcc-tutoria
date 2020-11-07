<?php
require_once "../vendor/autoload.php";
\App\Model\Session::startSession();

$senha = md5($_POST['senha'] . "cotilamigavel");

$aluno = new \App\Model\Aluno(
    $_POST['nome'],
    $_POST['login'],
    $senha,
    $_POST['email'],
    $_POST['curso'],
    $_POST['ano']
);

if (\App\Model\AlunoService::checkCredentials($aluno)) :
    $alunoDao = new \App\Model\AlunoDAO();

    $alunoDao->create($aluno);

    $_SESSION['cadastro_sucesso'] = true;

    \App\Helper\RequestHelper::makeRequest(
        'http://localhost:8080/tcc/site/mail-sender/sendEmail.php',
        'POST',
        array(
            'subject' => 'Conta cadastrada com sucesso!',
            'from' => 'cotiltutoria@gmail.com',
            'to' => $aluno->getEmail(),
            'model' => 'nova-conta',
            'replace' => array(
                'nome' => $aluno->getNomeAluno()
            )
        )
    );

    header('Location: ../login.php');
else :
    header('Location: ../cadastro.php');
endif;
