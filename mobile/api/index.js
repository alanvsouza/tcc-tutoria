const express = require('express');
const app = express();
const port = 7777;

const eventos = require('./app/routes/eventos');
const alunos = require('./app/routes/alunos')

app.use('/eventos', eventos);
app.use('/alunos', alunos);

app.listen(port);
