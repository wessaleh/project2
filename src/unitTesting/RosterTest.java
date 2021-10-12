package unitTesting;
import org.junit.jupiter.api.Test;
import tuition.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test for Roster class
 * @author Wesam Saleh, Najibullah Assadullah
 */

class RosterTest {

    @Test
    void add() {
        Resident resident1 = new Resident(new Profile("Student1", Major.CS), 15);
        Resident resident1Copy = new Resident(new Profile("Student1", Major.CS), 13);

        TriState triState1 = new TriState(new Profile("TriState1", Major.IT), 14, "CT");
        TriState triState2 = new TriState(new Profile("TriState2", Major.ME), 14, "NY");

        International international1 = new International(new Profile("International1", Major.EE), 12, true);
        International international2 = new International(new Profile("International2", Major.EE), 16, false);

        Roster roster = new Roster();

        assertTrue(roster.add(resident1));
        assertFalse(roster.add(resident1Copy));

        assertTrue(roster.add(triState1));
        assertTrue(roster.add(triState2));

        assertTrue(roster.add(international1));
        assertTrue(roster.add(international2));
    }

    @Test
    void remove() {
        Resident resident1 = new Resident(new Profile("Student1", Major.CS), 15);
        TriState triState1 = new TriState(new Profile("TriState1", Major.IT), 14, "CT");
        International international1 = new International(new Profile("International1", Major.EE), 12, true);

        Roster roster = new Roster();

        roster.add(resident1);
        roster.add(triState1);
        roster.add(international1);

        Resident notEnrolled = new Resident(new Profile("fake_name1", Major.CS), 12);

        assertFalse(roster.remove(notEnrolled));
        assertTrue(roster.remove(resident1));
        assertTrue(roster.remove(triState1));
        assertTrue(roster.remove(international1));
        assertFalse(roster.remove(resident1));
    }
}