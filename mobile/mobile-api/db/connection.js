const mysql = require('mysql');
const credentials = require('./credentials')

const con = mysql.createConnection(credentials);

module.exports = con;
