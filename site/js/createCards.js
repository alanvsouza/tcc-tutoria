
/*<?php
$sql = "SELECT * FROM tbeventos";
$resultado = mysqli_query($conexao,$sql) or die ("Consulta falhou");
?>*/

var classesDiv1 = new Array("col-xl-4","col-lg-4","col-md-6", "col-sm-8", "col-11");
var classesDiv2 = new Array("card","mb-3");

function addCard(){

    var elemento_pai = document.querySelector("article#cards-events");
    var antecessor = document.querySelector("div#card1");

    var div1 = document.createElement("div");
    div1.id = "card0";

    for (let index = 0; index < classesDiv1.length; index++) {
        div1.classList.add(classesDiv1[index])
    }

    var div2 = document.createElement("div");
    div2.id = "div2";

    for (let index = 0; index < classesDiv2.length; index++) {
        div2.classList.add(classesDiv2[index])
    }

    var img = document.createElement("img");
    img.id="img1";
    img.src="img/fundoLaranja.jpg";
    img.classList.add("card-img-top");

    var div3 = document.createElement("div");
    div3.id = "div3";
    div3.classList.add("card-body");

    var titulo = document.createElement("h5");
    tituloid = "titulo";
    titulo.classList.add("carte-title");
    titulo.textContent = $nome;

    var linha = document.createElement("hr");
    linha.id = "linha";

    var paragrafo = document.createElement("p");
    paragrafo.classList.add("card-content");
    paragrafo.textContent = "This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.";

    var div4 = document.createElement("div");
    div4.id = "div4"
    div4.classList.add("row");

    var span1 = document.createElement("span");
    var small1 = document.createElement("small");
    span1.id = "sp1";
    small1.id = "sm1";
    small1.classList.add("details");
    small1.textContent = "Data: 03/05/2020";

    var span2 = document.createElement("span");
    var small2 = document.createElement("small");
    span2.id = "sp2";
    small2.id = "sm2";
    small2.classList.add("details");
    small2.textContent = "Horário: 12:05 - 12:50";

    var span3 = document.createElement("span");
    var small3 = document.createElement("small");
    span3.id = "sp3";
    small3.id = "sm3";
    small3.classList.add("details");
    small3.textContent = "Local: Sala 14";

    elemento_pai.insertBefore(div1,antecessor);

    elemento_pai = document.querySelector("div#card0");
    elemento_pai.appendChild(div2);

    elemento_pai = document.querySelector("div#div2");
    elemento_pai.appendChild(img);
    elemento_pai.appendChild(div3);

    elemento_pai = document.querySelector("div#div3");
    elemento_pai.appendChild(titulo);
    elemento_pai.appendChild(linha);
    elemento_pai.appendChild(paragrafo);
    elemento_pai.appendChild(div4);

    elemento_pai = document.querySelector("div#div4");
    elemento_pai.appendChild(span1);
    elemento_pai = document.querySelector("span#sp1");
    elemento_pai.appendChild(small1);

    elemento_pai = document.querySelector("div#div4");
    elemento_pai.appendChild(span2);
    elemento_pai = document.querySelector("span#sp2");
    elemento_pai.appendChild(small2);

    elemento_pai = document.querySelector("div#div4");
    elemento_pai.appendChild(span3);
    elemento_pai = document.querySelector("span#sp3");
    elemento_pai.appendChild(small3);

}

addCard();

/*while($registro = mysql_fetch_array($resultado)){
    addCard($registro['nome']);
}*/


/*
<div class="col-xl-4 col-lg-4 col-md-6 col-sm-8 col-11" id="card2">
        <div class="card mb-3">
            <img src="img/fundoPreto.png" class="card-img-top" alt="...">
                <div class="card-body">
                    <h5 class="card-title">Sessão Cinema</h5>
                    <hr>
                    <p class="card-content">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                    <div class="row">
                        <span><small class="details">Data: 03/05/2020 </small></span>
                        <span><small class="details">Horário: 12:05 - 12:50 </small></span>
                        <span><small class="details">Local: Sala 14</small></span>
                    </div>
                </div>
        </div>
    </div>
*/
