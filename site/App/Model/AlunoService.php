<?php

namespace App\Model;

class AlunoService
{
    public static function login($a)
    {
        $alunoDao = new \App\Model\AlunoDAO();
        $result = $alunoDao->readByLogin($a->getLogin());

        if ($result != null) :
            if ($result["senha"] == $a->getSenha()) :
                $_SESSION["usuario"] = $a->getLogin();
                header('Location: ../index.php');
            endif;
        else :
            $_SESSION["nao_autenticado"] = false;
            header('Location: ../login.php');
        endif;
    }

    public static function checkCredentials($a)
    {
        $error = false;

        $alunoDao = new \App\Model\AlunoDAO();

        if (strlen($a->getNomeAluno()) == 0 || strlen($a->getLogin()) == 0  || strlen($a->getSenha()) == 0  || strlen($a->getEmail()) == 0) {
            $error = true;
            $_SESSION['errosCadastro'] = "Todos os campos devem ser preenchidos!";
        } else if (substr_count($a->getEmail(), '@') != 1 || substr_count($a->getEmail(), '.') < 1 ||  substr_count($a->getEmail(), '.') == 0) {
            $error = true;
            $_SESSION['errosCadastro'] =  "Informe um e-mail válido! Exenplo: email@gmail.com";
        } else if (stristr($a->getSenha(), "'")) {
            $error = true;
            $_SESSION['errosCadastro'] =  "O caractere ( ' ) é inválido!";
        } else if ($alunoDao->readByLogin($a->getLogin()) != null) {
            $error = true;
            $_SESSION['errosCadastro'] =  "Login de usuário indisponível! Por favor, escolha outro login";
        }

        return !$error;
    }
    
    public static function getFotoPerfil()
    {
        $usuario = $_SESSION['usuario'];
        
        if(isset($usuario)):        
            $alunoDao = new AlunoDAO();
            return $alunoDao->readFotoPerfilByLogin($usuario)['foto'];
        endif;               
        
        return null;
    }
}
