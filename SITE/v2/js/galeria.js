//imgMenu.getBoundingClientRect().height 
//nav.style.transform = "translate(0,-100)";
//window.pageYOffset

var textImg = document.querySelector('span#text-img');
var exibirImg = document.querySelector('img#imagem-exibir');
var imgSelected = document.querySelectorAll('.img-view');
var galeria = document.querySelector('div#galeryImages');


function removeBorder(){
    for(var a = 0; a < imgSelected.length; a++ ){
        if(imgSelected[a].classList.contains("img-Selecionada")){
        imgSelected[a].classList.remove("img-Selecionada");}
    }
}

function openModal(){
    removeBorder();
    imgSelected[0].classList.add("img-Selecionada");
    exibirImg.src = imgSelected[0].src;
    textImg.innerHTML =  imgSelected[0].alt;
    galeria.style.opacity = '1';
    galeria.style.visibility = 'visible';
}

function closeModal(){
    galeria.style.opacity = '0';
    galeria.style.visibility = 'hidden';
}

for(var img of imgSelected){
    img.addEventListener('click', function(){
    removeBorder();

    this.classList.add("img-Selecionada");

    exibirImg.src = this.src;
    textImg.innerHTML = this.alt;
    });
}


