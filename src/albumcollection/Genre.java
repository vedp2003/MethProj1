package albumcollection;

/**
 * This enum class lists the different genres for the music albums
 * @author Ved Patel, Vivek Manthri
 */
public enum Genre {
    CLASSICAL, COUNTRY, JAZZ, POP, UNKNOWN;

    /**
     * Returns a user-friendly string representation of the music album genre
     * @return string for the music album genre; IllegalArgumentException error if genre is not recognized
     */
    @Override
    public String toString() {
        switch(this){
            case POP:
                return "Pop";
            case COUNTRY:
                return "Country";
            case CLASSICAL:
                return "Classical";
            case JAZZ:
                return "Jazz";
            case UNKNOWN:
                return "Unknown";
            default:
                throw new IllegalArgumentException();
        }
    }
}
