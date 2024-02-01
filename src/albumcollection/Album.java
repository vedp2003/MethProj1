package albumcollection;

/**
 * This class creates, defines the properties of, and manipulates an Album object
 * Supports adding ratings and computing average ratings for an Album object
 * @author Ved Patel, Vivek Manthri
 */
public class Album {
    public static final int MAX_STAR = 5;

    private String title;
    private Artist artist;
    private Genre genre;
    private Date released;
    private Rating ratings; //a linked list of ratings

    /**
     * Parameterized constructor requires 4 parameters to create an album object
     * @param title the title of the album
     * @param artist the artist of the album
     * @param genre the genre of the album
     * @param released the release date of the album
     */
    public Album(String title, Artist artist, Genre genre, Date released) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.released = released;
    }

    /**
     * Parameterized constructor requires 2 parameters to create an album object
     * @param title the title of the album
     * @param artist the artist of the album
     */
    public Album(String title, Artist artist) {
        this.title = title;
        this.artist = artist;
    }

    /**
     * A getter method returns the title of the album
     * @return the title of the album
     */
    public String getTitle() {
        return title;
    }

    /**
     * A getter method returns the artist of the album
     * @return the artist of the album
     */
    public Artist getArtist() {
        return artist;
    }

    /**
     * A getter method returns the genre of the album
     * @return the genre of the album
     */
    public Genre getGenre() {
        return genre;
    }

    /**
     * A getter method returns the release date of the album
     * @return the release date of the album in mm/dd/yyyy format
     */
    public Date getReleased() {
        return released;
    }

    /**
     * Adds a star rating to the linked list
     * @param star the star rating to add
     */
    public void rate(int star) {
        if (ratings == null) {
            ratings = new Rating(star);
        }
        else {
            Rating current = ratings;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(new Rating(star));
        }
    }

    /**
     * Computes the average ratings of an album
     * @return a double representing the average rating or 0.0 if there are no ratings
     */
    public double avgRatings() {
        if (ratings == null) {
            return 0.00;
        }
        int counter = 0;
        int total = 0;
        Rating current = ratings;
        while (current != null) {
            total += current.getStar();
            counter++;
            current = current.getNext();
        }
        if (counter == 0) {
            return 0.00;
        }
        return (double) total / counter;
    }

    /**
     * Determines if two Album objects are equal based on title and artist
     * @param obj the album object to be compared
     * @return true if the titles and artists of the albums are the same; false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Album) {
            Album album = (Album) obj;
            return this.title.equalsIgnoreCase(album.getTitle()) && this.artist.equals(album.getArtist());
        }
        return false;
    }

    /**
     * Return a textual representation of an Album object in a formatted manner
     * @return a formatted string with Album object parameters, asterisks to
     * display the rating scales, and average rating
     */
    @Override
    public String toString() {
        String ratingsString = "";
        int[] stars = new int[MAX_STAR];
        Rating current = ratings;
        while (current != null) {
            stars[current.getStar() - 1]++;
            current = current.getNext();
        }
        for (int i = 0; i < stars.length; i++) {
                ratingsString += "*".repeat(i + 1) + "(" + stars[i] + ")";
        }
        String result;
        boolean starsZeroChecker = false;
        for (int i = 0; i < MAX_STAR; i++) {
            if(stars[i] != 0){
                starsZeroChecker = true;
            }
        }
        if (!starsZeroChecker) {
            result = "none";
        }
        else {
            result = ratingsString + "(average rating: " + String.format("%.2f", avgRatings()) + ")";

        }
        return "[" + title + "] Released " + released + " [" + artist.getName() + ":" +artist.getBorn() + "] [" + genre + "] Rating: " + result;
    }
}
