<?php
session_start();

foreach($_POST as $chave=> $valor){

    if(strlen($_POST['nome'] == 0)){
        $erro[] = "Informe seu nome completo.";
    }
    if(substr_count($_POST['email'],'@') != 1 || substr_count($_POST['email'],'.') < 1 ||  substr_count($_POST['email'],'.') > 2){
        $erro[] = "Informe um e-mail válido.";
    }
    if(strlen($_POST['login'] == 0)){
        $erro[] = "Informe um login válido";
    }
    if(strlen($_POST['senha'] == 0)){
        $erro[] = "Informe uma senha válida";
    }

}

    require("conexao.php");
    $nome = mysqli_real_escape_string($conexao, $_POST['nome']);
    $email = mysqli_real_escape_string($conexao,$_POST['email']);
    $curso = mysqli_real_escape_string($conexao,$_POST['curso']);
    $ano = mysqli_real_escape_string($conexao,$_POST['ano']);
    $login = mysqli_real_escape_string($conexao,$_POST['login']);
    $senha = mysqli_real_escape_string($conexao,$_POST['senha']);
    $senha = $senha . 'cotilamigavel';

    $query = "insert into tbaluno (nomealuno,login,senha,email,curso,anoingresso) values ('{$nome}','{$login}',md5('{$senha}'),'{$email}','{$curso}','{$ano}')";
    $result = mysqli_query($conexao, $query);
    $_SESSION['cadastro_sucesso'] = true;
    header('Location: ../login.php');

// $row = mysqli_affected_rows($result);
// if ($row) {
//     header('Location: projetoTutoria.php');
// } else {
//     exit();
// }


?>