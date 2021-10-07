package tuition;

/**
 * Student class defines the basic data for a student.
 * Parent class of Resident and NonResident students.
 * @author Wesam Saleh
 */

public class Student {
    private Profile profile;
    private int creditHours;
    private double tuition;
    private double payments;
    private Date lastPaymentDate;

    /**
     * Constructor for student class
     * @param profile - the profile of the student including their name and major
     */
    public Student(Profile profile, int creditHours){
        this.profile = profile;
        this.creditHours = creditHours;
        this.lastPaymentDate = null;
        this.tuition = 0;
        this.payments = 0;
    }

    /**
     * Sets the tuition of this Student
     * @param tuitionAmount - the amount to set the tuition
     */
    public void setTuition(double tuitionAmount) {
        this.tuition = tuitionAmount;
    }

    /**
     * Makes a payment for this student on the given date
     * @param payment - payment to be made
     * @param paymentDate - date payment was made
     */
    public void makePayment(double payment, Date paymentDate){
        this.payments += payment;
        this.lastPaymentDate = paymentDate;
    }

    /**
     * Getter for profile of this student
     * @return the profile of this student
     */
    public Profile getProfile() {
        return profile;
    }

    /**
     * Do nothing method for calculating the tuition due
     */
    public void tuitionDue() {

    }
}
