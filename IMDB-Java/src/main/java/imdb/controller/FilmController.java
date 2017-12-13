package imdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import imdb.bindingModel.FilmBindingModel;
import imdb.entity.Film;
import imdb.repository.FilmRepository;

import javax.validation.Valid;
import java.util.List;

@Controller
public class FilmController {

	private final FilmRepository filmRepository;

	@Autowired
	public FilmController(FilmRepository filmRepository) {
		this.filmRepository = filmRepository;
	}

	@GetMapping("/")
	public String index(Model model) {
        List<Film> films =  this.filmRepository.findAll();
        model.addAttribute("films", films);
        model.addAttribute("view", "film/index");
        return "base-layout";
	}

	@GetMapping("/create")
	public String create(Model model) {
        model.addAttribute("film", new FilmBindingModel());
        model.addAttribute("view", "film/create");
        return "base-layout";
	}

	@PostMapping("/create")
	public String createProcess(
	        Model model, @Valid FilmBindingModel filmBindingModel, BindingResult bindingResult) {

	    if (bindingResult.hasErrors()) {
            model.addAttribute("message", "Invalid data.");
            model.addAttribute("film", filmBindingModel);
            model.addAttribute("view", "film/create");
            return "base-layout";
        }

		Film f = new Film();
        f.setName(filmBindingModel.getName());
        f.setGenre(filmBindingModel.getGenre());
        f.setDirector(filmBindingModel.getDirector());
        f.setYear(filmBindingModel.getYear());
        filmRepository.saveAndFlush(f);

        return "redirect:/";
	}

	@GetMapping("/edit/{id}")
	public String edit(Model model, @PathVariable int id) {
	    Film film = this.filmRepository.findOne(id);
        if (film == null)
            return "redirect:/";

        model.addAttribute("film", film);
        model.addAttribute("view", "film/edit");
        return "base-layout";
	}

	@PostMapping("/edit/{id}")
	public String editProcess(
	        Model model, @PathVariable int id,
            @Valid FilmBindingModel filmBindingModel,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("message", "Invalid data.");
            model.addAttribute("film", filmBindingModel);
            model.addAttribute("view", "film/edit");
            return "base-layout";
        }

        Film film = this.filmRepository.findOne(id);
        if (film == null)
            return "redirect:/";

        film.setName(filmBindingModel.getName());
        film.setGenre(filmBindingModel.getGenre());
        film.setDirector(filmBindingModel.getDirector());
        film.setYear(filmBindingModel.getYear());
        filmRepository.saveAndFlush(film);

        return "redirect:/";
	}

	@GetMapping("/delete/{id}")
	public String delete(Model model, @PathVariable int id) {
        Film film = this.filmRepository.findOne(id);
        if (film == null)
            return "redirect:/";

        model.addAttribute("film", film);
        model.addAttribute("view", "film/delete");
        return "base-layout";
	}

	@PostMapping("/delete/{id}")
	public String deleteProcess(@PathVariable int id) {
        Film film = this.filmRepository.findOne(id);
        if (film == null)
            return "redirect:/";

        filmRepository.delete(film);
        filmRepository.flush();

        return "redirect:/";
	}
}
