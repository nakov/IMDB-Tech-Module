const filmController = require('../controllers/film');

module.exports = (app) => {
    app.get('/', filmController.index);

	app.get('/create/', filmController.createGet);
	app.post('/create/', filmController.createPost);

	app.get('/edit/:id', filmController.editGet);
	app.post('/edit/:id', filmController.editPost);

    app.get('/delete/:id', filmController.deleteGet);
    app.post('/delete/:id', filmController.deletePost);
};