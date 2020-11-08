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

    static readById(id) {
        return new Promise((resolve, reject) => {
            const sql = 'SELECT * FROM tbeventos WHERE idevento = ?'
            conn.query(sql, [id], (error, result) => {
                if (result === undefined)
                    reject(new Error('Result is undefined!'));
                else
                    resolve(result[0]);
            });
        })
    }

    static readGaleriaById(id) {
        return new Promise((resolve, reject) => {
            const sql = 'SELECT imagem FROM tbimgeventos WHERE idevento = ?';
            conn.query(sql, [id], (error, result) => {
                if (result === undefined)
                    reject(new Error('Result is undefined!'));
                else
                    resolve(result);
            })
        })
    }
}

module.exports = EventoDAO;
