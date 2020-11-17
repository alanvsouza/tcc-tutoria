<?php

require_once "../vendor/autoload.php";

\App\Model\Session::startSession();


$tipoPerfil = $_SESSION['tipo'];

switch ($tipoPerfil) {
    case "aluno":
        $target_dir = "img-alunos/";

        break;
    case "tutor":
        $target_dir = "img-professores/";

        break;
}

function random_string($length)
{
    $key = '';
    $keys = array_merge(range(0, 9), range('a', 'z'));

    for ($i = 0; $i < $length; $i++) {
        $key .= $keys[array_rand($keys)];
    }

    return $key;
}


$extension = strtolower(pathinfo($_FILES['foto-perfil']['name'], PATHINFO_EXTENSION));
$fileName = $_SESSION['idUsuario'] . '-' . random_string(10) . '.' . $extension;
$target_file = '../' . $target_dir . $fileName;

$uploadOk = 1;

if (isset($_POST["submit"])) {
    $check = getimagesize($_FILES["foto-perfil"]["tmp_name"]);
    if ($check !== false) {
        $uploadOk = 1;
    } else {
        $uploadOk = 0;
    }
}

if (file_exists($target_file)) {
    $uploadOk = 0;
}

if ($extension != "jpg" && $extension != "png" && $extension != "jpeg") {
    $uploadOk = 0;
}

if ($uploadOk != 0) {
    if (move_uploaded_file($_FILES["foto-perfil"]["tmp_name"], $target_file)) {
        $tutorDao = new \App\Model\TutorDAO();
        $tutorDao->updateFotoPerfil($_SESSION['idUsuario'], $fileName);

        header('Location: ../index.php');
    } else {
        $uploadOk = 0;
    }
}

if ($uploadOk == 0) {
    echo "Erro ao tentar atualizar a foto de perfil";
    header('Refresh: 2, ../index.php');
}
