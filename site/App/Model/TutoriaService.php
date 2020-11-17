<?php

namespace App\Model;

class TutoriaService
{
    public static function agendarTutoria(Tutoria $tutoria)
    {
        Session::startSession();
        $tutoriaDao = new \App\Model\TutoriaDAO();

        if ($tutoria->getTipoHorario() == 'fixo') {
            if ($tutoriaDao->create($tutoria))
                return true;
        } else if ($tutoria->getTipoHorario() == 'dinamico') {
            if ($tutoriaDao->createDinamico($tutoria))
                return true;
        }

        return false;
    }

    public static function renderizarTutoriasAgendadasTutor($id, $data)
    {
        Session::startSession();
        $tutoriaDao = new \App\Model\TutoriaDAO();
        $alunoDao = new \App\Model\AlunoDAO();
        $tutorDao = new \App\Model\TutorDAO();

        $result = $tutoriaDao->readTutoriasAgendadasByData($id, $data);

        if (is_null($result)) {
            echo "<span class='nenhuma-tutoria-agendada'>Não encontramos nenhuma tutoria agendada para este dia!</span>";
        }


        foreach ($result as $tutoria) {
            $aluno = $alunoDao->readById($tutoria['idaluno']);
            $tutoria['datatutoria'] = \DateTime::createFromFormat('Y-m-d', $tutoria['datatutoria'])->format('d/m/Y');
            $horario = $tutorDao->readHorarioById($tutoria['idhorario']);

            switch ($tutoria['confirmada']) {
                case 0:
                    echo "<div class='table-body'>
                    <div class='table-row'>
                        <div class='tutoria-agendada'>
                            <div class='tutoria-agendada-header'>
                                <img src='img-alunos/1.jpeg' class='img-usuario' alt=''>
                                <div class='column-header'>
                                    <span class='nome-usuario-tutoria'>{$aluno['nomealuno']}</span>
                                    <div class='line'>
                                        <i class='far fa-envelope'></i>
                                        <span class='info email'>{$aluno['email']}</span>
                                    </div>
                                    <div class='row-header mt-1'>
                                        <div class='line mr-5'>
                                            <i class='fas fa-street-view'></i><span class='info ml-1'>Ingresso: {$aluno['anoingresso']}</span>
                                        </div>
                                        <div class='line'>
                                            <i class='far fa-address-card'></i><span class='info ml-1'>{$aluno['curso']}</span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class='tutoria-agendada-body'>
                                <div class='infos-tutoria-agendada'>
                                    <div class='column'>
                                        <div class='line'>
                                            <i class='fas fa-calendar-alt'></i>
                                            <span class='info'>{$tutoria['datatutoria']}</span>
                                        </div>
                                        <div class='line'>
                                            <i class='far fa-clock'></i>
                                            <span class='info'>{$horario['horarios']}</span>
                                        </div>
                                    </div>                                    
                                    <form class='form-btn-tutoria' method='POST' action='functions/confirmarTutoria.php'>                                        
                                        <div class='line local'>
                                            <i class='fas fa-map-marker-alt'></i>
                                            <input type='text' name='local' class='info input-local'>
                                        </div>                                        
                                        <div class='line'>
                                            <input type='hidden' name='id-tutoria' value='{$tutoria['idtutoria']}'>                                            
                                            <input type='hidden' name='tipo-tutoria' value='fixo'>
                                            <button type='submit' class='btn-confirmar-tutoria'>Confirmar Tutoria</button>
                                        </div>                                        
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>";

                    break;
                case 1:
                    echo "<div class='table-body'>
                    <div class='table-row'>
                        <div class='tutoria-agendada'>
                            <div class='tutoria-agendada-header'>
                                <img src='img-alunos/1.jpeg' class='img-usuario' alt=''>
                                <div class='column-header'>
                                    <span class='nome-usuario-tutoria'>{$aluno['nomealuno']}</span>
                                    <div class='line'>
                                        <i class='far fa-envelope'></i>
                                        <span class='info email'>{$aluno['email']}</span>
                                    </div>
                                    <div class='row-header mt-1'>
                                        <div class='line mr-5'>
                                            <i class='fas fa-street-view'></i><span class='info ml-1'>Ingresso: {$aluno['anoingresso']}</span>
                                        </div>
                                        <div class='line'>
                                            <i class='far fa-address-card'></i><span class='info ml-1'>{$aluno['curso']}</span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class='tutoria-agendada-body'>
                                <div class='infos-tutoria-agendada'>
                                    <div class='column'>
                                        <div class='line'>
                                            <i class='fas fa-calendar-alt'></i>
                                            <span class='info'>{$tutoria['datatutoria']}</span>
                                        </div>
                                        <div class='line'>
                                            <i class='far fa-clock'></i>
                                            <span class='info'>{$horario['horarios']}</span>
                                        </div>
                                    </div>
                                    <div class='column'>
                                        <div class='line local'>
                                            <i class='fas fa-map-marker-alt'></i>
                                            <span type='text' class='info'>{$tutoria['localtutoria']}</span>
                                        </div>
                                        <div class='line local'>
                                            <i class='far fa-star'></i>
                                            <span class='info'>Confirmada</span>
                                        </div>                                        
                                    </div>
                                    <form class='form-btn-tutoria' method='POST' action='functions/cancelarTutoria.php'>
                                        <div class='line'>
                                            <input type='hidden' name='id-tutoria' value='{$tutoria['idtutoria']}'>
                                            <input type='hidden' name='tipo-tutoria' value='fixo'>
                                            <button type='submit' class='btn-confirmar-tutoria'>Cancelar Tutoria</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>";
                    break;
            }
        }
    }

