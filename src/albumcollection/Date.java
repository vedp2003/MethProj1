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
    public static final int NUMBER_MONTHS = 12;
    public static final int BIG_MONTH_DAYS = 31;
    public static final int SMALL_MONTH_DAYS = 30;
    public static final int LEAP_FEB_DAYS = 29;
    public static final int REG_FEB_DAYS = 28;
    public static final int FEB = 2;
    public static final int APR = 4;
    public static final int JUN = 6;
    public static final int SEP = 9;
    public static final int NOV = 11;

    private int year;
    private int month;
    private int day;

    /**
     * Default constructor/no-argument constructor
     */
    public Date() {
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
     * Helper method that checks if the given year is a leap year
     * @param year the year to check
     * @return true if the given year is a leap year; false otherwise
     */
    private boolean isLeap(int year){
        return (year % QUADRENNIAL == 0 && (year % CENTENNIAL != 0 || year % QUATERCENTENNIAL == 0));
    }

    /**
     * Checks if a date is a valid calendar date
     * @return true if the date is valid; false otherwise
     */
    public boolean isValid() {
        if(this.year < 0 || this.month > NUMBER_MONTHS || this.month < 1 ||
                this.day > BIG_MONTH_DAYS || this.day < 1) {
            return false;
        }
        if (this.month == APR || this.month == JUN || this.month == SEP || this.month == NOV) {
            if (this.day > SMALL_MONTH_DAYS) {
                return false;
            }
        }
        else if (this.month == FEB) {
            if (!isLeap(this.year) && this.day > REG_FEB_DAYS) {
                return false;
            }
            if (isLeap(this.year) && this.day > LEAP_FEB_DAYS) {
                return false;
            }
        }
        return true;
    }

    /**
     * Determines if the date is either today or in the future compared to the current date
     * @return true if the date is today or in the future; false otherwise
     */
    public boolean isTodayOrFutureDate() {
        Calendar calendarInstance = Calendar.getInstance();
        int currentYear = calendarInstance.get(Calendar.YEAR);
        int currentMonth = calendarInstance.get(Calendar.MONTH) + 1; //since Calendar numbers months from 0 to 11
        int currentDay = calendarInstance.get(Calendar.DAY_OF_MONTH);

        return (this.year > currentYear) || (this.year == currentYear && this.month > currentMonth) ||
                (this.year == currentYear && this.month == currentMonth && this.day > currentDay) ||
                    ((this.year == currentYear && this.month == currentMonth && this.day == currentDay));

    }

    /**
     * Determines if the date is before the year 1900
     * @return true if the date is before 1900; false otherwise
     */
    public boolean isBefore1900() {
        return this.year < 1900;
    }

    /**
     * Compare two Date objects based on year, month, and day
     * @param o the date object to be compared
     * @return a negative one, zero, or a positive one depending on if this date is
     * less than, equal to, or greater than the specified date
     */
    @Override
    public int compareTo(Date o) {
        if(this.year != o.year && this.year - o.year > 0) {
            return 1;
        }
        else if (this.year != o.year && this.year - o.year < 0) {
            return -1;
        }

        if(this.month != o.month && this.month - o.month > 0) {
            return 1;
        }
        else if (this.month != o.month && this.month - o.month < 0) {
            return -1;
        }

        if(this.day != o.day && this.day - o.day > 0) {
            return 1;
        }
        else if (this.day != o.day && this.day - o.day < 0) {
            return -1;
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
