var windowHeight = altura();
var windowWidth = largura();

var lastScrollTop = 0;
var topo = 0; 
var lastWidth = 0;

var verif = false;
var controleMenu = true;
var upActive = false;
var downActive = false;
var sobreClick = false;
var sobreMouse = false;
var cor = false;

var sobreIcon =  document.querySelector('i#icon-sobre');
var sobre = document.querySelector('li#sobre');
var sobreList = document.querySelector('ul#items-sobre');
var nav = document.querySelector('nav#menu');

menu();

window.addEventListener('resize', function () {
    windowWidth = largura(); 
    windowHeight = altura();
    menu();
});

function largura(){
    windowWidth = window.innerWidth;
    return windowWidth}

function altura(){
    windowHeight = window.innerHeight;
    return windowHeight}

function verificarCor(){
    if(topo == 0 && sobreClick == true)
    $('nav').addClass('cor');
    else
    $('nav').remove('cor');
}


nav.addEventListener('mouseover', background);
nav.addEventListener('mouseout', backgroundRetirar);

function menu(){

    if(windowWidth > lastWidth && windowWidth > 991 && topo > 1){
    $('nav').addClass('cor');}

    //Se eu estiver em uma tela grande e o menu estiver oculto pela função up() e eu for para uma tela abaixo de 991px (onde eu retiro a classe .rolamento, que é a responsável por fazer o menu aparecer e desaparecer) o menu não aparece mais na tela. O problema foi resolvido com o JS abaixo:
    if(controleMenu === false){
    $('nav').addClass('rolamento');
    down();
    $('nav').removeClass('rolamento');}

    lastWidth = windowWidth;
};

/* Adicinando background ao menu quando o mouse está sobre o menu ou retirando quando o mouse está fora dele*/
function background(){

    if ($(window).scrollTop() < 1 ){
    $('nav').addClass('cor');}
    verif = true;}

function backgroundRetirar() {
    if ($(window).scrollTop() < 1){
    $('nav').removeClass('cor');}
    verif = false;}


/* Acrescentando background conforme o rolamento*/
$(window).on('scroll', function () {

    if(windowWidth >  991){

        $('nav').addClass('rolamento');
        $('nav').addClass('cor');
        if ($(window).scrollTop() >= 1 && windowWidth >= 450) {
            topo = nav.scrollTop;
                $('nav').addClass('cor');
        }

        else {
            if (verif != true)
                $('nav').removeClass('cor');
        }
    }

    else{ 
        $('nav').removeClass('rolamento');
        $('nav').removeClass('cor');
    }

verificarCor();
});  

/* Efeito slide com menu conforme o scrollTop */
window.addEventListener('scroll', menuSlide)

function menuSlide(){
            var scrollTop = window.pageYOffset;
                    if (scrollTop != lastScrollTop){

                            if (scrollTop > lastScrollTop || scrollTop === 0){
                            controleMenu = true;
                            down();
                        }

                            else{
                            controleMenu = false;
                            up();
                        }
                    }
        lastScrollTop = scrollTop;
}

function down() {
    $('.rolamento').slideDown(400);
    $('nav').removeClass('rolamento');
}
    
function up() {
    $('.rolamento').slideUp(400);
    $('nav').removeClass('rolamento');
}


sobre.addEventListener('mouseover', function (){
    sobreMouse = true;
    sobreList.style.visibility = 'visible';
    sobreList.style.opacity = '1';
});

sobre.addEventListener('mouseout', function (){
    sobreMouse = false;
    sobreList.style.visibility = 'hidden';
    sobreList.style.opacity = '0';
});

sobreList.addEventListener('mouseover', function (){
    sobreMouse = true;
    sobreList.style.visibility = 'visible';
    sobreList.style.opacity = '1';
});

sobreList.addEventListener('mouseout', function (){
    sobreMouse = false;
    sobreList.style.visibility = 'hidden';
    sobreList.style.opacity = '0';
});

sobre.addEventListener('click', function (){

    if(sobreMouse == false){

        if(sobreClick == true){

            if(nav.classList.contains("cor")){
            cor = true;}

            if(topo == 0 )
            $('nav').remove('cor');
    
        sobreList.style.visibility = 'hidden';
        sobreList.style.opacity = '0'; 
    }
    
        else{
        
        if(topo == 0 )
        $('nav').addClass('cor');

        sobreList.style.visibility = 'visible';
        sobreList.style.opacity = '1';}
    
        if(sobreClick == true){
            sobreClick = false;
            verificarCor();}
        else
            sobreClick = true;
    }
    
});

