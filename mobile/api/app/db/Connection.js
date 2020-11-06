const mysql = require('mysql');
const credentials = require('./credentials');

class Connection {
    static connect() {
        if (!this.conn)
            this.conn = mysql.createConnection(credentials);

        this.conn.connect((error) => {
            if (error) return null;
        })

        return this.conn;
    }
}

module.exports = Connection;
