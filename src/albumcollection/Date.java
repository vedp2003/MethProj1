package albumcollection;

public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;
    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;
    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public boolean isLeap(){

    }
    public boolean isValid() {

        return false;
    }

    @Override
    public int compareTo(Date o) {
        return 0;
    }
}
