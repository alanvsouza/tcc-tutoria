<?php
require_once "vendor/autoload.php";
\App\Model\Session::startSession();
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
    include("nav.php");
    include("navside.php");
    include('calendario.php');
    //    include("db/credentials.php");
    //    include("db/db_connect.php");
    //    include("functions/agendarTutoria.php");
    ?>

    <div class="row container-body">
        <div class="content-tutor p-0 m-0 col-xl-6">
            <div class="header-tutor">
                <div class="row content-header-tutor">
                    <div class="row informacoes-tutor">
                        <?php
                        if (isset($_GET['professor']))
                            \App\Model\TutorService::renderizarInformacoes($_GET['professor']);
                        echo "<pre style='position: absolute; top: 100px'>" . var_export($_SESSION, true) . "</pre>";
                        ?>
                    </div>
                    <div class="p-0 m-auto content-selecionar">
                        <form action="<?= $_SERVER['PHP_SELF'] ?>">
                            <select name="professor" id="selecionar-tutor">
                                <option value="" <?php if (!isset($_GET['professor'])) echo 'selected="selected"' ?> disabled>Selecione um tutor...</option>
                                <?php \App\Model\TutorService::renderizarComboboxProfessores(); ?>
                            </select>

                            <?php if (isset($_GET['data'])) : ?>
                                <input id='data' type="hidden" name="data" value="<?= $_GET['data'] ?>" />
                            <?php else : ?>
                                <input id='data' type="hidden" name="data" disabled />
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
        }
        ?>
    </div>

    <script type="text/javascript" src="js/nav.js"></script>
    <script type="text/javascript" src="js/navside.js"></script>
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/calendario.js"></script>
    <script src="js/selecionarProfessor.js"></script>
    <script src="js/agendarTutoria.js"></script>

    <script type="text/javascript">
        function dataTutoria(data) {
            let dataFormatada = data.split("/");
            data = dataFormatada[1] + "/" + dataFormatada[0] + "/" + dataFormatada[2];

            let inputData = document.querySelector("input#data-selecionada");
            let fakeData = document.querySelector("#fakeData");
            let submitFakeData = document.querySelector("#submitFakeData");
            let idProf = document.querySelector("form input#idProf");

            inputData.value = data;
            fakeData.value = data;
            idProf.value = <?= $_GET["professor"] ?>;

            submitFakeData.click();
        }
    </script>

    <!-- <script type="text/javascrpit" src="node_modules/jquery/dist/jquery.slim.min.js"></script>
<script type="text/javascrpit" src="node_modules/popper.js/dist/umd/popper.min.js"></script>
<script type="text/javascrpit" src="node_modules/bootstrap/dist/js/bootstrap.min.js"></script> -->
</body>

</html>

<?php
unset($_SESSION['tutoria_agendada']);
