package imdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import imdb.entity.Film;

import java.util.List;

public interface FilmRepository extends JpaRepository<Film, Integer> {
}
