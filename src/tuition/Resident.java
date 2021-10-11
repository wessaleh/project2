package tuition;

import java.text.DecimalFormat;

/**
 * Resident class defines the basic data for a Resident student.
 * Subclass of Student
 * @author Wesam Saleh, Najibullah Assadullah
 */

public class Resident extends Student{

    private static final int PER_CREDIT_RESIDENT_TUITION_RATE = 404;
    private static final int FULL_TIME_RESIDENT_TUITION = 12536;
    private static final int NUM_DECIMAL_PLACES = 2;
    private static final int NUM_INT_PLACES = 1;

    public Resident(Profile profile, int creditHours) {
        super(profile, creditHours);
    }

    /**
     * calculates the sets due for this student
     */
    @Override
    public void tuitionDue(){
        double tuition;
        if (this.creditHours < PART_TIME_CREDIT_LIMIT){ // Tuition if Student is part-time
            tuition = PER_CREDIT_RESIDENT_TUITION_RATE*this.creditHours + PART_TIME_UNIV_FEE_PERCENTAGE*FULL_TIME_UNIV_FEE;
        }
        else if (this.creditHours > FREE_CREDIT_LIMIT){ // Tuition if Full Time Student is taking above 16 credits
            tuition = FULL_TIME_RESIDENT_TUITION + FULL_TIME_UNIV_FEE +
                    PER_CREDIT_RESIDENT_TUITION_RATE*(this.creditHours - FREE_CREDIT_LIMIT);
        }
        else{ // Tuition if Full time student is taking 12-16 credits
            tuition = FULL_TIME_RESIDENT_TUITION + FULL_TIME_UNIV_FEE;
        }
        this.tuition = tuition;
    }

    /**
     * Converts a Resident student's info to a string
     * @return a string containing a Resident's information
     */
    @Override
    public String toString(){
        DecimalFormat money_Format = new DecimalFormat("###,###.00");
        money_Format.setMinimumFractionDigits(NUM_DECIMAL_PLACES);
        money_Format.setMinimumIntegerDigits(NUM_INT_PLACES);

        return this.finAid == 0 ? super.toString() + ":resident" : super.toString() + ":resident" + ":financial aid " +
                money_Format.format(this.finAid);
    }
}
