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

require("../db/credentials.php");
require("../db/db_connect.php");

$nome = $_POST["nome"];
$email = $_POST['email'];
$curso = $_POST['curso'];
$ano = $_POST['ano'];
$login = $_POST['login'];
$senha = $_POST['senha'];
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

