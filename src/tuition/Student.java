package tuition;

/**
 * Student class defines the basic data for a student.
 * Parent class of Resident and NonResident students.
 * @author Wesam Saleh
 */

public class Student {
    Profile profile;

    /**
     * Constructor for student class
     * @param profile - the profile of the student including their name and major
     */
    public Student(Profile profile){
        this.profile = profile;
    }

    /**
     * Do nothing method for calculating the tuition due
     */
    public void tuitionDue() {

    }
}
