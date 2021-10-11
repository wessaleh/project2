package tuition;

/**
 * TriState class defines the basic data for a student living in the tri state area.
 * Subclass of NonResident
 * @author Wesam Saleh, Najibullah Assadullah
 */
public class TriState extends NonResident{

    private static int NY_DISCOUNT = 4000;
    private static int CT_DISCOUNT = 5000;

    String state;

    public TriState(Profile profile, int creditHours, String stateCode){
        super(profile, creditHours);
        this.state = stateCode;
    }

    @Override
    /**
     * calculates and sets the tuition due for this student
     */
    public void tuitionDue(){
        double tuition = 0.0;
        super.tuitionDue();

        if (this.state.equals("CT") && this.creditHours >= PART_TIME_CREDIT_LIMIT){
            tuition -= CT_DISCOUNT;
        }
        else if (this.state.equals("NY") && this.creditHours >= PART_TIME_CREDIT_LIMIT){
            tuition -= NY_DISCOUNT;
        }

        this.tuition = tuition;
    }

    @Override
    /**
     * Converts a TriState student's info to a string
     * @return a string containing a TriState student's information
     */
    public String toString(){
        if (this.state.equals("NY")){
            return super.toString() + "(tri-state):NY";
        }
        else{
            return super.toString() + "(tri-state):CT";
        }
    }
}
