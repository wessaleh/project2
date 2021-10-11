package tuition;

import java.util.InputMismatchException;
import java.lang.NumberFormatException;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Handles all the user commands entered into the console.
 * @author Wesam Saleh
 */

public class TuitionManager {
    private static final int INVALID = -1;
    private static final int MIN_NUM_CREDITS = 3;
    private static final int MAX_NUM_CREDITS = 24;
    private static final int REQUIRED_TOKENS = 2;
    private static final int MIN_NUM_CREDITS_INTERNATIONAL = 12;
    private static final int REQUIRED_TOKENS_TRISTATE = 4;
    private static Roster studentRoster;

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
        StringTokenizer st = new StringTokenizer(input, ",");

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
            default -> System.out.println("Command '" + command + "' not supported!");
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
            return INVALID;
        }

        int numCredits = Integer.parseInt(st.nextToken());

        boolean negative = numCredits < 0;
        boolean lessThanMinimum = numCredits < MIN_NUM_CREDITS;
        boolean greaterThanMaximum = numCredits > MAX_NUM_CREDITS;

        if(negative) {
            System.out.println("Credit hours cannot be negative.");
            return INVALID;
        }else if(lessThanMinimum) {
            System.out.println("Minimum credit hours is 3.");
            return INVALID;
        }else if(greaterThanMaximum) {
            System.out.println("Credit hours exceed the maximum 24.");
            return INVALID;
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
        return studyAbroad.equals("true");
    }

    /**
     * Adds a resident student to the roster
     * @param st - the rest of the tokens
     */
    public void addResidentStudent(StringTokenizer st) {
        Profile studentProfile = createStudentProfile(st, REQUIRED_TOKENS);

        // student profile could not be created
        if(studentProfile == null)
            return;

        // trying to get the number of credits
        try{
            int numCredits = validateCredits(st);
            if(numCredits == INVALID){
                return; // not a valid number of credits
            }

            Resident residentStudent = new Resident(studentProfile, numCredits); // creating student
            validateAddStudent(residentStudent); // adding student to roster
        }catch(NumberFormatException e) {
            System.out.println("Invalid credit hours"); // Credits passed in was not an integer
        }
    }

    /**
     * Adds a non-resident student to the roster
     * @param st - the rest of the tokens
     */
    public void addNonResidentStudent(StringTokenizer st) {
        Profile studentProfile = createStudentProfile(st, REQUIRED_TOKENS);

        // student profile could not be created
        if(studentProfile == null)
            return;

        // trying to get the number of credits
        try{
            int numCredits = validateCredits(st);
            if(numCredits == INVALID){
                return; // not a valid number of credits
            }

            NonResident nonResidentStudent = new NonResident(studentProfile, numCredits); // creating student
            validateAddStudent(nonResidentStudent); // adding student to roster
        }catch(NumberFormatException e) {
            System.out.println("Invalid credit hours"); // Credits passed in was not an integer
        }
    }

    /**
     * Adds a tri-state student to the roster
     * @param st - the rest of the tokens
     */
    public void addTriStateStudent(StringTokenizer st) {
        Profile studentProfile = createStudentProfile(st, REQUIRED_TOKENS_TRISTATE);

        // student profile could not be created
        if(studentProfile == null)
            return;

        // trying to get the number of credits
        try{
            int numCredits = validateCredits(st);
            if(numCredits == INVALID){
                return; // not a valid number of credits
            }

            String stateCode = getStateCode(st);
            if(stateCode == null)
                return;

            TriState triStateStudent = new TriState(studentProfile, numCredits, stateCode); // creating student
            validateAddStudent(triStateStudent); // adding student to roster
        }catch(NumberFormatException e) {
            System.out.println("Invalid credit hours"); // Credits passed in was not an integer
        }
    }

    /**
     * Adds an international student to the roster
     * @param st - the rest of the tokens
     */
    public void addInternationalStudent(StringTokenizer st) {
        Profile studentProfile = createStudentProfile(st, REQUIRED_TOKENS);

        // student profile could not be created
        if(studentProfile == null)
            return;

        // trying to get the number of credits
        try{
            int numCredits = validateCredits(st);

            if(numCredits == INVALID){
                return; // not a valid number of credits
            }

            // international students must take at least 12 credits
            if(numCredits < MIN_NUM_CREDITS_INTERNATIONAL){
                System.out.println("International students must enroll at least 12 credits.");
                return;
            }

            boolean statusAbroad = getStudyAbroadStatus(st);

            International internationalStudent = new International(studentProfile, numCredits, statusAbroad); // creating student
            validateAddStudent(internationalStudent); // adding student to roster
        }catch(NumberFormatException e) {
            System.out.println("Invalid credit hours"); // Credits passed in was not an integer
        }
    }

    /**
     * Removes a student from the roster
     * @param st - the rest of the tokens
     */
    public void removeStudent(StringTokenizer st) {
        int dummyCredits = 0;

        String studentName = st.nextToken();
        Major studentMajor = convertStringToMajorCode(st.nextToken());

        Profile studentProfile = new Profile(studentName, studentMajor);
        Student studentToRemove = new Student(studentProfile, dummyCredits);

        if(studentRoster.remove(studentToRemove)){
            System.out.println("Student removed from the roster");
        }else{
            System.out.println("Student is not in the roster");
        }
    }

    public static void calculateStudentTuition(StringTokenizer st) {

    }

    public static void payTuition(StringTokenizer st) {

    }

    public static void setStudyAbroadStatus(StringTokenizer st) {

    }

    public static void setFinancialAidAmount(StringTokenizer st) {

    }

    /**
     * prints the roster
     */
    public static void printRoster() {
        studentRoster.printRoster();
    }

    /**
     * prints the roster by names
     */
    public static void printRosterByNames() {
        studentRoster.printRosterByNames();
    }

    /**
     * prints the roster by payment dates
     */
    public static void printRosterByPaymentDates() {
        studentRoster.printRosterByPaymentDates();
    }
}
