package albumcollection;

import java.util.Calendar;
import java.util.StringTokenizer;

public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;
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
    public Date() {
        this.year = 0;
        this.month = 0;
        this.day = 0;
    }
    public Date(String date) {
        StringTokenizer stringTokenizer = new StringTokenizer(date);
        this.month = Integer.parseInt(stringTokenizer.nextToken("/"));
        this.day = Integer.parseInt(stringTokenizer.nextToken("/"));
        this.year = Integer.parseInt(stringTokenizer.nextToken());
    } //takes "mm/dd/yyyy" and create a Date object

    public boolean isLeap(int yr){
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
    public boolean isValid() {
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
    public boolean isFutureDate() {
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        int currentMonth = cal.get(Calendar.MONTH) + 1;
        int currentDay = cal.get(Calendar.DAY_OF_MONTH);

        return (year > currentYear) ||
                (year == currentYear && month > currentMonth) ||
                (year == currentYear && month == currentMonth && day > currentDay);

    } //true if DOB date is today or future

    public boolean isBefore1900() {
        return year < 1900;
    } // true if release date is before 1900


    @Override
    public int compareTo(Date o) {
        if(this.year != o.year) return this.year - o.year;
        else {
            if(this.month != o.month) return this.month - o.month;
            else{
                if(this.day != o.day) return this.day - o.day;
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        return this.month + "/" + this.day + "/" + this.year;
    }

    public static void main(String[] args) {
        String s = args[0];
        Date date = new Date(s);
        System.out.println(date.isValid());
    }
}
