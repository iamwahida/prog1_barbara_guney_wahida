package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.models.MovieAPI;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.Optional;


public class HomeController implements Initializable {
    @FXML
    public JFXButton searchBtn;

    @FXML
    public TextField searchField;

    @FXML
    public JFXListView movieListView;

    @FXML
    public JFXComboBox<String> genreComboBox;

    @FXML
    public JFXComboBox<String> releaseYearComboBox;

    @FXML
    public JFXComboBox<String> ratingComboBox;

    @FXML
    public JFXButton sortBtn;

    @FXML
    public JFXButton undoFilter;

    @FXML
    private Label resultLabel;

    @FXML
    public JFXButton getLongestMovieTitleBtn;

    @FXML
    public JFXButton getMoviesBetweenYearsBtn;

    @FXML
    public JFXButton popularActorBtn;

    @FXML
    public JFXButton moviesByDirectorBtn;

    public List<Movie> allMovies = Movie.initializeMovies();

    public List<String> genres = List.of("ACTION", "ADVENTURE", "ANIMATION", "BIOGRAPHY", "COMEDY", "CRIME", "DRAMA", "DOCUMENTARY", "FAMILY", "FANTASY", "HISTORY", "HORROR", "MUSICAL", "MYSTERY", "ROMANCE", "SCIENCE_FICTION", "SPORT", "THRILLER", "WAR");
    public List<String> releaseYears = new ArrayList<>();
    public List<String> ratings = List.of("1", "2", "3", "4", "5", "6", "7", "8", "9");

    private final ObservableList<Movie> observableMovies = FXCollections.observableArrayList();   // automatically updates corresponding UI elements when underlying data changes
    private final ObservableList<String> observableGenre = FXCollections.observableArrayList(genres);
    private final ObservableList<String> observableReleaseYears = FXCollections.observableArrayList();
    private final ObservableList<String> observableRating = FXCollections.observableArrayList(ratings);

    public List<Movie> filteredMovies = new ArrayList<>();

    public List<Movie> getFilteredMovies() {
        return this.filteredMovies;
    }

    public HomeController() throws IOException {
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        observableMovies.addAll(allMovies);
        releaseYears = collectYears(allMovies);
        observableReleaseYears.addAll(releaseYears);

        movieListView.setItems(observableMovies);
        movieListView.setCellFactory(movieListView -> new MovieCell());

        genreComboBox.setPromptText("Filter by Genre");
        releaseYearComboBox.setPromptText("Filter by Release Year");
        ratingComboBox.setPromptText("Filter by Rating");
        genreComboBox.getItems().addAll(observableGenre);
        ratingComboBox.getItems().addAll(observableRating);
        releaseYearComboBox.getItems().addAll(observableReleaseYears);

        searchBtn.setOnAction(event -> {
            filteredMovies.clear();
            String searchText = searchField.getText();
            String genre = genreComboBox.getValue();
            String releaseYear = releaseYearComboBox.getValue();
            String rating = ratingComboBox.getValue();


            try {
                filteredMovies = Movie.filterMovies(searchText, genre, releaseYear, rating);
                setFilteredList();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        undoFilter.setOnAction(ActionEvent -> {
            genreComboBox.setValue(null);
            searchField.clear();
            ratingComboBox.setValue(null);
            releaseYearComboBox.setValue(null);
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
        popularActorBtn.setOnAction(event -> {
            String mostPopularActor = getMostPopularActor(filteredMovies);
            // Display or use the most popular actor information as needed...
            System.out.println("Most popular actor: " + mostPopularActor);
        });

        moviesByDirectorBtn.setOnAction(event -> {
            String director = "Christopher Nolan"; // Replace with the desired director's name
            long moviesCount = countMoviesFrom(filteredMovies, director);
            // Display or use the count of movies by the director as needed...
            System.out.println("Number of movies directed by " + director + ": " + moviesCount);
        });

        getLongestMovieTitleBtn.setOnAction(actionEvent -> {
            Movie longestTitleMovie = allMovies.stream()
                    .max(Comparator.comparingInt(movie -> movie.getTitle().length()))
                    .orElse(null);

            if(longestTitleMovie != null) {
                observableMovies.clear();
                observableMovies.add(longestTitleMovie);
                movieListView.setItems(observableMovies);
            }
        });


        getMoviesBetweenYearsBtn.setOnAction(actionEvent -> {
            TextInputDialog dialog = new TextInputDialog("2020");
            dialog.setHeaderText("Search for movies between two years");
            dialog.setContentText("Enter the start year:");

            Optional<String> startYearResult = dialog.showAndWait();
            startYearResult.ifPresent(startYearString -> {
                dialog.setContentText("Enter the end year:");
                Optional<String> endYearResult = dialog.showAndWait();
                endYearResult.ifPresent(endYearString -> {
                    try {
                        int startYear = Integer.parseInt(startYearString);
                        int endYear = Integer.parseInt(endYearString);

                        List<Movie> moviesBetweenYears = getMoviesBetweenYears(allMovies, startYear, endYear);

                        observableMovies.clear();
                        observableMovies.addAll(moviesBetweenYears);
                        movieListView.setItems(observableMovies);
                    } catch (NumberFormatException e) {
                        System.out.println("Error");
                    }
                });
            });
        });

    }

    public void setFilteredList() {
        observableMovies.clear();
        observableMovies.addAll(filteredMovies);
        movieListView.setItems(observableMovies);
        movieListView.setCellFactory(movieListView -> new MovieCell());
    }

    public List<String> collectYears(List<Movie> allMovies) {
        for (Movie movie : allMovies) {
            String trimmedYear = movie.getReleaseYear().substring(0, movie.getReleaseYear().length() - 2);
            releaseYears.add(trimmedYear);
            releaseYears.sort(Comparator.naturalOrder());
        }
        return releaseYears;
    }

    public void setBackOriginalList() {
        observableMovies.clear();
        observableMovies.addAll(allMovies);
        movieListView.setItems(observableMovies);
        movieListView.setCellFactory(movieListView -> new MovieCell());
    }


    List<Movie> getMoviesBetweenYears(List<Movie> movies, int startYear, int endYear) {
        return movies.stream()
                .filter(movie -> {
                    try {
                        int year = Integer.parseInt(movie.getReleaseYear().trim());
                        return year >= startYear && year <= endYear;
                    } catch (NumberFormatException e) {
                        System.out.println("Error with the year: " + movie.getReleaseYear());
                        return false;
                    }
                })
                .collect(Collectors.toList());
    }




    public String getMostPopularActor(List<Movie> movies) {
        return movies.stream()
                .flatMap(movie -> movie.getMainCast().stream())
                .collect(Collectors.groupingBy(actor -> actor, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    public long countMoviesFrom(List<Movie> movies, String director) {
        return movies.stream()
                .filter(movie -> movie.getDirector().equalsIgnoreCase(director))
                .count();
    }

}




   /*
    String getLongestMovieTitle(List<Movie> movies) {
        return movies.stream()
                .max(Comparator.comparingInt(movie -> movie.getTitle().length()))
                .map(Movie::getTitle)
                .orElse("");
    }
     */










    /*




    }

    public List <Movie> getMovies(Object object){
        for(Movie movie: observableMovies){
            for(int i = 0; i < movie.getListGenres().size(); i++) {
                if(movie.getListGenres().get(i).equals(object) && !filteredMovies.contains(movie)) {
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
    }*/