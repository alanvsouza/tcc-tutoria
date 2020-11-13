<?php

namespace App\Model;

class TutorService
{
    public static function renderizarProfessoresAgendamentoTutoria()
    {
        $tutorDao = new TutorDAO();
        $tutores = $tutorDao->readAll();

        for ($i = 0; $i < sizeof($tutores); $i++) :
            $row = $tutores[$i];
            $nome = $row["nometutor"];
            $foto_banco = $row["foto"];
            $id = $row["idtutor"];

            $foto = null;
            if ($foto_banco == null)
                $foto = "img/defaultPicture.png";
            else
                $foto = "img-professores/" . $foto_banco;

            echo
                '<li> 
                    <div class="col-lg-12 p-0 m-0 row"  id="info-prof">
                    <img src="' . $foto . '" class="image col-lg-5" alt="">
                        <div id="dados" class="col-lg-7" >
                            <div id="nome-professor">' . $nome . '</div>
                            <form method="GET" action="' . htmlspecialchars($_SERVER["PHP_SELF"]) . '">
                                <input type="hidden" name="id" value="' . $id . '">
                                <button id="btn-colsultar-horarios"> Consultar horários </button>
                            </form>
                        </div>
                    </div>
                </li>';

        endfor;
    }

    public static function renderizarComboboxProfessores()
    {
        $tutorDao = new TutorDAO();
        $tutores = $tutorDao->readAll();
        $count = 0;

        foreach ($tutores as $tutor) {
            $count += 1;
            $id = $tutor['idtutor'];
            $nome = $tutor['nometutor'];
            $selected = isset($_GET['professor'])
                && $_GET['professor'] == $id
                ? 'selected="selected"'
                : null;
            if ($count == 1)
                echo  "<option class='option-tutor' value='{$id}' selected='selected'>{$nome}</option>";
            else
                echo "<option class='option-tutor' value='{$id}' {$selected}>{$nome}</option>";
        }
    }

    public static function renderizarProfessoresProjetoTutoria()
    {
        $tutorDao = new TutorDAO();
        $tutores = $tutorDao->readAll();

        for ($i = 0; $i < sizeof($tutores); $i++) :
            $row = $tutores[$i];
            $nome = $row["nometutor"];
            $foto = $row["foto"];
            $redesSociais =
                array(
                    "facebook" => $row["facebook"] == null ? "\"" . "#" . "\"" : "\"" . $row["facebook"] . "\"" .
                        " target='_blank'",
                    "twitter" => $row["twitter"] == null ? "\"" . "#" . "\"" : "\"" . $row["twitter"] . "\"" .
                        " target='_blank'",
                    "linkedin" => $row["linkedin"] == null ? "\"" . "#" . "\"" : "\"" . $row["linkedin"] . "\"" .
                        " target='_blank'",
                    "instagram" => $row["instagram"] == null ? "\"" . "#" . "\"" : "\"" . $row["instagram"] . "\"" .
                        " target='_blank'"
                );

            $descricao = $row["descricao"];
            $materias = $row["disciplinas"];
            // $materias = TutorService::getMateriasString($row['idtutor']) == null
            //     ? "" : TutorService::getMateriasString($row['idtutor']);

            if ($foto == null)
                $foto = "defaultPicture.png";

            echo
                '<div class="card-container mt-4" id="' . str_replace(" ", "-", (trim($nome))) . '" name="' . str_replace("é", "e", str_replace(" ", "-", (trim($nome)))) . '">
                    <img class="round" src="img-professores/' . $foto . '" alt="user" />
                    <h3>' . $nome . '</h3>
                    <h6>' . $materias . '</h6>
                        <p class="justify">' . $descricao . '</p>
                        <div class="midias">
                            <ul>
                                <li><a href=' . $redesSociais["linkedin"] . '>Linkedin</a></li>
                                <li><a href=' . $redesSociais["twitter"] . '>Twitter</a></li>
                                <li><a href=' . $redesSociais["facebook"] . '>Facebook</a></li>
                                <li><a href=' . $redesSociais["instagram"] . '>Instagram</a></li>
                            </ul>
                        </div>
                </div>';
        endfor;
    }

