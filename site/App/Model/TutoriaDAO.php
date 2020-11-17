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

        if ($stmt->execute())
            return true;

        return false;
    }

    public function confirmarTutoria($id, $local)
    {
        $query = "UPDATE tbtutoria SET confirmada = 1, `localtutoria` = ? WHERE idtutoria = ?";
        $stmt = Connection::getConn()->prepare($query);

        $stmt->bindValue(1, $local);
        $stmt->bindValue(2, $id);

        if ($stmt->execute())
            return true;

        return false;
    }

    public function confirmarTutoriaDinamica($id, $local)
    {
        $query = "UPDATE tbtutoriadinamica SET confirmada = 1, `local` = ? WHERE idtutoria = ?";
        $stmt = Connection::getConn()->prepare($query);

        $stmt->bindValue(1, $local);
        $stmt->bindValue(2, $id);

        if ($stmt->execute())
            return true;

        return false;
    }

    public function cancelarTutoria($id)
    {
        $query = "DELETE FROM tbtutoria WHERE idtutoria = ?";
        $stmt = Connection::getConn()->prepare($query);

        $stmt->bindValue(1, $id);

        if ($stmt->execute())
            return true;

        return false;
    }

    public function cancelarTutoriaDinamica($id)
    {
        $query = "DELETE FROM tbtutoriadinamica WHERE idtutoria = ?";
        $stmt = Connection::getConn()->prepare($query);

        $stmt->bindValue(1, $id);

        if ($stmt->execute())
            return true;

        return false;
    }

    public function readTutoriasAgendadasByData($id, $data)
    {
        $query = "SELECT * FROM `tbtutoria` WHERE `idtutor` = ? AND `datatutoria` = ?";
        $stmt = Connection::getConn()->prepare($query);

        $stmt->bindValue(1, $id);
        $stmt->bindValue(2, $data);

        $stmt->execute();

        if ($stmt->rowCount() > 0) {
            return $stmt->fetchAll();
        }

        return null;
    }

    public function readTutoriasDinamicasAgendadasByData($id, $data)
    {
        $query = "SELECT * FROM `tbtutoriadinamica` WHERE `idtutor` = ? AND `data` = ?";
        $stmt = Connection::getConn()->prepare($query);

        $stmt->bindValue(1, $id);
        $stmt->bindValue(2, $data);

        $stmt->execute();

        if ($stmt->rowCount() > 0) {
            return $stmt->fetchAll();
        }

        return null;
    }

    public function createDinamico(Tutoria $tutoria)
    {
        $query = "INSERT INTO `tbtutoriadinamica` (`idaluno`, `idtutor`, `data`, `idhorario`) VALUES (?, ?, ?, ?);";
        $stmt = Connection::getConn()->prepare($query);

        $stmt->bindValue(1, $tutoria->getIdAluno());
        $stmt->bindValue(2, $tutoria->getIdTutor());
        $stmt->bindValue(3, $tutoria->getDataTutoria());
        $stmt->bindValue(4, $tutoria->getIdHorario());

        if ($stmt->execute())
            return true;

        return false;
    }

    public function readByHorarioAndData($idHorario, $data)
    {
        $query = "SELECT * FROM `tbtutoria` WHERE `idhorario` = ? AND `datatutoria` = ?";
        $stmt = Connection::getConn()->prepare($query);

        $stmt->bindValue(1, $idHorario);
        $stmt->bindValue(2, $data);

        $stmt->execute();

        if ($stmt->rowCount() > 0) {
            return $stmt->fetch();
        }

        return null;
    }

    public function readByHorarioDinamicoAndData($idHorario, $data)
    {
        $query = "SELECT * FROM `tbtutoriadinamica` WHERE `idhorario` = ? AND `data` = ?";
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
