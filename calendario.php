<?php

    function diasMeses(){
        $retorno = array();
            for($i = 1; $i <= 12; $i++){
                $retorno[$i] = cal_days_in_month(CAL_GREGORIAN,$i, date('Y'));//Retorna quantos dias tem cada mês nesse ano - cal_days_in_month(formato,mes,ano)
            }
        return $retorno;
    }

    function montaCalendario(){
        $daysWeek = array(
            'Sun',
            'Mon',
            'Tue',
            'Wed',
            'Thu',
            'Fri',
            'Sat'
        );

        $diasSemana = array(
            'Dom',
            'Seg',
            'Ter',
            'Qua',
            'Qui',
            'Sex',
            'Sab'
        );

        $arrayMes = array(
            1 => 'Janeiro',
            2 => 'Fevereiro',
            3 => 'Abril',
            4 => 'Março',
            5 => 'Maio',
            6 => 'Junho',
            7 => 'Julho',
            8 => 'Agosto',
            9 => 'Setembro',
            10 => 'Outubro',
            11 => 'Novembro',
            12 => 'Dezembro'
        );

        $diasMeses = diasMeses();
        
        $arrayRetorno = array();

        for($i = 1; $i <= 12; $i++){
            $arrayRetorno[$i] = array();
            for($n = 1; $n <= $diasMeses[$i]; $n++){
                $dayMonth = gregoriantojd($i,$n,date('Y'));//Retorna um número que vai indicar qual o dia da semana
                $weekMonth = substr(jddayofweek($dayMonth, 1),0,3);//Vai me retornar o dia da semana em inglês e abreviado
                if($weekMonth == 'Mun') $weekMonth = 'Mon';
                $arrayRetorno[$i][$n] = $weekMonth;//Mês com cada dia abreviado em inglês
            } 
        }
        echo '<a href="#" id="volta">&laquo;</a><a href="#" id="vai">&raquo;</a>';
        echo '<table>';

        foreach($arrayMes as $num => $mes){
            echo '<tbody id="mes_'. $num .'" class="mes">';
            echo '<tr class="mes_title"><td colspan="7">'. $mes .'</td></tr>';
            echo '<tr class="dias_title">';
            foreach($diasSemana as $i => $day){
                echo '<td>'. $day .'</td>';
            }
            echo '</tr><tr>';
            $y = 0;
            foreach($arrayRetorno[$num] as $numero => $dia){
                $y++;
                if($numero == 1){
                    $qtd = array_search($dia,$daysWeek);
                    for($i = 1; $i <= $qtd; $i++){
                        echo '<td></td>';
                        $y+=1;
                    }
                }
                (string)$data = $num."/".$numero."/".date('Y');
                echo '<td onclick="dataTutoria(\''.$data.' \')" id="'.$num."/".$numero."/".date('Y').'">' .$numero. '</td>';
                if($y == 7){ 
                    $y=0;
                    echo '<tr></tr>';
                }
            }
            echo '</tr>';

            echo '</tbody>';
        }

        echo '</table>';

        // print_r($arrayRetorno);
    }
?>
