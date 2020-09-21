<?php

function connect($hostname, $dbname, $username, $password) {
    $conn = new PDO("mysql:host=$hostname;dbname=$dbname;charset=utf8", $username, $password);
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

    return $conn;
}
