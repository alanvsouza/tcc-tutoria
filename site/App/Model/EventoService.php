<?php

namespace App\Model;

class EventoService
{
    public static function renderizarEventos()
    {
        $meses = array(
            "01" => "JAN",
            "02" => "FEV",
            "03" => "MAR",
            "04" => "ABR",
            "05" => "MAI",
            "06" => "JUN",
            "07" => "JUL",
            "08" => "AGO",
            "09" => "SET",
            "10" => "OUT",
            "11" => "NOV",
            "12" => "DEZ",
        );

        $eventoDao = new EventoDAO();

        $eventos = $eventoDao->readAllFromToday();

        if ($eventos != null) :
            for ($i = 0; $i < sizeof($eventos); $i++) :
                $row = $eventos[$i];

                $data = (str_replace('-', '/', $row['dataevento']));
                $dataSplit = explode("/", $data);

                echo '
                <div class="post">
                <div class="header_post">
                    <img src="img-eventos/' . $row['image'] . '" alt="img-card">
                    <div id="data">' . $dataSplit[2] . '<br> ' . $meses[$dataSplit[1]] . '</div>
                    <div id="triangulo-para-baixo"></div>
                </div>

                <div class="body_post">
                    <div class="post_content">

                        <h1>' . $row['nome'] . '</h1>
                        <p>' . $row['descricao'] . '</p>

                        <div class="container_infos">
                            <div class="postedBy">
                                <span>Local</span>
                                ' . $row['localevento'] . '
                            </div>

                            <div class="container_horarios">
                                <span>Horário</span>
                                <div class="horarios">
                                    <ul>
                                        <li class="efeito">'  . substr($row['inicio'], 0, 5) . ' </li>
                                        <li> &nbsp-&nbsp </li>
                                        <li class="efeito">' . substr($row['termino'], 0, 5) . '</li>
                                    </ul>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>';
            endfor;
        else :
            echo "<span class='no-events'>Nenhum evento agendado!</span>";
        endif;
    }

    public static function renderizarGaleriaEventos()
    {
        $eventoDao = new EventoDAO();
        $eventos = $eventoDao->readAllBeforeToday();

        foreach ($eventos as $evento) :
            $nomeEvento = $evento['nome'];
            $idEvento = $evento['idevento'];
            $dataEvento = date_format(date_create($evento['dataevento']), 'd/m/Y');
            $thumbEvento = $evento['image'];
            $pastaEvento = $evento['pastaGaleria'];
            $galeriaEvento = $eventoDao->readImagensEventoById($idEvento);
            $totalImagens = sizeof($galeriaEvento);

            echo "<div class='card-novo'>
                <img src='img-eventos/{$thumbEvento}' class='card-novo__image'>
                <div class='card-novo__card-text'>
                    <h2 class='card-novo__title'>{$nomeEvento}</h2>
                    <div class='card-novo__card-date'>{$dataEvento}</div>
                    <div class='card-novo__card-detalhes'>
                        <p class='card-novo__card-descricao'>Lorem ipsum dolor sit amet consectetur adipisicing elit. Est pariatur nemo tempore repellat? Ullam sed officia iure architecto deserunt distinctio, pariatur&hellip;</p>
                        <a href='#' id='galeria-fotos-{$idEvento}' class='card-novo__ver-fotos'>Ver Fotos <i class='fas fa-long-arrow-alt-right'></i></a>
                    </div>
                </div>
            </div>

            <div class='container-fotos' id='modal-fotos__galeria-fotos-{$idEvento}'>
                <div class='modal-fotos'>
                    <i id='close__galeria-fotos-{$idEvento}' class='fas fa-times close'></i>
                    <div class='arrows'>
                        <a href='#' class='btn prev' id='galeria-fotos-{$idEvento}__prev'>&#171;</a>
                        <a href='#' class='btn next' id='galeria-fotos-{$idEvento}__next'>&#187;</a>
                        <span id='galeria-fotos-{$idEvento}__index'>1 / {$totalImagens}</span>
                    </div>
                    <div id='galeria-fotos-{$idEvento}__galeria'>";
            foreach ($galeriaEvento as $index => $imagem) :
                $index++;
                $class = $index == 1 ? 'foto-visivel' : 'foto-invisivel';

                echo "<img src='img-galeria-eventos/{$pastaEvento}/{$imagem['imagem']}' class='fotos {$class}' id='galeria-fotos-{$idEvento}__foto-{$index}' alt=''>";
            endforeach;
            echo "</div>
                </div>
            </div>";

        endforeach;
    }
}
