package tuition;

public class International extends NonResident{
    boolean studyingAbroad;

    public International(Profile profile, int creditHours, boolean studyingAbroad) {
        super(profile, creditHours);
        this.studyingAbroad = studyingAbroad;
    }

    @Override
    /**
     * calculates and returns the tuition due for this student
     */
    public void tuitionDue(){

    }
}
