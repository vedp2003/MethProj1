package albumcollection;

/**
 * This class defines a node in a singly linked list that maintains a list of ratings
 * @author Ved Patel, Vivek Manthri
 */
public class Rating {
    private int star;
    private Rating next;

    /**
     * Parameterized constructor requires 1 parameter to create a Rating object
     * @param star the integer rating star value
     */
    public Rating(int star) {
        this.star = star;
    }

    /**
     * A getter method returns the rating star value of the rating
     * @return the rating star value of the rating
     */
    public int getStar() {
        return star;
    }

    /**
     * A getter method returns the next rating in the list of ratings
     * @return the next rating in the list
     */
    public Rating getNext() {
        return next;
    }

    /**
     * Sets the next rating in the list of ratings
     * @param next the rating to be set as the next rating in the list
     */
    public void setNext(Rating next) {
        this.next = next;
    }
}
