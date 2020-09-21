<?php

function connect($connection_info) {
    $hostname = $connection_info["hostname"];
    $dbname = $connection_info["dbname"];
    $username = $connection_info["username"];
    $password = $connection_info["password"];

    $conn = new PDO("mysql:host=$hostname;dbname=$dbname;charset=utf8", $username, $password);
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

    return $conn;
}
