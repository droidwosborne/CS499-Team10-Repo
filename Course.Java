/**
 * Course
 * This class imports course data and writes changes to the file
 *
 * @author Braden McGee
 */

// Imports
import java.util.*;

public class Course {
    // Vars
    public Vector<Student> roster;
    public Gradebook gradebook;

    // Constructor
    public Course(){
        gradebook = new Gradebook();
        roster = new Vector<Student>();
    }
};

// ---- Data Classes ----

// Student
class Student {
    public String firstName;
    public String lastName;
    public float overallGrade;
    public int ID;

    // Constructor
    public Student(){
        firstName = "Undefined firstName";
        lastName = "Undefined lastName";
        overallGrade = -1;
        ID = -1;
    }
};

// Assignment
class Assignment {
    public String name;
    // Category that the assignment belongs to based on numerical ID of category
    public int category;
    public int maxScore;
    // Scores are in order based on student ID (I.E, scores[0] to ID 1, scores[1] to ID 2)
    public Vector<Integer> scores;

    // Constructor
    public Assignment(){
        name = "Undefined Assignment Name";
        category = -1;
        maxScore = -1;
        scores = new Vector<Integer>();
    }
};

// Gradebook
class Gradebook {
    public Vector<Assignment> assignments;
    // Scale works by integers setting cutoff per grade (I.E, if percent score is below scale[0], it is an F; if percent score is not below score[3], it must be an A)
    // Default scale would be scale = [60,70,80,90]
    public int[] scale;
    public Vector<String> catNames;
    public Vector<Integer> catID;

    public Gradebook() {
        assignments = new Vector<Assignment>();
        scale = new int[]{60, 70, 80, 90};
        catNames = new Vector<String>();
        catID = new Vector<Integer>();
    }
};