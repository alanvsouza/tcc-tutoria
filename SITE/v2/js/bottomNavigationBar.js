var windowHeight = altura();
var windowWidth = largura();
sizes();

window.addEventListener('resize', function () {
    windowWidth = largura(); 
    windowHeight = altura();
    sizes();
});

function largura(){
    windowWidth = window.innerWidth;
    return windowWidth}

function altura(){
    windowHeight = window.innerHeight;
    return windowHeight}

// Altura do menu que fica embaixo.
function sizes(){

    var bottomNavigationBar = document.querySelector('div.bottomNavigationBar');
    var tamanhoJanela = windowWidth + 'x' + windowHeight;

    console.log('Tamanho da janela: ' + tamanhoJanela);

    if (windowWidth < 301){
        bottomNavigationBar.style.top = (windowHeight - 25) + 'px';
    }

    else if (windowWidth >= 301 && windowWidth < 402){
        bottomNavigationBar.style.top = (windowHeight - 35) + 'px';
    }

    else if (windowWidth >= 402 && windowWidth < 760){
        bottomNavigationBar.style.top = (windowHeight - 40) + 'px';
    }

    else if (windowWidth >= 760 && windowWidth < 891){
        bottomNavigationBar.style.top = (windowHeight - 40) + 'px';
    }
    else {
        bottomNavigationBar.style.top = (windowHeight - 40) + 'px';
    }
};
