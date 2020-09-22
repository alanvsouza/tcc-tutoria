const methods = {
    findAll: async (con, table, response) => {
        let query = await con.query(`SELECT * FROM ${table}`, (error, result) => {
            console.log(result);
            
            return response.json(result);
        });
    },
    findById    
};

module.exports = methods;
