<?php
    session_start(); 

    function conec(){
        require_once("db/credentials.php");
        require_once("db/db_connect.php");
        
        $data = null;
        $dias = array(
        "Mon"=>"segunda",
        "Tur"=>"terca", 
        "Wed"=>"quarta", 
        "Thu"=> "quinta",
        "Fri"=>"sexta",
        "Sat"=>"sabado",
        "Sun"=>"domingo");

        $data = explode("/",$_GET["data"]);

        $data = implode('-', $data);        
        
        $id = $_GET["id"];

        $unix_time_stamp = strtotime($data);
        $dia = $dias[date("D", $unix_time_stamp)];        

        $row = null;

        try {
            $conn = connect($connection_info);
            $sql = "SELECT `horarios` FROM `tbhorarios` WHERE `idprofessor` = $id AND `diaSemana` = '$dia'";            

            $result = $conn->query($sql);            
            
            $row = $result->fetch();  

            $conn = null;
        } catch(PDOException $e) {
            echo "Connection failed";
        }                

        $horarios = explode(";", $row["horarios"]);        
        $values = array();

        foreach($horarios as $horario){            
            array_push($values, explode('-', $horario));
        }                

        return $values;
    }

    function criarTabelaHorarios(){
        $horarios = conec();                
        
        echo 
        '<div class="col-lg-7 col-md-7 col-sm-11 col-11 column" id="horarios-professor">
            <table id="table-professor">
                <tr><th >Inicio</><th>Término</th><th>Local</th></tr>';
                foreach($horarios as $horario) {           
                    echo "<tr class='ativado'><td>$horario[0]</td><td>$horario[1]</td><td></td></tr>";
                }         
        echo '</table>';
    }
?>