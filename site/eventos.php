<?php

$query = "SELECT * FROM tbeventos";
$result = mysqli_query($conexao, $query);
$i = 0;
while($reg = mysqli_fetch_array($result)){
    $data = date('d/m/Y',strtotime(str_replace('-','/', $reg['dataevento'])));
    echo '<div class="col-xl-4 col-lg-4 col-md-6 col-sm-8 col-11" id="'."card".$i .'">
    <div class="card mb-3">
        <img src="img/'.$reg['image'].'" class="card-img-top" alt="...">
            <div class="card-body">
                    <h5 class="card-title">'.$reg['nome'].'</h5> 
                <hr>
                <p class="card-content">'. $reg['descricao'].'</p>
                <div class="row">
                    <span><small class="details">Data: ' .$data.' </small></span>
                    <span><small class="details">Horário: ' . substr($reg['inicio'],0,5).' - '. substr($reg['termino'],0,5) . '</small></span>
                    <span><small class="details">Local: ' .$reg['localevento'].'</small></span>
                </div>
            </div>
    </div>
    </div>';
    $i++;
}
?>