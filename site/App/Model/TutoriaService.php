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
