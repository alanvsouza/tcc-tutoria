var sidebar = document.querySelector("div#sidebar");
windowWidth = largura();

window.addEventListener('resize', function (){
    windowWidth = largura(); 
});
    
function largura(){
    windowWidth = window.innerWidth;

    if (windowWidth <= 767) {
        sidebar.style.transform = 'translateX(-300px)';
    }else{
        sidebar.style.transform = 'translateX(0px)';
    }
}






