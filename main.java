import java.text.SimpleDateFormat;
import java.util.*;

class Date implements Comparable<Date> {
    private int day, month, year;

    public Date(int day, int month, int year) {
        if (isValidDate(day, month, year)) {
            this.day = day;
            this.month = month;
            this.year = year;
        } else {
            throw new IllegalArgumentException("Invalid date!");
        }
    }

    public boolean isValidDate(int day, int month, int year) {
        if (year < 1 || month < 1 || month > 12 || day < 1) return false;
        int[] daysInMonth = {31, isLeapYear(year) ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        return day <= daysInMonth[month - 1];
    }

    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public void updateDate(int d, int m, int y) {
        if (isValidDate(d, m, y)) {
            this.day = d;
            this.month = m;
            this.year = y;
        } else {
            throw new IllegalArgumentException("Invalid date!");
        }
    }

    public String getDayOfWeek() {
        Calendar cal = new GregorianCalendar(year, month - 1, day);
        String[] daysOfWeek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        return daysOfWeek[cal.get(Calendar.DAY_OF_WEEK) - 1];
    }

    public int calculateDifference(Date otherDate) {
        Calendar cal1 = new GregorianCalendar(year, month - 1, day);
        Calendar cal2 = new GregorianCalendar(otherDate.year, otherDate.month - 1, otherDate.day);
        long diff = Math.abs(cal1.getTimeInMillis() - cal2.getTimeInMillis());
        return (int) (diff / (1000 * 60 * 60 * 24));
    }

    public void printDate() {
        System.out.printf("%d %s, %d\n", day, new SimpleDateFormat("MMMM").format(new GregorianCalendar(year, month - 1, day).getTime()), year);
    }

    @Override
    public int compareTo(Date other) {
        if (this.year != other.year) return this.year - other.year;
        if (this.month != other.month) return this.month - other.month;
        return this.day - other.day;
    }
}

public class Main {
    public static void main(String[] args) {
        List<Date> dates = new ArrayList<>();
        dates.add(new Date(15, 3, 2023));
        dates.add(new Date(1, 1, 2022));
        dates.add(new Date(29, 2, 2024)); // Leap year
        dates.add(new Date(10, 7, 2021));
        
        System.out.println("Unsorted Dates:");
        for (Date d : dates) {
            d.printDate();
        }

        Collections.sort(dates);

        System.out.println("\nSorted Dates:");
        for (Date d : dates) {
            d.printDate();
        }

        Date d1 = new Date(12, 4, 2022);
        Date d2 = new Date(25, 12, 2023);
        System.out.println("\nDays Difference: " + d1.calculateDifference(d2));
        System.out.println("Day of the Week: " + d1.getDayOfWeek());
    }
}
