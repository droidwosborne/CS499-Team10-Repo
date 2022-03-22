/*
 * Main.java
 * CS 499 Team 10 Spring 2022
 * Creates Frame and Window instances to produce GUI
 */
package src;
import src.SaveHandling.ReadSave;
import java.util.ArrayList;

/*
 * Main class where the gradebook application is constructed
 */
public class Main {

    public static void main(String[] args) {

        //readSaveTest
        CourseList clTest = new CourseList();
        System.out.println(clTest.getTeacherLName());
        
        // Create the GUI
        Frame processWindow = new Frame();
        Window processPanel = new Window();
        processWindow.add(processPanel);
        processWindow.pack();
        processWindow.setVisible(true);
    }
}
