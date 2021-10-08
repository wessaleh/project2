package tuition;

/**
 *
 */

public class Resident extends Student{
    public Resident(Profile profile, int creditHours) {
        super(profile, creditHours);
    }

    @Override
    /**
     * calculates and returns the student's balance
     */
    public double calcTuition(){
        if (this.creditHours <= 12){
            this.tuition = 404*this.creditHours + 0.8*3268;
        }
        else{
            this.tuition = 12536 + 3268;
        }
    }

    @Override
    /**
     * calculates and returns the tuition due for this student
     */
    // Should this return the tuition due or just change Student.tuition??
    public double tuitionDue(){
        return this.tuition - this.payments;
    }
}
