<?php

try {        
    $pdo = new PDO('mysql:host=143.106.241.3;dbname=cl18152;charset=utf8', 'cl18152', 'cl*07062002');
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    $output = 'Conexão estabelecida. <br>';
} catch (PDOException $e) {
    $output = 'Impossível conectar BD : ' . $e . '<br>';
}

?>