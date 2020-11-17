<!DOCTYPE html>
<html lang="pt-BR">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Cadastro</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="css/style.min.css">

    <!-- My css -->
    <link rel="stylesheet" href="css/cadastro.css">

    <!-- Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.4.1.js"></script>

    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Berkshire+Swash|Josefin+Sans&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Josefin+Sans&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Archivo+Narrow&family=Julius+Sans+One&family=Source+Sans+Pro:wght@300&display=swap" rel="stylesheet">

</head>

<body>
    <form accept-charset="utf-8" class="col-xl-8 col-lg-8 col-md-9 col-sm-11 col-11" action="functions/cadastrar.php" method="post" id="formCadastro">
        <div id="header">
            <p>Sistema de Cadastro</p>
            <a class="nomeSite" href="index.php">Cotil Amigável</a>
        </div>

        <?php
        require_once "vendor/autoload.php";
        \App\Model\Session::startSession();

        if (isset($_SESSION["errosCadastro"])) {
            echo '<div class="errosCadastro"> ' . $_SESSION["errosCadastro"] . ' </div>';
            unset($_SESSION["errosCadastro"]);
        }
        ?>

        <div id="content-body" class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
            <div class="flex-row col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12 p-0">
                <input class="input col-lg-5 col-md-11 col-sm-11 col-11 hoverEfect" placeholder="Informe seu nome completo" maxlength="50" type="text" id="nome" name="nome">
                <input class="input col-lg-5 col-md-11 col-sm-11 col-11 hoverEfect" placeholder="Informe seu e-mail" maxlength="50" type="text" id="email" name="email">
            </div>

            <div class="flex-row col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12 p-0 m-0 " id="selects">

                <div class="col-lg-5 col-md-5 col-sm-11 col-11 p-0 m-0  ">
                    <select class="input hoverEfect" name="curso" id="curso" style="min-height:40px">
                        <option value="Nenhum Curso">Nenhum Curso</option>
                        <option value="Informática">Informática</option>
                        <option value="Mecânica">Mecânica</option>
                        <option value="Qualidade">Qualidade</option>
                        <option value="Edificações">Edificações</option>
                        <option value="Enfermagem">Enfermagem</option>
                        <option value="Geodésia e Cartografia">Geodésia e Cartografia</option>
                    </select>
                </div>

                <div class="col-xl-1 col-lg-1 col-md-1 col-sm-0 col-0 p-0 m-0 "></div>

                <div class="col-lg-5 col-md-5 col-sm-11 col-11 p-0 m-0 ">
                    <select class="input hoverEfect" name="ano" id="ano" style="min-height:40px">
                        <option value="2020">Ingressei em 2020</option>
                        <option value="2019">Ingressei em 2019</option>
                        <option value="2018">Ingressei em 2018</option>
                    </select>
                </div>
            </div>

            <div class="flex-row p-0 col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                <div class=" col-xl-5 col-lg-5 col-md-11 col-sm-11 col-11  p-0 m-auto">
                    <input class="input col-lg-12 hoverEfect" id="login" name="login" placeholder="Informe um login de usuário" maxlength="50" type="text">
                </div>
                <div class="col-xl-5 col-lg-5 col-md-11 col-sm-11 col-11 p-0 m-auto">
                    <input class="input col-lg-12 hoverEfect" id="senha" name="senha" maxlength="20" placeholder="Informe uma senha" type="password">
                    <i class="fa fa-eye" id="olho" aria-hidden="true"></i>
                </div>
            </div>

            <input id="btn-cadastrar" type="submit" value="Cadastrar">
            <span class="possuiLogin"><a href="login.php">Já possui uma conta? Então efetue o login para ter acesso completo ao nosso site!</a></span>
        </div>
    </form>
    <script src="js/cadastro.js"></script>
    <script src="https://kit.fontawesome.com/bc2be06805.js"></script>
</body>

</html>