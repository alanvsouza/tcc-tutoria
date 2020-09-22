const router = require("../routes");
const mysql_con = require('../../db/connection');
const mysql_methods = require('./db_methods');

mysql_con.connect((error) => {
    if(error) throw error;

    console.log('Successful connection!');
});

router.get('/', (req, res) => {
    mysql_methods.findAll(mysql_con, "tbaluno", res);
});

router.get('/:login', (req, res) => {
    mysql_methods.findByLogin(mysql_con, "tbaluno", res, req.params.login);
});

router.post('/', (req, res) => {        
    let body = req.body;

    let inserted = false;
    
    const nome = body.nomealuno;
    const login = body.login;
    const senha = body.senha;
    const email = body.email;
    const curso = body.curso;
    const anoIngresso = body.anoingresso;
    
    const info = [ nome, login, senha, email, curso, anoIngresso ];

    console.log(info);

    let query = mysql_con.query('INSERT INTO tbaluno (nomealuno, login, senha, email, curso, anoingresso) VALUES (?)', [info], (error, result) => {
        if(error) throw error;
        
        return res.json(result.affectedRows);
    });

    return;
});

module.exports = router;