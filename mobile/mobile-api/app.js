const express = require('express');
const app = express();

const mysql_con = require('./db/connection');

const bodyparser = require('body-parser');
const path = require('path');

//Body parser
app.use(bodyparser.json());
app.use(bodyparser.urlencoded({ extended: false }));

//Router
const alunos = require('./routes/alunos/index');
app.use("/alunos", alunos);

app.listen(3030, () => {
    console.log('Servidor rodando');
});

module.exports = mysql_con;
