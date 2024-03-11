package at.ac.fhcampuswien.fhmdb;


import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static at.ac.fhcampuswien.fhmdb.models.Movie.initializeMovies;
import static org.junit.jupiter.api.Assertions.*;

public class HomeControllerTest {
    public List<Movie> allMovies = Movie.initializeMovies();
/*
für Home-Controller
Richtige Initialisierung der Filme in initializeMovies().
 --> jeder Film in der Liste hat Titel, Beschreibung und Genre
 --> Liste darf nicht leer sein

Richtige Filtern und Sortieren der Filme in der HomeController-Klasse.

für Movie:
Ob die Erstellung neuer Movie-Objekte korrekt funktioniert.
Ob die Getter-Methoden die richtigen Werte zurückgeben.
 */

    @Test
    void test_if_initialization_of_initializeMovies_is_correct() {
        List<Movie> movies = Movie.initializeMovies();

        assertFalse(movies.isEmpty(), "The list is not supposed to be empty.");

        for (Movie movie : movies) {
            assertNotNull(movie.getTitle(), "Titel should not be null.");
            assertFalse(movie.getTitle().isEmpty(), "Titel should not be empty.");

            assertNotNull(movie.getDescription(), "Description is not supposed to be null.");
            assertFalse(movie.getDescription().isEmpty(), "Description should not be empty.");

            assertNotNull(movie.getListGenres(), "Genre List should not be null.");
            assertFalse(movie.getListGenres().isEmpty(), "Genre List should not be empty.");
        }
    }

    @Test
    void test_if_correct_movies_are_filtered_when_text_is_entered() {
        HomeController controller = new HomeController();

       // controller.initialize(null,null);
        String searchText = "Avengers";
        List<Movie> filteredMovies = controller.getMovies(searchText);
        for (Movie movie : filteredMovies) {
            boolean isInTitle = movie.getTitle().contains(searchText);
            boolean isInDescription = movie.getDescription().contains(searchText);

            assertTrue(isInTitle || isInDescription, "Movie should contain the search text in its title or description.");
        }
    }

}