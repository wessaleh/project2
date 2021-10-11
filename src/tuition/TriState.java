package tuition;

/**
 * TriState class defines the basic data for a student living in the tri state area.
 * Subclass of NonResident
 * @author Wesam Saleh, Najibullah Assadullah
 */
public class TriState extends NonResident{

    private static final int NY_DISCOUNT = 4000;
    private static final int CT_DISCOUNT = 5000;

    private final String state;

    /**
     * Constructor for tristate class
     * @param profile - the profile of the student
     * @param creditHours - number of credits the student is taking
     * @param stateCode - the student's state code (NY or CT)
     */
    public TriState(Profile profile, int creditHours, String stateCode){
        super(profile, creditHours);
        this.state = stateCode;
    }

    /**
     * calculates and sets the tuition due for this student
     */
    @Override
    public void tuitionDue(){
        super.tuitionDue();

        if (this.state.equals("CT") && this.creditHours >= PART_TIME_CREDIT_LIMIT){
            this.tuition -= CT_DISCOUNT;
        }
        else if (this.state.equals("NY") && this.creditHours >= PART_TIME_CREDIT_LIMIT){
            this.tuition -= NY_DISCOUNT;
        }
    }

    /**
     * Converts a TriState student's info to a string
     * @return a string containing a TriState student's information
     */
    @Override
    public String toString(){
        if (this.state.equals("NY")){
            return super.toString() + "(tri-state):NY";
        }
        else{
            return super.toString() + "(tri-state):CT";
        }
    }
}
