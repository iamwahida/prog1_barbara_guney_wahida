package at.ac.fhcampuswien.fhmdb.models;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    private String title;
    private String description;
    // TODO add more properties here -> DONE
    public enum Genre {ACTION, ADVENTURE, ANIMATION, BIOGRAPHY, COMEDY,
        CRIME, DRAMA, DOCUMENTARY, FAMILY, FANTASY, HISTORY, HORROR,
        MUSICAL, MYSTERY, ROMANCE, SCIENCE_FICTION, SPORT, THRILLER, WAR, WESTERN}
    private List <Genre> genres;


    public Movie(String title, String description, List <Genre> genres) {
        this.title = title;
        this.description = description;
        this.genres = genres;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List <Genre> getListGenres(){ return genres; }


    public static List<Movie> initializeMovies() {
        List<Movie> movies = new ArrayList<>();
        // TODO add some dummy data here -> DONE

        movies.add(new Movie("Now you see me","Four magicians (Eisenberg, Harrelson, Fisher, and Franco) are invited to an address. Inside, they encounter a few tricks. A year later, these four have become The Four Horsemen and are doing a show in Las Vegas. They end the first performance that we see with a bank robbery that doesn't look like an illusion.", List.of(Genre.ACTION, Genre.CRIME, Genre.MYSTERY)));
        movies.add(new Movie("No Country for Old Men","The film follows three main characters: Llewelyn Moss (Brolin), a Vietnam War veteran and welder who stumbles upon a large sum of money in the desert; Anton Chigurh (Bardem), a hitman who is sent to recover the money; and Ed Tom Bell (Jones), a sheriff investigating the crime.", List.of(Genre.ACTION, Genre.DRAMA, Genre.WESTERN)));
        movies.add(new Movie("Drive","Driver is a skilled Hollywood stuntman who moonlights as a getaway driver for criminals. Though he projects an icy exterior, lately he's been warming up to a pretty neighbor named Irene and her young son, Benicio. When Irene's husband gets out of jail, he enlists Driver's help in a million-dollar heist.", List.of(Genre.ACTION, Genre.CRIME)));
        movies.add(new Movie("21 Jump Street", "Two rookie cops go from park duty to prom when they're given a big assignment: Bust a drug ring by going undercover as high school students.", List.of(Genre.COMEDY, Genre.CRIME)));
        movies.add(new Movie("Lamborghini: The Man Behind the Legend", "The life story of Ferruccio Lamborghini, the founder of Lamborghini.", List.of(Genre.BIOGRAPHY, Genre.DRAMA)));
        movies.add(new Movie("Harry Potter and the Half-Blood Prince", "Harry Potter learns a lot about Lord Voldemort's past, and Harry Potter prepares for the final battle against his nemesis with the help of Headmaster Dumbledore. But in that time, Voldemort returns to power, and makes a plan to destroy Harry.", List.of(Genre.ADVENTURE, Genre.FANTASY)));
        movies.add(new Movie("Oppenheimer", "The story of American scientist J. Robert Oppenheimer and his role in the development of the atomic bomb.",List.of(Genre.BIOGRAPHY, Genre.DRAMA)));
        movies.add(new Movie("Inception", "A thief who enters the dreams of others to steal secrets from their subconscious gets a chance to redeem himself by planting an idea in the mind of a CEO.", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.SCIENCE_FICTION)));
        movies.add(new Movie("The Shawshank Redemption", "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.", List.of(Genre.DRAMA, Genre.CRIME)));
        movies.add(new Movie("The Dark Knight", "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.", List.of(Genre.ACTION, Genre.CRIME, Genre.DRAMA)));
        movies.add(new Movie("Spirited Away", "During her family's move to the suburbs, a sullen 10-year-old girl wanders into a world ruled by gods, witches, and spirits, and where humans are changed into beasts.", List.of(Genre.ANIMATION, Genre.ADVENTURE, Genre.FAMILY)));
        movies.add(new Movie("Catch Me If You Can", "A seasoned FBI agent pursues Frank Abagnale Jr. who, before his 19th birthday, successfully forged millions of dollars' worth of checks while posing as a Pan Am pilot, a doctor, and a legal prosecutor.", List.of(Genre.BIOGRAPHY, Genre.CRIME, Genre.DRAMA)));
        movies.add(new Movie("Avatar", "A paraplegic Marine dispatched to the moon Pandora on a unique mission becomes torn between following his orders and protecting the world he feels is his home.", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.FANTASY)));
        movies.add(new Movie("Toy Story", "A cowboy doll is profoundly threatened and jealous when a new spaceman figure supplants him as top toy in a boy's room.", List.of(Genre.ANIMATION, Genre.ADVENTURE, Genre.COMEDY)));
        movies.add(new Movie("The Social Network", "As Harvard student Mark Zuckerberg creates the social networking site that would become known as Facebook, he is sued by the twins who claimed he stole their idea, and by the co-founder who was later squeezed out of the business.", List.of(Genre.BIOGRAPHY, Genre.DRAMA)));
        movies.add(new Movie("An Inconvenient Truth", "A documentary on the threat that climate change poses to the Earth - it's causes, effects and history and potential solutions to it. Presented by Al Gore through a lecture that he has given to audiences across the globe.", List.of(Genre.DOCUMENTARY)));
        movies.add(new Movie("Finding Nemo", "After his son is captured in the Great Barrier Reef and taken to Sydney, a timid clownfish sets out on a journey to bring him home.", List.of(Genre.ANIMATION, Genre.ADVENTURE, Genre.COMEDY)));
        movies.add(new Movie("The Godfather", "The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.", List.of(Genre.CRIME, Genre.DRAMA)));
        movies.add(new Movie("Jurassic Park", "During a preview tour, a theme park suffers a major power breakdown that allows its cloned dinosaur exhibits to run amok.", List.of(Genre.ADVENTURE, Genre.SCIENCE_FICTION)));
        movies.add(new Movie("Pride and Prejudice", "Sparks fly when spirited Elizabeth Bennet meets single, rich, and proud Mr. Darcy. But Mr. Darcy reluctantly finds himself falling in love with a woman beneath his class.", List.of(Genre.DRAMA, Genre.ROMANCE)));
        movies.add(new Movie("The Wizard of Oz", "Dorothy Gale is swept away from a farm in Kansas to a magical land of Oz in a tornado and embarks on a quest with her new friends to see the Wizard who can help her return home to Kansas and help her friends as well.", List.of(Genre.ADVENTURE, Genre.FAMILY, Genre.FANTASY)));
        movies.add(new Movie("Star Wars: Episode IV - A New Hope", "Luke Skywalker joins forces with a Jedi Knight, a cocky pilot, a Wookiee and two droids to save the galaxy from the Empire's world-destroying battle station, while also attempting to rescue Princess Leia from the mysterious Darth Vader.", List.of(Genre.ACTION, Genre.ADVENTURE, Genre.FANTASY)));
        movies.add(new Movie("The Sound of Music", "A woman leaves an Austrian convent to become a governess to the children of a Naval officer widower.", List.of(Genre.BIOGRAPHY, Genre.DRAMA, Genre.MUSICAL)));
        movies.add(new Movie("High Noon", "A town Marshal, despite the disagreements of his newlywed bride and the townspeople around him, must face a gang of deadly killers alone at high noon when the gang leader, an outlaw he sent up years ago, arrives on the noon train.", List.of(Genre.DRAMA, Genre.WESTERN)));
        movies.add(new Movie("The Shining", "A family heads to an isolated hotel for the winter where an evil spiritual presence influences the father into violence, while his psychic son sees horrific forebodings from both past and future.", List.of(Genre.HORROR, Genre.DRAMA)));
        movies.add(new Movie("Gone Girl", "With his wife's disappearance having become the focus of an intense media circus, a man sees the spotlight turned on him when it's suspected that he may not be innocent.", List.of(Genre.THRILLER, Genre.DRAMA, Genre.MYSTERY)));
        movies.add(new Movie("Saving Private Ryan", "Following the Normandy Landings, a group of U.S. soldiers go behind enemy lines to retrieve a paratrooper whose brothers have been killed in action.", List.of(Genre.WAR, Genre.DRAMA)));



        return movies;
    }
}