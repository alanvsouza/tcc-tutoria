<?php

namespace App\Model;

class EventoService {
    public static function renderizarEventos() {
        $meses = array(
            "01"=>"JAN",
            "02"=>"FEV",
            "03"=>"MAR",
            "04"=>"ABR",
            "05"=>"MAI",
            "06"=>"JUN",
            "07"=>"JUL",
            "08"=>"AGO",
            "09"=>"SET",
            "10"=>"OUT",
            "11"=>"NOV",
            "12"=>"DEZ",);

        $eventoDao = new EventoDAO();
        
        $eventos = $eventoDao->readAllFromToday();
        
        if($eventos != null):        
            for($i = 0; $i < sizeof($eventos); $i++):
                $row = $eventos[$i];

                $data = (str_replace('-','/', $row['dataevento']));
                $dataSplit = explode("/",$data);

                echo'
                <div class="post">
                <div class="header_post">
                    <img src="img/'.$row['image'].'" alt="img-card">
                    <div id="data">'.$dataSplit[2].'<br> '.$meses[$dataSplit[1]].'</div>
                    <div id="triangulo-para-baixo"></div>
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
                                        <li class="efeito">'  . substr($row['inicio'],0,5). ' </li>
                                        <li> &nbsp-&nbsp </li>
                                        <li class="efeito">' . substr($row['termino'],0,5) .'</li>
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
