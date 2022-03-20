/**
 * CourseList
 * This class imports a list of courses and is used for selecting & loading a course
 *
 * @author Braden McGee
 */
package src;
// Imports
import java.util.*;

public class CourseList {
    // Vars
    private Vector<String> courses;
    private Vector<String> filepaths;

    // Constructor
    public CourseList(String fileName){
        courses = new Vector<String>();
        filepaths = new Vector<String>();
        
    }

    // Get/Set
    public Vector<String> getCourses() {
        return courses;
    }

    public void setCourses(Vector<String> courses) {
        this.courses = courses;
    }

    public Vector<String> getFilepaths() {
        return filepaths;
    }

    public void setFilepaths(Vector<String> filepaths) {
        this.filepaths = filepaths;
    }
}