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
        $query = "SELECT * FROM `tbhorarios` WHERE `idprofessor` = ? AND `diaSemana` = ?";
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

    public function deleteAllHorariosByDia($id, $dia)
    {
        $query = "DELETE FROM tbhorarios WHERE idprofessor = ? AND diaSemana = ?";
        $stmt = Connection::getConn()->prepare($query);

        $stmt->bindValue(1, $id);
        $stmt->bindValue(2, $dia);

        $stmt->execute();
    }

    public function readHorariosById($id)
    {
        $query = "SELECT * FROM tbhorarios WHERE idprofessor = ? ORDER BY diaSemana";
        $stmt = Connection::getConn()->prepare($query);

        $stmt->bindValue(1, $id);

        $stmt->execute();

        if ($stmt->rowCount() > 0)
            return $stmt->fetchAll();

        return null;
    }

    public function readHorariosDinamicosByIdAndData($id, $data)
    {
        $dataFormatada = \DateTime::createFromFormat('d/m/Y', $data)->format('Y-m-d');
        $query = "SELECT horarios FROM tbhorariosdinamicos WHERE idprofessor = ? AND data = ?";
        $stmt = Connection::getConn()->prepare($query);

        $stmt->bindValue(1, $id);
        $stmt->bindValue(2, $dataFormatada);

        $stmt->execute();

        if ($stmt->rowCount() > 0)
            return $stmt->fetch();

        return null;
    }

    public function createHorario($id, $horario, $dia)
    {
        $query = "INSERT INTO tbhorarios (idprofessor, horarios, diaSemana) VALUES (?, ?, ?)";
        $stmt = Connection::getConn()->prepare($query);

        $stmt->bindValue(1, $id);
        $stmt->bindValue(2, $horario);
        $stmt->bindValue(3, $dia);

        $stmt->execute();
    }
}
