const express = require('express');
const app = express();
const port = 7777;

const eventos = require('./app/routes/eventos');

app.use('/eventos', eventos);

app.listen(port);
