const methods = {
    findAll: async (con, table, response) => {
        let query = await con.query(`SELECT * FROM ${table}`, (error, result) => {
            return response.json(result);
        });
    },
    findById: async (con, table, response, id) => {        
        const sql = `SELECT * FROM ${table} WHERE idaluno = ?`;
        let query = await con.query(sql, [id], (error, result) => {
            return response.json(result);
        })
    },
    findByLogin: async (con, table, response, login) => {
        const sql = `SELECT senha FROM ${table} WHERE login = ?`;

        let query = await con.query(sql, [login], (error, result) => {
            return response.json(result);
        });
    }    
};

module.exports = methods;
