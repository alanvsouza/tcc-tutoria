var iconMenu = document.querySelector('span.icon-menu');
var aberto = false;
var navbar = document.querySelector('div#navbar');

var windowHeight = altura();
var windowWidth = largura();

window.addEventListener('resize', function () {
    windowWidth = largura(); 
    windowHeight = altura();
});

function largura(){
windowWidth = window.innerWidth;

if(windowWidth >= 991 && aberto == true){
navbar.style.transitionDuration = '0s';
closeNavbar();
navbar.style.transitionDuration = '.15s';}

return windowWidth}

function altura(){
    windowHeight = window.innerHeight;
    return windowHeight}

iconMenu.addEventListener('click', function navbar(){

if(aberto == false)
openNavbar();
else
closeNavbar();
});


function openNavbar(){
aberto = true;
navbar.style.transform = 'translateX(0px)'}

function closeNavbar(){
aberto = false;
navbar.style.transform = 'translateX(-250px)'}
