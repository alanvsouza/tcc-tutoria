function addClass(id, classe) {
    let elemento = document.getElementById(id);
    let classes = elemento.className.split(' ');//Divide um array de strings de acordo com um separador
    let getIndex = classes.indexOf(classe);//O método indexOf() retorna o primeiro índice em que o elemento pode ser encontrado no array, retorna -1 caso o mesmo não esteja presente.

    if (getIndex === -1) {
    classes.push(classe);//Coloca mo array de classes do meu elemento a classe informada
    elemento.className = classes.join(' ');// junta todos os elementos de um array em uma string de acordo com um separador e a retorna.
    //O className retorna e define a classe do elemento.
    }
}

function delClass(id, classe) {
    var elemento = document.getElementById(id);
    var classes = elemento.className.split(' ');
    var getIndex = classes.indexOf(classe);

    if (getIndex > -1)
    classes.splice(getIndex, 1); //O método splice() altera o conteúdo de uma lista, adicionando novos elementos enquanto remove elementos antigos. Nesse caso estou removendo 1 elemento que está na posição 'getIndex' e não estou adicionando nada no lugar.
    
    elemento.className = classes.join(' ');
}

