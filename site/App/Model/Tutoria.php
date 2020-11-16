<?php

namespace App\Model;

class Tutoria
{
    private
        $idTutoria,
        $idTutor,
        $idAluno,
        $dataTutoria,
        $idHorario,
        $localTutoria,
        $tipoHorario;

    public function __construct(
        $idAluno,
        $idTutor,
        $dataTutoria,
        $idHorario,
        $tipoHorario
    ) {
        $this->idAluno = $idAluno;
        $this->idTutor = $idTutor;
        $this->dataTutoria = $dataTutoria;
        $this->idHorario = $idHorario;
        $this->tipoHorario = $tipoHorario;
    }

    /**
     * Get the value of idTutoria
     */
    public function getIdTutoria()
    {
        return $this->idTutoria;
    }

    /**
     * Set the value of idTutoria
     *
     * @return  self
     */
    public function setIdTutoria($idTutoria)
    {
        $this->idTutoria = $idTutoria;

        return $this;
    }

    /**
     * Get the value of idAluno
     */
    public function getIdAluno()
    {
        return $this->idAluno;
    }

    /**
     * Set the value of idAluno
     *
     * @return  self
     */
    public function setIdAluno($idAluno)
    {
        $this->idAluno = $idAluno;

        return $this;
    }

    /**
     * Get the value of dataTutoria
     */
    public function getDataTutoria()
    {
        return $this->dataTutoria;
    }

    /**
     * Set the value of dataTutoria
     *
     * @return  self
     */
    public function setDataTutoria($dataTutoria)
    {
        $this->dataTutoria = $dataTutoria;

        return $this;
    }

    /**
     * Get the value of idHorario
     */
    public function getIdHorario()
    {
        return $this->idHorario;
    }

    /**
     * Set the value of idHorario
     *
     * @return  self
     */
    public function setIdHorario($idHorario)
    {
        $this->idHorario = $idHorario;

        return $this;
    }

    /**
     * Get the value of localTutoria
     */
    public function getLocalTutoria()
    {
        return $this->localTutoria;
    }

    /**
     * Set the value of localTutoria
     *
     * @return  self
     */
    public function setLocalTutoria($localTutoria)
    {
        $this->localTutoria = $localTutoria;

        return $this;
    }

    /**
     * Get the value of idTutor
     */
    public function getIdTutor()
    {
        return $this->idTutor;
    }

    /**
     * Set the value of idTutor
     *
     * @return  self
     */
    public function setIdTutor($idTutor)
    {
        $this->idTutor = $idTutor;

        return $this;
    }

    /**
     * Get the value of tipoHorario
     */
    public function getTipoHorario()
    {
        return $this->tipoHorario;
    }

    /**
     * Set the value of tipoHorario
     *
     * @return  self
     */
    public function setTipoHorario($tipoHorario)
    {
        $this->tipoHorario = $tipoHorario;

        return $this;
    }
}
