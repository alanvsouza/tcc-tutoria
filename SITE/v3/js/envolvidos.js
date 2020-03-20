// Items que eu quero animar.

//Debounce do Lodash
debounce = function(func, wait, immediate){
    var timeout;
    return function(){
        var context = this, args = arguments;
        var later =  function(){
            timeout = null;
            if(!immediate) func.apply(context,args);
        };
        var callNow = immediate  && !timeout;
        clearTimeout(timeout);
        timeout = setTimeout(later,wait);
        if(callNow) func.apply(context,args)
    };
};

(function (){
var $target = $('.card-prof'),
    aniamtionClass = 'card-prof-start';

 offset = $(window).height() *   4/5; //Pra nunca deixar mais de 3/4 da minha janela em branco (sem os cards);

function animeScroll(){
    var documentTop = $(document).scrollTop();

    $target.each(function(){
        var itemTop = $(this).offset().top; //Pegar a distância entre o topo e o elemento. O this faz referência ao target(a um dos elementos com a class card-prof) atual.
        if(documentTop > itemTop - offset){
        $(this).addClass(aniamtionClass);
        }
        else
        $(this).removeClass(aniamtionClass);
    })
} 
animeScroll();
$(document).resize(debounce(function(){
    animeScroll();
},100));

$(document).scroll(debounce(function(){
    animeScroll();
},100));

}());//Encapsulando e invocando a função automaticamente para evitar futuros conflitos com outras funções