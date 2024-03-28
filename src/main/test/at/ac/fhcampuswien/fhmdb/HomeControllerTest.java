package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HomeControllerTest {
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
        List <Genre> genresList = new ArrayList<>();
        genresList.add(Genre.DRAMA);
        genresList.add(Genre.ACTION);
        genresList.add(Genre.ADVENTURE);

        //Act
        Movie movie = new Movie("Java Unit Tests", "A movie about testing your programs", genresList);

        //Assert
        assertEquals("Java Unit Tests", movie.getTitle());
        assertEquals("A movie about testing your programs", movie.getDescription());
        assertTrue(movie.getListGenres().contains(Genre.DRAMA));
        assertTrue(movie.getListGenres().contains(Genre.ACTION));
        assertTrue(movie.getListGenres().contains(Genre.ADVENTURE));
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
    void test_the_items_in_the_filteredMovies_list_by_genre(){
        //Arrange
        HomeController hc = new HomeController();
        //Act
        List <Movie> actualResult = hc.getMovies(Genre.BIOGRAPHY);
        //Assert that the filtered list has been updated
        assertEquals(actualResult, hc.getFilteredMovies());
    }
    @Test
    void test_the_items_in_the_filteredMovies_list_by_genre_and_text_input(){
        //Arrange
        HomeController hc = new HomeController();
        //Act
        List <Movie> actualResult = hc.getMovies(Genre.ACTION, "Story");
        //Assert that the filtered list has been updated
        assertEquals(actualResult, hc.getFilteredMovies());
    }

    @Test
    void test_sort_ascending(){
        //Arrange
        List <Movie> movies = Arrays.asList(new Movie("a", "Description 0", List.of(Genre.BIOGRAPHY)), new Movie("aAchello", "Description 1", List.of(Genre.ACTION)), new Movie("achello", "Description 2", List.of(Genre.ROMANCE)), new Movie("Zyyyyzzz", "Description 3", List.of(Genre.CRIME)), new Movie("zz", "Description 4", List.of(Genre.SCIENCE_FICTION)));
        //Act
        movies.sort((movie1, movie2) -> movie1.getTitle().compareToIgnoreCase(movie2.getTitle()));
        //Assert
        assertEquals("a", movies.get(0).getTitle());
        assertEquals("zz", movies.get(4).getTitle());
    }
    @Test
    void test_sort_descending(){
        //Arrange
        List <Movie> movies = Arrays.asList(new Movie("a", "Description 0", List.of(Genre.BIOGRAPHY)), new Movie("aAchello", "Description 1", List.of(Genre.ACTION)), new Movie("achello", "Description 2", List.of(Genre.ROMANCE)), new Movie("Zyyyyzzz", "Description 3", List.of(Genre.CRIME)), new Movie("zz", "Description 4", List.of(Genre.SCIENCE_FICTION)));
        //Act
        movies.sort((movie1, movie2) -> movie2.getTitle().compareToIgnoreCase(movie1.getTitle()));
        //Assert
        assertEquals("a", movies.get(4).getTitle());
        assertEquals("zz", movies.get(0).getTitle());
    }
}