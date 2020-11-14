<?php

namespace App\Model;

class AcessoDAO
{
    public function readByNivelAcessoAndFuncionalidade($nivelAcesso, $funcionalidade)
    {
        $query = "SELECT * FROM tbacesso WHERE nivelAcesso = ? && funcionalidade = ?";
        $stmt = Connection::getConn()->prepare($query);

        $stmt->bindValue(1, $nivelAcesso);
        $stmt->bindValue(2, $funcionalidade);

        $stmt->execute();

        if ($stmt->rowCount() > 0)
            return $stmt->fetch();

        return null;
    }
}
