<?php
namespace App\Model;

class TutorService
{
    public static function renderizarProfessores()
    {
        $tutorDao = new TutorDAO();       
        $tutores = $tutorDao->readAll();
        
        for($i = 0; $i < sizeof($tutores); $i++):
            $row = $tutores[$i];
            $nome = $row["nometutor"];
            $foto = $row["foto"];
            $descricao = $row["descricao"];
            
            if($foto == null)
                $foto = "defaultPicture.png";
                
                echo '
                <article class="card-prof col-xl-5 col-lg-7 col-md-9 col-sm-11 col-10" id="'.str_replace(" ","-",(trim($nome))).'" name="'.str_replace("é","e",str_replace(" ","-",(trim($nome)))).'">
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
                            <li><a href="#"><i class="fab fa-facebook-square"></i></a></li>
                            <li><a href="#"><i class="fab fa-twitter-square"></i></a></li>
                            <li><a href="#"><i class="fab fa-google-plus-square"></i></a></li>
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
}

