<?php

namespace App\Model;

class Aluno {
    private 
        $idAluno,
        $nomeAluno,
        $login,
        $senha,
        $email,
        $curso,
        $anoIngresso,
        $foto;           

        public function __construct() {
            $arguments = func_get_args();
            $numberOfArguments = func_num_args();

            if(method_exists($this, $function = '__construct' . $numberOfArguments))
                call_user_func_array(array($this, $function), $arguments);
        }

        public function __construct2($login, $senha) {
            $this->login = $login;
            $this->senha = $senha;
        }

        public function __construct6($nomeAluno, $login, $senha, $email, $curso, $anoIngresso) {
            $this->nomeAluno = $nomeAluno;
            $this->login = $login;
            $this->senha = $senha;
            $this->email = $email;
            $this->curso = $curso;
            $this->anoIngresso = $anoIngresso;
        }

        /**
         * Getters and Setters
         **/ 

        public function getIdAluno() {
            return $this->idAluno;
        }
        
        public function setIdAluno($idAluno) {
            $this->idAluno = $idAluno;

            return $this;
        }

        public function getNomeAluno() {
            return $this->nomeAluno;
        }
        
        public function setNomeAluno($nomeAluno) {
            $this->nomeAluno = $nomeAluno;

            return $this;
        }

        public function getLogin() {
            return $this->login;
        }
        
        public function setLogin($login) {
            $this->login = $login;

            return $this;
        }

        public function getSenha() {
            return $this->senha;
        }
        
        public function setSenha($senha) {
            $this->senha = $senha;

            return $this;
        }
        
        public function getEmail() {
            return $this->email;
        }

        public function setEmail($email) {
            $this->email = $email;

            return $this;
        }

        public function getCurso() {
            return $this->curso;
        }

        public function setCurso($curso) {
            $this->curso = $curso;

            return $this;
        }

        public function getAnoIngresso() {
            return $this->anoIngresso;
        }

        public function setAnoIngresso($anoIngresso) {
            $this->anoIngresso = $anoIngresso;

            return $this;
        }

        public function getFoto() {
            return $this->foto;
        }

        public function setFoto($foto) {
            $this->foto = $foto;

            return $this;
        }
}        