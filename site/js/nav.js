var windowHeight = altura();
var windowWidth = largura();

var sobreClick = false;
var sobreMouse = false;

var sobre = document.querySelector('li#sobre');
var sobreList = document.querySelector('ul#items-sobre');

window.addEventListener('resize', function () {
    windowWidth = largura(); 
    windowHeight = altura();
});

function largura() {
    windowWidth = window.innerWidth;
    return windowWidth
}

function altura() {
    windowHeight = window.innerHeight;
    return windowHeight
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

        sobreList.style.visibility = 'hidden';
        sobreList.style.opacity = '0'; 
    }
    
        else{
        sobreList.style.visibility = 'visible';
        sobreList.style.opacity = '1';}
    
        if(sobreClick == true){
            sobreClick = false;}
        else
            sobreClick = true;
    }
    
});