    public static function renderizarTutoriasDinamicasAgendadasTutor($id, $data)
    {
        Session::startSession();
        $tutoriaDao = new \App\Model\TutoriaDAO();
        $alunoDao = new \App\Model\AlunoDAO();
        $tutorDao = new \App\Model\TutorDAO();

        $result = $tutoriaDao->readTutoriasDinamicasAgendadasByData($id, $data);

        if (is_null($result)) {
            echo "<span class='nenhuma-tutoria-agendada'>Não encontramos nenhuma tutoria agendada para este dia!</span>";
        }

        foreach ($result as $tutoria) {
            $aluno = $alunoDao->readById($tutoria['idaluno']);
            $tutoria['data'] = \DateTime::createFromFormat('Y-m-d', $tutoria['data'])->format('d/m/Y');
            $horario = $tutorDao->readHorarioDinamicoById($tutoria['idhorario']);

            switch ($tutoria['confirmada']) {
                case 0:
                    echo "<div class='table-body'>
                    <div class='table-row'>
                        <div class='tutoria-agendada'>
                            <div class='tutoria-agendada-header'>
                                <img src='img-alunos/1.jpeg' class='img-usuario' alt=''>
                                <div class='column-header'>
                                    <span class='nome-usuario-tutoria'>{$aluno['nomealuno']}</span>
                                    <div class='line'>
                                        <i class='far fa-envelope'></i>
                                        <span class='info email'>{$aluno['email']}</span>
                                    </div>
                                    <div class='row-header mt-1'>
                                        <div class='line mr-5'>
                                            <i class='fas fa-street-view'></i><span class='info ml-1'>Ingresso: {$aluno['anoingresso']}</span>
                                        </div>
                                        <div class='line'>
                                            <i class='far fa-address-card'></i><span class='info ml-1'>{$aluno['curso']}</span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class='tutoria-agendada-body'>
                                <div class='infos-tutoria-agendada'>
                                    <div class='column'>
                                        <div class='line'>
                                            <i class='fas fa-calendar-alt'></i>
                                            <span class='info'>{$tutoria['data']}</span>
                                        </div>
                                        <div class='line'>
                                            <i class='far fa-clock'></i>
                                            <span class='info'>{$horario['horarios']}</span>
                                        </div>
                                    </div>
                                    <form class='form-btn-tutoria' method='POST' action='functions/confirmarTutoria.php'>
                                        <div class='line local'>
                                            <i class='fas fa-map-marker-alt'></i>
                                            <input type='text' name='local' class='info input-local'>
                                        </div>
                                        <div class='line'>
                                            <input type='hidden' name='id-tutoria' value='{$tutoria['idtutoria']}'>
                                            <input type='hidden' name='tipo-tutoria' value='dinamico'>
                                            <button type='submit' class='btn-confirmar-tutoria'>Confirmar Tutoria</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>";

                    break;
                case 1:
                    echo "<div class='table-body'>
                    <div class='table-row'>
                        <div class='tutoria-agendada'>
                            <div class='tutoria-agendada-header'>
                                <img src='img-alunos/1.jpeg' class='img-usuario' alt=''>
                                <div class='column-header'>
                                    <span class='nome-usuario-tutoria'>{$aluno['nomealuno']}</span>
                                    <div class='line'>
                                        <i class='far fa-envelope'></i>
                                        <span class='info email'>{$aluno['email']}</span>
                                    </div>
                                    <div class='row-header mt-1'>
                                        <div class='line mr-5'>
                                            <i class='fas fa-street-view'></i><span class='info ml-1'>Ingresso: {$aluno['anoingresso']}</span>
                                        </div>
                                        <div class='line'>
                                            <i class='far fa-address-card'></i><span class='info ml-1'>{$aluno['curso']}</span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class='tutoria-agendada-body'>
                                <div class='infos-tutoria-agendada'>
                                    <div class='column'>
                                        <div class='line'>
                                            <i class='fas fa-calendar-alt'></i>
                                            <span class='info'>{$tutoria['data']}</span>
                                        </div>
                                        <div class='line'>
                                            <i class='far fa-clock'></i>
                                            <span class='info'>{$horario['horarios']}</span>
                                        </div>
                                    </div>
                                    <div class='column'>
                                        <div class='line local'>
                                            <i class='fas fa-map-marker-alt'></i>
                                            <span type='text' class='info'>{$tutoria['local']}</span>
                                        </div>
                                        <div class='line local'>
                                            <i class='far fa-star'></i>
                                            <span class='info'>Confirmada</span>
                                        </div>                                        
                                    </div>
                                    <form class='form-btn-tutoria' method='POST' action='functions/cancelarTutoria.php'>
                                        <div class='line'>
                                            <input type='hidden' name='id-tutoria' value='{$tutoria['idtutoria']}'>
                                            <input type='hidden' name='tipo-tutoria' value='dinamico'>
                                            <button type='submit' class='btn-confirmar-tutoria'>Cancelar Tutoria</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>";
                    break;
            }
        }
    }

    public static function existeTutoria($data, $idHorario, $tipoHorario)
    {
        $tutoriaDao = new \App\Model\TutoriaDAO();


        if ($tipoHorario == 'fixo') {
            if (!is_null($tutoriaDao->readByHorarioAndData($idHorario, $data)))
                return true;
        } else if ($tipoHorario == 'dinamico') {
            if (!is_null($tutoriaDao->readByHorarioDinamicoAndData($idHorario, $data)))
                return true;
        }

        return false;
    }
}
