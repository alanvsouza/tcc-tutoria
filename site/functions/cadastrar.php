<?php
session_start();
require_once("../db/credentials.php");
require_once("../db/db_connect.php");

$erroCadastro = false;
$row = null;

$nome = $_POST['nome'];
$login = $_POST['login'];
$senha = $_POST['senha'];
$email = $_POST['email'];

try {
    $conn = connect($connection_info);
    $sql = "SELECT COUNT(idaluno) from tbaluno where `login` = '$login'";
    $result = $conn->query($sql); 

    $row = $result->fetch();    
} catch(PDOException $e) {
    echo $e;
}

if(strlen($nome) == 0 || strlen($login) == 0  || strlen($senha) == 0  || strlen($email) == 0){
    $erroCadastro = true;
    $_SESSION['errosCadastro'] = "Preencha todos os campos!";
} else if($row["COUNT(idaluno)"] != false){
    $erroCadastro = true;
    $_SESSION =  "O login já está em uso!";
}
else if(substr_count($email,'@') != 1 || substr_count($email,'.') < 1 ||  substr_count($email,'.') == 0){            
    $erroCadastro = true;
    $_SESSION['errosCadastro'] =  "Informe um e-mail válido!";
}
else if(stristr($senha,"'")){            
    $erroCadastro = true;
    $_SESSION['errosCadastro'] =  "Caractere ( ' ) inválido!";
}


if($erroCadastro == true) header('Location: ../cadastro.php');
else {
    $conn = null;
    $curso = $_POST['curso'];
    $anoingresso = $_POST['ano'];
    $senha = md5($senha . 'cotilamigavel');

    try {
        $conn = connect($connection_info);

        $sql = "INSERT INTO tbaluno (nomealuno, login, senha, email, curso, anoingresso) VALUES ('$nome', '$login', '$senha', '$email', '$curso', '$anoingresso')";
        $conn->exec($sql);

        $_SESSION['cadastro_sucesso'] = true;    

        header('Location: ../login.php');
    } catch(PDOException $e) {
        echo $e;
    }
}
