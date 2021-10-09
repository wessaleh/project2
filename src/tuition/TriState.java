package tuition;

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
     * calculates and returns the student's tuition before any payments
     * @return the tuition of the student before any payments
     */
    public double calcTuition(){
        double tuition = 0.0;

        if (this.creditHours < PART_TIME_CREDIT_LIMIT){ // Tuition if Tri State Student is part time
            tuition = PER_CREDIT_NONRESIDENT_TUITION_RATE*this.creditHours + PART_TIME_UNIV_FEE_PERCENTAGE*FULLTIME_UNIV_FEE;
        }
        else if (this.creditHours > FREE_CREDIT_LIMIT){ // Tuition if Full Time Tri State Student with above 16 credits
            tuition = FULL_TIME_NONRESIDENT_TUITION + FULL_TIME_UNIV_FEE +
                    PER_CREDIT_NONRESIDENT_TUITION_RATE*(this.creditHours - FREE_CREDIT_LIMIT);
        }
        else{ // Tuition if Full Time Tri State Student is taking 12-16 credits
            tuition = FULL_TIME_NONRESIDENT_TUITION + FULL_TIME_UNIV_FEE;
        }

        if (this.state.equals("CT")){
            tuition -= CT_DISCOUNT;
        }
        else if (this.state.equals("NY")){
            tuition -= NY_DISCOUNT;
        }

        return tuition;
    }

    @Override
    /**
     * calculates and sets the tuition due for this student
     */
    public void tuitionDue(){
        this.tuition = this.calcTuition() - this.payments;
    }
}
