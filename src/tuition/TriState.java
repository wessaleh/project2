package tuition;

public class TriState extends NonResident{
    String state;

    public TriState(Profile profile, int creditHours, String stateCode){
        super(profile, creditHours);
        this.state = stateCode;
    }

    @Override
    /**
     * calculates and returns the tuition due for this student
     */
    public void tuitionDue(){

    }
}
