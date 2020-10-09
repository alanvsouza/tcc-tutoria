<?php
session_start();
// require_once("../db/credentials.php");
// require_once("../db/db_connect.php");

require_once("../db/db_connect2.php");

$erroCadastro = false;
$row = null;

$nome = $_POST['nome'];
$login = $_POST['login'];
$senha = $_POST['senha'];
$email = $_POST['email'];

try {

    $stmt = $pdo->prepare("select idaluno from tbaluno where login = :login");
    $stmt->bindParam(':login', $login);
    $stmt->execute();

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
else if($row = $stmt->fetch()){            
    $erroCadastro = true;
    $_SESSION['errosCadastro'] =  "Login de usuário indisponível!";
}

if($erroCadastro == true) header('Location: ../cadastro.php');
else {
    $curso = $_POST['curso'];
    $anoingresso = $_POST['ano'];
    $senha = md5($senha . 'cotilamigavel');

    try {
        $stmt = $pdo->prepare("insert into tbaluno (nomealuno, login, senha, email, curso, anoingresso) VALUES (:nome, :login, :senha, :email, :curso, :anoingresso)");
        $stmt->bindParam(':nome', $nome);
        $stmt->bindParam(':login', $login);
        $stmt->bindParam(':senha', $senha);
        $stmt->bindParam(':email', $email);
        $stmt->bindParam(':curso', $curso);
        $stmt->bindParam(':anoingresso', $anoingresso);
        $stmt->execute();

        $_SESSION['cadastro_sucesso'] = true;    
        header('Location: ../login.php');
    } catch(PDOException $e) {
        echo $e;
    }
}
