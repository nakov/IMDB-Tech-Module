package imdb.bindingModel;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class FilmBindingModel {

    @NotNull
    @Size(min=1)
    private String name;

    @NotNull
    @Size(min=1)
    private String genre;

    @NotNull
    @Size(min=1)
    private String director;

    private int year;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
