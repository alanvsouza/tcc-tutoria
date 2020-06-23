<?php

    namespace App\Model;

    class UsuarioDao {
        public function cadastrar(Usuario $u) {
            
            $sql = 'INSERT INTO tbaluno (nomealuno, login, senha, email, curso, anoingresso) VALUES (?, ?, ?, ?, ?, ?)';

            $stmt = Conexao::getConn()->prepare($sql);
            $stmt->bindValue(1, $u->getNome());
            $stmt->bindValue(2, $u->getLogin());
            $stmt->bindValue(3, $u->getSenha());
            $stmt->bindValue(4, $u->getEmail());
            $stmt->bindValue(5, $u->getCurso());
            $stmt->bindValue(6, $u->getAno());

            return $stmt->execute();

        }

        public function logar($login, $senha) {

            $sql = 'SELECT senha FROM tbaluno WHERE login=?';            

            $stmt = Conexao::getConn()->prepare($sql);
            $stmt->bindValue(1, $login);

            $stmt->execute();

            if($stmt->rowCount() > 0):
                $resultado = $stmt->fetchAll(\PDO::FETCH_ASSOC);                                                                

                var_dump($resultado);

                if(strcmp($resultado[0]["senha"], $senha) === 0) {
                    return true;
                }
            endif;

            return false;
        }

        public function update(Usuario $u) {

        }

        public function delete(Usuario $u) {

        }
    }