package albumcollection;

import java.util.Scanner;

/**
 * This is the user interface class that processes the input and output of data
 *
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

    /**
     * Default constructor/no-argument constructor
     */
    public CollectionManager() {
        collection = new Collection();
    }

    /**
     * Runs the Collection Manager, takes in input commands, and calls the appropriate processing method
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Collection Manager is up running.");
        String inputStr;
        while (true) {
            inputStr = scanner.nextLine().trim();
            while (inputStr.isEmpty()) {
                inputStr = scanner.nextLine();
            }
            if ("Q".equals(inputStr)) {
                System.out.println("Collection Manager terminated.");
                break;
            }
            processInputs(inputStr);
        }
    }

    /**
     * Helper method to process the input command and delegate to the respective methods
     *
     * @param input the string representing the command terminal input
     */
    private void processInputs(String input) {
        String[] strSplit = input.split(",");
        String commandName = strSplit[CMD_NAME_INDEX];
        if ("A".equals(commandName)) {
            addAlbum(strSplit);
        } else if ("D".equals(commandName)) {
            removeAlbum(strSplit);
        } else if ("R".equals(commandName)) {
            rateAlbum(strSplit);
        } else if ("PD".equals(commandName)) {
            if (checkEmptyCollection()) {
                return;
            }
            System.out.println("* Collection sorted by Released Date/Title *");
            collection.printByDate();
            printAlbums();
            System.out.println("* end of list *");
        } else if ("PG".equals(commandName)) {
            if (checkEmptyCollection()) {
                return;
            }
            System.out.println("* Collection sorted by Genre/Artist *");
            collection.printByGenre();
            printAlbums();
            System.out.println("* end of list *");
        } else if ("PR".equals(commandName)) {
            if (checkEmptyCollection()) {
                return;
            }
            System.out.println("* Collection sorted by Rating/Title *");
            collection.printByRating();
            printAlbums();
            System.out.println("* end of list *");
        } else {
            System.out.println("Invalid command!");
        }
    }

    /**
     * Helper method to print the albums after sorting
     */
    private void printAlbums() {
        Album[] albumToPrint = collection.getAlbums();
        for (int i = 0; i < collection.getSize(); i++) {
            if (albumToPrint[i] != null) {
                System.out.println(albumToPrint[i]);
            }
        }
    }

    /**
     * Helper method to check whether the collection of albums is empty or not
     *
     * @return true if the collection of albums is empty; false if not empty
     */
    private boolean checkEmptyCollection() {
        if (collection.getSize() == 0) {
            System.out.println("Collection is empty!");
            return true;
        }
        return false;
    }

    /**
     * Adds an album to the collection and prints out corresponding message.
     * Verifies whether the album Dates are valid and the album exists
     *
     * @param parts an array of strings, where each element represents a specific piece of information
     *              from the command line argument
     */
    private void addAlbum(String[] parts) {
        if (parts.length < ADD_INPUT_MAX) {
            System.out.println("Invalid command!");
            return;
        }
        String title = parts[TITLE_INDEX];
        String artistName = parts[ARTIST_NAME_INDEX];
        Date artistDob = new Date(parts[ARTIST_DOB_INDEX]);
        if (!artistDob.isValid() || artistDob.isTodayOrFutureDate() || artistDob.isBefore1900()) {
            System.out.println("Artist DOB: " + artistDob + " is invalid.");
            return;
        }
        Date releaseDate = new Date(parts[RELEASE_DATE_INDEX]);
        if (!releaseDate.isValid() || releaseDate.isTodayOrFutureDate() || releaseDate.isBefore1900()) {
            System.out.println("Date Released: " + releaseDate + " is invalid.");
            return;
        }
        Genre genre;
        try {
            genre = Genre.valueOf(parts[GENRE_INDEX].toUpperCase());
        } catch (IllegalArgumentException e) {
            genre = Genre.UNKNOWN;
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
     * Removes an album from the collection and prints out corresponding message.
     * Verifies whether the album actually exists
     *
     * @param parts an array of strings, where each element represents a specific piece of information
     *              from the command line argument
     */
    private void removeAlbum(String[] parts) {
        if (parts.length < REMOVE_INPUT_MAX) {
            System.out.println("Invalid command!");
            return;
        }
        String title = parts[TITLE_INDEX];
        String artistName = parts[ARTIST_NAME_INDEX];
        Date artistDob = new Date(parts[ARTIST_DOB_INDEX]);
        Artist artistToRemove = new Artist(artistName, artistDob);
        Album albumToRemove = new Album(title, artistToRemove);

        if (collection.remove(albumToRemove)) {
            System.out.println(title + artistToRemove + " removed from the collection.");
        } else {
            System.out.println(title + artistToRemove + " is not in the collection");
        }
    }

    /**
     * Rates an album in the collection and prints out corresponding message.
     * Verifies whether the album ratings are within the 1-5 range
     *
     * @param parts an array of strings, where each element represents a
     *              specific piece of information from the command line argument
     */
    private void rateAlbum(String[] parts) {
        if (parts.length < RATE_INPUT_MAX) {
            System.out.println("Invalid command!");
            return;
        }
        String title = parts[TITLE_INDEX];
        String artistName = parts[ARTIST_NAME_INDEX];
        Date artistDob = new Date(parts[ARTIST_DOB_INDEX]);
        int rating = Integer.parseInt(parts[RATING_INDEX]);

        if (rating < RATING_MIN || rating > RATING_MAX) {
            System.out.println("Invalid rating, rating scale is 1 to 5.");
            return;
        }
        Artist rateArtist = new Artist(artistName, artistDob);
        Album rateAlbum = new Album(title, rateArtist);

        if (collection.contains(rateAlbum)) {
            collection.rate(rateAlbum, rating);
            System.out.println("You rate " + rating + " for " + rateAlbum.getTitle() + ":" +
                    collection.getReleasedForRating(rateAlbum) + "(" + rateAlbum.getArtist().getName() + ")");
        } else {
            System.out.println(title + rateArtist + " is not in the collection");
        }
    }
}
