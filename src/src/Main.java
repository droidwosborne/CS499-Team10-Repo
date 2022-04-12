/*
 * Main.java
 * CS 499 Team 10 Spring 2022
 * Creates Frame and Window instances to produce GUI
 */
package src;
import java.io.IOException;
import src.SaveHandling.*;
import java.util.ArrayList;

/*
 * Main class where the gradebook application is constructed
 */
public class Main {

    public static void main(String[] args) throws IOException {

        //readSaveTest
        CourseList clTest = new CourseList("testPword", "uname");
        System.out.println(clTest.getTeacherLName());
        clTest.setPassword("testPword");
        
        WriteSave saveOut = new WriteSave();
        
        saveOut.saveClassList(clTest);
        
        
        // Create the GUI
        Frame processWindow = new Frame();
        Window processPanel = new Window();
        processWindow.add(processPanel);
        processWindow.pack();
        processWindow.setVisible(true);
    }
}
