const conn = require('../db/Connection').connect();

class EventoDAO {
    static readAll() {
        return new Promise((resolve, reject) => {
            conn.query('SELECT * FROM tbeventos', (error, result) => {
                if (result === undefined)
                    reject(new Error('Result is undefined!'));
                else
                    resolve(result);
            });
        })
    }
}

module.exports = EventoDAO;
