<?php

namespace App\Model;

class EventoDAO {
    public function readAllFromToday()
    {
        $query = "SELECT * from tbeventos WHERE dataevento >= DATE_FORMAT(CURDATE(), '%d-%c-%Y');";
        $stmt = Connection::getConn()->prepare($query);

        $stmt->execute();

        if ($stmt->rowCount() > 0) :
            $result = $stmt->fetchAll(\PDO::FETCH_ASSOC);
            return $result;
        endif;

        return null;
    }
    
    public function readImagensEventoById($id) 
    {
        $query = "SELECT * FROM tbimgeventos WHERE idevento = ?";
        $stmt = Connection::getConn()->prepare($query);
        
        $stmt->bindValue(1, $id);
        
        $stmt->execute();
        
        if($stmt->rowCount() > 0):
            $result = $stmt->fetchAll(\PDO::FETCH_ASSOC);            
            
            return $result;
        endif;
        
        return null;
    }
}