<?php

namespace App\Helper;

class AcessoHelper
{
    public static function nivelAcesso($nivelAcesso, $dirname, $redirectFile)
    {
        $pastaAtual = explode('/', $dirname);
        $pastaAtual = $pastaAtual[sizeof($pastaAtual) - 1];

        $falha = false;

        if (is_null($nivelAcesso)) {
            $falha = true;
        } else {
            switch ($nivelAcesso) {
                case 0:
                    if ($pastaAtual == "tutor") {
                        $falha = true;
                    }
                    break;
                case 1:
                    if ($pastaAtual != "tutor")
                        $falha = true;
                    break;
                default:
                    break;
            }
        }

        if ($falha)
            header("Location: {$redirectFile}");
    }
}
