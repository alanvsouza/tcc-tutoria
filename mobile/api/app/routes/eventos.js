const express = require('express');
const router = express.Router();

const Evento = require('../models/Evento');
const EventoDAO = require('../models/EventoDAO');

router.get('/', (req, res) => {
    EventoDAO.readAll()
        .then((result) => {
            return res.send(result);
        })
        .catch((error) => {
            return res.send(error);
        });
});

router.get('/:id', (req, res) => {
    EventoDAO.readById(req.params.id)
        .then((result) => {
            return res.send(result);
        })
        .catch((error) => {
            return res.send(error);
        });
});

router.get('/:id/imagens', (req, res) => {
    EventoDAO.readGaleriaById(req.params.id)
        .then((result) => {
            return res.send(result);
        })
        .catch((error) => {
            return res.send(error);
        })
})

module.exports = router;
