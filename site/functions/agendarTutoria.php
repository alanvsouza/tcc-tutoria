<?php

require("db/db_connect.php");
error_reporting(0);

function renderizarProfessores($connection_info) {
    try {
        $conn = connect($connection_info);

        $sql = "SELECT idtutor, nometutor, foto FROM tbtutor ORDER BY nometutor";
        
        $result = $conn->query($sql);
        
        $conn = null;
    } catch(PDOException $e) {
        echo "Connection failed";
    }

    while($row = $result->fetch()) {
        $nome = $row["nometutor"];
        $foto_banco = $row["foto"];
        $id = $row["idtutor"];

        $foto = null;
        if($foto_banco == null)
            $foto = "img/defaultPicture.png";
        else
            $foto = "img-professores/" . $foto_banco;

        echo 
        '<li> 
            <div class="col-lg-12 p-0 m-0 row"  id="info-prof">
            <img src="' . $foto . '" class="image col-lg-5" alt="">
                <div id="dados" class="col-lg-7" >
                    <div id="nome-professor">' . $nome . '</div>
                    <form method="GET" action="' . htmlspecialchars($_SERVER["PHP_SELF"]) . '">
                        <input type="hidden" name="id" value="' . $id . '">
                        <button id="btn-colsultar-horarios"> Consultar horários </button>
                    </form>
                </div>
            </div>
        </li>';
    }
}

function renderizarInformacoes($id, $connection_info) {
    try {
        $conn = connect($connection_info);

        $sql = "SELECT nometutor, descricao FROM tbtutor WHERE idtutor = $id";
        
        $result = $conn->query($sql);
        
        $conn = null;
    } catch(PDOException $e) {
        echo "Connection failed";
        }

    
    $row = $result->fetch();
    $nome = $row["nometutor"];

    echo 
    "<div id='professor' class='m-0 p-0'>
        <div class='col-lg-12 m-0' id='description'>
            <div id='header-description'>
                <h3>" . $nome . "</h3>
                <a id='ver-perfil' href='#'>Ver Perfil</a>
            </div>
            <div id='body-description'> 
                <span>".$row['descricao']."</span>
            </div>
        </div>
    </div>

    <div class='col-lg-12'>
        <div id='agendar-tutoria'  class='col-lg-12' >
            <div class='calendario' class='col-lg-4 col-md-4 col-sm-7 col-11'>
                "; montaCalendario(); echo "
            </div>

            <div class='col-lg-7 col-md-7 col-sm-11 col-11 column' id='horarios-professor'>
                <table id='table-professor'>
                    <tr bgcolor='#f2f2f2'><th >Inicio</><th>Término</th><th>Local</th></tr>
                    <tr class='ativado'><td >12:00</td><td>12:45</td><td>Sala 10</td></tr>
                    <tr class='ativado'><td>13:00</td><td>13:40</td><td>Sala 11</td></tr>
                    <tr class='desativado'><td>15:30</td><td>15:45</td><td>Sala 4</td></tr>
                    <tr class='ativado'><td >14:00</td><td>15:00</td><td>Sala 7</td></tr>
                </table>

                <form method='POST' action='agendamentoTutoria.php'>
                    <span style='font-size:17px;font-weight: 600;'>Data selecionada:</span> 
                    <input type='text' name='data' disabled placeholder='20/09/2020' id='data-selecionada'>
                    <input type='hidden' name='professor' value='" . $id . "'>
                    <div class='col-lg-12 column'><input type='button' id='btn-agendar' value='Agendar Tutoria'></div>
                </form>

                <form method='GET' action='" . $_SERVER['PHP_SELF'] . "'>
                    <input id='fakeData' type='hidden' name='data'>
                    <input id='idProf' type='hidden' name='id'>
                    <input id='submitFakeData' type='submit' hidden>
                </form>
            </div>
        </div>
        
    </div>";
}
