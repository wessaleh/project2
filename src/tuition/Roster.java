package tuition;

public class Roster {
    private final int invalidIndex = -1;
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

        return -1; //student was not found
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
     * Adds a student to the roster
     * @param student - the student to add
     * @return true if the student was added, false if not
     */
    public boolean add(Student student) {
        boolean rosterHasStudent = this.find(student) != invalidIndex;

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
        boolean isNotInRoster = studentIndex == invalidIndex;

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

}
