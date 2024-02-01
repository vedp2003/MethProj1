package albumcollection;

/**
 * This class creates and defines the properties of an Artist object
 * @author Ved Patel, Vivek Manthri
 */
public class Artist implements Comparable<Artist> {
    private String name;
    private Date born;

    /**
     * Parameterized constructor requires 2 parameters to create an artist object
     * @param name the name of the artist
     * @param born the date of birth of the artist
     */
    public Artist(String name, Date born) {
        this.name = name;
        this.born = born;
    }

    /**
     * A getter method returns the name of the artist
     * @return the name of the artist
     */
    public String getName() {
        return name;
    }

    /**
     * A getter method returns the date of birth of the artist
     * @return the date of birth of the artist in mm/dd/yyyy format
     */
    public Date getBorn() {
        return born;
    }

    /**
     * Compare two Artist objects based on the name of the artist first, then date of birth
     * @param o the artist object to be compared
     * @return a negative one, zero, or a positive one depending on if this artist is less than, equal to, or greater than the specified artist
     */
    @Override
    public int compareTo(Artist o) {

        /*
        int nameComparison = this.name.compareToIgnoreCase(o.name);
        if(nameComparison != 0) {
            return nameComparison;
        }
        return this.born.compareTo(o.born);

         */



        //DO THESE -1, 1, 0 need to be made into constants to avoid magic numbers??


        int nameComparison = this.name.compareToIgnoreCase(o.name);
        if (nameComparison > 0) {
            return 1;
        }
        else if (nameComparison < 0) {
            return -1;
        }
        else {
            int dateComparison = this.born.compareTo(o.born);
            if (dateComparison > 0) {
                return 1;
            }
            else if (dateComparison < 0) {
                return -1;
            }
            else {
                return 0;
            }
        }

    }

    /**
     * Determines if two Artist objects are equal based on name and date of birth
     * @param obj the artist object to be compared
     * @return true if the names and date of births of the albums are the same; false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Artist) {
            Artist artist = (Artist) obj;
            return this.name.equalsIgnoreCase(artist.name) && this.born.compareTo(artist.born) == 0;
        }
        return false;
    }

    /**
     * Return a textual representation of an Artist object
     * @return a string of the format (Name:DateOfBirth)
     */
    @Override
    public String toString() {
        return "(" + name + ":" + born + ")";
    }

    /**
     * Testbed main() for Date class. Tests the compareTo() method
     * @param args the command line arguments containing dates to be tested
     */
    public static void main(String[] args) {

        //Artist class – design test cases for testing the compareTo() method; two test cases return -1, two test cases
        //return 1, and one test case returns 0

    }
}
