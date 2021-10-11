package tuition;

/**
 * International class defines the basic data for an International student.
 * Subclass of NonResident
 * @author Wesam Saleh, Najibullah Assadullah
 */
public class International extends NonResident{

    private static int INTERNATIONAL_ADDITIONAL_FEE = 2650;

    boolean studyingAbroad;

    public International(Profile profile, int creditHours, boolean studyingAbroad) {
        super(profile, creditHours);
        this.studyingAbroad = studyingAbroad;
    }

    /**
     * sets studying abroad status
     * @param studyingAbroad - a boolean indicating if the student is studying abroad or not
     */
    public void setStudyingAbroad(boolean studyingAbroad){
        this.studyingAbroad = studyingAbroad;
    }

    @Override
    /**
     * calculates and sets the tuition due for this student
     */
    public void tuitionDue(){
        double tuition = 0.0;
        super.tuitionDue();
        if (studyingAbroad){ // Tuition if Studying Abroad
            tuition = FULL_TIME_UNIV_FEE + INTERNATIONAL_ADDITIONAL_FEE;
        }
        else{
            this.tuition += =INTERNATIONAL_ADDITIONAL_FEE;
        }
    }

    @Override
    /**
     * Converts an International student's info to a string
     * @return a string containing a TriState student's information
     */
    public String toString(){
        if (studyingAbroad){
            return super.toString() + ":international:studying abroad"
        }
        else{
            return super.toString() + ":international";
        }
    }
}
