package tuition;

import java.text.DecimalFormat;

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
    private static final int NUM_DECIMAL_PLACES = 2;
    private static final int NUM_INT_PLACES = 1;

    private final Profile profile;
    public int creditHours;
    public double tuition;
    public double finAid;
    public double payments;
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
     * Returns the student's balance after payments are made
     * @return the tuition of this student before making any payments
     */
    public double getBalance(){
        return this.tuition - this.payments - this.finAid;
    }

    /**
     * Sets the financial aid amount
     * @param finAidAmount - the financial aid amount
     */
    public void setFinAid(double finAidAmount){
        this.finAid = finAidAmount;
    }

    /**
     * Allows the student to make a payment
     * @param paymentAmount - the amount of the payment being made
     */
    public void makePayment(double paymentAmount, Date paymentDate){
        this.payments += paymentAmount;
        this.lastPaymentDate = paymentDate;
    }

    /**
     * Do nothing method for calculating the tuition due
     */
    public void tuitionDue() {

    }

    /**
     * Converts a Student's info to a string
     * @return a string containing a Student's information
     */
    @Override
    public String toString(){
        String toReturn;
        DecimalFormat money_Format = new DecimalFormat("###,###.00");
        money_Format.setMinimumFractionDigits(NUM_DECIMAL_PLACES);
        money_Format.setMinimumIntegerDigits(NUM_INT_PLACES);

        if (this.lastPaymentDate == null){
            toReturn = this.profile.getName() + ":" + this.profile.getMajor() + ":" + this.creditHours +
                    " credit hours:tuition due:" + money_Format.format(this.getBalance()) + ":total payment:" + money_Format.format(this.payments) +
                    ":last payment date: --/--/--";
        }
        else{
            toReturn = this.profile.getName() + ":" + this.profile.getMajor() + ":" + this.creditHours +
                    " credit hours:tuition due:" + money_Format.format(this.getBalance()) + ":total payment:" + money_Format.format(this.payments) +
                    ":last payment date: " + this.lastPaymentDate.toString();
        }
        return toReturn;

    }
}
