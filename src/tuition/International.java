package tuition;

/**
 * International class defines the basic data for an International student.
 * Subclass of NonResident
 * @author Wesam Saleh, Najibullah Assadullah
 */
public class International extends NonResident{

    private static final int INTERNATIONAL_ADDITIONAL_FEE = 2650;
    private static final int MAX_CREDITS_STUDYING_ABROAD = 12;

    private boolean studyingAbroad;

    public International(Profile profile, int creditHours, boolean studyingAbroad) {
        super(profile, creditHours);
        this.studyingAbroad = studyingAbroad;
    }

    /**
     * sets studying abroad status
     */
    public void setStudyingAbroad(){
        this.studyingAbroad = true;
        this.payments = 0;
        this.lastPaymentDate = null;
        if(this.creditHours > MAX_CREDITS_STUDYING_ABROAD){
            this.creditHours = MAX_CREDITS_STUDYING_ABROAD;
        }

        this.tuitionDue();
    }

    /**
     * calculates and sets the tuition due for this student
     */
    @Override
    public void tuitionDue(){
        super.tuitionDue();
        if (studyingAbroad){ // Tuition if Studying Abroad
            this.tuition = FULL_TIME_UNIV_FEE + INTERNATIONAL_ADDITIONAL_FEE;
        }
        else{
            this.tuition += INTERNATIONAL_ADDITIONAL_FEE;
        }
    }

    /**
     * Converts an International student's info to a string
     * @return a string containing a TriState student's information
     */
    @Override
    public String toString(){
        if (studyingAbroad){
            return super.toString() + ":international:study abroad";
        }
        else{
            return super.toString() + ":international";
        }
    }
}
