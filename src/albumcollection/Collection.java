package albumcollection;

/**
 * This class defines the ADT Collection; an instance of Collection
 * can hold a list of Album objects
 * @author Ved Patel, Vivek Manthri
 */
public class Collection {
    private static final int INITIAL_CAPACITY = 4;
    private static final int GROW_CAPACITY = 4;
    private static final int NOT_FOUND = -1;
    private Album[] albums; //list of albums
    private int size; //number of albums in the list

    /**
     * Default constructor/no-argument constructor
     */
    public Collection(){
        albums = new Album[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Helper method to find the index of a given album in the list of albums
     * @param album the album to find in the list
     * @return the integer index of the album; -1 if not found in the list
     */
    private int find(Album album) {
        for (int i = 0; i < size; i++) {
            if (albums[i].equals(album)) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    /**
     * Helper method to increase the capacity of the albums list by 4
     */
    private void grow() {
        if (albums[albums.length - 1] != null) {
            Album[] growAlbum = new Album[albums.length + GROW_CAPACITY];
            for (int i = 0; i < albums.length; i++) {
                growAlbum[i] = albums[i];
            }
            albums = growAlbum;
        }
    }

    /**
     * Checks if the list of albums contains a given album
     * @param album the album to check
     * @return true if album is in the list; false otherwise
     */
    public boolean contains(Album album) {
        return find(album) != NOT_FOUND;
    }

    /**
     * Adds a new album to the list of albums
     * @param album the album to add
     * @return true if the album is new and is added; false if it already exists
     */
    public boolean add(Album album) {
        if (contains(album)) {
            return false;
        }
        if (size == albums.length) {
            grow();
        }
        albums[size] = album;
        size++;
        return true;
    }

    /**
     * Removes an album from the list of albums
     * @param album the album to remove
     * @return true if the album is found and removed; false if it doesn't exist
     */
    public boolean remove(Album album) {
        int albumIndex = find(album);
        if (albumIndex == NOT_FOUND) {
            return false;
        }
        albums[albumIndex] = null;
        for (int i = albumIndex; i < size - 1; i++) {
            albums[i] = albums[i + 1];
        }
        size--;
        return true;
    }

    /**
     * Rates an album in the list and prints out corresponding message of the rating for the album
     * @param album the album to rate
     * @param rating the rating to assign
     */
    public void rate(Album album, int rating) {
        int albumIndex = find(album);
        if (albumIndex != NOT_FOUND) {
            albums[albumIndex].rate(rating);
            System.out.println("You rate " + rating + " for " + albums[albumIndex].getTitle() + ":" +
                    albums[albumIndex].getReleased() + "(" + albums[albumIndex].getArtist().getName() + ")");
        }
        else {
            System.out.println(album.getTitle() + album.getArtist() + " is not in the collection");
        }
    }

    /**
     * Displays all the albums in the collection sorted by release dates, and then titles.
     * Uses insertion sort logic to order the albums
     */
    public void printByDate() {
        if (size == 0) {
            System.out.println("Collection is empty!");
            return;
        }
        for (int i = 1; i < size; i++) {
            Album key = albums[i];
            int j = i - 1;
            while (j >= 0 && (
                    (albums[j].getReleased().compareTo(key.getReleased()) == 1)
                            || (albums[j].getReleased().compareTo(key.getReleased()) == 0
                            && albums[j].getTitle().compareToIgnoreCase(key.getTitle()) > 0)
                            || (albums[j].getReleased().compareTo(key.getReleased()) == 0
                            && albums[j].getTitle().compareToIgnoreCase(key.getTitle()) == 0
                            && albums[j].getArtist().getName().compareToIgnoreCase(key.getArtist().getName()) > 0)
            )) {
                albums[j + 1] = albums[j];
                j -= 1;
            }
            albums[j + 1] = key;
        }
        printAlbums("Released Date/Title");
    }


    /**
     * Displays all the albums in the collection sorted by genres, then artist's names,
     * and then artist's date of births.
     * Uses insertion sort logic to order the albums
     */
    public void printByGenre() {
        if (size == 0) {
            System.out.println("Collection is empty!");
            return;
        }
        for (int i = 1; i < size; i++) {
            Album key = albums[i];
            int j = i - 1;
            while (j >= 0 && (
                    (albums[j].getGenre().toString().compareToIgnoreCase(key.getGenre().toString()) > 0)
                            || ((albums[j].getGenre().toString().compareToIgnoreCase(key.getGenre().toString()) == 0)
                            && albums[j].getArtist().compareTo(key.getArtist()) == 1)
            )) {
                albums[j + 1] = albums[j];
                j -= 1;
            }
            albums[j + 1] = key;
        }
        printAlbums("Genre/Artist");
    }

    /**
     * Displays all the albums in the collection sorted by average ratings, and then titles.
     * Uses insertion sort logic to order the albums
     */
    public void printByRating() {
        if (size == 0) {
            System.out.println("Collection is empty!");
            return;
        }
        for (int i = 1; i < size; i++) {
            Album key = albums[i];
            int j = i - 1;
            while (j >= 0 && (
                    albums[j].avgRatings() < key.avgRatings()
                            || (albums[j].avgRatings() == key.avgRatings()
                            && albums[j].getTitle().compareToIgnoreCase(key.getTitle()) > 0)
            )) {
                albums[j + 1] = albums[j];
                j -= 1;
            }
            albums[j + 1] = key;
        }
        printAlbums("Rating/Title");
    }

    /**
     * Helper method to print the albums after sorting
     * @param sortedBy the string representing the criteria used for sorting the albums
     */
    private void printAlbums(String sortedBy) {
        System.out.println("* Collection sorted by " + sortedBy + " *");
        for (int i = 0; i < size; i++) {
            if (albums[i] != null) {
                System.out.println(albums[i]);
            }
        }
        System.out.println("* end of list *");
    }
}
