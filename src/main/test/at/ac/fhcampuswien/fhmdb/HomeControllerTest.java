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

    @Test
    void test_if_correct_movies_are_filtered_when_text_is_entered() {
        HomeController controller = new HomeController();

        List<Movie> filteredMovies = controller.filteredMovies(allMovies, "Avatar");

        for (Movie movie : filteredMovies) {
            assertEquals("Avatar", movie.getTitle());
        }
    }
}