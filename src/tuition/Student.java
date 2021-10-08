package tuition;

/**
 * Student class defines the basic data for a student.
 * Parent class of Resident and NonResident students.
 * @author Wesam Saleh, Najibullah Assadullah
 */

public class Student {
    private Profile profile;
    private int creditHours;
    public double tuition;
    public double payment;
    public double fees;
    public Date lastPaymentDate;

    /**
     * Constructor for student class
     * @param profile - the profile of the student including their name and major
     */
    public Student(Profile profile, int creditHours){
        this.profile = profile;
        this.creditHours = creditHours;
        this.tuition = 0;
        this.payment = 0;
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
     * Do nothing method for calculating the student's balance
     * @return the balance of this student
     */
    public double calcTuition(){

    }

    /**
     * Allows the student to make a payment
     */
    public void makePayment(double paymentAmount){
        this.payment += paymentAmount;
    }

    /**
     * Do nothing method for calculating the tuition due
     */
    public void tuitionDue() {

    }
}
