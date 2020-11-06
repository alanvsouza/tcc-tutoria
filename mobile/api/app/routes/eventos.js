const express = require('express');
const router = express.Router();

const Evento = require('../models/Evento');
const EventoController = require('../models/EventoDAO');

router.get('/', (req, res) => {
    EventoController.readAll()
        .then((result) => {
            return res.send(result);
        })
        .catch((error) => {
            return res.send(error);
        })
});

module.exports = router;
