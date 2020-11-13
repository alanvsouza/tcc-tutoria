<?php

namespace App\Model;

class TutoriaDAO
{
    public function create(Tutoria $tutoria)
    {
        $query = "INSERT INTO `tbtutoria` (`idaluno`, `idtutor`, `datatutoria`, `idhorario`) VALUES (?, ?, ?, ?);";
        $stmt = Connection::getConn()->prepare($query);

        $stmt->bindValue(1, $tutoria->getIdAluno());
        $stmt->bindValue(2, $tutoria->getIdTutor());
        $stmt->bindValue(3, $tutoria->getDataTutoria());
        $stmt->bindValue(4, $tutoria->getIdHorario());

        $stmt->execute();
    }

    public function readByHorarioAndData($idHorario, $data)
    {
        $query = "SELECT idtutoria FROM `tbtutoria` WHERE `idhorario` = ? AND `datatutoria` = ?";
        $stmt = Connection::getConn()->prepare($query);

        $stmt->bindValue(1, $idHorario);
        $stmt->bindValue(2, $data);

        $stmt->execute();

        if ($stmt->rowCount() > 0) {
            return $stmt->fetch();
        }

        return null;
    }

    public function readAll()
    {
        $query = "SELECT * FROM tbtutoria";
        $stmt = Connection::getConn()->prepare($query);

        $stmt->execute();

        if ($stmt->rowCount() > 0) {
            return $stmt->fetchAll();
        }

        return null;
    }
}
