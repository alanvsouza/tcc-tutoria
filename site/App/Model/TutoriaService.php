<?php

namespace App\Model;

class TutoriaService
{
    public static function agendarTutoria(Tutoria $tutoria)
    {
        Session::startSession();
        $tutoriaDao = new \App\Model\TutoriaDAO();
        $return = false;

        if ($tutoriaDao->create($tutoria)) {
            $_SESSION['tutoria_agendada'] = true;
            $return = true;
        }

        return $return;
    }

    public static function existeTutoria($data, $idHorario)
    {
        $tutoriaDao = new \App\Model\TutoriaDAO();

        if ($tutoriaDao->readByHorarioAndData($idHorario, $data) != null)
            return true;

        return false;
    }
}
