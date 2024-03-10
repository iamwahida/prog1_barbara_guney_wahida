package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    public JFXButton searchBtn;

    @FXML
    public TextField searchField;

    @FXML
    public JFXListView movieListView;

    @FXML
    public JFXComboBox genreComboBox;

    @FXML
    public JFXButton sortBtn;

    public List<Movie> allMovies = Movie.initializeMovies();

    final ObservableList<Movie> observableMovies = FXCollections.observableArrayList();   // automatically updates corresponding UI elements when underlying data changes

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        observableMovies.addAll(allMovies);         // add dummy data to observable list

        // initialize UI stuff
        movieListView.setItems(observableMovies);
        movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data

        // TODO add genre filter items with genreComboBox.getItems().addAll(...)
        genreComboBox.setPromptText("Filter by Genre");
        genreComboBox.getItems().addAll(Genre.values());

        genreComboBox.setOnAction(actionEvent ->{
            Genre selectedGenre = (Genre) genreComboBox.getValue();
            ObservableList<Movie> filteredGenres = FXCollections.observableArrayList();
            for(Movie movie : allMovies){
                if(movie.getGenres().contains(selectedGenre)){
                    filteredGenres.add(movie);
                }
            }
            movieListView.setCellFactory(movieListView -> new MovieCell());
            observableMovies.clear();
            movieListView.setCellFactory(movieListView -> new MovieCell());
            observableMovies.addAll(filteredGenres);
            //movieListView.setCellFactory(movieListView -> new MovieCell());
        });

        // TODO add event handlers to buttons and call the regarding methods
        // either set event handlers in the fxml file (onAction) or add them here
        searchBtn.setOnAction(actionEvent -> {
            observableMovies.clear();
            String input = searchField.getText().toLowerCase().trim();
            List<Movie> filteredMoviesList = filteredMovies(allMovies, input); // Aufruf der Methode filteredMovies()
            ObservableList<Movie> filteredMovies = FXCollections.observableArrayList(filteredMoviesList); // Erstellen einer ObservableList aus der gefilterten Liste
            movieListView.setCellFactory(movieListView -> new MovieCell());
            observableMovies.addAll(filteredMovies);
        });


        // Sort button example:
        sortBtn.setOnAction(actionEvent -> {
            if(sortBtn.getText().equals("Sort (asc)")) {
                // TODO sort observableMovies ascending
                observableMovies.sort((movie1, movie2) -> movie1.getTitle().compareToIgnoreCase(movie2.getTitle()));
                sortBtn.setText("Sort (desc)");
            } else {
                // TODO sort observableMovies descending
                observableMovies.sort((movie1, movie2) -> movie2.getTitle().compareToIgnoreCase(movie1.getTitle()));
                sortBtn.setText("Sort (asc)");
            }
        });


    }
    public List<Movie> filteredMovies(List<Movie> movies, String input) {
        List<Movie> filteredMovies = new ArrayList<>();
        for(Movie movie : movies) {
            if(movie.getTitle().toLowerCase().contains(input) || movie.getDescription().toLowerCase().contains(input)) {
                filteredMovies.add(movie);
            }
        }
        return filteredMovies;
    }

}