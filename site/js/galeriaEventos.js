var btnOpenGaleria = document.querySelector("button#btn-ver-galeriaEventos");
var btnCloseGaleria = document.querySelector("i#close-galeria");
var modal = document.querySelector("div#modal-galeria-eventos");
var visibildiade = document.querySelector("div.visibilidade-modal");

btnOpenGaleria.addEventListener('click', function (){
    visibildiade.style.visibility = 'visible';
    visibildiade.style.opacity = '1';
    modal.style.top = '0';
});

btnCloseGaleria.addEventListener('click', function(){
    visibildiade.style.visibility = 'hidden';
    visibildiade.style.opacity = '0';
    modal.style.top = '100vh';
});