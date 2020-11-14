<?php

namespace App\Helper;

class AcessoHelper
{
    public static function nivelAcesso($nivelAcesso, $arquivo)
    {
        $arquivo = basename($arquivo, '.php');

        $falha = false;

        if (is_null($nivelAcesso)) {
            $falha = true;
        } else {
            $acessoDao = new \App\Model\AcessoDAO();
            $result = $acessoDao->readByNivelAcessoAndFuncionalidade($nivelAcesso, $arquivo);

            if (is_null($result))
                $falha = true;
        }

        if ($falha)
            header("Location: index.php");
    }
}
