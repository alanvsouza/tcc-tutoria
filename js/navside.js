var navside = document.querySelector('div#navside');
var iconMenu = document.querySelector('span.icon-menu');
var aberto = false;

var windowHeight = altura();
var windowWidth = largura();

window.addEventListener('resize', function () {
windowWidth = largura(); 
windowHeight = altura();
});

function largura(){
windowWidth = window.innerWidth;

    if(windowWidth >= 991 && aberto == true){
        closeNavside();
    }

return windowWidth;}

function altura(){
    windowHeight = window.innerHeight;
    return windowHeight;
}

iconMenu.addEventListener('click', function navside(){
    if(aberto == false) openNavside();
    else closeNavside();
});

function openNavside(){
aberto = true;
navside.style.transform = 'translateX(0px)';}

function closeNavside(){
aberto = false;
if (windowWidth <= 767)  navside.style.transform = 'translateX(-200px)';
else navside.style.transform = 'translateX(-250px)';}

