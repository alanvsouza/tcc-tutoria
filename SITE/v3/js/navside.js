var iconMenu = document.querySelector('span.icon-menu');
var aberto = false;
var navside = document.querySelector('div#navside');

var windowHeight = altura();
var windowWidth = largura();

window.addEventListener('resize', function () {
    windowWidth = largura(); 
    windowHeight = altura();
});

function largura(){
windowWidth = window.innerWidth;

if(windowWidth >= 991 && aberto == true){
navside.style.transitionDuration = '0s';
closeNavside();
navside.style.transitionDuration = '.15s';}

return windowWidth}

function altura(){
    windowHeight = window.innerHeight;
    return windowHeight}

iconMenu.addEventListener('click', function navside(){

if(aberto == false)
openNavside();
else
closeNavside();
});


function openNavside(){
aberto = true;
navside.style.transform = 'translateX(0px)'}

function closeNavside(){
aberto = false;
navside.style.transform = 'translateX(-250px)'}
