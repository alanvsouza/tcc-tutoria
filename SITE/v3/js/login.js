var login = document.querySelector('div#loginModal');
var heightAtual;

function openLogin(){
    loginModal.style.visibility = 'visible';
    loginModal.style.top = '0';
    loginModal.style.transitionDuration = '.6s';
    loginModal.style.opacity = '1';
    loginModal.style.position = 'fixed';
}

function closeLogin(){
    loginModal.style.transitionDuration = '1s';
    loginModal.style.top = '-300%' ;
    loginModal.style.opacity = '0';
    loginModal.style.visibility = 'hidden';
}
