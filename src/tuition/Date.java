package tuition;

import java.util.Calendar;
import java.util.StringTokenizer;

/**
 * This class stores all information for a date of the form "mm/dd/yy".
 * It can also check if the date is valid per certain specifications.
 * @author Wesam Saleh, Najibullah Assadullah
 */

public class Date implements Comparable<Date>{
    private final int year;
    private final int month;
    private final int day;

    private static final int QUADRENNIAL = 4;
    private static final int CENTENNIAL = 100;
    private static final int QUARTER_CENTENNIAL = 400;
    private static final int MINIMUM_DAY_VALUE = 1;
    private static final int MINIMUM_MONTH_VALUE = 1;
    private static final int NUMBER_OF_MONTHS_IN_A_YEAR = 12;
    private static final String[] MONTHS = new String[] {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep",
            "Oct", "Nov", "Dec"};
    private static final int[] DAYS_OF_MONTH = new int[] {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static final int[] DAYS_OF_MONTH_IN_LEAP_YEAR = new int[] {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    /**
     * take "mm/dd/yyyy" and create a Date object
     * @param date - the date in string form
     */
    public Date(String date) {
        StringTokenizer st = new StringTokenizer(date, "/");
        this.month = Integer.parseInt(st.nextToken());
        this.day = Integer.parseInt(st.nextToken());
        this.year = Integer.parseInt(st.nextToken());
    }

    /**
     * Creates an object with today's date (see Calendar class)
     * Uses string tokenizer to format the date received from Calendar class
     */
    public Date() {
        String date = Calendar.getInstance().getTime().toString();
        StringTokenizer dateTokens = new StringTokenizer(date); // parsing the date which is in a particular format

        dateTokens.nextToken();
        this.month = Integer.parseInt(convertMonthCodeToNumber(dateTokens.nextToken()));
        this.day = Integer.parseInt(dateTokens.nextToken());
        dateTokens.nextToken();
        dateTokens.nextToken();
        this.year = Integer.parseInt(dateTokens.nextToken());
    }

    /**
     * Takes a 3 letter month and converts it to the month's numerical value
     * @param month - the 3 letter month
     * @return - the numerical value of the month as a String
     */
    private static String convertMonthCodeToNumber(String month){
        String monthNumber = "";

        for(int i = 0; i < MONTHS.length; i++){
            if(month.equals(MONTHS[i])){
                monthNumber = (i+1)/10 + "" + (i+1)%10; // to ensure single digits have a 0 in front
            }
        }

        return monthNumber;
    }

    /**
     * Check to see if the current date is valid
     * Dates before 1980 or beyond the current date are invalid
     * Days of the month must also be valid (including leap years)
     * @return - true if the date is valid, false if not
     */
    public boolean isValid() {
        Date currentDate = new Date();

        // validating that the date did already occur
        if(this.compareTo(currentDate) > 0){
            return false;
        }

        // validating year
        if(this.year != 2021){
            return false;
        }

        // validating month
        if(this.month < MINIMUM_MONTH_VALUE || this.month > NUMBER_OF_MONTHS_IN_A_YEAR)
            return false;

        // validating day
        int monthIndex = month-1;
        int numDaysInThisMonth = isLeapYear(this.year) ? DAYS_OF_MONTH_IN_LEAP_YEAR[monthIndex] :
                DAYS_OF_MONTH[monthIndex];

        return this.day >= MINIMUM_DAY_VALUE && this.day <= numDaysInThisMonth;
    }

    /**
     * Checks if a given year is a leap year
     * @param year - the year
     * @return - true if the year is a leap year, false if not
     */
    private static boolean isLeapYear(int year) {
        boolean isDivisibleByFour = year % QUADRENNIAL == 0;
        boolean isDivisibleBy100 = year % CENTENNIAL == 0;
        boolean isDivisibleBy400 = year % QUARTER_CENTENNIAL == 0;

        if(isDivisibleByFour){
            if(isDivisibleBy100){
                return isDivisibleBy400;
            }
            return true;
        }
        return false;
    }

    /**
     * Compares a given date to this date
     * @param date - the date to compare
     * @return a numerical value indicating the comparison
     */
    @Override
    public int compareTo(Date date) {
        if(this.year - date.year != 0){ // check year first
            return this.year - date.year;
        }else if(this.month - date.month != 0){ // then check month
            return this.month - date.month;
        }else if(this.day - date.day != 0){ // then check day
            return this.day - date.day;
        }else {
            return 0; // otherwise, the two dates are equal
        }
    }

    /**
     * Converts this date to a string
     * @return string representation of the date
     */
    @Override
    public String toString() {
        return this.month + "/" + this.day + "/" + this.year;
    }
}
