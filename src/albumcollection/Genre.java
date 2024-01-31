package albumcollection;

public enum Genre {
    POP, COUNTRY, CLASSICAL, JAZZ, UNKNOWN;

    @Override
    public String toString() {

        //DO we need to ovverride this - idk if this is right for this enum class

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
