/**
 * Student
 * This class aggregates student info (name, overallGrade, individual assignment scores, and ID)
 *
 * @author Braden McGee, davis
 */
package src;

import java.util.Vector;

public class Student {
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
    public Student(String fName,String lName, int id, float totalAvg, Vector<Float> grades){
        firstName = fName;
        lastName = lName;
        overallGrade = totalAvg;
        ID = id;
        scores = new Vector<Float>(grades);
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
}
