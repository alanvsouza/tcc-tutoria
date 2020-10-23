var modal = document.querySelector("div#modal-galeria-eventos");
var visibildiade = document.querySelector("div.visibilidade-modal");

var btnOpenGaleria = document.querySelector("button#btn-ver-galeriaEventos");
btnOpenGaleria.addEventListener('click', function (){
    visibildiade.style.visibility = 'visible';
    visibildiade.style.opacity = '1';
    modal.style.top = '0';
});

var btnCloseGaleria = document.querySelector("i#close-galeria");
btnCloseGaleria.addEventListener('click', function(){
    visibildiade.style.visibility = 'hidden';
    visibildiade.style.opacity = '0';
    modal.style.top = '100vh';
});

var galeriaFotos = document.querySelectorAll('.card-novo__card-detalhes a');
for (var i = 0; i < galeriaFotos.length; i++) {
    galeriaFotos[i].addEventListener("click", function (e) {
    	e.preventDefault();
        var galeria = document.getElementById("modal-fotos__" + this.id);
        galeria.style.visibility = "visible";
        galeria.style.opacity = "1";
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




