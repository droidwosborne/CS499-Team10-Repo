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

    // Score Functions
    public void calculateOverallGrade(int StudentID){
        float[] catScores = new float[gradebook.getCatID().size()];
        float[] catMaxScores = new float[gradebook.getCatID().size()];
        float overallGrade = 0.0F;
        float excludedWeight = 0.0F;
        float totalWeight = 0.0F;
        // Loop to compute category scores
        for(int i=0; i<gradebook.getCatID().size(); i++){
            // Initializing values to 0
            catScores[i]=0;
            catMaxScores[i]=0;
            // Loop to find applicable assignments
            for(int j=0; j<gradebook.getAssignments().size(); j++){
                if(gradebook.getCatID().get(i).intValue() == gradebook.getAssignments().get(j).getCategory()){
                    catScores[i] += roster.get(StudentID).getScores().get(j);
                    catMaxScores[i] += gradebook.getAssignments().get(j).getMaxScore();
                }
            }
            // Adding to Overall Grade: first percentile for category is calculated, then modified by weight and added to overall
            if(catMaxScores[i]==0)
                excludedWeight += gradebook.getCatWeights().get(i);
            else
                overallGrade += ((catScores[i]/catMaxScores[i])*gradebook.getCatWeights().get(i));
            totalWeight += gradebook.getCatWeights().get(i);
        }
        // Finalizing Overall Grade
        overallGrade = overallGrade*(1/(totalWeight-excludedWeight))*100;
        // Setting Overall Grade with assumption that it is the last entry in Scores
        roster.get(StudentID).setOverallGrade(overallGrade);
    }

    public void setScore(int StudentID, int AssignmentID, float score){
        roster.get(StudentID).setScore(AssignmentID, score);
    }

    // Roster Functions
    public void addStudent(String firstName, String lastName){
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        // Empty roster contingency
        if(roster.size() == 0) student.setID(0);
        else student.setID(roster.lastElement().getID()+1);
        roster.add(student);
    }

    public void deleteStudent(int StudentID){
        for(int i=0; i<roster.size(); i++){
            if(roster.get(i).getID()==StudentID){
                roster.remove(i);
                break;
            }
        }
    }

    // Gradebook Functions
    public void addAssignment(String name, int category, int maxScore){
        Assignment assignment = new Assignment();
        assignment.setName(name);
        assignment.setCategory(category);
        assignment.setMaxScore(maxScore);
        gradebook.getAssignments().add(assignment);
    }

    public void deleteAssignment(int AssignmentID){
        gradebook.getAssignments().remove(AssignmentID);
    }

    public void addCategory(String categoryName, Float categoryWeight){
        gradebook.getCatNames().add(categoryName);
        gradebook.getCatID().add(gradebook.getCatID().lastElement()+1);
        gradebook.getCatWeights().add(categoryWeight);
    }

    public void deleteCategory(Integer categoryID){
        // First, unassigning all assignments in that category
        for(int i=0; i<gradebook.getAssignments().size(); i++){
            if(gradebook.getAssignments().get(i).getCategory()==categoryID){
                gradebook.getAssignments().get(i).setCategory(0);
            }
        }
        // Deleting category
        for(int i=0; i<gradebook.getCatID().size(); i++){
            if(gradebook.getCatID().get(i).equals(categoryID)){
                gradebook.getCatNames().remove(i);
                gradebook.getCatID().remove(i);
                gradebook.getCatWeights().remove(i);
                break;
            }
        }
    }

    // Get/Set
    public Vector<Student> getRoster() {
        return roster;
    }

    public void setRoster(Vector<Student> roster) {
        this.roster = roster;
    }

    public Gradebook getGradebook() {
        return gradebook;
    }

    public void setGradebook(Gradebook gradebook) {
        this.gradebook = gradebook;
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
        ID = -2;
        scores = new Vector<Float>();
    }

    // Get/Set
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public float getOverallGrade() {
        return overallGrade;
    }

    public void setOverallGrade(float overallGrade) {
        this.overallGrade = overallGrade;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Vector<Float> getScores() {
        return scores;
    }

    public void setScores(Vector<Float> scores) {
        this.scores = scores;
    }

    public void setScore(int AssignmentID, float score){
        this.scores.set(AssignmentID, score);
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
        category = 0;
        maxScore = 0;
    }

    // Get/Set
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
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

    // Constructor
    public Gradebook() {
        assignments = new Vector<Assignment>();
        scale = new int[]{60, 70, 80, 90};
        catNames = new Vector<String>();
        catID = new Vector<Integer>();
        catWeights = new Vector<Float>();
    }

    // Get/Set
    public Vector<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(Vector<Assignment> assignments) {
        this.assignments = assignments;
    }

    public int[] getScale() {
        return scale;
    }

    public void setScale(int[] scale) {
        this.scale = scale;
    }

    public Vector<String> getCatNames() {
        return catNames;
    }

    public void setCatNames(Vector<String> catNames) {
        this.catNames = catNames;
    }

    public Vector<Integer> getCatID() {
        return catID;
    }

    public void setCatID(Vector<Integer> catID) {
        this.catID = catID;
    }

    public Vector<Float> getCatWeights() {
        return catWeights;
    }

    public void setCatWeights(Vector<Float> catWeights) {
        this.catWeights = catWeights;
    }
};