    public static function renderizarInformacoes($id)
    {
        $tutorDao = new TutorDAO;
        $infos = $tutorDao->readById($id);

        echo "
        <img src='img-professores/{$infos['foto']}' class='img-tutor'></img>
        <div class='column reposicionar'>
            <span class='nome-tutor'>{$infos['nometutor']}</span>
            <div class='informacoes row'>
                <span id='disciplinas'>{$infos['disciplinas']}</span>
            </div>
        </div>
        ";
    }

    public static function renderizarTabelaHorarios($idTutor, $data)
    {
        $tutorDao = new TutorDAO;
        echo $data;
        $dataFormatada = date_format(date_create($data), 'd/m/Y');
        var_dump($dataFormatada);
        die();
        $diaSemana = date_format(date_create($dataFormatada), 'N');

        $horarios = $tutorDao->readHorariosDiaById($idTutor, $diaSemana);

        if (!$horarios) {
            echo "<div class='tabela-sem-horarios col-xl-6'</div>";
        } else {
            echo "<div class='tabela-horarios-tutor col-xl-6'>";
        }

        echo "
            <ul class='responsive-table'>
                <div id='data-tutoria' class='col'>{$dataFormatada}</div>
                <li class='table-header'>
                    <div>Início</div>
                    <div>Término</div>
                    <div>Disponibilidade</div>
                    <div>Agendar</div>
                </li>";

        if (!$horarios) {
            echo "<div class='sem-resultados'>Nenhum horário encontrado para este dia!</div>";

            return;
        }

        foreach ($horarios as $horario) {
            // echo '<pre>' . var_export($horario, true) . '</pre>';
            $id = $horario['idhorario'];
            $horario = explode('-', $horario['horarios']);

            if (TutoriaService::existeTutoria($data, $id)) {
                echo "
                <li class='table-row'>
                    <div class='' data-label='{$horario[0]}'>{$horario[0]}</div>
                    <div data-label='{$horario[1]}'>{$horario[1]}</div>
                    <div data-label='Disponível'>Indisponível</div>
                    <form class='d-flex align-items-center form-selecionar-horario'>
                        <input type='hidden' name='idhorario' value='{$id}'>
                        <input class='btn-selecionar indisponivel' type='button' value='Selecionar'>
                    </form>
                </li>
                ";

                continue;
            }

            echo "
            <li class='table-row'>
                <div class='' data-label='{$horario[0]}'>{$horario[0]}</div>
                <div data-label='{$horario[1]}'>{$horario[1]}</div>
                <div data-label='Disponível'>Disponível</div>
                <form class='d-flex align-items-center form-selecionar-horario'>
                    <input type='hidden' name='idhorario' value='{$id}'>
                    <input class='btn-selecionar disponivel' type='button' value='Selecionar'>
                </form>
            </li>
            ";
        }

        echo "
            <div class='footer-tabela-horarios'>
                <div class='col-xl-7 col-lg-7 col-md-7 col-sm-7 col-12 observacao'>O local da tutoria será definido pelo tutor após este confirmar o agendamento da mesma.</div>
                <form id='form-envio-tutoria' method='POST' action='functions/agendarTutoria.php' class='col-xl-5 col-lg-5 col-md-5 col-sm-5 col-12'>
                    <input type='hidden' name='idhorario' />
                    <input type='hidden' name='idtutor' value='{$_GET['professor']}' />
                    <input type='hidden' name='idaluno' value='{$_SESSION['idUsuario']}' />
                    <input type='hidden' name='data' value='{$_GET['data']}' />
                    
                    <input type='button' id='btn-agendar' value='Agendar Tutoria' />
                    <input type='submit' hidden />
                </form>
            </div>
        </div>
        ";
    }
}
