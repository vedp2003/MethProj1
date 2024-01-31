package albumcollection;

public class Collection {

    private static final int INITIAL_CAPACITY = 4;
    private static final int GROW_CAPACITY = 4;
    private static final int NOT_FOUND = -1;
    private Album[] albums; //list of albums
    private int size; //number of albums in the list

    public Collection(){
        albums = new Album[INITIAL_CAPACITY];
        size = 0;
    }
    private int find(Album album) {
        for(int i = 0; i < size; i++){
            if(albums[i].equals(album) && albums[i].getArtist().getBorn().compareTo(album.getArtist().getBorn()) == 0){
                return i;
            }
        }
        return NOT_FOUND;

        //ASK THISS - Should find be if the whole album is equal or if only TITLE and ARTIST is equal
    }//helper method
    private void grow() {
        if(albums[albums.length - 1] != null){
            Album[] growAlbum = new Album[albums.length + GROW_CAPACITY];
            for(int i = 0; i < albums.length; i++) {
                growAlbum[i] = albums[i];
            }
            albums = growAlbum;
        }

    }//helper method to increase the capacity by 4
    public boolean contains(Album album) {
        if(find(album) != NOT_FOUND){
            return true;
        }
        else{
            return false;
        }
    }
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

    }//false if the album exists
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


    }//false if the album doesn’t exist
    public void rate(Album album, int rating) {
        int albumIndex = NOT_FOUND;

        for(int i = 0; i < size; i++){
            if(albums[i].getTitle().equalsIgnoreCase(album.getTitle()) && albums[i].getArtist().equals(album.getArtist())){
                albumIndex = i;
            }
        }

        if(albumIndex != NOT_FOUND){
            albums[albumIndex].rate(rating);
            System.out.println("You rate " + rating + " for " + albums[albumIndex].getTitle() + ":" +albums[albumIndex].getReleased() + "(" + albums[albumIndex].getArtist().getName() + ")");
        }
        else{
            System.out.println(album.getTitle() + "(" + album.getArtist().getName() + ":" +album.getArtist().getBorn() + ") is not in the collection");

        }

        //ASK THISSS - when the rating album is not found (ex: Blue(April:1/11/2015) is not in the collection) , is 1/11/2015 the DOB or released Date??
    }
    public void printByDate() {
        if(size == 0) {
            System.out.println("Collection is empty!");
            return;
        }

        for(int i = 1; i < size; i++) {
            Album key = albums[i];
            int j = i - 1;

            while(j >= 0 && (albums[j].getReleased().compareTo(key.getReleased()) > 0 ||
                    (albums[j].getReleased().equals(key.getReleased()) && albums[j].getTitle().compareToIgnoreCase(key.getTitle()) > 0))) {
                albums[j + 1] = albums[j];
                j = j - 1;
            }
            albums[j + 1] = key;
        }

        System.out.println("* Collection sorted by Released Date/Title *");

        for(int i = 0; i < size; i++) {
            if (albums[i] != null) {
                System.out.println(albums[i]);
            }
        }
        System.out.println("* end of list *");
    }//sort by release date, then title

    public void printByGenre() {
        if(size == 0) {
            System.out.println("Collection is empty!");
            return;
        }

        for(int i = 1; i < size; i++) {
            Album key = albums[i];
            int j = i - 1;

            while(j >= 0 && (albums[j].getGenre().compareTo(key.getGenre()) > 0 ||
                    (albums[j].getGenre().equals(key.getGenre()) && albums[j].getArtist().getName().compareToIgnoreCase(key.getArtist().getName()) > 0))) {
                albums[j + 1] = albums[j];
                j = j - 1;
            }
            albums[j + 1] = key;
        }

        System.out.println("* Collection sorted by Genre/Artist *");

        for(int i = 0; i < size; i++) {
            if (albums[i] != null) {
                System.out.println(albums[i]);
            }
        }
        System.out.println("* end of list *");
    }//sort by genre, then artist

    public void printByRating() {
        if(size == 0) {
            System.out.println("Collection is empty!");
            return;
        }

        for(int i = 1; i < size; i++) {
            Album key = albums[i];
            int j = i - 1;

            while(j >= 0 && (albums[j].avgRatings() < key.avgRatings() ||
                    (albums[j].avgRatings() == key.avgRatings() && albums[j].getTitle().compareToIgnoreCase(key.getTitle()) > 0))) {
                albums[j + 1] = albums[j];
                j = j - 1;
            }
            albums[j + 1] = key;
        }

        System.out.println("* Collection sorted by Rating/Title *");

        for(int i = 0; i < size; i++) {
            if (albums[i] != null) {
                System.out.println(albums[i]);
            }
        }
        System.out.println("* end of list *");
    }//sort by average rating, then title
}
