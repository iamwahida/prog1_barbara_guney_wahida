package at.ac.fhcampuswien.fhmdb.ui;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class MovieCell extends ListCell<Movie> {
    private final Label title = new Label();
    private final Label detail = new Label();
    private final Label genre = new Label();
    private final Label releaseYear = new Label();
    private final Label rating = new Label();
    private final VBox layout = new VBox(title, detail, genre, releaseYear, rating);

    @Override
    protected void updateItem(Movie movie, boolean empty) {
        super.updateItem(movie, empty);

        if (empty || movie == null) {
            setText(null);
            setGraphic(null);
        } else {
            this.getStyleClass().add("movie-cell");
            title.setText(movie.getTitle());
            detail.setText(movie.getDescription() != null
                            ? movie.getDescription()
                            : "No description available"
            );
            StringBuilder listWithoutBrackets = new StringBuilder();
            for(int i = 0; i < movie.getListGenres().size(); i++){
                listWithoutBrackets.append(movie.getListGenres().get(i));
                if(i < movie.getListGenres().size() - 1){
                    listWithoutBrackets.append(", ");
                }
            }
            genre.setText(listWithoutBrackets.toString());
            releaseYear.setText(movie.getReleaseYear());
            rating.setText(movie.getRating());

            // color scheme
            title.getStyleClass().add("text-yellow");
            detail.getStyleClass().add("text-white");
            genre.getStyleClass().add("text-italic");
            releaseYear.getStyleClass().add("text-white");
            rating.getStyleClass().add("text-red");
            layout.setBackground(new Background(new BackgroundFill(Color.web("#454545"), null, null)));

            // layout
            title.fontProperty().set(title.getFont().font(20));
            detail.setMaxWidth(this.getScene().getWidth() - 30);
            detail.setWrapText(true);
            layout.setPadding(new Insets(10));
            layout.spacingProperty().set(10);
            layout.alignmentProperty().set(javafx.geometry.Pos.CENTER_LEFT);
            setGraphic(layout);
        }
    }
}

