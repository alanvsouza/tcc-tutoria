<?php
namespace App\Model;

class TutorService
{
    public static function renderizarProfessoresAgendamentoTutoria()
    {
        $tutorDao = new TutorDAO();
        $tutores = $tutorDao->readAll();

        for($i = 0; $i < sizeof($tutores); $i++):
            $row = $tutores[$i];
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
        endfor;
    }

    public static function renderizarProfessoresProjetoTutoria()
    {
        $tutorDao = new TutorDAO();
        $tutores = $tutorDao->readAll();
        
        for($i = 0; $i < sizeof($tutores); $i++):
            $row = $tutores[$i];
            $nome = $row["nometutor"];
            $foto = $row["foto"];
            $redesSociais =
                array(
                    "facebook" => $row["facebook"] == null ? "\"" . "#" . "\"" : "\"" . $row["facebook"] . "\"" .
                        " target='_blank'",
                    "twitter" => $row["twitter"] == null ? "\"" . "#" . "\"" : "\"" . $row["twitter"] . "\"" .
                        " target='_blank'",
                    "linkedin" => $row["linkedin"] == null ? "\"" . "#" . "\"" : "\"" . $row["linkedin"] . "\"" .
                        " target='_blank'");
            $descricao = $row["descricao"];

            if($foto == null)
                $foto = "defaultPicture.png";

            echo '
            <article class="card-prof col-xl-5 col-lg-7 col-md-9 col-sm-11 col-10" 
                id="'.str_replace(" ","-",(trim($nome))).'" name="' .
                str_replace("é","e",str_replace(" ","-",(trim($nome)))).'"
                >
                <div class="imagem col-lg-5 col-md-5 col-sm-5 col-12">
                    <img src="img-professores/'.$foto.'" alt="">
                </div>
    
                <div class="col-lg-7 col-md-7 col-sm-7 col-12 information-main">
                    <header>
                        <h1>'.$nome.'</h1>
                    </header>
                    <span>
                        '.$descricao.'
                    </span>
                    <ul class="rede-social">
                        <li><a href=' . $redesSociais["facebook"] . '"><i class="fab fa-facebook-square"></i></a></li>
                        <li><a href=' . $redesSociais["twitter"] . '"><i class="fab fa-twitter-square"></i></a></li>
                        <li><a href=' . $redesSociais["linkedin"] . '"><i class="fab fa-linkedin"></i></a></li>
                    </ul>
                </div>
            </article>';
        endfor;
    }
    
    public static function renderizarLinksProfessores()
    {
        $tutorDao = new TutorDAO();
        $tutores = $tutorDao->readAll();
               
        for($i = 0; $i < sizeof($tutores); $i++):
            $row = $tutores[$i];
        
            $nome = trim($row["nometutor"]);
            $href = str_replace(" ","-",$nome);
            echo '<li class="col-xl-3 col-lg-3 col-md-3 col-sm-4 col-6"><a class="ancora" href="#'.$href.'">'.$nome.'</a></li>';
        
        endfor;
    }

    public static function renderizarInformacoes($id)
    {
//        require_once("functions/tabelaHorarios.php");

        $query = "SELECT `nometutor`, `descricao` FROM `tbtutor` WHERE `idtutor` = ?";
        $stmt = Connection::getConn()->prepare($query);

        $stmt->bindParam(1, $id);

        $stmt->execute();

        if($stmt->rowCount() > 0)
            $row = $stmt->fetch();
        else
            return;

        $nome = $row["nometutor"];
        $data = "";

        if(isset($_GET["data"])) {
            $data = $_GET["data"];
        }

        echo "
        <div id='professor' class='m-0 p-0'>
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

    public static function renderizarTabelaHorarios($id)
    {
        $tutorDao = new TutorDAO();
        $horarios = $tutorDao->readHorariosDiaById($id);

        var_dump($horarios);
    }
}

