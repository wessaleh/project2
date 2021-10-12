package unitTesting;

/**
 * Test for Date class
 * @author Wesam Saleh, Najibullah Assadullah
 */

import tuition.International;
import tuition.Profile;
import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    @org.junit.jupiter.api.Test
    void tuitionDue(){
        International internationalStudyAbroad = new International(new Profile("I study abroad"), "CS"), 12, true);
        International internationalNotStudyAbroad = new International(new Profile("I don't study abroad"), "CS"), 14, false);
        International internationalNotStudyAbroad2 = new International(new Profile("Over 16 credits"), "CS"), 17, false);

        internationalStudyAbroad.tuitionDue();
        internationalNotStudyAbroad.tuitionDue();
        internationalNotStudyAbroad2.tuitionDue();

        assertTrue(5918 == internationalStudyAbroad.tuition);
        assertTrue(35655 == internationalNotStudyAbroad.tuition);
        assertTrue(36631 == internationalNotStudyAbroad2.tuition);

    }
}