/**
 * Gradebook
 * This class contains course grading information: vector of assignments, scale implemented by lower bounds of top four grades, and category info
 *
 * @author Braden McGee, davis
 */
package src;

import java.util.Vector;

public class Gradebook {
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
    
    public Gradebook(Vector<Assignment> assignments, int[] scale, Vector<String> catNames, Vector<Integer> catID, Vector<Float> catWeights){
    
        this.assignments = new Vector<Assignment>(assignments);
        this.scale = scale.clone();
        this.catNames = new Vector<String>(catNames);
        this.catID = new Vector<Integer>(catID);
        this.catWeights = new Vector<Float>(catWeights);
        
    
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
}