<?php
session_start();

require_once "../vendor/autoload.php";

$aluno = new \App\Model\Aluno($_POST['usuario'], md5($_POST['senha'] . "cotilamigavel"));

\App\Model\AlunoService::login($aluno);

// $row;

// try {
//     $conn = connect($connection_info);

//     $sql = "SELECT senha FROM tbaluno WHERE `login` = '$usuario'";
    
//     $result = $conn->query($sql);

//     $row = $result->fetch();

//     $conn = null;
// } catch(PDOException $e) {
//     echo $e;
// }

// if($row["senha"] == $senha) {
//     $_SESSION["usuario"] = $usuario;
//     header('Location: ../index.php');
// } else {
//     $_SESSION["nao_autenticado"] = false;
//     header('Location: ../login.php');
// }
