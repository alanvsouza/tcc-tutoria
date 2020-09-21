var ativado = true;
document.querySelector("i#olho").addEventListener("click",function(){

    if (ativado){
        document.getElementById("senha"). type = 'text';
        ativado = false;
    } else{
        document.getElementById("senha"). type = 'password';
        ativado = true;
    }
});
