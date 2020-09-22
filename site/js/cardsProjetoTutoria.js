//Debounce para diminuir o número de eventos disparados e melhor o desempenho do site.
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
var $target = ($('.card-prof')),

offset = $(window).height() *   9/10; //Pra nunca deixar mais de 9/10 da minha janela em branco (sem os cards);

function animeScroll(){
    var documentTop = $(document).scrollTop();

    $target.each(function(){
        var itemTop = $(this).offset().top; //Pegar a distância entre o topo e o elemento. O this faz referência ao target(a um dos elementos com a class card-prof) atual.
        if(documentTop > itemTop - offset)
        $(this).addClass('card-prof-start');
        /*else
        $(this).removeClass('card-prof-start');*/
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

//animando a ancora
$('a[href^="#"].ancora').on('click', function(e){
    e.preventDefault(); //para o link não funcionar de forma padrão - pular instantaneamente para o eleme.
    $('html, body').animate({scrollTop:$(this.hash).offset().top - 100}, 700);
})

