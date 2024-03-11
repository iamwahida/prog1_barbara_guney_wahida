package at.ac.fhcampuswien.fhmdb;


import at.ac.fhcampuswien.fhmdb.models.Movie;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

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
    void test_if_movie_object_is_correctly_instantiated(){
        //Arrange
        List <Movie.Genre> genresList = new ArrayList<>();
        genresList.add(Movie.Genre.DRAMA);
        genresList.add(Movie.Genre.ACTION);
        genresList.add(Movie.Genre.ADVENTURE);

        //Act
        Movie movie = new Movie("Java Unit Tests", "A movie about testing your programs", genresList);

        //Assert
        assertEquals("Java Unit Tests", movie.getTitle());
        assertEquals("A movie about testing your programs", movie.getDescription());
        assertTrue(movie.getListGenres().contains(Movie.Genre.DRAMA));
        assertTrue(movie.getListGenres().contains(Movie.Genre.ACTION));
        assertTrue(movie.getListGenres().contains(Movie.Genre.ADVENTURE));
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

    @Test
    void test_sort_ascending(){
        //Arrange
        List <Movie> movies = Arrays.asList(new Movie("a", "Description 0", List.of(Movie.Genre.BIOGRAPHY)), new Movie("aAchello", "Description 1", List.of(Movie.Genre.ACTION)), new Movie("achello", "Description 2", List.of(Movie.Genre.ROMANCE)), new Movie("Zyyyyzzz", "Description 3", List.of(Movie.Genre.CRIME)), new Movie("zz", "Description 4", List.of(Movie.Genre.SCIENCE_FICTION)));
        //Act
        movies.sort((movie1, movie2) -> movie1.getTitle().compareToIgnoreCase(movie2.getTitle()));
        //Assert
        assertTrue(movies.get(0).getTitle().equals("a"));
        assertTrue(movies.get(4).getTitle().equals("zz"));
    }
    @Test
    void test_sort_descending(){
        //Arrange
        List <Movie> movies = Arrays.asList(new Movie("a", "Description 0", List.of(Movie.Genre.BIOGRAPHY)), new Movie("aAchello", "Description 1", List.of(Movie.Genre.ACTION)), new Movie("achello", "Description 2", List.of(Movie.Genre.ROMANCE)), new Movie("Zyyyyzzz", "Description 3", List.of(Movie.Genre.CRIME)), new Movie("zz", "Description 4", List.of(Movie.Genre.SCIENCE_FICTION)));
        //Act
        movies.sort((movie1, movie2) -> movie2.getTitle().compareToIgnoreCase(movie1.getTitle()));
        //Assert
        assertTrue(movies.get(4).getTitle().equals("a"));
        assertTrue(movies.get(0).getTitle().equals("zz"));
    }

}