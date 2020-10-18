<?php

namespace App\Model;

class EventoService {
    public static function renderizarEventos() {
        $eventoDao = new EventoDAO();
        
        $eventos = $eventoDao->readAllFromToday();
        
        if($eventos != null):        
            for($i = 0; $i < sizeof($eventos); $i++):
                $row = $eventos[$i];

                $data = (str_replace('-','/', $row['dataevento']));

                echo '
                <div class="col-xl-4 col-lg-4 col-md-6 col-sm-8 col-12 cardTranslateY cardWidth" id="'."card".$i.'">
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
            endfor;
        else:
            echo "<span class='no-events'>Nenhum evento agendado!</span>";
        endif;        
    }
}
