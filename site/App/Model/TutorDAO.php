<?php

namespace App\Model;

class TutorDAO
{
    public function readAll()
    {
        $query = "SELECT * FROM tbtutor ORDER BY nometutor";
        $stmt = Connection::getConn()->prepare($query);
        
        $stmt->execute();
        
        if($stmt->rowCount() > 0):
            $result = $stmt->fetchAll();
            return $result;
        endif;
        
        return null;
    }
}

