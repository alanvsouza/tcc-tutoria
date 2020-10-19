<?php
namespace App\Model;

class Session
{       
    public static function startSession()
    {
        if(session_status() == PHP_SESSION_NONE)
            session_start();
    }
}
