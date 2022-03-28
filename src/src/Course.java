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
    public Course(Gradebook gb, Vector<Student> rost){
    
        gradebook = gb;
        roster = new Vector<Student>(rost);
    
    }

    // Functions
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
                if(gradebook.getAssignments().get(j).getCategory() == i){
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
        roster.get(StudentID).setScore(roster.get(StudentID).getScores().size()-1, overallGrade);
    }

    public void setScore(int StudentID, int AssignmentID, float score){
        roster.get(StudentID).setScore(AssignmentID, score);
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

