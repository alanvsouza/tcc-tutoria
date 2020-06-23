<?php

    namespace App\Model;

    class Usuario {
        private $id, $nome, $email, $curso, $ano, $login, $senha;
        
        //setters
        public function setId($id) {
            $this->id = $id;
        }

        public function setNome($nome) {
            $this->nome = $nome;
        }

        public function setEmail($email) {
            $this->email = $email;
        }

        public function setCurso($curso) {
            $this->curso = $curso;
        }

        public function setAno($ano) {
            $this->ano = $ano;
        }

        public function setLogin($login) {
            $this->login = $login;
        }

        public function setSenha($senha) {
            $this->senha = $senha;
        }

        //getters
        public function getId() {
            return $this->id;
        }

        public function getNome() {
            return $this->nome;
        }

        public function getEmail() {
            return $this->email;
        }

        public function getCurso() {
            return $this->curso;
        }

        public function getAno() {
            return $this->ano;
        }

        public function getLogin() {
            return $this->login;
        }

        public function getSenha() {
            return $this->senha;
        }
    }