package albumcollection;

import java.util.Scanner;

/**
 * This is user interface class that processes the input and output of data
 * @author Ved Patel, Vivek Manthri
 */
public class CollectionManager {
    public static final int CMD_NAME_INDEX = 0;
    public static final int TITLE_INDEX = 1;
    public static final int ARTIST_NAME_INDEX = 2;
    public static final int ARTIST_DOB_INDEX = 3;
    public static final int GENRE_INDEX = 4;
    public static final int RATING_INDEX = 4;
    public static final int RELEASE_DATE_INDEX = 5;
    public static final int RATING_MIN = 1;
    public static final int RATING_MAX = 5;
    public static final int ADD_INPUT_MAX = 6;
    public static final int REMOVE_INPUT_MAX = 4;
    public static final int RATE_INPUT_MAX = 5;


    private Collection collection;
    private Scanner scanner;

    /**
     * Default constructor/no-argument constructor
     */
    public CollectionManager(){

        //ASKK - can we inialize this here & can we have Collection /Scanner private
        collection = new Collection();
        scanner = new Scanner(System.in);
    }

    /**
     * Runs the Collection Manager, takes in input commands, and calls the appropriate processing method
     */
    public void run(){
        System.out.println("Collection Manager is up running.");
        String inputStr;
        while(true){
            inputStr = scanner.nextLine().trim();

            while(inputStr.isEmpty()) {
                inputStr = scanner.nextLine();
            }

            if("Q".equals(inputStr)){
                System.out.println("Collection Manager terminated.");
                break;
            }
            processInputs(inputStr);
        }
    }

    /**
     * Helper method to process the input command and delegate to the respective methods
     * @param input the string representing the command terminal input
     */
    private void processInputs(String input){
        String[] strSplit = input.split(",");
        if(strSplit.length == 0){
            return;
        }
        String commandName = strSplit[CMD_NAME_INDEX].trim();
        switch (commandName){
            case "A":
                addAlbum(strSplit);
                break;
            case "D":
                removeAlbum(strSplit);
                break;
            case "R":
                rateAlbum(strSplit);
                break;
            case "PD":
                collection.printByDate();
                break;
            case "PG":
                collection.printByGenre();
                break;
            case "PR":
                collection.printByRating();
                break;
            default:
                System.out.println("Invalid command!");
                break;
        }
    }

    /**
     * Adds an album to the collection and prints out corresponding message
     * Verifies whether the album Dates are valid and the album exists
     * @param parts an array of strings, where each element represents a specific piece of information
     *              from the command line argument
     */
    private void addAlbum(String[] parts) {
        // Example input: A,Fearless,Taylor Swift,12/13/1989,pop,11/8/2008

        //FOR the A, D, and R commands, the only way they are invalid is if the length is less right?
        //DO WE NEED TO trim() everytime - check this

        if (parts.length < ADD_INPUT_MAX) {
            System.out.println("Invalid command!");
            return;
        }
        String title = parts[TITLE_INDEX].trim();
        String artistName = parts[ARTIST_NAME_INDEX].trim();
        Date artistDob = new Date(parts[ARTIST_DOB_INDEX].trim());
        if (!artistDob.isValid() || artistDob.isTodayOrFutureDate() || artistDob.isBefore1900()) {
            System.out.println("Artist DOB: " + artistDob + " is invalid.");
            return;
        }
        Genre genre;
        try {
            genre = Genre.valueOf(parts[GENRE_INDEX].trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            genre = Genre.UNKNOWN;
        }
        Date releaseDate = new Date(parts[RELEASE_DATE_INDEX].trim());
        if (!releaseDate.isValid() || releaseDate.isTodayOrFutureDate() || releaseDate.isBefore1900()) {
            System.out.println("Date Released: " + releaseDate + " is invalid.");
            return;
        }
        Artist newArtist = new Artist(artistName, artistDob);
        Album newAlbum = new Album(title, newArtist, genre, releaseDate);

        if (collection.add(newAlbum)) {
            System.out.println(title + newArtist + " added to the collection.");
        } else {
            System.out.println(title + newArtist + " is already in the collection.");
        }
    }

    /**
     * Removes an album from the collection and prints out corresponding message
     * Verifies whether the album actually exists
     * @param parts an array of strings, where each element represents a specific piece of information
     *              from the command line argument
     */
    private void removeAlbum(String[] parts) {
        // Example input: D,Blue,April,1/11/2015

        if (parts.length < REMOVE_INPUT_MAX) {
            System.out.println("Invalid command!");
            return;
        }
        String title = parts[TITLE_INDEX].trim();
        String artistName = parts[ARTIST_NAME_INDEX].trim();
        Date artistDob = new Date(parts[ARTIST_DOB_INDEX].trim());

        Artist artistToRemove = new Artist(artistName, artistDob);
        Album albumToRemove = new Album(title, artistToRemove);
        if (collection.remove(albumToRemove)) {
            System.out.println(title + artistToRemove + " removed from the collection.");
        } else {
            System.out.println(title + artistToRemove + " is not in the collection");
        }

    }

    /**
     * Rates an album in the collection and prints out corresponding message
     * Verifies whether the album ratings are within the 1-5 range
     * @param parts an array of strings, where each element represents a specific piece of information
     *              from the command line argument
     */
    private void rateAlbum(String[] parts) {
        // Example input: R,Fearless,Taylor Swift,12/13/1989,5
        if (parts.length < RATE_INPUT_MAX) {
            System.out.println("Invalid command!");
            return;
        }
        String title = parts[TITLE_INDEX].trim();
        String artistName = parts[ARTIST_NAME_INDEX].trim();
        Date artistDob = new Date(parts[ARTIST_DOB_INDEX].trim());
        int rating;
        try {
            rating = Integer.parseInt(parts[RATING_INDEX].trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid rating, rating scale is 1 to 5.");
            return;
        }
        if (rating < RATING_MIN || rating > RATING_MAX) {
            System.out.println("Invalid rating, rating scale is 1 to 5.");
            return;
        }
        collection.rate(new Album(title, new Artist(artistName, artistDob)), rating);
    }
}
