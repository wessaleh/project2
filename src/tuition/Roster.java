package tuition;

public class Roster {
    private Student[] roster;
    private int size; //keep track of the number of students in the roster

    /**
     * Constructor for a student roster. Initial capacity is 4.
     */
    public Roster(){
        roster = new Student[4];
        size = 0;
    }

    /**
     * Finds a student in the roster
     * @param student - the student to be found
     * @return - the index of the student in the roster if found
     */
    private int find(Student student) {
        return 0;
    }

    /**
     * Increases the student roster by 4
     */
    private void grow() {

    }

    /**
     * Adds a student to the roster
     * @param student - the student to add
     * @return true if the student was added, false if not
     */
    public boolean add(Student student) {
        return true;
    }

    /**
     * Removes a student from the roster
     * @param student - the student to remove
     * @return true if the student was removed, false if not
     */
    public boolean remove(Student student) {
        return true;
    }
}
