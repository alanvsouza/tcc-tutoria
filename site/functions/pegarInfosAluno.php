<?php

function getFotoAluno(){    
require_once("db/db_connect.php");
$usuario = $_SESSION['usuario'];
    try {

        $conn = connect();

        $sql = "SELECT foto FROM tbaluno WHERE `login` = '$usuario'";
        
        $result = $conn->query($sql);
    
        $row = $result->fetch();
    
        $conn = null;
    } catch(PDOException $e) {
        echo $e;
    }
    
    return $row['foto'];
}