package unitTesting;

/**
 * Test for Date class
 * @author Wesam Saleh
 */

import tuition.Date;
import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    @org.junit.jupiter.api.Test
    void isValid() {
        Date wrongDay = new Date("12/00/2021");
        Date wrongDay2 = new Date("12/32/2021");
        Date wrongMonth = new Date("00/27/2021");
        Date wrongMonth2 = new Date("13/27/2021");
        Date futureDate = new Date("12/27/2021");
        Date notLeapYear = new Date("2/29/2021");

        Date correctDate = new Date("09/27/2021");
        Date todaysDate = new Date();

        assertFalse(wrongDay.isValid());
        assertFalse(wrongDay2.isValid());
        assertFalse(wrongMonth.isValid());
        assertFalse(wrongMonth2.isValid());
        assertFalse(futureDate.isValid());
        assertFalse(notLeapYear.isValid());

        assertTrue(correctDate.isValid());
        assertTrue(todaysDate.isValid());
    }
}