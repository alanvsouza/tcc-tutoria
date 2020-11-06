class Evento {
    constructor(nome, data, inicio, termino, descricao, local, imagem) {
        this.nome = nome;
        this.data = data;
        this.inicio = inicio;
        this.termino = termino;
        this.descricao = descricao;
        this.local = local;
        this.imagem = imagem;
    }
}

module.exports = Evento;
