package tuition;

/**
 * NonResident class defines the basic data for a non resident student.
 * Parent class of International and TriState classes.
 * Subclass of Student
 * @author Wesam Saleh, Najibullah Assadullah
 */

public class NonResident extends Student {

    public static int PER_CREDIT_NONRESIDENT_TUITION_RATE = 966;
    public static int FULL_TIME_NONRESIDENT_TUITION = 29737;

    public NonResident(Profile profile, int creditHours) {
        super(profile, creditHours);
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

    @Override
    /**
     * Converts a NonResident student's info to a string
     * @return a string containing a Resident's information
     */
    public String toString(){
        return super.toString() + ":non-resident";
    }
}
