package tuition;

/**
 * Resident class defines the basic data for a Resident student.
 * @author Wesam Saleh, Najibullah Assadullah
 */

public class Resident extends Student{

    private static int PER_CREDIT_RESIDENT_TUITION_RATE = 404;
    private static int FULL_TIME_RESIDENT_TUITION = 12536;

    public Resident(Profile profile, int creditHours) {
        super(profile, creditHours);
        this.tuition = this.calcTuition();
        this.payments = 0;
        this.lastPaymentDate = null;
    }

    @Override
    /**
     * calculates and returns the student's tuition before any payments
     * @return the tuition of the student before any payments
     */
    public double calcTuition(){
        double tuition = 0.0;
        if (this.creditHours < PART_TIME_CREDIT_LIMIT){ // Tuition if Student is part time
            tuition = PER_CREDIT_RESIDENT_TUITION_RATE*this.creditHours + PART_TIME_UNIV_FEE_PERCENTAGE*FULL_TIME_UNIV_FEE;
        }
        else if (this.creditHours > FREE_CREDIT_LIMIT){ // Tuition if Full Time Student is taking above 16 credits
            tuition = FULL_TIME_RESIDENT_TUITION + FULL_TIME_UNIV_FEE +
                    PER_CREDIT_RESIDENT_TUITION_RATE*(this.creditHours - FREE_CREDIT_LIMIT);
        }
        else{ // Tuition if Full time student is taking 12-16 credits
            tuition = FULL_TIME_RESIDENT_TUITION + FULL_TIME_UNIV_FEE;
        }
        return tuition;
    }

    @Override
    /**
     * calculates the sets due for this student
     */
    public void tuitionDue(){
        this.tuition = this.calcTuition() - this.payments;
    }
}
