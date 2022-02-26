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
        ReadSave rTest = new ReadSave();
        ArrayList<String[]> tTest = new ArrayList<String[]>(rTest.teacherIn());
        System.out.println(tTest.get(0)[0]);
        
        // Create the GUI
        Frame processWindow = new Frame();
        Window processPanel = new Window();
        processWindow.add(processPanel);
        processWindow.pack();
        processWindow.setVisible(true);
    }
}
