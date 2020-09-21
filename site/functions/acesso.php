
<?php
session_start();

// Verificando se foi informado algum usuario e senha
if (empty($_POST['usuario']) || empty($_POST['senha'])){
    header('Location: login.php');
    exit();
}else{
    header("location: agendarTutoria.php");
}

?>
