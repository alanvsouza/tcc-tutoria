var galeriaFotos = document.querySelectorAll('.card-novo__card-detalhes a');
var prevFoto = null;
var nextFoto = null;
var geleriaFoto = null;

for (var i = 0; i < galeriaFotos.length; i++) {
    galeriaFotos[i].addEventListener("click", function (e) {
        e.preventDefault();
        var galeria = document.getElementById("modal-fotos__" + this.id);
        galeria.style.visibility = "visible";
        galeria.style.opacity = "1";
        infoGaleria(this.id);
    });
}

var closeModal = document.querySelectorAll('.modal-fotos i');
for(var i = 0; i < closeModal.length; i++){
	closeModal[i].addEventListener('click', function(e){
		vetor = this.id.split("__");
		var modal = document.getElementById("modal-fotos__" + vetor[1]);
		modal.style.visibility = "hidden";
		modal.style.opacity = "0";
	});
}

function infoGaleria(id){
    prevFoto = document.getElementById(id+'__prev');
    nextFoto = document.getElementById(id+'__next');
    geleriaFoto = document.getElementById(id+'__galeria');
    exibirFoto();
}

function exibirFoto(){
    prevFoto.addEventListener("click", function (e) {
        e.preventDefault();
        console.log(galeriaFoto);
        var fotos = document.querySelectorAll('#' + geleriaFoto + 'img');
        console.log(fotos);
    });
}



