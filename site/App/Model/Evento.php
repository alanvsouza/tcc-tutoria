<?php

class Evento
{
    private
        $idEvento,
        $nome,
        $dataEvento,
        $inicio,
        $termino,
        $descricao,
        $localEvento,
        $arquivo,
        $image;
    
    public function __construct()
    {
        $arguments = func_get_args();
        $numberOfArguments = func_num_args();

        if (method_exists($this, $function = '__construct' . $numberOfArguments))
            call_user_func_array(array($this, $function), $arguments);
    }       
    
    public function __construct9($idEvento, $nome, $dataEvento, $inicio, $termino, $descricao, $localEvento, $arquivo, $image) 
    {
        $this->idEvento = $idEvento;
        $this->nome = $nome;
        $this->dataEvento = $dataEvento;
        $this->inicio = $inicio;
        $this->termino = $termino;
        $this->descricao = $descricao;
        $this->localEvento = $localEvento;
        $this->arquivo = $arquivo;
        $this->image = $image;
    }

    public function getIdEvento()
    {
        return $this->idEvento;
    }

    public function setIdEvento($idEvento)
    {
        $this->ideEvento = $idEvento;
    }

    public function getNome()
    {
        return $this->nome;
    }

    public function setNome($nome)
    {
        $this->nome = $nome;
    }

    public function getDataEvento()
    {
        return $this->dataEvento;
    }

    public function setDataEvento($dataEvento)
    {
        $this->dataEvento = $dataEvento;
    }

    public function getInicio()
    {
        return $this->inicio;
    }

    public function setInicio($inicio)
    {
        $this->inicio = $inicio;
    }

    public function getTermino()
    {
        return $this->termino;
    }

    public function setTermino($termino)
    {
        $this->termino = $termino;
    }
    public function getDescricao()
    {
        return $this->descricao;
    }

    public function setDescricao($descricao)
    {
        $this->descricao = $descricao;
    }

    public function getLocalEvento()
    {
        return $this->localEvento;
    }

    public function setLocalEvento($localEvento)
    {
        $this->localEvento = $localEvento;
    }
    
    public function getArquivo() 
    {
        return $this->arquivo;
    }

    public function getImage() 
    {
        return $this->image;
    }

    public function setArquivo($arquivo) 
    {
        $this->arquivo = $arquivo;
    }

    public function setImage($image) 
    {
        $this->image = $image;
    }

    
}
