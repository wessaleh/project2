package tuition;

import java.util.InputMismatchException;
import java.lang.NumberFormatException;
import java.util.Locale;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Handles all the user commands entered into the console.
 * @author Wesam Saleh
 */

public class TuitionManager {
    StringTokenizer st;
    static Roster studentRoster;

    /**
     * Constructor for TuitionManager initializes the student roster
     */
    public TuitionManager() {
        studentRoster = new Roster();
    }

    /**
     * Runs a while loop to allow user to keep entering commands
     */
    public void run() {
        System.out.println("Tuition Manager starts running.");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        // allows user to keep inputting commands
        while(!input.equals("Q")){
            executeCommand(input);
            input = scanner.nextLine();
        }

        System.out.println("Tuition Manager terminated.");
    }

    /**
     * Parses the command from the input and executes the given command
     * @param input - the input from the user
     */
    public void executeCommand(String input) {
        st = new StringTokenizer(input, ",");

        String command = st.nextToken();

        // checking which command to use
        switch(command) {
            case "AR" -> addResidentStudent(st);
            case "AN" -> addNonResidentStudent(st);
            case "AT" -> addTriStateStudent(st);
            case "AI" -> addInternationalStudent(st);
            case "R" -> removeStudent(st);
            case "C" -> calculateStudentTuition(st);
            case "T" -> payTuition(st);
            case "S" -> setStudyAbroadStatus(st);
            case "F" -> setFinancialAidAmount(st);
            case "P" -> printRoster();
            case "PN" -> printRosterByNames();
            case "PT" -> printRosterByPaymentDates();
            default -> System.out.println("Command \'" + command + "\' not supported!");
        }
    }

    /**
     * Converts a string code to a major enum
     * @param major - the major to convert
     * @return student's major
     * @throws InputMismatchException - if the major is invalid
     */
    private static Major convertStringToMajorCode(String major) throws InputMismatchException{
        Major studentMajor = switch(major.toUpperCase()){
            case "CS" -> Major.CS;
            case "IT" -> Major.IT;
            case "BA" -> Major.BA;
            case "EE" -> Major.EE;
            case "ME" -> Major.ME;
            default -> throw new InputMismatchException();
        };

        return studentMajor;
    }

    /**
     * Checks to see if the number of credits is valid
     * @param st - the next token (should be number of credits)
     * @return true if credits are valid, false if not
     */
    private static int validateCredits(StringTokenizer st) throws NumberFormatException{
        if(!st.hasMoreTokens()) {
            System.out.println("Credit hours missing.");
            return -1;
        }

        int numCredits = Integer.parseInt(st.nextToken());

        boolean negative = numCredits < 0;
        boolean lessThanMinimum = numCredits < 3;
        boolean greaterThanMaximum = numCredits > 24;

        if(negative) {
            System.out.println("Credit hours cannot be negative.");
            return -1;
        }else if(lessThanMinimum) {
            System.out.println("Minimum credit hours is 3.");
            return -1;
        }else if(greaterThanMaximum) {
            System.out.println("Credit hours exceed the maximum 24.");
            return -1;
        }

        return numCredits;
    }

    /**
     * Checks to see if the number of tokens is valid
     * @param st - the rest of the tokens
     * @return true if the number of tokens is valid, false if not
     */
    private static boolean validateTokens(StringTokenizer st, int requiredTokens) {
        if(st.countTokens() < requiredTokens){
            System.out.println("Missing data in command line.");
            return false;
        }else{
            return true;
        }
    }

    /**
     * Validates the addition of a student
     * @param student - the student to add
     */
    private static void validateAddStudent(Student student) {
        if(studentRoster.add(student))
            System.out.println("Student added.");
        else
            System.out.println("Student is already in the roster.");
    }

