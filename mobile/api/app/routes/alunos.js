const express = require('express');
const router = express.Router();

const AlunoDAO = require('../models/AlunoDAO');

router.get('/:login', (req, res) => {
    AlunoDAO.readByLogin(req.params.login)
        .then(result => res.send(result))
        .catch(error => res.send(error));
});

module.exports = router;
