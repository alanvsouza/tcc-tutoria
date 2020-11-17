const modalFotoPerfil = document.querySelector('#modal-selecionar-foto');
const fotoPerfil = document.querySelector('#icon-perfil');

var aberto = false;

fotoPerfil.addEventListener('click', function(e){
	e.preventDefault();

	aberto = true;
    modalFotoPerfil.style.display = "block";
    setTimeout(function () {
         	modalFotoPerfil.style.opacity = "1";
	modalFotoPerfil.style.visibility = "visible";
    }, 100);
});


modalFotoPerfil.addEventListener('click', function(e){
	e.preventDefault();

	aberto = true;
	modalFotoPerfil.style.opacity = "0";
	modalFotoPerfil.style.visibility = "hidden";
    setTimeout(function () {
         modalFotoPerfil.style.display = "none";
    }, 300);
});