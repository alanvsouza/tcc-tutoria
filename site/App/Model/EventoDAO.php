<?php

namespace App\Model;

class EventoDAO
{
    public function readAll()
    {
        $query = "SELECT * FROM tbeventos";
        $stmt = Connection::getConn()->prepare($query);

        $stmt->execute();

        if ($stmt->rowCount() > 0) :
            $result = $stmt->fetchAll(\PDO::FETCH_ASSOC);
            return $result;
        endif;

        return null;
    }

    public function readAllBeforeToday()
    {
        $query = "SELECT * FROM tbeventos WHERE DATE_FORMAT(NOW(), '%Y/%m/%d') > dataevento;";
        $stmt = Connection::getConn()->prepare($query);

        $stmt->execute();

        if ($stmt->rowCount() > 0) :
            $result = $stmt->fetchAll(\PDO::FETCH_ASSOC);
            return $result;
        endif;

        return null;
    }

    public function readAllFromToday()
    {
        $query = "SELECT * FROM tbeventos WHERE DATE_FORMAT(NOW(), '%Y/%m/%d') <= dataevento;";
        $stmt = Connection::getConn()->prepare($query);

        $stmt->execute();

        if ($stmt->rowCount() > 0) :
            $result = $stmt->fetchAll(\PDO::FETCH_ASSOC);
            return $result;
        endif;

        return null;
    }

    public function readImagensEventoById($id)
    {
        $query = "SELECT * FROM tbimgeventos WHERE idevento = ?";
        $stmt = Connection::getConn()->prepare($query);

        $stmt->bindValue(1, $id);

        $stmt->execute();

        if ($stmt->rowCount() > 0) :
            $result = $stmt->fetchAll(\PDO::FETCH_ASSOC);

            return $result;
        endif;

        return null;
    }
}
