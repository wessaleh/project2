package tuition;

public class International extends NonResident{

    private static int INTERNATIONAL_ADDITIONAL_FEE = 2650;

    boolean studyingAbroad;

    public International(Profile profile, int creditHours, boolean studyingAbroad) {
        super(profile, creditHours);
        this.studyingAbroad = studyingAbroad;
        this.tuition = this.calcTuition();
        this.payments = 0;
        this.lastPaymentDate = null;
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
        if (this.creditHours <= PART_TIME_CREDIT_LIMIT){ // Tuition if Studying Abroad (they can only take a max of 12 credits)
            tuition = FULL_TIME_UNIV_FEE + INTERNATIONAL_ADDITIONAL_FEE;
        }
        else if (this.creditHours > FREE_CREDIT_LIMIT){ // Tuition if International Student is taking above 16 credits
            tuition = FULL_TIME_NONRESIDENT_TUITION + FULL_TIME_UNIV_FEE + INTERNATIONAL_ADDITIONAL_FEE +
                    PER_CREDIT_NONRESIDENT_TUITION_RATE*(this.creditHours - FREE_CREDIT_LIMIT);
        }
        else{ // Tuition if International Student is taking 12 - 16 credits
            tuition = FULL_TIME_NONRESIDENT_TUITION + FULL_TIME_UNIV_FEE + INTERNATIONAL_ADDITIONAL_FEE;
        }
        this.tuition = tuition;
    }
}
