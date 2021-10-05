package unitTesting;

import tuition.Date;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    @org.junit.jupiter.api.Test
    void isValid() {
        Date wrongDay = new Date("12/00/2000");
        Date wrongDay2 = new Date("12/32/2000");
        Date wrongMonth = new Date("00/27/2000");
        Date wrongMonth2 = new Date("13/27/2000");
        Date futureDate = new Date("12/27/2021");
        Date notLeapYear = new Date("2/29/2001");

        Date leapYear = new Date("2/29/2004");
        Date correctDate = new Date("12/27/2000");
        Date todaysDate = new Date();
        Date todaysDate2 = new Date();

        assertFalse(wrongDay.isValid());
        assertFalse(wrongDay2.isValid());
        assertFalse(wrongMonth.isValid());
        assertFalse(wrongMonth2.isValid());
        assertFalse(futureDate.isValid());
        assertFalse(notLeapYear.isValid());


        assertTrue(leapYear.isValid());
        assertTrue(correctDate.isValid());
        assertTrue(todaysDate.isValid());
        assertTrue(todaysDate2.isValid());
    }
}