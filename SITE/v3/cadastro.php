<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Cadastro</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="css/style.min.css">

    <!-- My Style -->
    <link rel="stylesheet" href="css/cadastro.css">

    <!-- Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
    
    <!-- Font Awesonme -->
    <link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Berkshire+Swash|Josefin+Sans&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Josefin+Sans&display=swap" rel="stylesheet">
</head>
<body>
        <div class="center">
            <a href="index.php" class="title">COTIL Amigável</a>
        </div>
        <form class="col-lg-8 col-md-9 col-sm-11 col-11 p-0" action="#" method="post" id="form">
            
                <div>
                    <label for="nome">Nome</label>
                    <input class="input col-sm-12" placeholder="Informe seu nome" maxlength="50" type="text" id="nome" name="nome">
                </div>

                <div>
                    <label for="nome">E-mail</label>
                    <input class="input" placeholder="Informe seu e-mail" maxlength="50" type="text" id="email" name="nome">
                </div>

            <div class="col-lg-12 mt-3 p-0 flex-row" >
                <div id="curso" class="col-lg-6 col-md-6 col-sm-12 p-0  m-auto">
                    <label for="nome">Curso</label>
                        <select class="input" name="" style="min-height:40px">
                            <option value="0">Informática</option>
                            <option value="1">Mecânica</option>
                            <option value="2">Qualidade</option>
                            <option value="3">Edificações</option>
                            <option value="4">Enfermagem</option>
                            <option value="5">Geodésia e Cartografia</option>
                            <option value="6">Nenhum</option>
                        </select>
                </div>
                <div  class="col-lg-1 p-0"></div>
                <div id="anoIngresso" class="col-lg-5 col-md-5 col-sm-12 m-auto p-0">
                    <label  for="ano">Ano de Ingresso</label>
                    <input disabled class="col-lg-12 input" id="ano" type="number" value="2018" >
                        <label class="angle-arrow" onclick="more()" id="more"><i class="fas fa-angle-up"></i> </label>
                        <label class="angle-arrow"  onclick="less()" id="less"><i class="fas fa-angle-down"></i></label>
            </div>
            </div>

            <div class="flex-row mt-3 p-0">
                <div class="col-lg-5 col-md-5 col-sm-12  col-12 p-0 m-auto">
                    <label  for="nome">Senha</label>
                    <input class="input col-lg-12" placeholder="Informe uma senha" type="password">
                </div>
                
                <div class="col-lg-2 p-0"></div>

                <div class="col-lg-5 col-md-5 col-sm-12 col-12 p-0  m-auto" style="margin: auto auto 40px auto;">
                    <label  for="ano">Confirmação da Senha</label>
                    <input class="input col-lg-12" placeholder="Informe novamente a senha" type="password">
                </div>

            </div>
            <span class="no-share">Seus dados não serão compartilhados!</span>
            <input method="post" id="btn-submit" type="submit"  value="Cadastrar">
                
            
        </form>






    <!-- My JS -->
    <script src="js/cadastro.js"></script>


    <script type="text/javascrpit" src="node_modules/jquery/dist/jquery.slim.min.js"></script>
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
        crossorigin="anonymous"></script>
        
<script src="https://kit.fontawesome.com/bc2be06805.js"></script>
</body>
</html>