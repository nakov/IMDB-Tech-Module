const mongoose = require('mongoose');
mongoose.Promise = global.Promise;

module.exports = (config) => {
    mongoose.connect(config.connectionString, { useMongoClient: true });

    let database = mongoose.connection;

    database.once('open', (error) => {
        if (error) {
            console.log(error);
            return;
        }

        console.log('MongoDB ready!')
    });

    require('./../models/Film');
};




