<?php
define('HOST','143.106.241.3');
define('USUARIO','cl18152');
define('SENHA','cl*07062002');
define('DB','cl18152');

//Função para conectar ao servidor
$conexao = mysqli_connect(HOST,USUARIO,SENHA) or die("Não foi possível estabelecer uma conexão com o banco de dados!");

//Função para selecionar o banco de dados
$db = mysqli_select_db($conexao,DB);

?>
