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
        /*
        int albumIndex = NOT_FOUND;

        for(int i = 0; i < size; i++){
            if(albums[i].getTitle().equalsIgnoreCase(album.getTitle()) && albums[i].getArtist().equals(album.getArtist())){
                albumIndex = i;
            }
        }

         */

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

        //Printbydate, genre, and rating dont sort it properly - WE NEED TO FIX THESE 3 methods, everything else works pretty sure

        if (size == 0) {
            System.out.println("Collection is empty!");
            return;
        }
//        //ved-
//        //ASK do we need efficient sorting method - this is like O(n^2)
//        for (int i = 0; i < size - 1; i++) {
//            for (int j = 0; j < size - i - 1; j++) {
//                if (albums[j].getReleased().compareTo(albums[j + 1].getReleased()) > 0 ||
//                        (albums[j].getReleased().equals(albums[j + 1].getReleased()) &&
//                                albums[j].getTitle().compareToIgnoreCase(albums[j + 1].getTitle()) > 0)) {
//                    Album temp = albums[j];
//                    albums[j] = albums[j + 1];
//                    albums[j + 1] = temp;
//                }
//            }
//        }
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

    //gpt-
    private int compareAlbumsByDateAndTitle(Album album1, Album album2) {
        int dateComparison = album1.getReleased().compareTo(album2.getReleased());
        if (dateComparison == 0) {
            // If release dates are the same, compare by title and then by artist's name
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
    //ved-
//    public void printByGenre() {
//        if (size == 0) {
//            System.out.println("Collection is empty!");
//            return;
//        }
//
//        for (int i = 0; i < size - 1; i++) {
//            for (int j = 0; j < size - i - 1; j++) {
//                if (albums[j].getGenre().compareTo(albums[j + 1].getGenre()) > 0 ||
//                        (albums[j].getGenre().equals(albums[j + 1].getGenre()) &&
//                                albums[j].getArtist().getName().compareToIgnoreCase(albums[j + 1].getArtist().getName()) > 0) ||
//                                    (albums[j].getGenre().equals(albums[j + 1].getGenre()) &&
//                                            albums[j].getArtist().getName().equalsIgnoreCase(albums[j + 1].getArtist().getName()) &&
//                                                albums[j].getArtist().getBorn().compareTo(albums[j + 1].getArtist().getBorn()) > 0)) {
//                    Album temp = albums[j];
//                    albums[j] = albums[j + 1];
//                    albums[j + 1] = temp;
//                }
//            }
//        }
//
//        System.out.println("* Collection sorted by Genre/Artist *");
//
//        for(int i = 0; i < size; i++) {
//            if (albums[i] != null) {
//                System.out.println(albums[i]);
//            }
//        }
//        System.out.println("* end of list *");
//    }
    //vivek gpt-
//    public void printByGenre() {
//        if (size == 0) {
//            System.out.println("Collection is empty!");
//            return;
//        }
//
//        for (int i = 0; i < size - 1; i++) {
//            for (int j = 0; j < size - i - 1; j++) {
//                int genreComparison = albums[j].getGenre().compareTo(albums[j + 1].getGenre());
//                int artistNameComparison = albums[j].getArtist().getName().compareToIgnoreCase(albums[j + 1].getArtist().getName());
//
//                // If genres are the same, compare artist names
//                if (genreComparison == 0) {
//                    // If artist names are the same, compare artist dates of birth
//                    int artistDobComparison = albums[j].getArtist().getBorn().compareTo(albums[j + 1].getArtist().getBorn());
//
//                    if (artistNameComparison > 0 || (artistNameComparison == 0 && artistDobComparison > 0)) {
//                        Album temp = albums[j];
//                        albums[j] = albums[j + 1];
//                        albums[j + 1] = temp;
//                    }
//                } else if (genreComparison > 0) {
//                    Album temp = albums[j];
//                    albums[j] = albums[j + 1];
//                    albums[j + 1] = temp;
//                }
//            }
//        }
//
//        System.out.println("* Collection sorted by Genre/Artist *");
//
//        for (int i = 0; i < size; i++) {
//            if (albums[i] != null) {
//                System.out.println(albums[i]);
//            }
//        }
//        System.out.println("* end of list *");
//    }
    //ved gpt-
//    public void printByGenre() {
//        if (size == 0) {
//            System.out.println("Collection is empty!");
//            return;
//        }
//
//        for (int i = 0; i < size - 1; i++) {
//            for (int j = 0; j < size - i - 1; j++) {
//                if (needsSwapByGenre(albums[j], albums[j + 1])) {
//                    Album temp = albums[j];
//                    albums[j] = albums[j + 1];
//                    albums[j + 1] = temp;
//                }
//            }
//        }
//
//        System.out.println("* Collection sorted by Genre/Artist ");
//        for(int i = 0; i < size; i++) {
//            if (albums[i] != null) {
//                System.out.println(albums[i]);
//            }
//        }
//        System.out.println(" end of list *");
//
//    }
//
//    private boolean needsSwapByGenre(Album album1, Album album2) {
//        int genreComparison = album1.getGenre().compareTo(album2.getGenre());
//        if (genreComparison > 0) {
//            return true;
//        } else if (genreComparison < 0) {
//            return false;
//        }
//
//        int artistNameComparison = album1.getArtist().getName().compareToIgnoreCase(album2.getArtist().getName());
//        if (artistNameComparison > 0) {
//            return true;
//        } else if (artistNameComparison < 0) {
//            return false;
//        }
//
//        return album1.getArtist().getBorn().compareTo(album2.getArtist().getBorn()) > 0;
//    }

    //more ved gpt-
    public void printByGenre() {
        if (size == 0) {
            System.out.println("Collection is empty!");
            return;
        }

        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (shouldSwap(albums[j], albums[j + 1])) {
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

    private boolean shouldSwap(Album a1, Album a2) {
        int genreComparison = a1.getGenre().compareTo(a2.getGenre());
        if (genreComparison > 0) return true;
        if (genreComparison < 0) return false;

        int artistComparison = a1.getArtist().compareTo(a2.getArtist());
        return artistComparison > 0;
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
