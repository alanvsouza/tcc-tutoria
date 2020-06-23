
<?php
session_start();
include("conexao.php");
// Verificando se foi informado algum usuario e senha
if (empty($_POST['usuario']) || empty($_POST['senha'])){
    $_SESSION['nao_autenticado'] = true;
    header('Location: login.php');
    exit();
}
//pegar o valor e ainda trata qualquer ataque de SQL injection
$usuario = mysqli_real_escape_string($conexao, $_POST['usuario']);
$senha = mysqli_real_escape_string($conexao, $_POST['senha']);
$senha = $senha . 'cotilamigavel';
$query = "select nomealuno,email,curso,anoingresso,foto from tbaluno where login = '{$usuario}' and senha = md5('{$senha}')";

$result = mysqli_query($conexao, $query);
$row = mysqli_num_rows($result);

if ($row) {
$_SESSION['usuario'] = $usuario;
header('Location: projetoTutoria.php');
}else{
$_SESSION['nao_autenticado'] = true;
header('Location: login.php');
exit();
}

?>
