<?php

namespace App\Model;

class AlunoDAO
{
    public function create(Aluno $a)
    {
        $query = "INSERT INTO `tbaluno` (`nomealuno`, `login`, `senha`, `email`, `curso`, `anoingresso`) VALUES (?, ?, ?, ?, ?, ?)";
        $stmt = Connection::getConn()->prepare($query);

        $stmt->bindValue(1, $a->getNomeAluno());
        $stmt->bindValue(2, $a->getLogin());
        $stmt->bindValue(3, $a->getSenha());
        $stmt->bindValue(4, $a->getEmail());
        $stmt->bindValue(5, $a->getCurso());
        $stmt->bindValue(6, $a->getAnoIngresso());

        $stmt->execute();
    }

    public function readByLogin($login)
    {
        $query = "SELECT * from `tbaluno` WHERE login = ?";
        $stmt = Connection::getConn()->prepare($query);

        $stmt->bindValue(1, $login);

        $stmt->execute();

        if ($stmt->rowCount() > 0) :
            $result = $stmt->fetch(\PDO::FETCH_ASSOC);
            return $result;
        endif;

        return null;
    }

    public function readDuplicateLogin($login)
    {
        $query = "SELECT `t1`.login from `tbaluno` as `t1` WHERE login = ? union all SELECT `t2`.login from `tbtutor` as `t2` where login = ?";
        $stmt = Connection::getConn()->prepare($query);
        $stmt->bindValue(1, $login);
        $stmt->bindValue(2, $login);

        $stmt->execute();

        if ($stmt->rowCount() > 0) :
            return true;
        endif;

        return false;
    }

    public function readFotoPerfilByLogin($login)
    {
        $query = "SELECT `foto` FROM `tbaluno` WHERE `login` = ?";
        $stmt = Connection::getConn()->prepare($query);

        $stmt->bindValue(1, $login);

        $stmt->execute();

        if ($stmt->rowCount() > 0) :
            $result = $stmt->fetch(\PDO::FETCH_ASSOC);

            return $result;
        endif;

        return null;
    }
}
