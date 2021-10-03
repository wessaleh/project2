package tuition;

/**
 * The profile class defines the data for a student
 * @author Wesam Saleh
 */

public class Profile {
    private String name;
    private Major major; // 5 majors and 2-characters each: CS, IT, BA, EE, ME

    /**
     * Constructor for Profile of a student
     * @param name - the name of the student
     * @param major - the major of the student
     */
    public Profile(String name, Major major){
        this.name = name;
        this.major = major;
    }
}
