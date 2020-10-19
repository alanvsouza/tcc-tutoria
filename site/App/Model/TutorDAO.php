<?php

namespace App\Model;

class TutorDAO
{
    public function readAll()
    {
        $query = "SELECT * FROM tbtutor ORDER BY nometutor";
        $stmt = Connection::getConn()->prepare($query);
        
        $stmt->execute();
        
        if($stmt->rowCount() > 0):
            $result = $stmt->fetchAll();
            return $result;
        endif;
        
        return null;
    }
    
    public function readHorarioDiaById($id, $diaSemana)
    {        
        $query = "SELECT `horarios` FROM `tbhorarios` WHERE `idprofessor` = ? AND `diaSemana` = ?";
        $stmt = Connection::getConn()->prepare($query);

        $stmt->bindParam(1, $id);
        $stmt->bindParam(2, $diaSemana);

        if($stmt->rowCount() > 0):
            $result = $stmt->fetch();
            return $result;
        endif;

        return null;
    }
}

