const express = require('express');
const path = require('path');
const bodyParser = require('body-parser');
const session = require('express-session');
const helpers = require('../helpers/handlebarsHelpers');

module.exports = (app, config) => {
    // View engine setup.
    app.set('views', path.join(config.rootFolder, '/views'));
    app.set('view engine', 'hbs');

    // This set up which is the parser for the request's data.
    app.use(bodyParser.json());
    app.use(bodyParser.urlencoded({extended: true}));

    app.use((req, res, next) => {
        next();
    });

    // This makes the content in the "public" folder accessible for every user.
    app.use(express.static(path.join(config.rootFolder, 'public')));
};



