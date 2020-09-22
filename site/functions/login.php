<?php
session_start();

require("../db/db_connect.php");
require("../db/credentials.php");

$usuario = $_POST['usuario'];
$senha = md5($_POST['senha'] . "cotilamigavel");

$row;

try {
    $conn = connect($connection_info);

    $sql = "SELECT senha FROM tbaluno WHERE `login` = '$usuario'";
    
    $result = $conn->query($sql);

    $row = $result->fetch();

    $conn = null;
} catch(PDOException $e) {
    echo $e;
}

if($row["senha"] == $senha) {
    $_SESSION["usuario"] = $usuario;
    header('Location: ../index.php');
} else {
    $_SESSION["nao_autenticado"] = false;
    header('Location: ../login.php');
}
