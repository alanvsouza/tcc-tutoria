<?php
require("db/db_connect.php");

$hostname = $connection_info["hostname"];
$dbname = $connection_info["dbname"];
$username = $connection_info["username"];
$password = $connection_info["password"];

    try {
        $conn = connect($hostname, $dbname, $username, $password);
        $sql = " SELECT * FROM tbeventos;";
        $result = $conn->query($sql);
        $conn = null;
    } catch(PDOException $e) {
        echo "Connection failed";
    }

$i = 0;

while($row = $result->fetch()){
    $data = date('d/m/Y',strtotime(str_replace('-','/', $row['dataevento'])));
    echo '<div class="col-xl-4 col-lg-4 col-md-6 col-sm-8 col-11 cardTranslateY cardWidth" id="'."card".$i.'">
    <div class="card mb-3">
        <img src="img/'.$row['image'].'" class="card-img-top" alt="...">
            <div class="card-body">
                    <h5 class="card-title">'.$row['nome'].'</h5> 
                <hr>
                <p class="card-content">'. $row['descricao'].'</p>
                <div class="row">
                    <span><small class="details">Data: ' .$data.' </small></span>
                    <span><small class="details">Horário: ' . substr($row['inicio'],0,5).' - '. substr($row['termino'],0,5) . '</small></span>
                    <span><small class="details">Local: ' .$row['localevento'].'</small></span>
                </div>
            </div>
    </div>
    </div>';
    $i++;
}
?>