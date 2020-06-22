<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Cadastro</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="css/style.min.css">
    <link rel="stylesheet" href="css/cadastro.css">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="css/style.min.css">

    <!-- Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.4.1.js"></script>

    <!-- Font Awesonme -->
    <link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Berkshire+Swash|Josefin+Sans&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Josefin+Sans&display=swap" rel="stylesheet">


</head>
<body>
<form accept-charset="utf-8" class="col-xl-8 col-lg-8 col-md-9 col-sm-11 col-11" action="cadastrar.php" method="post" id="form">
    <div id="header">
        <p>Sistema de Cadastro</p>
        <a href="index.php">COTIL Amigável</a>
    </div>

    <div id="aviso">
        <span>AVISO: Todos os campos são obrigatórios! Suas informações poderão ser alterados em seu perfil.</span>
    </div>

    <div id="body" class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
        <div class="flex-row col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12 p-0">
            <input required class="input col-lg-5 col-md-10 col-sm-11 col-11" placeholder="Informe seu nome completo" maxlength="50" type="text" id="nome" name="nome">
            <input required class="input col-lg-5 col-md-10 col-sm-11 col-11" placeholder="Informe seu e-mail" maxlength="50" type="text" id="email" name="email">
        </div>

        <div class="flex-row col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12 p-0 m-0" id="selects">

            <div class="col-lg-5 col-md-5 col-sm-11 col-11 p-0 m-0 ">
                <select class="input" name="curso" id="curso" style="min-height:40px">
                    <option value="Nenhum Curso">Nenhum Curso</option>
                    <option value="Informática">Informática</option>
                    <option value="Mecânica">Mecânica</option>
                    <option value="Qualidade">Qualidade</option>
                    <option value="Edificações">Edificações</option>
                    <option value="Enfermagem">Enfermagem</option>
                    <option value="Geodésia e Cartografia">Geodésia e Cartografia</option>
                </select>
            </div>

            <div class="col-xl-1 col-lg-1 col-md-1 col-sm-0 col-0 p-0 m-0 display"></div>

            <div class="col-lg-5 col-md-5 col-sm-11 col-11 p-0 m-0 ">
                <select class="input" name="ano" id="ano" style="min-height:40px">
                    <option value="2020">Ingressei em 2020</option>
                    <option value="2019">Ingressei em 2019</option>
                    <option value="2018">Ingressei em 2018</option>
                </select>
            </div>
        </div>

            <div class="flex-row p-0  col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                <div class=" col-xl-5 col-lg-5 col-md-10 col-sm-11 col-11  p-0 m-auto">
                    <input required class="input col-lg-12" id="login" name="login" placeholder="Informe um login de usuário" maxlength="50" type="text">
                </div>
                <div class="col-xl-5 col-lg-5 col-md-10 col-sm-11 col-11 p-0 m-auto">
                    <input required class="input col-lg-12" id="senha" name="senha" maxlength="20" placeholder="Informe uma senha" type="password">
                    <i class="fa fa-eye" id="olho" aria-hidden="true"></i>
                </div>
            </div>

        <input method="post" id="btn-submit" type="submit"  value="Cadastrar">
    </div>

    <div id="aviso2">
        <span>AVISO: Suas informações não serão compartilhadas e ninguém além de você terá acesso a elas.</span>
    </div>
</form>

    <script src="js/cadastro.js"></script>
    <script src="https://kit.fontawesome.com/bc2be06805.js"></script>
    <!-- <script type="text/javascrpit" src="node_modules/jquery/dist/jquery.slim.min.js"></script>
    <script type="text/javascrpit" src="node_modules/popper.js/dist/umd/popper.min.js"></script>
    <script type="text/javascrpit" src="node_modules/bootstrap/dist/js/bootstrap.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script> -->
</body>
</html>