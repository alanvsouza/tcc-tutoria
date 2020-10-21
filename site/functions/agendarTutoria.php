<?php


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

public static function renderizarInformacoes($id) {
    require_once("functions/tabelaHorarios.php");

    $query = "SELECT `nometutor`, `descricao` FROM tbtutor WHERE idtutor = ?";
    $stmt = Connection::getConn()->prepare($query);

    $stmt->bindParam(1, $id);

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
    $data = "";

    if(isset($_GET["data"])) {
        $data = $_GET["data"];
    }

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
                "; criarTabelaHorarios(); echo "
                <form method='POST' action='agendamentoTutoria.php'>
                    <div class='linha'>
                        <span  class='col-lg-4 id='span-data'>Data selecionada:</span> 
                        <input class='col-lg-4' type='text' name='data' disabled id='data-selecionada' value='" . $_GET["data"] . "'>
                        <input class='col-lg-0' type='hidden' name='professor' value='" . $id . "'>
                        <div class='col-lg-4'><input type='button' id='btn-agendar' value='Agendar Tutoria'></div>
                    </div>
                </form>
                <form method='GET' action='" . $_SERVER['PHP_SELF'] . "'>
                    <input id='fakeData' type='hidden' name='data'>
                    <input id='idProf' type='hidden' name='id'>
                    <input id='submitFakeData' type='submit' hidden>
                </form>
            </div>
        </div>        
    </div>";

    unset($_GET["data"]);
}
