package tuition;

/**
 * Student class defines the basic data for a student.
 * Parent class of Resident and NonResident students.
 * @author Wesam Saleh
 */

public class Student {
    private Profile profile;
    private int creditHours;

    /**
     * Constructor for student class
     * @param profile - the profile of the student including their name and major
     */
    public Student(Profile profile, int creditHours){
        this.profile = profile;
        this.creditHours = creditHours;
    }

    /**
     * Getter for profile of this student
     * @return the profile of this student
     */
    public Profile getProfile() {
        return profile;
    }

    /**
     * Do nothing method for calculating the tuition due
     */
    public void tuitionDue() {

    }
}
