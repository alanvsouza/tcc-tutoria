<?php
namespace App\Model;

class Professor {
    private
        $idTutor,
        $nomeTutor,
        $login,
        $senha,
        $email,
        $telefone,
        $rg,
        $foto,
        $descricao,
        $facebook,
        $twitter,
        $linkedin;
        
    /**
     * @return mixed
     */
    public function getIdTutor()
    {
        return $this->idTutor;
    }

    /**
     * @return mixed
     */
    public function getNomeTutor()
    {
        return $this->nomeTutor;
    }

    /**
     * @return mixed
     */
    public function getLogin()
    {
        return $this->login;
    }

    /**
     * @return mixed
     */
    public function getSenha()
    {
        return $this->senha;
    }

    /**
     * @return mixed
     */
    public function getEmail()
    {
        return $this->email;
    }

    /**
     * @return mixed
     */
    public function getTelefone()
    {
        return $this->telefone;
    }

    /**
     * @return mixed
     */
    public function getRg()
    {
        return $this->rg;
    }

    /**
     * @return mixed
     */
    public function getFoto()
    {
        return $this->foto;
    }

    /**
     * @return mixed
     */
    public function getDescricao()
    {
        return $this->descricao;
    }

    /**
     * @return mixed
     */
    public function getFacebook()
    {
        return $this->facebook;
    }

    /**
     * @return mixed
     */
    public function getTwitter()
    {
        return $this->twitter;
    }

    /**
     * @return mixed
     */
    public function getLinkedin()
    {
        return $this->linkedin;
    }

    /**
     * @param mixed $idTutor
     */
    public function setIdTutor($idTutor)
    {
        $this->idTutor = $idTutor;
    }

    /**
     * @param mixed $nomeTutor
     */
    public function setNomeTutor($nomeTutor)
    {
        $this->nomeTutor = $nomeTutor;
    }

    /**
     * @param mixed $login
     */
    public function setLogin($login)
    {
        $this->login = $login;
    }

    /**
     * @param mixed $senha
     */
    public function setSenha($senha)
    {
        $this->senha = $senha;
    }

    /**
     * @param mixed $email
     */
    public function setEmail($email)
    {
        $this->email = $email;
    }

    /**
     * @param mixed $telefone
     */
    public function setTelefone($telefone)
    {
        $this->telefone = $telefone;
    }

    /**
     * @param mixed $rg
     */
    public function setRg($rg)
    {
        $this->rg = $rg;
    }

    /**
     * @param mixed $foto
     */
    public function setFoto($foto)
    {
        $this->foto = $foto;
    }

    /**
     * @param mixed $descricao
     */
    public function setDescricao($descricao)
    {
        $this->descricao = $descricao;
    }

    /**
     * @param mixed $facebook
     */
    public function setFacebook($facebook)
    {
        $this->facebook = $facebook;
    }

    /**
     * @param mixed $twitter
     */
    public function setTwitter($twitter)
    {
        $this->twitter = $twitter;
    }

    /**
     * @param mixed $linkedin
     */
    public function setLinkedin($linkedin)
    {
        $this->linkedin = $linkedin;
    }

    
    
}

