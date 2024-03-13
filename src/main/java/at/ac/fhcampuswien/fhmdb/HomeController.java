package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

    @FXML
    public JFXButton undoFilter;

    public List <Movie> allMovies = Movie.initializeMovies();

    private final ObservableList <Movie> observableMovies = FXCollections.observableArrayList();   // automatically updates corresponding UI elements when underlying data changes
    private final ObservableList <Movie.Genre> observableGenre = FXCollections.observableArrayList(Movie.Genre.values());

    public List <Movie> filteredMovies = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        observableMovies.addAll(allMovies);         // add dummy data to observable list
        // initialize UI stuff
        movieListView.setItems(observableMovies);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data

        // TODO add genre filter items with genreComboBox.getItems().addAll(...)
        genreComboBox.setPromptText("Filter by Genre");
        genreComboBox.getItems().addAll(observableGenre);

        // TODO add event handlers to buttons and call the regarding methods
        // either set event handlers in the fxml file (onAction) or add them here
        searchBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                filteredMovies.clear();
                if(genreComboBox.getValue() != null && searchField.getText().isEmpty()){
                    getMovies(genreComboBox.getValue());
                    setFilteredList();

                } else if(!searchField.getText().isEmpty() && genreComboBox.getValue() == null){
                    getMovies(searchField.getText());
                    setFilteredList();

                }  else {
                    getMovies(genreComboBox.getValue(), searchField.getText());
                    setFilteredList();
                }

            }
        });
        undoFilter.setOnAction(ActionEvent -> {
            genreComboBox.setValue(null);
            searchField.clear();
            sortBtn.setText("Sort (asc)");
            setBackOriginalList();

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

    public List <Movie> getMovies(Object object){
        for(Movie movie: observableMovies){
            for(int i = 0; i < movie.getListGenres().size(); i++){
                if(movie.getListGenres().get(i).equals(object) && !filteredMovies.contains(movie)){
                    filteredMovies.add(movie);
                }
            }
        }
        return filteredMovies;
    }

    public List <Movie> getMovies(String string){
        String trimmed = string.trim();
        for(Movie movie: observableMovies){
            if(movie.getTitle().toLowerCase().contains(trimmed.toLowerCase()) || movie.getDescription().toLowerCase().contains(trimmed.toLowerCase())){
                filteredMovies.add(movie);
            }
        }
        return filteredMovies;
    }

    public List <Movie> getMovies(Object object, String string){
        getMovies(object);
        String trimmed = string.trim();
        for(int i = 0; i < filteredMovies.size(); i++){
            if (!filteredMovies.get(i).getTitle().toLowerCase().contains(trimmed.toLowerCase()) && !filteredMovies.get(i).getDescription().toLowerCase().contains(trimmed.toLowerCase())) {
                filteredMovies.remove(i);
                i--;
            }
        }
        return filteredMovies;
    }

    public void setFilteredList(){
        observableMovies.clear();
        observableMovies.addAll(filteredMovies);
        movieListView.setItems(observableMovies);
        movieListView.setCellFactory(movieListView -> new MovieCell());
    }

    public void setBackOriginalList(){
        observableMovies.clear();
        observableMovies.addAll(allMovies);
        movieListView.setItems(observableMovies);
        movieListView.setCellFactory(movieListView -> new MovieCell());
    }


}