/**
 * Course
 * This class imports course data and writes changes to the file
 *
 * @author Braden McGee
 */
package src;
// Imports
import java.util.*;

public class Course {
    // Vars
    private Vector<Student> roster;
    private Gradebook gradebook;

    // Constructor
    public Course(){
        gradebook = new Gradebook();
        roster = new Vector<Student>();
    }
};

// ---- Data Classes ----

// Student
class Student {
    private String firstName;
    private String lastName;
    private float overallGrade;
    private int ID;
    private Vector<Float> scores;

    // Constructor
    public Student(){
        firstName = "Undefined firstName";
        lastName = "Undefined lastName";
        overallGrade = -4;
        ID = -5;
        scores = new Vector<Float>();
    }
};

// Assignment
class Assignment {
    private String name;
    // Category that the assignment belongs to based on numerical ID of category
    private int category;
    private int maxScore;
    

    // Constructor
    public Assignment(){
        name = "Undefined Assignment Name";
        category = -6;
        maxScore = -7;
    }
};

// Gradebook
class Gradebook {
    private Vector<Assignment> assignments;
    // Scale works by integers setting cutoff per grade (I.E, if percent score is below scale[0], it is an F; if percent score is not below score[3], it must be an A)
    // Default scale would be scale = [60,70,80,90]
    private int[] scale;
    private Vector<String> catNames;
    private Vector<Integer> catID;
    private Vector<Float> catWeights;

    public Gradebook() {
        assignments = new Vector<Assignment>();
        scale = new int[]{60, 70, 80, 90};
        catNames = new Vector<String>();
        catID = new Vector<Integer>();
        catWeights = new Vector<Float>();
    }
};