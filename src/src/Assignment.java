/**
 * Assignment
 * This class contains assignment's data (name, category, max score)
 *
 * @author Braden McGee
 */
package src;

public class Assignment {
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
    public Assignment(String name, int category, int maxScore){
        this.name = name;
        this.category = category;
        this.maxScore = maxScore;
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
}