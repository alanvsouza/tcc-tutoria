<?php
session_start();

// Verificando se foi informado algum usuario e senha
if (!isset($_SESSION["usuario"]))
    header('Location: login.php');    
