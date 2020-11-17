var navside = document.querySelector('div#navside');
var iconMenu = document.querySelector('span.icon-menu');
var aberto = false;

var windowWidth = largura();

window.addEventListener('resize', function () {
    windowWidth = largura();
});

function largura() {
    windowWidth = window.innerWidth;

    if (windowWidth >= 991 && aberto == true) {
        closeNavside();
    }

    return windowWidth;
}

iconMenu.addEventListener('click', function navside() {
    if (aberto == false) openNavside();
    else closeNavside();
});

function openNavside() {
    aberto = true;
    navside.style.transform = 'translateX(0px)';
}

function closeNavside() {
    aberto = false;
    navside.style.transform = 'translateX(-200px)';
}

//Animação do botão 'Ver Eventos'
function rolarPara(elemento) {
    $('html, body').animate({
        scrollTop: ($(elemento).offset().top - 100)
    }, 600);
}