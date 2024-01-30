package albumcollection;

public class Album {
    private String title;
    private Artist artist;
    private Genre genre;
    private Date released;
    private Rating ratings; //a linked list of ratings

    public Album(String title, Artist artist, Genre genre, Date released) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.released = released;
        this.ratings = null;

    }

    //ASKK - will we be penalized for unused setters and getters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Date getReleased() {
        return released;
    }

    public void setReleased(Date released) {
        this.released = released;
    }

    public Rating getRatings() {
        return ratings;
    }

    public void setRatings(Rating ratings) {
        this.ratings = ratings;
    }

    public void rate(int star) {
        if(ratings == null) {
            ratings = new Rating(star);
        }
        else {
            Rating current = ratings;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(new Rating(star));
        }
    } //add a rating to the linked list
    public double avgRatings() {
        if(ratings == null){
            return 0.0;
        }
        int counter = 0;
        int total = 0;
        Rating current = ratings;
        while(current != null){
            total += current.getStar();
            counter++;
            current = current.getNext();
        }
        double result = 0.0;
        if(counter > 0){
            result = (double) total / counter;
        }
        else {
            result = 0.0;
        }
        return result;
    } //compute the average ratings

    @Override
    public boolean equals(Object obj) {
        //method returns true if the titles and artists of the albums are the same
        //ASK - is this case sensititve
        if(this == obj){
            return true;
        }
        if(obj == null || getClass() != obj.getClass()){
            return false;
        }
        Album album = (Album) obj;
        return title.equalsIgnoreCase(album.title) && artist.equals(album.artist);
    }

    @Override
    public String toString() {
        String ratingsString = "";

        int[] stars = new int[5];
        Rating current = ratings;
        while(current != null){
            stars[current.getStar() - 1]++;
            current = current.getNext();
        }
        for(int i = 0; i < stars.length; i++){
            if(stars[i] > 0){
                ratingsString += "*".repeat(i + 1) + "(" + stars[i] + ")";
            }
        }
        String result;
        if(ratingsString.isEmpty()){
            result = "none";
        }
        else{
            result = ratingsString + "(average rating: " + avgRatings() + ")";
        }
        return "[" + title + "] Released " + released + " [" + artist + "] [" + genre + "] Rating: " + ratingsString;

    }
}
