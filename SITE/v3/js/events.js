function verificar(c1,c2){

    var cont1 = document.querySelector("div#"+c1);
    var cont2 = document.querySelector("div#"+c2);

            if($("#"+ c1).hasClass("visivel")){
                cont1.classList.remove("visivel");
                cont1.classList.add("invisivel");
                cont2.classList.add("visivel");
                cont2.classList.remove("invisivel");
            }

            else{
                cont2.classList.remove("visivel")
                cont2.classList.add("invisivel");
                cont1.classList.add("visivel");
                cont1.classList.remove("invisivel")}
}
