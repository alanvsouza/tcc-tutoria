const conn = require('../db/Connection').connect();

class AlunoDAO {
    static readByLogin(login) {
        return new Promise((resolve, reject) => {
            const sql = 'SELECT * FROM tbaluno WHERE login = ?';

            conn.query(sql, [login], (error, result) => {
                if (result === undefined)
                    reject(new Error('Result is undefined!'));
                else
                    resolve(result[0]);
            })
        })
    }
}

module.exports = AlunoDAO;
