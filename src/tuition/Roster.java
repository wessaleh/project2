package tuition;

/**
 * Roster class keeps track of a list of students
 * Clients can add, remove, change students and their status
 * @author Wesam Saleh, Najibullah Assadullah
 */

public class Roster {
    private final int INVALID = -1;
    private Student[] roster;
    private int size; //keep track of the number of students in the roster

    /**
     * Constructor for a student roster. Initial capacity is 4.
     */
    public Roster() {
        int initialCapacity = 4;
        roster = new Student[initialCapacity];
        size = 0;
    }

    /**
     * Finds a student in the roster
     * @param student - the student to be found
     * @return - the index of the student in the roster if found
     */
    private int find(Student student) {
        Profile profileToFind = student.getProfile();

        // traversing roster to find student
        for(int i = 0; i < size; i++) {
            Profile currentStudentProfile = roster[i].getProfile();

            // student found
            if(currentStudentProfile.equals(profileToFind)) {
                return i;
            }
        }

        return INVALID; //student was not found
    }

    /**
     * Increases the student roster by 4
     */
    private void grow() {
        int growthRate = 4;
        Student[] newRoster = new Student[roster.length + growthRate]; // growing roster

        // copying students to new roster
        for(int i = 0; i < size; i++) {
            newRoster[i] = roster[i];
        }

        this.roster = newRoster;
    }

    /**
     * Cleans up any gaps in the roster caused by removing a student
     */
    private void cleanUp() {
        Student[] newRoster = new Student[roster.length]; // growing roster

        // copying students to new roster
        int newRosterIndex = 0;
        for(Student student: roster) {
            if(student == null){
                continue;
            }

            newRoster[newRosterIndex] = student;
            newRosterIndex++;
        }

        this.roster = newRoster;
    }

    /**
     * Sorts the roster by names
     */
    private void sortByNames() {
        int lesserNameIndex;

        // selection sort
        for(int i = 0; i < size - 1; i++){
            lesserNameIndex = i;

            for(int j = i+1; j < size; j++){
                String currentStudentName = this.roster[j].getProfile().getName();
                String lesserStudentName = this.roster[lesserNameIndex].getProfile().getName();

                if(currentStudentName.compareTo(lesserStudentName) < 0){
                    lesserNameIndex = j;
                }
            }

            // swapping two albums to put them in order
            Student temp = this.roster[i];
            this.roster[i] = this.roster[lesserNameIndex];
            this.roster[lesserNameIndex] = temp;
        }
    }

    /**
     * Sorts the roster by payment dates
     */
    private Student[] sortByPaymentDates() {
        Student[] sortedRoster = new Student[size];

        // Copying only students who have made payments
        int rosterIndex = 0;
        for(int i = 0; i < size; i++){
            if(this.roster[i] == null || this.roster[i].lastPaymentDate == null){
                continue;
            }

            sortedRoster[rosterIndex] = this.roster[i];
            rosterIndex++;
        }

        int earlierPaymentDateIndex;

        // selection sort
        for(int i = 0; i < size - 1; i++){
            if(sortedRoster[i] == null){
                break;
            }

            earlierPaymentDateIndex = i;

            for(int j = i+1; j < size; j++){
                if(sortedRoster[j] == null){
                    break;
                }

                Date currentStudentPaymentDate = sortedRoster[j].lastPaymentDate;
                Date earlierStudentPaymentDate = sortedRoster[earlierPaymentDateIndex].lastPaymentDate;

                if(currentStudentPaymentDate.compareTo(earlierStudentPaymentDate) < 0){
                    earlierPaymentDateIndex = j;
                }
            }

            // swapping two albums to put them in order
            Student temp = sortedRoster[i];
            sortedRoster[i] = sortedRoster[earlierPaymentDateIndex];
            sortedRoster[earlierPaymentDateIndex] = temp;
        }

        return sortedRoster;
    }

