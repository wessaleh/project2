package unitTesting;
import tuition.International;
import tuition.Major;
import tuition.Profile;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test for International class
 * @author Wesam Saleh, Najibullah Assadullah
 */

class InternationalTest {

    @org.junit.jupiter.api.Test
    void tuitionDue(){
        International internationalStudyAbroad = new International(new Profile("fake_name", Major.CS), 12, true);
        International internationalNotStudyAbroad = new International(new Profile("fake_name2", Major.CS), 14, false);
        International internationalNotStudyAbroad2 = new International(new Profile("fake_name3", Major.CS), 17, false);

        internationalStudyAbroad.tuitionDue();
        internationalNotStudyAbroad.tuitionDue();
        internationalNotStudyAbroad2.tuitionDue();

        assertEquals(5918, internationalStudyAbroad.tuition);
        assertEquals(35655, internationalNotStudyAbroad.tuition);
        assertEquals(36621, internationalNotStudyAbroad2.tuition);

    }
}