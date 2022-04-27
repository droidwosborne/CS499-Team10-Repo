/*
 * Main.java
 * CS 499 Team 10 Spring 2022
 * Creates Frame and Window instances to produce GUI
 */
package src;
import java.io.IOException;
import src.SaveHandling.*;

import java.util.Vector;

/*
 * Main class where the gradebook application is constructed
 */
public class Main {

    public static void main(String[] args) throws IOException {
        // Code to make an example course
        boolean makeCourse1 = true;
        if(makeCourse1) makeCourse1();
        
        boolean makeCourse2 = true;
        if(makeCourse2) makeCourse2();

        //readSaveTest
        CourseList clTest = new CourseList();
        //System.out.println(clTest.getTeacherLName());
        //clTest.setPassword("testPword");
        
        WriteSave saveOut = new WriteSave();
        
        saveOut.saveClassList(clTest);
        
        // Create the GUI
        Frame processWindow = new Frame();
        Window processPanel = new Window();
        processWindow.add(processPanel);
        processWindow.pack();
        processWindow.setVisible(true);
    }
    public static void makeCourse1(){
        // Roster population
        Vector<Student> roster = new Vector<Student>();
        Vector<Float> student1grades = new Vector<Float>();
        Vector<Float> student2grades = new Vector<Float>();
        Vector<Float> student3grades = new Vector<Float>();
        for(int i = 0; i<3; i++){
            student1grades.add((Float)((float)((100-i))));
            student2grades.add((Float)((float)((90-i))));
            student3grades.add((Float)((float)((80+i))));
        }
        Student student1 = new Student("Yolanda", "Summers", 0, 0.00F, student1grades);
        Student student2 = new Student("Kyle", "Williams", 1, 0.00F, student2grades);
        Student student3 = new Student("Maria", "Jackson", 2, 0.00F, student3grades);
        roster.add(student1);
        roster.add(student2);
        roster.add(student3);
        // Gradebook population
        Vector<Assignment> assignments = new Vector<Assignment>();
        Assignment assignment1 = new Assignment("Sections 1-2", 0, 100);
        Assignment assignment2 = new Assignment("Chapter 2", 1, 200);
        Assignment assignment3 = new Assignment("Chapters 1-3", 2, 300);
        assignments.add(assignment1);
        assignments.add(assignment2);
        assignments.add(assignment3);
        int[] scale = {61, 71, 81, 89};
        Vector<String> catNames = new Vector<String>();
        catNames.add("Homework");
        catNames.add("Quzzes");
        catNames.add("Tests");
        Vector<Integer> catID = new Vector<Integer>();
        for(Integer i=0; i<3; i++) catID.add(i);
        Vector<Float> catWeights = new Vector<Float>();
        catWeights.add(15.0F);
        catWeights.add(40.0F);
        catWeights.add(45.0F);
        Gradebook gradebook = new Gradebook(assignments, scale, catNames, catID, catWeights);
        // Final course stuff
        Course course1 = new Course(gradebook, roster);
        course1.calculateOverallGrade(0);
        course1.calculateOverallGrade(1);
        course1.calculateOverallGrade(2);
        WriteSave writeSave= new WriteSave();
        try {
            writeSave.saveCourse(course1, "course1");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void makeCourse2(){
        // Roster population
        Vector<Student> roster = new Vector<Student>();
        Vector<Float> student1grades = new Vector<Float>();
        Vector<Float> student2grades = new Vector<Float>();
        Vector<Float> student3grades = new Vector<Float>();
        for(int i = 0; i<3; i++){
            student1grades.add((Float)((float)((100-i))));
            student2grades.add((Float)((float)((90-i))));
            student3grades.add((Float)((float)((80+i))));
        }
        Student student1 = new Student("Kyra", "Robertson", 0, 0.00F, student1grades);
        Student student2 = new Student("Utopia", "Ross", 1, 0.00F, student2grades);
        Student student3 = new Student("Michael", "Sterling", 2, 0.00F, student3grades);
        roster.add(student1);
        roster.add(student2);
        roster.add(student3);
        // Gradebook population
        Vector<Assignment> assignments = new Vector<Assignment>();
        Assignment assignment1 = new Assignment("Lab Assignment", 0, 200);
        Assignment assignment2 = new Assignment("Mid-Term", 1, 300);
        Assignment assignment3 = new Assignment("Final", 2, 400);
        assignments.add(assignment1);
        assignments.add(assignment2);
        assignments.add(assignment3);
        int[] scale = {61, 71, 81, 89};
        Vector<String> catNames = new Vector<String>();
        catNames.add("Labs");
        catNames.add("Exam");
        catNames.add("Final");
        Vector<Integer> catID = new Vector<Integer>();
        for(Integer i=0; i<3; i++) catID.add(i);
        Vector<Float> catWeights = new Vector<Float>();
        catWeights.add(30.0F);
        catWeights.add(35.0F);
        catWeights.add(35.0F);
        Gradebook gradebook = new Gradebook(assignments, scale, catNames, catID, catWeights);
        // Final course stuff
        Course course2 = new Course(gradebook, roster);
        course2.calculateOverallGrade(0);
        course2.calculateOverallGrade(1);
        course2.calculateOverallGrade(2);
        WriteSave writeSave= new WriteSave();
        try {
            writeSave.saveCourse(course2, "class2");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
