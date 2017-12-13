const Film = require('../models/Film');

module.exports = {
	index: (req, res) => {
        Film.find().then(films => {
            return res.render('film/index', {'films': films});
        }).catch(err => {
            return res.send("error");
        });
	},

	createGet: (req, res) => {
        res.render('film/create');
	},

	createPost: (req, res) => {
        let film = req.body;
        Film.create(film).then(film => {
            res.redirect("/");
        }).catch(err => {
            film.error = 'Cannot create film.';
            res.render('film/create', film);
        });
	},

	editGet: (req, res) => {
        let filmId = req.params.id;
        Film.findById(filmId).then(film => {
            if (film) {
                res.render('film/edit', film );
            }
            else {
                res.redirect('/');
            }
        }).catch(err => res.redirect('/'));
	},

	editPost: (req, res) => {
        let filmId = req.params.id;
        let film = req.body;

        Film.findByIdAndUpdate(filmId, film, {runValidators: true}).then(film => {
            res.redirect("/");
        }).catch(err => {
            film.id = filmId;
            film.error = "Cannot edit film.";
            return res.render("film/edit", film);
        });
	},

	deleteGet: (req, res) => {
        let id = req.params.id;
        Film.findById(id).then(film => {
            if (film) {
                return res.render('film/delete', film);
            }
            else {
                return res.redirect('/');
            }
        }).catch(err => res.redirect('/'));
	},

	deletePost: (req, res) => {
        let id = req.params.id;
        Film.findByIdAndRemove(id).then(data => {
            res.redirect('/');
        }).catch(err => res.redirect('/'));
	}
};