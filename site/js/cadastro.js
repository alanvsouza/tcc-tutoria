function more(){
    var atual = document.getElementById("ano").value;
    if(atual < 2020){ 
    var novoAno = atual - (-1); //Evitando Concatenacoes
    document.getElementById("ano").value = novoAno;
    }

}
function less(){
    var atual = document.getElementById("ano").value;
    if(atual > 2018) { 
    var novoAno = atual - 1;
    document.getElementById("ano").value = novoAno;
    }
}