    /**
     * Creates the student profile
     * @param st - the rest of the tokens
     * @return the profile of the student
     */
    private static Profile createStudentProfile(StringTokenizer st, int requiredTokens) {
        // validating the number of tokens for adding this student is correct
        if(!validateTokens(st, requiredTokens)) {
            return null;
        }

        // getting student name and major
        String studentName = st.nextToken();
        String majorToken = st.nextToken();
        Major studentMajor;

        // trying to convert major to enum
        try {
            studentMajor = convertStringToMajorCode(majorToken);
        }catch(InputMismatchException e) {
            System.out.println(majorToken + " is not a valid major."); // student major is not a valid major
            return null;
        }

        // creating student profile
        Profile studentProfile = new Profile(studentName, studentMajor);
        return studentProfile;
    }

    /**
     * Gets the state code given the rest of the string tokens
     * @param st - the rest of the tokens
     * @return the state code, if valid
     */
    private static String getStateCode(StringTokenizer st) {
        String stateCode = st.nextToken().toUpperCase();

        if(stateCode.equals("NY") || stateCode.equals("CT")){
            return stateCode;
        }else{
            System.out.println("Not part of the tri-state area.");
            return null;
        }
    }

    /**
     * Gets the study abroad status
     * @param st - the rest of the tokens
     * @return true if student is studying abroad, false if not
     */
    private static boolean getStudyAbroadStatus(StringTokenizer st) {
        String studyAbroad = st.nextToken();
        if(studyAbroad.equals("true"))
            return true;
        else
            return false;
    }

    /**
     * Adds a resident student to the roster
     * @param st - the rest of the tokens
     */
    public void addResidentStudent(StringTokenizer st) {
        Profile studentProfile = createStudentProfile(st, 2);

        // student profile could not be created
        if(studentProfile == null)
            return;

        // trying to get the number of credits
        try{
            int numCredits = validateCredits(st);
            if(numCredits == -1){
                return; // not a valid number of credits
            }

            Resident residentStudent = new Resident(studentProfile, numCredits); // creating student
            validateAddStudent(residentStudent); // adding student to roster
        }catch(NumberFormatException e) {
            System.out.println("Invalid credit hours"); // Credits passed in was not an integer
            return;
        }
    }

    /**
     * Adds a non-resident student to the roster
     * @param st - the rest of the tokens
     */
    public static void addNonResidentStudent(StringTokenizer st) {
        Profile studentProfile = createStudentProfile(st, 2);

        // student profile could not be created
        if(studentProfile == null)
            return;

        // trying to get the number of credits
        try{
            int numCredits = validateCredits(st);
            if(numCredits == -1){
                return; // not a valid number of credits
            }

            NonResident nonResidentStudent = new NonResident(studentProfile, numCredits); // creating student
            validateAddStudent(nonResidentStudent); // adding student to roster
        }catch(NumberFormatException e) {
            System.out.println("Invalid credit hours"); // Credits passed in was not an integer
            return;
        }
    }

    /**
     * Adds a tri-state student to the roster
     * @param st - the rest of the tokens
     */
    public static void addTriStateStudent(StringTokenizer st) {
        Profile studentProfile = createStudentProfile(st, 4);

        // student profile could not be created
        if(studentProfile == null)
            return;

        // trying to get the number of credits
        try{
            int numCredits = validateCredits(st);
            if(numCredits == -1){
                return; // not a valid number of credits
            }

            String stateCode = getStateCode(st);
            if(stateCode == null)
                return;

            TriState triStateStudent = new TriState(studentProfile, numCredits, stateCode); // creating student
            validateAddStudent(triStateStudent); // adding student to roster
        }catch(NumberFormatException e) {
            System.out.println("Invalid credit hours"); // Credits passed in was not an integer
            return;
        }
    }

    public static void addInternationalStudent(StringTokenizer st) {

    }

    public static void removeStudent(StringTokenizer st) {

    }

    public static void calculateStudentTuition(StringTokenizer st) {

    }

    public static void payTuition(StringTokenizer st) {

    }

    public static void setStudyAbroadStatus(StringTokenizer st) {

    }

    public static void setFinancialAidAmount(StringTokenizer st) {

    }

    public static void printRoster() {

    }

    public static void printRosterByNames() {

    }

    public static void printRosterByPaymentDates() {

    }
}
