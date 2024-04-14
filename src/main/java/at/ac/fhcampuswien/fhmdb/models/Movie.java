package at.ac.fhcampuswien.fhmdb.models;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.FileReader;
import com.google.gson.Gson;

public class Movie {
    private String title;
    private String description;
    private List <String> genres;
    private String releaseYear;
    private String rating;


    public Movie(String title, String description, List <String> genres, String releaseYear, String rating) {
        this.title = title;
        this.description = description;
        this.genres = genres;
        this.releaseYear = releaseYear;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List <String> getListGenres(){ return genres; }

    public String getReleaseYear(){ return releaseYear; }

    public String getRating(){ return rating; }


    public static List <Movie> initializeMovies() throws IOException{
        MovieAPI movieApi = new MovieAPI();
        List <Movie> movies;
        String outputPath = "apiResponse.json";
        movieApi.getDataAndSaveAsJson("https://prog2.fh-campuswien.ac.at/movies", outputPath);
        movies = movieApi.readMoviesFromJsonFile(outputPath);
        return movies;
    }

    public static List <Movie> filterMovies(String textField, String genre, String year, String rating) throws IOException {
        MovieAPI movieApi = new MovieAPI();
        List <Movie> movies;
        String apiURL = "";
        String outputPath = "apiResponse.json";
        if(textField != null && genre != null && year != null && rating != null){
            apiURL = "https://prog2.fh-campuswien.ac.at/movies" + "?" + "query=" + textField + "&genre=" + genre + "&releaseYear=" + year + "&ratingFrom=" + rating;
            System.out.println(apiURL);
        } else if(textField == null && genre != null && year != null && rating != null){
            apiURL = "https://prog2.fh-campuswien.ac.at/movies" + "?" + "&genre=" + genre + "&releaseYear=" + year + "&ratingFrom=" + rating;
            System.out.println(apiURL);
        } else if(textField == null && genre == null && year != null && rating != null){
            apiURL = "https://prog2.fh-campuswien.ac.at/movies" + "?" + "&releaseYear=" + year + "&ratingFrom=" + rating;
        } else if(textField == null && genre == null && year == null && rating != null){
            //apiURL = "https://prog2.fh-campuswien.ac.at/movies" + "?" + "ratingFrom=" + rating;
            System.out.println("all three null");
            apiURL = "https://prog2.fh-campuswien.ac.at/movies?ratingFrom=9";
            System.out.println(apiURL);
        }
        movieApi.getDataAndSaveAsJson(apiURL, outputPath);
        movies = movieApi.readMoviesFromJsonFile(outputPath);
        return movies;
    }
}