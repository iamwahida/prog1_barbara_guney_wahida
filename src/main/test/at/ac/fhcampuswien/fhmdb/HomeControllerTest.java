package at.ac.fhcampuswien.fhmdb;


import at.ac.fhcampuswien.fhmdb.models.Movie;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class HomeControllerTest {
    public List<Movie> allMovies = Movie.initializeMovies();

    public HomeControllerTest() throws IOException {
    }
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
    void test_if_initialization_of_initializeMovies_is_correct() throws IOException {
        List<Movie> movies = Movie.initializeMovies();

        assertFalse(movies.isEmpty(), "The list is not supposed to be empty.");

        for (Movie movie : movies) {
            assertNotNull(movie.getTitle(), "Title should not be null.");
            assertFalse(movie.getTitle().isEmpty(), "Title should not be empty.");

            assertNotNull(movie.getDescription(), "Description is not supposed to be null.");
            assertFalse(movie.getDescription().isEmpty(), "Description should not be empty.");

            assertNotNull(movie.getListGenres(), "Genre List should not be null.");
            assertFalse(movie.getListGenres().isEmpty(), "Genre List should not be empty.");

            assertNotNull(movie.getReleaseYear(), "Release year should not be null.");
            assertFalse(movie.getReleaseYear().isEmpty(), "Release year should not be empty.");

            assertNotNull(movie.getRating(), "Rating should not be null.");
            assertFalse(movie.getRating().isEmpty(), "Rating should not be empty.");
        }
    }


    @Test
    void test_if_movie_object_is_correctly_instantiated(){
        // Arrange
        String title = "Test Movie";
        String description = "A test movie for unit testing";
        List<String> genresList = Arrays.asList("Drama", "Action", "Adventure");
        String year = "2008";
        String rating = "8";

        // Act
        Movie movie = new Movie(title, description, genresList, year, rating);

        // Assert
        assertEquals(title, movie.getTitle(), "Title should match");
        assertEquals(description, movie.getDescription(), "Description should match");
        assertTrue(movie.getListGenres().containsAll(genresList), "Genres should match");
        assertEquals(year, movie.getReleaseYear(), "Year should match");
        assertEquals(rating, movie.getRating(), "Rating should match");
    }


    /*@Test
    void test_if_correct_movies_are_filtered_when_text_is_entered() throws IOException {
        HomeController controller = new HomeController();

        String searchText = "Avengers";
        String horror = "Horror";
        List<Movie> movies = Arrays.asList(
                new Movie("Movie 1", "1999", List.of(horror), "1999", "8"),
                new Movie("Movie 2", "2005", List.of(horror), "2005", "7")
        );

        List<Movie> filteredMovies = controller.getMovies(searchText);
        for (Movie movie : filteredMovies) {
            boolean isInTitle = movie.getTitle().contains(searchText);
            boolean isInDescription = movie.getDescription().contains(searchText);

            assertTrue(isInTitle || isInDescription, "Movie should contain the search text in its title or description.");
        }
    }



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
    }*/

    @Test
    void test_sort_ascending(){
        //Arrange
        String Horror = "Horror";
        List<Movie> movies = Arrays.asList(
                new Movie("movie1", "1999", List.of(Horror), "1999", "8"),
                new Movie("movie2", "2005", List.of(Horror), "2005", "7"));//Act
        movies.sort((movie1, movie2) -> movie1.getTitle().compareToIgnoreCase(movie2.getTitle()));
        //Assert
        assertEquals("movie1", movies.get(0).getTitle());
        assertEquals("movie2", movies.get(1).getTitle());
    }
    @Test
    void test_sort_descending(){
        //Arrange
        String Horror = "Horror";
        List<Movie> movies = Arrays.asList(
                new Movie("movie1", "1999", List.of(Horror), "1999", "8"),
                new Movie("movie2", "2005", List.of(Horror), "2005", "7"));
        //Act
        movies.sort((movie1, movie2) -> movie2.getTitle().compareToIgnoreCase(movie1.getTitle()));
        //Assert
        assertEquals("movie2", movies.get(0).getTitle());
        assertEquals("movie1", movies.get(1).getTitle());
    }
    @Test
    void test_Get_Movies_Between_StartYear_And_Endyear() throws IOException {
        // Arrange
        HomeController homeController = new HomeController();
        String Horror = "Horror";
        List<Movie> movies = Arrays.asList(
                new Movie("Movie 1", "1999", List.of(Horror), "1999", "8"),
                new Movie("Movie 2", "2005", List.of(Horror), "2005", "7"),
                new Movie("Movie 3", "2010", List.of(Horror), "2010", "6"),
                new Movie("Movie 4", "2015", List.of(Horror), "2015", "5"),
                new Movie("Movie 5", "2020", List.of(Horror), "2020", "4")
        );
        int startYear = 2000;
        int endYear = 2015;

        // Act
        List<Movie> result = homeController.getMoviesBetweenYears(movies, startYear, endYear);

        // Assert
        assertEquals(3, result.size());
    }

    @Test
    void test_get_Most_Popular_Actor() throws IOException{
        // Arrange
        HomeController homeController = new HomeController();

        // Erstellen von Testfilmen mit verschiedenen Schauspielern
        Movie movie1 = new Movie("Movie 1", "1999", Arrays.asList("Actor A", "Actor B", "Actor C"), "1999", "8");
        Movie movie2 = new Movie("Movie 2", "2005", Arrays.asList("Actor B", "Actor D", "Actor E"), "2005", "7");
        Movie movie3 = new Movie("Movie 3", "2010", Arrays.asList("Actor A", "Actor E", "Actor F"), "2010", "6");

        List<Movie> movies = Arrays.asList(movie1, movie2, movie3);

        // Act
        String mostPopularActor = homeController.getMostPopularActor(movies);

        // Assert
        assertEquals("Actor A", mostPopularActor);
    }
    @Test
    public void test_if_Ratings_List_is_same() throws IOException{
        // Arrange
        HomeController homeController = new HomeController();
        List<String> expectedRatings = List.of("1", "2", "3", "4", "5", "6", "7", "8", "9");

        // Act
        List<String> actualRatings = homeController.ratings;

        // Assert
        assertEquals(expectedRatings, actualRatings, "Ratings list should match expected ratings");
    }
}