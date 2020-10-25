var galeriaFotos = document.querySelectorAll('.card-novo__card-detalhes a');

let prevFoto = null;
let nextFoto = null;
let fotos = null;
let index = null;
let indexFoto = 1;
let tamanho = 0;
let count = 0;
const delay = 500;

//event listeners
const prevFotoListener = function (e) {
    e.preventDefault();
    count = 0;
    while (count < tamanho) {
        let elemento = document.getElementById(fotos[count].id);
        let classes = elemento.className.split(' ');
        let getIndex = classes.indexOf("foto-visivel");

        if (getIndex !== -1) {
            var antImg = null;

            if (count - 1 < 0) {
                indexFoto = tamanho;
                count = tamanho - 1;
                index.innerHTML = indexFoto + " / " + indexFoto;
                antImg = document.getElementById(fotos[count].id);
            }
            else {
                count--;
                indexFoto--;
                index.innerHTML = indexFoto + " / " + tamanho;
                antImg = document.getElementById(fotos[count].id);
            }

            // classEdit.js
            addClass(elemento.id, "foto-invisivel")
            delClass(elemento.id, "foto-visivel");;
            addClass(antImg.id, "foto-visivel");
            delClass(antImg.id, "foto-invisivel");
            break;
        }
        count++;
    }
};

const nextFotoListener = function (e) {
    e.preventDefault();
    count = 0;

    while (count < tamanho) {
        let elemento = document.getElementById(fotos[count].id);
        let classes = elemento.className.split(' ');
        let getIndex = classes.indexOf("foto-visivel");

        if (getIndex !== -1) {
            var proxImg = null;

            if (count + 1 > tamanho - 1) {
                indexFoto = 1;
                index.innerHTML = indexFoto + " / " + tamanho;
                proxImg = document.getElementById(fotos[0].id);
            } else {
                indexFoto++;
                count++;
                index.innerHTML = indexFoto + " / " + tamanho;
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
};

//abre o modal
for (var i = 0; i < galeriaFotos.length; i++) {
    galeriaFotos[i].addEventListener("click", function (e) {
        e.preventDefault();

        indexFoto = 1;
        count = 0;
        var galeria = document.getElementById("modal-fotos__" + this.id);

        galeria.style.visibility = "visible";
        galeria.style.opacity = "1";
        galeria.style.display = "flex";
        galeria.style.alignItemns = "center";
        galeria.style.justifyContent = "center";

        if (nextFoto != null)
            nextFoto.removeEventListener("click", nextFotoListener);
        if (prevFoto != null)
            prevFoto.removeEventListener("click", prevFotoListener);

        infoGaleria(this.id);
    });
}

//fecha o modal
var closeModal = document.querySelectorAll('.modal-fotos i');
for (var i = 0; i < closeModal.length; i++) {
    closeModal[i].addEventListener('click', function (e) {
        e.preventDefault();

        indexFoto = 1;
        count = 0;
        var vetor = this.id.split("__");
        var modal = document.getElementById("modal-fotos__" + vetor[1]);

        modal.style.visibility = "hidden";
        modal.style.opacity = "0";

        setTimeout(function () {
            modal.style.display = "none";
            setInicio();
        }, delay)

    });
}

function infoGaleria(id) {
    prevFoto = document.getElementById(id + '__prev');
    nextFoto = document.getElementById(id + '__next');
    fotos = document.querySelectorAll('#' + id + '__galeria img');
    index = document.getElementById(id + '__index');
    tamanho = fotos.length;
    exibirFoto();
}

function exibirFoto() {

    if (nextFoto != null) {
        nextFoto.addEventListener("click", nextFotoListener);
    }
    if (prevFoto != null) {
        prevFoto.addEventListener("click", prevFotoListener);
    }
}

function setInicio() {
    count = 0;
    indexFoto = 1;
    index.innerHTML = `1 / ${tamanho}`;

    for (let foto of fotos) {
        if (count == 0) {
            foto.classList.remove('foto-invisivel');
            foto.classList.remove('foto-visivel');
            foto.classList.add('foto-visivel');

            count++;

            continue;
        }

        foto.classList.remove('foto-invisivel');
        foto.classList.remove('foto-visivel');
        foto.classList.add('foto-invisivel');

        count++;
    }
}
