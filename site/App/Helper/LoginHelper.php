<?php

namespace App\Helper;

class LoginHelper
{
    public static function login($login, $senha)
    {
        $alunoDao = new \App\Model\AlunoDAO();
        $tutorDao = new \App\Model\TutorDAO();

        $resultAluno = $alunoDao->readByLogin($login);
        $resultTutor = $tutorDao->readByLogin($login);

        $return = false;

        if ($resultAluno && $resultAluno['senha'] == md5($senha . 'cotilamigavel')) {
            $_SESSION['usuario'] = $login;
            $_SESSION['nome'] = $resultAluno['nomealuno'];
            $_SESSION['idUsuario'] = $resultAluno['idaluno'];
            $_SESSION['nivelAcesso'] = 0;
            $_SESSION['tipo'] = 'aluno';

            $return = true;
        } else if ($resultTutor && $resultTutor['senha'] == $senha) {
            $_SESSION['usuario'] = $login;
            $_SESSION['nome'] = $resultTutor['nometutor'];
            $_SESSION['idUsuario'] = $resultTutor['idtutor'];
            $_SESSION['nivelAcesso'] = 1;
            $_SESSION['tipo'] = 'tutor';

            $return = true;
        }

        return $return;
    }
}