    /**
     * Adds a student to the roster
     * @param student - the student to add
     * @return true if the student was added, false if not
     */
    public boolean add(Student student) {
        boolean rosterHasStudent = this.find(student) != INVALID;

        // if student is already in roster, they should not be added
        if(rosterHasStudent){
            return false;
        }

        // if not enough space, grow
        if(size >= roster.length){
            this.grow();
        }

        // adding new student
        roster[size] = student;
        size++;

        return true;
    }

    /**
     * Removes a student from the roster
     * @param student - the student to remove
     * @return true if the student was removed, false if not
     */
    public boolean remove(Student student) {
        int studentIndex = this.find(student);
        boolean isNotInRoster = studentIndex == INVALID;

        // should not remove a student that is not in the roster
        if(isNotInRoster){
            return false;
        }

        // removing the student;
        roster[studentIndex] = null;
        size--;

        //cleaning up roster
        this.cleanUp();

        return true;
    }

    /**
     * Calculates tuition for all students
     */
    public void calculateTuitionForAllStudents() {
        for(int i = 0; i < size; i++){
            this.roster[i].tuitionDue();
        }
    }

    /**
     * Makes a payment for the given student
     * @param student - the student to make a payment for
     * @return true if payment is too much, false if not
     */
    public boolean makeStudentPayment(Student student, double paymentAmount, Date paymentDate) {
        int studentIndex = this.find(student);
        Student studentInRoster = this.roster[studentIndex];

        if(paymentAmount > studentInRoster.getBalance()){
            return false;
        }

        studentInRoster.makePayment(paymentAmount, paymentDate);
        return true;
    }

    /**
     * Sets the study abroad status of a student
     * @param student - the student to set status
     * @return true if student was found, false if not
     */
    public boolean setStudyAbroadStatus(Student student) {
        int studentIndex = this.find(student);

        if(studentIndex == INVALID){
            return false;
        }

        if(this.roster[studentIndex] instanceof International){
            ((International) this.roster[studentIndex]).setStudyingAbroad();
            return true;
        }

        return false;
    }

    /**
     * Sets the financial aid amount of a student
     * @param student - the student to set
     * @param finAidAmount - the financial aid amount to set
     * @return message indicating whether the financial aid was given
     */
    public String setFinancialAid(Student student, double finAidAmount) {
        int studentIndex = this.find(student);

        if(studentIndex == INVALID){
            return "Student not in the roster.";
        }else if(!(this.roster[studentIndex] instanceof Resident)){
            return "Not a resident student.";
        }

        int fullTimeCreditHours = 12;

        if(this.roster[studentIndex].creditHours < fullTimeCreditHours){
            return "Part-time student doesn't qualify for the award.";
        }else if(this.roster[studentIndex].finAid > 0){
            return "Awarded once already.";
        }

        this.roster[studentIndex].setFinAid(finAidAmount);
        return "Tuition updated.";
    }

    /**
     * Prints the roster
     */
    public void printRoster() {
        if(this.size == 0) {
            System.out.println("Student roster is empty!");
            return;
        }

        System.out.println("* list of students in the roster **");

        for(int i = 0; i < size; i++){
            System.out.println(this.roster[i].toString());
        }

        System.out.println("* end of roster **");
    }

    /**
     * Prints the roster by names
     */
    public void printRosterByNames() {
        if(this.size == 0) {
            System.out.println("Student roster is empty!");
            return;
        }

        this.sortByNames();

        System.out.println("* list of students ordered by name **");

        for(int i = 0; i < size; i++){
            System.out.println(this.roster[i].toString());
        }

        System.out.println("* end of roster **");
    }

    /**
     * Prints the roster by payment dates
     */
    public void printRosterByPaymentDates() {
        if(this.size == 0) {
            System.out.println("Student roster is empty!");
            return;
        }

        Student[] sortedRoster = this.sortByPaymentDates();

        System.out.println("* list of students made payments ordered by payment date **");

        for(int i = 0; i < size; i++){
            if(sortedRoster[i] == null){
                break;
            }
            System.out.println(sortedRoster[i].toString());
        }

        System.out.println("* end of roster **");
    }
}
