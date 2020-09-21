<?php

function connect($connection_info) {
    $connection_info = array("hostname"=>"143.106.241.3", "dbname"=>"cl18152", "username"=>"cl18152", "password"=>"cl*07062002");
    
    $hostname = $connection_info["hostname"];
    $dbname = $connection_info["dbname"];
    $username = $connection_info["username"];
    $password = $connection_info["password"];

    $conn = new PDO("mysql:host=$hostname;dbname=$dbname;charset=utf8", $username, $password);
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

    return $conn;
}
