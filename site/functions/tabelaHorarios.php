<?php
session_start(); 

    $data = null;
    $value = null;

    $dias = array(
    "Mon"=>"segunda",
    "Tur"=>"terca", 
    "Wed"=>"quarta", 
    "Thu"=> "quinta",
    "Fri"=>"sexta",
    "Sat"=>"sabado",
    "Sun"=>"domingo");

    echo '<script type="text/javascript">
        function dataTutoria(data){
            let dataFormatada = data.split("/");
            data = dataFormatada[1] + "/" + dataFormatada[0]  + "/" + dataFormatada[2];
            
            let inputData = document.querySelector("input#data-selecionada");
            let fakeData = document.querySelector("#fakeData");
            let submitFakeData = document.querySelector("#submitFakeData");
            let idProf = document.querySelector("form input#idProf");

            inputData.value = data;
            fakeData.value = data;
            idProf.value = ' . $_GET["id"] . '
            submitFakeData.click();}
    </script>';

    $data = explode("/",$_GET["data"]);
    $dayMonth = gregoriantojd($data[0],$data[1],$data[2]);
    $weekMonth = substr(jddayofweek($dayMonth, 1),0,3);
    $dia = $dias[$weekMonth]; 
    $id = isset($_GET["id"]);

    try {
        $conn = connect($connection_info);
        $sql = "SELECT $dia FROM tbhorarios WHERE idtutor = $id ";
        $result = $conn->query($sql);
        $conn = null;
    }catch(PDOException $e) {
        echo "Connection failed";
    }

    $horarios = explode("-",$result[$dia]);
    // print_r(" " . $horarios . "<br>" . $dia);

        function criarTabelaHorarios(){
        echo '<div class="col-lg-7 col-md-7 col-sm-11 col-11 column" id="horarios-professor">
                <table id="table-professor">
                <tr><th >Inicio</><th>Término</th><th>Local</th></tr>
                <tr class="ativado"><td>12:00</td><td>12:45</td><td>Sala 10</td></tr>
                <tr class="ativado"><td>13:00</td><td>13:40</td><td>Sala 11</td></tr>
                <tr class="desativado"><td>15:30</td><td>15:45</td><td>Sala 4</td></tr>
                <tr class="ativado"><td >14:00</td><td>15:00</td><td>Sala 7</td></tr>
            </table>';
        
    }
?>