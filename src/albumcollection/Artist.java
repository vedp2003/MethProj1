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
     * @return a negative one, zero, or a positive one depending on if this artist is
     * less than, equal to, or greater than the specified artist
     */
    @Override
    public int compareTo(Artist o) {
        if (this.name.compareToIgnoreCase(o.name) > 0) {
            return 1;
        }
        else if (this.name.compareToIgnoreCase(o.name) < 0) {
            return -1;
        }

        if (this.born.compareTo(o.born) == 1) {
            return 1;
        }
        else if (this.born.compareTo(o.born) == -1) {
            return -1;
        }
        return 0;
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
     * Two test cases return -1, two test cases return 1, and one test case returns 0
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Artist artistCheck1 = new Artist("Abraham Lincoln", new Date("11/12/2004")); //name comes before John even when Date is same as the next artist
        Artist artistCheck2 = new Artist("John Wang", new Date("11/12/2004"));
        System.out.println("Test Case 1 comparing " + artistCheck1 + " and " + artistCheck2 + " : " + artistCheck1.compareTo(artistCheck2));

        Artist artistCheck3 = new Artist("Mark Sandberg", new Date("1/26/1990")); //name is same but this date comes before the next artist
        Artist artistCheck4 = new Artist("Mark Sandberg", new Date("8/11/2016"));
        System.out.println("Test Case 2 comparing " + artistCheck3 + " and " + artistCheck4 + " : " + artistCheck3.compareTo(artistCheck4));

        Artist artistCheck5 = new Artist("Vivek Manthri", new Date("7/4/1990")); //name comes after Ved even though when Date comes before the next artist
        Artist artistCheck6 = new Artist("Ved Patel", new Date("10/15/2003"));
        System.out.println("Test Case 3 comparing " + artistCheck5 + " and " + artistCheck6 + " : " + artistCheck5.compareTo(artistCheck6));

        Artist artistCheck7 = new Artist("Zuck Riggy", new Date("12/15/1998")); //name is same but date comes after the next artist
        Artist artistCheck8 = new Artist("Zuck Riggy", new Date("12/4/1998"));
        System.out.println("Test Case 4 comparing " + artistCheck7 + " and " + artistCheck8 + " : " + artistCheck7.compareTo(artistCheck8));

        Artist artistCheck9 = new Artist("Bob Builder", new Date("6/6/2000")); //name and date as same
        Artist artistCheck10 = new Artist("Bob Builder", new Date("6/6/2000"));
        System.out.println("Test Case 5 comparing " + artistCheck9 + " and " + artistCheck10 + " : " + artistCheck9.compareTo(artistCheck10));

    }
}
