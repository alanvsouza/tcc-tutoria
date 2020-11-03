<?php

namespace App\Model;

class TutorDAO
{
    public function readAll()
    {
        $query = "SELECT * FROM tbtutor ORDER BY nometutor";
        $stmt = Connection::getConn()->prepare($query);

        $stmt->execute();

        if ($stmt->rowCount() > 0) :
            $result = $stmt->fetchAll();
            return $result;
        endif;

        return null;
    }

    public function readHorariosDiaById($id, $dia)
    {
        $query = "SELECT `idhorario`, `horarios` FROM `tbhorarios` WHERE `idprofessor` = ? AND `diaSemana` = ?";
        $stmt = Connection::getConn()->prepare($query);

        $stmt->bindParam(1, $id);
        $stmt->bindParam(2, $dia);

        $stmt->execute();

        if ($stmt->rowCount() > 0) :
            $result = $stmt->fetchAll();
            return $result;
        endif;

        return null;
    }

    // public function readMaterias($id)
    // {
    //     $query = "SELECT `nome` FROM `tbmateria` INNER JOIN `tbtutor_materia` 
    //         ON `tbmateria`.`id` = `tbtutor_materia`.`id_materia`
    //         INNER JOIN `tbtutor` ON `tbtutor`.`idtutor` = `tbtutor_materia`.`id_tutor`
    //         WHERE `tbtutor`.`idtutor` = ? ORDER BY `tbmateria`.`nome`";
    //     $stmt = Connection::getConn()->prepare($query);

    //     $stmt->bindParam(1, $id);

    //     $stmt->execute();

    //     if ($stmt->rowCount() > 0) :
    //         $result = $stmt->fetchAll();
    //         return $result;
    //     endif;

    //     return null;
    // }
}
