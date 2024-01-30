package albumcollection;

import java.util.StringTokenizer;

public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;
    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;
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

    public boolean isLeap(){
        return (year % QUADRENNIAL == 0 && (year % CENTENNIAL != 0 || year % QUATERCENTENNIAL == 0));
    }
    public boolean isValid() {

        return false;
    }
    public boolean isFutureDate() {
        return false;
    }

    public boolean isBefore1900() {
        return false;
    }


    @Override
    public int compareTo(Date o) {
        return 0;
    }

    @Override
    public String toString() {
        return this.month + "/" + this.day + "/" + this.year;
    }

    public static void main(String[] args) {

    }
}
