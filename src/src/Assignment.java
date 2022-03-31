/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

/**
 *
 * @author davis
 */
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
    public Assignment(String name, int catagory, int maxScore){
        this.name = name;
        this.category = catagory;
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