package at.ac.fhcampuswien.fhmdb.models;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;


public class MovieAPI {

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create(); //GsonBuilder -> Object to configure Json -> setPrettyPrinting() = human readable version -> create = saves into Gson Object

    public void getDataAndSaveAsJson(String urlApi, String outputPath) throws IOException {
        URL url = new URL(urlApi);
        HttpURLConnection connect = (HttpURLConnection) url.openConnection();
        connect.setRequestMethod("GET");
        int httpResponse = connect.getResponseCode();
        if(httpResponse == HttpURLConnection.HTTP_OK){
            String jsonData = getJsonData(connect);
            saveJsonData(jsonData, outputPath);
        } else {
            System.err.println("Failed to get data. Http Response: " + httpResponse);
        }
        connect.disconnect();
    }

    private String getJsonData(HttpURLConnection connection) throws IOException {
        StringBuilder jsonData = new StringBuilder();
        Scanner scanner = new Scanner(connection.getInputStream());
        while(scanner.hasNextLine()){
            jsonData.append(scanner.nextLine());
        }
        scanner.close();
        return jsonData.toString();
    }

    private void saveJsonData(String jsonData, String outputPath) throws IOException {
        try (FileWriter fileWriter = new FileWriter(outputPath)){
            gson.toJson(gson.fromJson(jsonData, Object.class), fileWriter);
        }
    }

    public List<Movie> readMoviesFromJsonFile(String filePath) throws IOException {
        FileReader fileReader = new FileReader(filePath);
        Type listType = new TypeToken<List<Movie>>() {}.getType(); //Define the type of data structure to deserialize
        return new Gson().fromJson(fileReader, listType); //Use Gson to deserialize the JSON data into a list of Movies
    }

}
