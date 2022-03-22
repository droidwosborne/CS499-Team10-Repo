/**
 * CourseList
 * This class imports a list of courses and is used for selecting & loading a course
 *
 * @author Braden McGee
 */
package src;
// Imports
import java.util.*;
import src.SaveHandling.ReadSave;

public class CourseList {
    // Vars
    private Vector<String> courses;
    private String teacherLName;
    private String teacherFName;

    // Constructor
    public CourseList(){
        
        ReadSave tIn = new ReadSave();
        
        Vector<String> temp = new Vector<String>(tIn.teacherIn());
        
        teacherLName = temp.get(0);
        teacherFName = temp.get(1);
        
        courses = new Vector<String>();
        
        for (int i = 2; i < temp.size(); i++)
            courses.add(temp.get(i));
        
    }

    // Get/Set
    public Vector<String> getCourses() {
        return courses;
    }

    public void setCourses(Vector<String> courses) {
        this.courses = courses;
    }
    
    public String getTeacherLName(){
        return teacherLName; 
    }
    
    public void setTeacherLName(String lName){
        teacherLName = lName;
    }
    
    public String getTeacherFName(){
        return teacherFName; 
    }
    
    public void setTeacherFName(String fName){
        teacherFName = fName;
    }
    
}