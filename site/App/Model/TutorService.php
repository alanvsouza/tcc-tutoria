<?php

namespace App\Model;

use DateTime;

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

    public static function getFotoPerfil()
    {
        $usuario = $_SESSION['usuario'];

        if (isset($usuario)) :
            $tutorDao = new TutorDAO();
            return $tutorDao->readFotoPerfilByLogin($usuario)['foto'];
        endif;

        return null;
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
                    "facebook" => $row["facebook"] == null ? "" : "<li><a href='{$row["facebook"]}' target='_blank'>Facebook</a></li>",
                    "twitter" => $row["twitter"] == null ? "" : "<li><a href='{$row["twitter"]}' target='_blank'>Twitter</a></li>",
                    "linkedin" => $row["linkedin"] == null ? "" : "<li><a href='{$row["linkedin"]}' target='_blank'>Linkedin</a></li>",
                    "instagram" => $row["instagram"] == null ? "" : "<li><a href='{$row["instagram"]}' target='_blank'>Instagram</a></li>"
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
                                ' . $redesSociais["linkedin"] . '
                                ' . $redesSociais["twitter"] . '
                                ' . $redesSociais["facebook"] . '
                                ' . $redesSociais["instagram"] . '
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
        $dataFormatada = DateTime::createFromFormat('j/m/Y', $data);
        $dataHoje = new DateTime();
        $dataFormatadaString = $dataFormatada->format('d/m/Y');
        $diaSemana = date_format($dataFormatada, 'N');

        $horarios = $tutorDao->readHorariosDinamicosByIdAndData($idTutor, $dataFormatada->format('Y-m-d'));
        $tipoHorario = 'dinamico';

        if (is_null($horarios)) {
            $horarios = $tutorDao->readHorariosDiaById($idTutor, $diaSemana);
            $tipoHorario = 'fixo';
        }

        if (!$horarios) {
            echo "<div class='tabela-sem-horarios col-xl-6'</div>";
        } else {
            echo "<div class='tabela-horarios-tutor col-xl-6'>";
        }

        echo "
            <ul class='responsive-table'>
                <div id='data-tutoria' class='col'>{$dataFormatadaString}</div>
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

            if (TutoriaService::existeTutoria($dataFormatada->format('Y-m-d'), $id, $tipoHorario)) {
                echo "
                <li class='table-row'>
                    <div class='' data-label='{$horario[0]}'>{$horario[0]}</div>
                    <div data-label='{$horario[1]}'>{$horario[1]}</div>
                    <div data-label='Indisponível'>Indisponível</div>
                    <form class='d-flex align-items-center form-selecionar-horario'>         
                        <input class='btn-selecionar indisponivel' disabled type='button' value='Selecionar'>
                    </form>
                </li>
                ";
            } else {
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
        }

        echo "
            <div class='footer-tabela-horarios'>
                <div class='col-xl-7 col-lg-7 col-md-7 col-sm-7 col-12 observacao'>O local da tutoria será definido pelo tutor após este confirmar o agendamento da mesma.</div>
                <form id='form-envio-tutoria' method='POST' action='functions/agendarTutoria.php' class='col-xl-5 col-lg-5 col-md-5 col-sm-5 col-12'>
                    <input type='hidden' name='idhorario' />
                    <input type='hidden' name='idtutor' value='{$_GET['professor']}' />
                    <input type='hidden' name='idaluno' value='{$_SESSION['idUsuario']}' />
                    <input type='hidden' name='data' value='{$dataFormatadaString}' />
                    <input type='hidden' name='tipo' value='{$tipoHorario}'>
                    
                    <input type='button' id='btn-agendar' value='Agendar Tutoria' />
                    <input type='submit' hidden />
                </form>
            </div>
        </div>
        ";
    }

    public static function renderizarTabelaHorariosFixosTutor()
    {
        $diaSemana = 1;
        $tutorDao = new TutorDAO;

        $diasSemanaNomes = array(
            1 => 'SEGUNDA',
            2 => 'TERÇA',
            3 => 'QUARTA',
            4 => 'QUINTA',
            5 => 'SEXTA',
            6 => 'SÁBADO'
        );

        echo "<ul id='ul-horarios-fixos' class='responsive-table more-width'>";

        while ($diaSemana < 7) {
            $horarios = $tutorDao->readHorariosDiaById($_SESSION['idUsuario'], $diaSemana);

            if (is_null($horarios)) {
                echo "<li class='table-row' id='{$diaSemana}'>
                    <div class='dia-semana'>{$diasSemanaNomes[$diaSemana]}</div>
                    <input class='input-hora' type='text' />
                    <input class='input-hora' type='text' />
                    <input class='input-hora' type='text' />
                    <input class='input-hora' type='text' />
                </li>";

                $diaSemana++;
                continue;
            }

            echo "<li class='table-row' id='sabado'>
                    <div class='dia-semana'>{$diasSemanaNomes[$diaSemana]}</div>";

            $tamanho = count($horarios);
            foreach ($horarios as $horario) {
                echo "<input class='input-hora' type='text' value='{$horario['horarios']}' />";
            }

            for ($i = $tamanho; $i < 4; $i++) {
                echo "<input class='input-hora' type='text' />";
            }

            echo "</li>";

            $diaSemana++;
        }

        echo "</ul>";
    }

    public static function renderizarInputsHorarioDinamico($data)
    {
        $idTutor = $_SESSION['idUsuario'];
        $tutorDao = new TutorDAO();

        $horarios = $tutorDao->readHorariosDinamicosByIdAndData($idTutor, $data);

        if (is_null($horarios)) {
            echo "
                <input class='input-hora' type='text' />
                <input class='input-hora' type='text' />
                <input class='input-hora' type='text' />
                <input class='input-hora' type='text' />
            ";
        } else {
            foreach ($horarios as $key => $horario) {
                echo "<input class='input-hora' type='text' value='{$horario['horarios']}' />";
            }

            $numInputs = count($horarios) / 2.0;

            for ($i = $numInputs; $i < 4; $i++) {
                echo "<input class='input-hora' type='text' />";
            }
        }
    }
}
