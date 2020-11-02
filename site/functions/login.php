<?php
session_start();

require_once "../vendor/autoload.php";

$aluno = new \App\Model\Aluno($_POST['usuario'], md5($_POST['senha'] . "cotilamigavel"));

\App\Model\AlunoService::login($aluno);
