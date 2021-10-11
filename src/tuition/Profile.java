package tuition;

/**
 * The profile class defines the data for a student
 * @author Wesam Saleh, Najibullah Assadullah
 */

public class Profile {
    private final String name;
    private final Major major; // 5 majors and 2-characters each: CS, IT, BA, EE, ME

    /**
     * Constructor for Profile of a student
     * @param name - the name of the student
     * @param major - the major of the student
     */
    public Profile(String name, Major major){
        this.name = name;
        this.major = major;
    }

    /**
     * Getter for student's name
     * @return name of the student
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for student's major
     * @return student's major
     */
    public Major getMajor() {
        return major;
    }

    /**
     * Checks if a given object is equal to this profile
     * @param obj - the object to compare
     * @return true if the profiles are equal, false if not
     */
    @Override
    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }

        if(obj instanceof Profile){
            Profile profileToCompare = (Profile) obj;

            boolean hasSameName = profileToCompare.name.equals(this.name);
            boolean hasSameMajor = profileToCompare.major.equals(this.major);

            return hasSameName && hasSameMajor;
        }else{
            return false;
        }
    }

    /**
     * Converts a profile to a string
     * @return String representation of this profile
     */
    @Override
    public String toString(){
        return this.name + ":" + this.major;
    }
}
