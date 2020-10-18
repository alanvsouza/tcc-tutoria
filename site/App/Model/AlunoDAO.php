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

    public function update(Aluno $aluno)
    {
    }

    public function delete($id)
    {
    }
}
