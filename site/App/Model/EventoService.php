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
                <div class="post">
                <div class="header_post">
                    <img src="img/'.$row['image'].'" alt="img-card">
                </div>

                <div class="body_post">
                    <div class="post_content">

                        <h1>'.$row['nome'].'</h1>
                        <p>'.$row['descricao'].'</p>

                        <div class="container_infos">
                            <div class="postedBy">
                                <span>Local</span>
                                ' .$row['localevento'].'
                            </div>

                            <div class="container_horarios">
                                <span>Horário</span>
                                <div class="horarios">
                                    <ul>
                                        <li>'  . substr($row['inicio'],0,5). ' </li>
                                        <li> &nbsp- ' . substr($row['termino'],0,5) .'</li>
                                    </ul>
                                </div>

                            </div>
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
