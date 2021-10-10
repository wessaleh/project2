package tuition;

/**
 * Student class defines the basic data for a student.
 * Parent class of Resident and NonResident classes.
 * @author Wesam Saleh, Najibullah Assadullah
 */

public class Student {

    public static double PART_TIME_UNIV_FEE_PERCENTAGE = 0.8;
    public static int FULL_TIME_UNIV_FEE = 3268;
    public static int PART_TIME_CREDIT_LIMIT = 12;
    public static int FREE_CREDIT_LIMIT = 16;

    private Profile profile;
    public int creditHours;
    public double tuition;
    public double payments;
    //public double fees; dont think we need this. What do you think?
    public Date lastPaymentDate;

    /**
     * Constructor for student class
     * @param profile - the profile of the student including their name and major
     * @param creditHours - the amount of credits the student is taking
     */
    public Student(Profile profile, int creditHours){
        this.profile = profile;
        this.creditHours = creditHours;
        this.tuition = 0;
        this.payments = 0;
        this.lastPaymentDate = null;
    }

    /**
     * Getter for profile of this student
     * @return the profile of this student
     */
    public Profile getProfile() {
        return profile;
    }

    /**
     * Do nothing method for calculating the student's tuition prior to any payments being made
     * @return the tuition of this student before making any payments
     */
    public double calcTuition(){
        return 0;
    }

    /**
     * @param paymentAmount - the amount of the payment being made
     */
    public void makePayment(double paymentAmount){
        this.payments += paymentAmount;
    }

    /**
     * Do nothing method for calculating the tuition due
     */
    public void tuitionDue() {

    }
}
