<?php

    namespace App\Model;

    class Conexao {
        private static $instance;

        public static function getConn() {

            if(!isset(self::$instance)):
                self::$instance = new \PDO('mysql:host=143.106.241.3;dbname=cl18152;charset=utf8', 'cl18152', 'cl*07062002');                            
            endif;

            return self::$instance;
        }
    }