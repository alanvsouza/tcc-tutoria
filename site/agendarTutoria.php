<?php
require_once "vendor/autoload.php";
\App\Model\Session::startSession();

date_default_timezone_set('America/Sao_Paulo');

if (isset($_SESSION['tutoria_agendada']))
    header('refresh: 0.7');

$nivelAcesso = isset($_SESSION['nivelAcesso']) ? $_SESSION['nivelAcesso'] : NULL;
\App\Helper\AcessoHelper::nivelAcesso($nivelAcesso, __FILE__);

include("functions/acesso.php");
?>

<!DOCTYPE html>
<html lang="pt-BR">

<head>
    <title>Agendamento de tutoria</title>

    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="css/style.min.css">

    <!-- My styles CSS -->
    <link href="css/style.scss">
    <link rel="stylesheet" href="css/nav.css">
    <link rel="stylesheet" href="css/navside.css">
    <link rel="stylesheet" href="css/agTutoria.css">
    <link rel="stylesheet" href="css/calendario.css">
    <link rel="stylesheet" href="css/fotoPerfil.css">

    <!-- Font Awesome -->
    <link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Berkshire+Swash|Josefin+Sans&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Josefin+Sans&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.0-2/css/all.min.css">
    <style>
        table#horarios-professor,
        td#horarios-professor,
        th#horarios-professor {
            border: 1px solid black;
            border-collapse: collapse;
        }

        table#horarios-professor {
            text-align: center;
        }

        td#horarios-professor {
            padding: 0;
        }

        table#horarios-professor button[type="submit"] {
            width: 100%;
            height: 100%;
            border: none;
            background-color: orange;
            color: white;
        }
    </style>

</head>

<body>

    <?php
    include("navAluno.php");
    include("navside.php");
    include('calendario.php');
    ?>

<div id="modal-selecionar-foto">
        <form class="form-foto-perfil">
             <i id="close-selecionar-foto" class='fas fa-times close'></i>
            <label for='selecao-arquivo'><img id="img-upload" src="img/upload.png"></label>
            <input id='selecao-arquivo' type='file'>
        </form>
    </div>

    <div class="row container-body">
        <div class="content-tutor m-0 col-xl-6">
            <div class="header-tutor">
                <div class="row content-header-tutor">
                    <div class="row informacoes-tutor">
                        <?php
                        if (isset($_GET['professor']))
                            \App\Model\TutorService::renderizarInformacoes($_GET['professor']);
                        ?>
                    </div>
                    <div class="p-0 m-auto content-selecionar">
                        <form id="form-info-tutoria" action="<?= $_SERVER['PHP_SELF'] ?>">
                            <select name="professor" id="selecionar-tutor">
                                <?php \App\Model\TutorService::renderizarComboboxProfessores(); ?>
                            </select>

                            <?php if (isset($_GET['data'])) : ?>
                                <input id='data' type="hidden" name="data" value="<?= $_GET['data'] ?>" />
                            <?php else : ?>
                                <input id='data' type="hidden" name="data" value="<?= date('d/m/Y', time()) ?>" />
                            <?php endif; ?>

                            <input id="select-professor-combobox" type="submit" hidden />
                        </form>
                    </div>
                </div>
            </div>

            <div class="content-agentar-tutoria mb-2">
                <div class="calendario">
                    <?php montaCalendario(); ?>
                </div>
            </div>
        </div>
        <?php
        if (isset($_GET['professor']) && isset($_GET['data'])) {
            $professor = $_GET['professor'];
            $data = $_GET['data'];

            \App\Model\TutorService::renderizarTabelaHorarios($professor, $data);
        } else if (isset($_SESSION['tutoria_agendada'])) {
            echo "
            <div class='tabela-sem-horarios col-xl-6'</div>
            <ul class='responsive-table'>                
                <li class='table-header'>
                    <div>Início</div>
                    <div>Término</div>
                    <div>Disponibilidade</div>
                    <div>Agendar</div>
                </li>";

            if ($_SESSION['tutoria_agendada']) {
                echo "<div id='tutoria-agendada-sucesso' class='sem-resultados'>Tutoria agendada com sucesso!</div>";
            } else {
                echo "<div id='tutoria-agendada-falha' class='sem-resultados'>Erro ao agendar tutoria!</div>";
            }

            unset($_SESSION['tutoria_agendada']);
        }
        ?>
    </div>

    <script type="text/javascript" src="js/nav.js"></script>
    <script type="text/javascript" src="js/navside.js"></script>
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/calendario.js"></script>
    <script src="js/selecionarDataTutoria.js"></script>
    <script src="js/selecionarProfessor.js"></script>
    <script src="js/selecionarPrimeiroProf.js"></script>
    <script src="js/agendarTutoria.js"></script>

    <script type="text/javascript">
        function dataTutoria(data) {
            let dataFormatada = data.split("/");
            data = dataFormatada[1] + "/" + dataFormatada[0] + "/" + dataFormatada[2];

            selecionarData(data);
        }
    </script>
    <script src="js/selecionarProfessor.js"></script>

</body>

<script type="text/javascript" src="js/fotoPerfil.js"></script>
</html>

<?php
unset($_SESSION['tutoria_agendada']);
