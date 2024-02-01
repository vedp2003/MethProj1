package albumcollection;

import java.util.Calendar;
import java.util.StringTokenizer;

/**
 * This class creates and defines the properties of a Date object
 * @author Ved Patel, Vivek Manthri
 */
public class Date implements Comparable<Date> {
    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;
    public static final int APR = 4;
    public static final int JUN = 6;
    public static final int SEP = 9;
    public static final int NOV = 11;
    public static final int FEB = 2;
    public static final int LONGMONTH = 31;
    public static final int SHORTMONTH = 30;
    public static final int LEAPFEB = 29;
    public static final int REGFEB = 28;
    public static final int MONTHS = 12;

    private int year;
    private int month;
    private int day;

    //ASK ABOUT order of these, do public static final come before private ints


    /**
     * Default constructor/no-argument constructor
     */
    public Date() {

        //ASK IF WE EVEN NEED THIS default constructor
        this.year = 0;
        this.month = 0;
        this.day = 0;
    }

    /**
     * Parameterized constructor requires 1 parameter to create a Date object
     * @param date a string of a date in the format mm/dd/yyyy
     */
    public Date(String date) {
        StringTokenizer stringTokenizer = new StringTokenizer(date);
        this.month = Integer.parseInt(stringTokenizer.nextToken("/"));
        this.day = Integer.parseInt(stringTokenizer.nextToken("/"));
        this.year = Integer.parseInt(stringTokenizer.nextToken());
    }

    /**
     * Checks if the given year is a leap year (has 29 days in February)
     * @param yr the year to check
     * @return true if the given year is a leap year; false otherwise
     */
    public boolean isLeap(int yr){

        //SHOULD THIS method be private boolean since its a helper method??

        /*
        if(yr % QUADRENNIAL != 0) return false;
        if(yr % CENTENNIAL != 0) return true;
        else {
            if(yr % QUATERCENTENNIAL == 0) return true;
        }
        return false;
         */

        return (yr % QUADRENNIAL == 0 && (yr % CENTENNIAL != 0 || yr % QUATERCENTENNIAL == 0));
    }

    /**
     * Checks if a date is a valid calendar date
     * @return true if the date is valid; false otherwise
     */
    public boolean isValid() {

        //CHANGE THIS LOGIC UP
        //ALSO ask if isValid should include isTodayOrFutureDate and isBefore1900 inside it
        // (such as when we have to test isValid in the testbed main()

        if(this.month < 1 || this.month > MONTHS) return false;
        if(this.day < 1 || this.day > LONGMONTH) return false;
        if(this.year < 0) return false;
        if(this.month == APR || this.month == JUN
                || this.month == SEP || this.month == NOV){
            if(this.day > SHORTMONTH) return false;
        } else if(this.month == FEB){
            if (isLeap(this.year)) {
                if(this.day > LEAPFEB) return false;
            } else {
                if(this.day > REGFEB) return false;
            }
        }
        return true;
    }

    /**
     * Determines if the date is either today or in the future compared to the current date
     * @return true if the date is today or in the future; false otherwise
     */
    public boolean isTodayOrFutureDate() {
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        int currentMonth = cal.get(Calendar.MONTH) + 1;
        int currentDay = cal.get(Calendar.DAY_OF_MONTH);

        return (year > currentYear) || (year == currentYear && month > currentMonth) ||
                (year == currentYear && month == currentMonth && day > currentDay) ||
                    ((year == currentYear && month == currentMonth && day == currentDay));

    }

    /**
     * Determines if the date is before the year 1900
     * @return true if the date is before 1900; false otherwise
     */
    public boolean isBefore1900() {
        return year < 1900;
    }

    /**
     * Compare two Artist objects based on the nam
     * @param o the date object to be compared
     * @return a negative integer, zero, or a positive integer depending on if this date is less than, equal to, or greater than the specified date
     */
    @Override
    public int compareTo(Date o) {
        if (this.year != o.year)
            return this.year - o.year;
        else {
            if (this.month != o.month)
                return this.month - o.month;
            else {
                if(this.day != o.day)
                    return this.day - o.day;
            }
        }
        return 0;
    }

    /**
     * Return a textual representation of a Date object
     * @return a string of the format mm/dd/yyyy
     */
    @Override
    public String toString() {
        return this.month + "/" + this.day + "/" + this.year;
    }

    /**
     * Testbed main() for Date class. Tests the isValid() method
     * @param args the command line arguments containing dates to be tested
     */
    public static void main(String[] args) {

        //FINISH the isValid test cases - not done yet
        //Date class – design five invalid and two valid test cases for testing the isValid() method
        String s = args[0];
        Date date = new Date(s);
        System.out.println(date.isValid());
    }
}
