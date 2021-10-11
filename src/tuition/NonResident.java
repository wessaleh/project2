package tuition;

/**
 * NonResident class defines the basic data for a non resident student.
 * Parent class of International and TriState classes.
 * @author Wesam Saleh, Najibullah Assadullah
 */

public class NonResident extends Student {

    public static int PER_CREDIT_NONRESIDENT_TUITION_RATE = 966;
    public static int FULL_TIME_NONRESIDENT_TUITION = 29737;

    public NonResident(Profile profile, int creditHours) {
        super(profile, creditHours);
        this.tuition = 0;
        this.payments = 0;
        this.lastPaymentDate = null;
    }

    @Override
    /**
     * calculates and sets the tuition due for this student
     */
    public void tuitionDue(){
        double tuition = 0.0;
        if (this.creditHours < PART_TIME_CREDIT_LIMIT){ // Tuition if Non Resident Student is part time
            tuition = PER_CREDIT_NONRESIDENT_TUITION_RATE*this.creditHours + PART_TIME_UNIV_FEE_PERCENTAGE*FULL_TIME_UNIV_FEE;
        }
        else if (this.creditHours > FREE_CREDIT_LIMIT){ // Tuition if Full Time Non-Resident Student with above 16 credits
            tuition = FULL_TIME_NONRESIDENT_TUITION + FULL_TIME_UNIV_FEE +
                    PER_CREDIT_NONRESIDENT_TUITION_RATE*(this.creditHours - FREE_CREDIT_LIMIT);
        }
        else{ // Tuition if Full Time Non-Resident Student is taking 12-16 credits
            tuition = FULL_TIME_NONRESIDENT_TUITION + FULL_TIME_UNIV_FEE;
        }
        this.tuition = tuition;
    }
}
