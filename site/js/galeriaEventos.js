var galeriaFotos = document.querySelectorAll('.card-novo__card-detalhes a');
var prevFoto = null;
var nextFoto = null;
var fotos = null;
var index = null;
var count = 0;

for (count = 0; count < galeriaFotos.length; count++) {
    galeriaFotos[count].addEventListener("click", function (e) {
        e.preventDefault();
        var galeria = document.getElementById("modal-fotos__" + this.id);
        galeria.style.visibility = "visible";
        galeria.style.opacity = "1";
        galeria.style.display = "flex";
        galeria.style.alignItemns = "center";
        galeria.style.justifyContent = "center";
        infoGaleria(this.id);
    });
}

var closeModal = document.querySelectorAll('.modal-fotos i');
for(count = 0; count < closeModal.length; count++){
	closeModal[count].addEventListener('click', function(e){
        e.preventDefault();
		vetor = this.id.split("__");
		var modal = document.getElementById("modal-fotos__" + vetor[1]);
		modal.style.visibility = "hidden";
        modal.style.opacity = "0";
        modal.style.display = "none";
	});
}

function infoGaleria(id){
    prevFoto = document.getElementById(id+'__prev');
    nextFoto = document.getElementById(id+'__next');
    fotos = document.querySelectorAll('#'+id+'__galeria img');
    index = document.getElementById(id+'__index');
    console.log(fotos);
    exibirFoto();
}

function exibirFoto(){
    length = Number(fotos.length);
    if(nextFoto != null){
        nextFoto.addEventListener("click", function (e) {
            e.preventDefault();
            count = 0;
            while(count < length){
                let elemento = document.getElementById(fotos[count].id);
                let classes = elemento.className.split(' ');
                let getIndex = classes.indexOf("foto-visivel");

                if(getIndex !== -1){
                    var proxImg = null;

                    if(count + 1 > length) {
                        count = 0;
                        index.innerHTML = count + 1 + " / " + length;
                        proxImg = document.getElementById(fotos[0].id);
                    } else{
                        count += 1; 
                        index.innerHTML =  count + " / " + length;
                        proxImg = document.getElementById(fotos[count].id);
                    } 
                    

                    // classEdit.js
                    addClass(elemento.id, "foto-invisivel")
                    delClass(elemento.id, "foto-visivel");;
                    addClass(proxImg.id, "foto-visivel");
                    delClass(proxImg.id, "foto-invisivel");
                    break;
                }
                count++;
            }
        });
    }
    if(prevFoto != null){
        prevFoto.addEventListener("click", function (e) {
            e.preventDefault();
            count = 0;
            while(count < length){
                let elemento = document.getElementById(fotos[count].id);
                let classes = elemento.className.split(' ');
                let getIndex = classes.indexOf("foto-visivel");

                if(getIndex !== -1){
                    var proxImg = null;
                    if(count - 1 < 0) { 
                        count = length;
                        index.innerHTML  = length + " / " + length;
                        proxImg = document.getElementById(fotos[count].id);
                    }
                    else {
                        count -= 1; 
                        index.innerHTML = count - 1 + " / " + length;
                        proxImg = document.getElementById(fotos[count].id);
                    }

                    // classEdit.js
                    addClass(elemento.id, "foto-invisivel")
                    delClass(elemento.id, "foto-visivel");;
                    addClass(proxImg.id, "foto-visivel");
                    delClass(proxImg.id, "foto-invisivel");
                    break;
                }
                count++;
            }
        });
    }
}


