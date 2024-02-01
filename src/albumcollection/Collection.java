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
        for(int i = 0; i < size; i++){
            if(albums[i].equals(album)){
                return i;
            }
        }
        return NOT_FOUND;
    }

    /**
     * Helper method to increase the capacity of the albums list by 4
     */
    private void grow() {
        if(albums[albums.length - 1] != null){
            Album[] growAlbum = new Album[albums.length + GROW_CAPACITY];
            for(int i = 0; i < albums.length; i++) {
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
        if(find(album) != NOT_FOUND){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Adds a new album to the list of albums
     * @param album the album to add
     * @return true if the album is new and is added; false if it already exists
     */
    public boolean add(Album album) {
        if(contains(album)){
            return false;
        }
        if(size == albums.length){
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
        if(albumIndex == NOT_FOUND){
            return false;
        }
        albums[albumIndex] = null;
        for(int i = albumIndex; i < size - 1; i++){
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
        if(albumIndex != NOT_FOUND){
            albums[albumIndex].rate(rating);
            System.out.println("You rate " + rating + " for " + albums[albumIndex].getTitle() + ":" +
                    albums[albumIndex].getReleased() + "(" + albums[albumIndex].getArtist().getName() + ")");
        }
        else{
            System.out.println(album.getTitle() + album.getArtist() + " is not in the collection");

        }

    }

    /**
     * Displays all the albums in the collection sorted by release dates, and then titles
     */
    public void printByDate() {

        if (size == 0) {
            System.out.println("Collection is empty!");
            return;
        }

        //gpt code
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (compareAlbumsByDateAndTitle(albums[j], albums[j + 1]) > 0) {
                    Album temp = albums[j];
                    albums[j] = albums[j + 1];
                    albums[j + 1] = temp;
                }
            }
        }

        System.out.println("* Collection sorted by Released Date/Title *");

        for(int i = 0; i < size; i++) {
            if (albums[i] != null) {
                System.out.println(albums[i]);
            }
        }
        System.out.println("* end of list *");
    }//sort by release date, then title

    private int compareAlbumsByDateAndTitle(Album album1, Album album2) {
        int dateComparison = album1.getReleased().compareTo(album2.getReleased());
        if (dateComparison == 0) {
            int titleComparison = album1.getTitle().compareToIgnoreCase(album2.getTitle());
            if (titleComparison == 0) {
                return album1.getArtist().getName().compareToIgnoreCase(album2.getArtist().getName());
            } else {
                return titleComparison;
            }
        } else {
            return dateComparison;
        }
    }

    /**
     * Displays all the albums in the collection sorted by genres, then artist's names, and then artist's date of births
     */
    public void printByGenre() {

        if (size == 0) {
            System.out.println("Collection is empty!");
            return;
        }

        //Implement sorting algorithm here
        //Sort by genre, then artist's names, and then artist's date of births

        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {

                //if (compareByGenreArtistDOB(albums[j], albums[j + 1]) == 1) {
                if ((albums[j].getGenre().toString().compareToIgnoreCase(albums[j + 1].getGenre().toString()) > 0)
                    || ((albums[j].getGenre().toString().compareToIgnoreCase(albums[j + 1].getGenre().toString()) == 0 &&
                        albums[j].getArtist().compareTo(albums[j + 1].getArtist()) == 1))) {
                    Album temp = albums[j];
                    albums[j] = albums[j + 1];
                    albums[j + 1] = temp;
                }
            }
        }

        System.out.println("* Collection sorted by Genre/Artist ");
        for (int i = 0; i < size; i++) {
            if (albums[i] != null) {
                System.out.println(albums[i]);
            }
        }
        System.out.println(" end of list *");
    }

    private int compareByGenreArtistDOB(Album a1, Album a2) {
        int genreComparison = a1.getGenre().toString().compareTo(a2.getGenre().toString());

        if (genreComparison > 0) return 1;
        if (genreComparison < 0) return -1;

        return a1.getArtist().compareTo(a2.getArtist());
    }

    /**
     * Displays all the albums in the collection sorted by average ratings, and then titles
     */
    public void printByRating() {
        if (size == 0) {
            System.out.println("Collection is empty!");
            return;
        }

        // Sort by average rating, then title
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (albums[j].avgRatings() < albums[j + 1].avgRatings() ||
                        (albums[j].avgRatings() == albums[j + 1].avgRatings() &&
                            albums[j].getTitle().compareToIgnoreCase(albums[j + 1].getTitle()) > 0)) {
                    Album temp = albums[j];
                    albums[j] = albums[j + 1];
                    albums[j + 1] = temp;
                }
            }
        }

        System.out.println("* Collection sorted by Rating/Title *");

        for(int i = 0; i < size; i++) {
            if (albums[i] != null) {
                System.out.println(albums[i]);
            }
        }
        System.out.println("* end of list *");
    }
}
