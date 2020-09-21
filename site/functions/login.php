<?php
session_start();
require("../db/db_connect.php");
require("../db/credentials.php");

$usuario = $_POST['usuario'];
$senha = $_POST['senha'];

$result = null;

try {
    $conn = connect($connection_info);

    $sql = "SELECT senha FROM tbaluno WHERE login = $usuario";
    
    $result = $conn->query($sql);
    
    $conn = null;
} catch(PDOException $e) {
    echo "Connection failed";
}

$row = $result->fetch();
$senha = $row["senha"];

echo $senha;

// if($row["password"] == $senha) {
//     $_SESSION["usuario"] = $usuario;
//     header('Location: ../index.php');
// } else {
//     $_SESSION["nao_autenticado"] = false;
//     header('Location: ../login.php');
// }
