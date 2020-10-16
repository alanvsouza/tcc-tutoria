<?php

require("db/db_connect.php");
session_start();

function getFotoAluno() {    
    $conn = connect();
    $usuario = $_SESSION['usuario'];
    
    $row;
    
    try {
        $conn = connect($connection_info);
    
        $sql = "SELECT foto FROM tbaluno WHERE `login` = '$usuario'";
        
        $result = $conn->query($sql);
    
        $row = $result->fetch();
    
        $conn = null;
    } catch(PDOException $e) {
        echo $e;
    }
    
    return $row['foto'];
}