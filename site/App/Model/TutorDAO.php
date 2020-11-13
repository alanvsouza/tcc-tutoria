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

    public function readFotoPerfilByLogin($login)
    {
        $query = "SELECT foto FROM tbtutor WHERE `login` = ?";
        $stmt = Connection::getConn()->prepare($query);

        $stmt->bindValue(1, $login);

        $stmt->execute();

        if ($stmt->rowCount() > 0)
            return $stmt->fetch();

        return null;
    }

    public function readByLogin($login)
    {
        $query = "SELECT * FROM tbtutor WHERE `login` = ?";
        $stmt = Connection::getConn()->prepare($query);

        $stmt->bindValue(1, $login);

        $stmt->execute();

        if ($stmt->rowCount() > 0)
            return $stmt->fetch();

        return null;
    }

    public function readById($id)
    {
        $query = "SELECT * FROM tbtutor WHERE idtutor = ?";
        $stmt = Connection::getConn()->prepare($query);

        $stmt->bindValue(1, $id);

        $stmt->execute();

        if ($stmt->rowCount() > 0) {
            $result = $stmt->fetch();
            return $result;
        }

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
}
