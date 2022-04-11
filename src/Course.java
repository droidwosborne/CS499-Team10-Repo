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
    private String courseName;

    // Constructor
    public Course(){
        gradebook = new Gradebook();
        roster = new Vector<Student>();
    }
    public Course(Gradebook gb, Vector<Student> rost){
        gradebook = gb;
        roster = new Vector<Student>(rost);
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

    public void setMaxScore(int AssignmentID, int maxScore){
        gradebook.getAssignments().get(AssignmentID).setMaxScore(maxScore);
    }

    public void setCatWeight(Integer categoryID, Float categoryWeight){
        for(int i=0; i<gradebook.getCatID().size(); i++){
            if(gradebook.getCatID().get(i).equals(categoryID)){
                gradebook.getCatWeights().set(i, categoryWeight);
            }
        }
    }

    public void setScale(int[] scale){
        gradebook.setScale(scale);
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

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}