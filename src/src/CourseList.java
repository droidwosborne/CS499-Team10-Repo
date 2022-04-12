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
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CourseList {
    // Vars
    private Vector<String> courses;
    private String teacherLName;
    private String teacherFName;
    private String userName;
    private String pwordHash;

    // Constructor
    public CourseList(){
        
        ReadSave tIn = new ReadSave();
        
        Vector<String> temp = new Vector<String>(tIn.teacherIn());
        
        teacherLName = temp.get(0);
        teacherFName = temp.get(1);
        pwordHash = temp.get(2);
        userName = temp.get(3);
        
        courses = new Vector<String>();
        
        for (int i = 4; i < temp.size(); i++)
            courses.add(temp.get(i));
        
    }
    
    public CourseList(String pRaw, String uName){
        
        ReadSave tIn = new ReadSave();
        
        Vector<String> temp = new Vector<String>(tIn.teacherIn());
        
        try {
            pRaw = hash(pRaw);
        }
        catch(NoSuchAlgorithmException e){
            throw new RuntimeException(e);
        }
        if(pRaw.equals(temp.get(2)) == true && uName.equals(temp.get(3)) == true){
            
            teacherLName = temp.get(0);
            teacherFName = temp.get(1);
            pwordHash = temp.get(2);
            userName = temp.get(3);

            courses = new Vector<String>();

            for (int i = 4; i < temp.size(); i++)
                courses.add(temp.get(i));
            
        }
        
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
    
    public void setPassword(String pRaw){
        try {
            pwordHash = hash(pRaw);
        }
        catch(NoSuchAlgorithmException e){
            throw new RuntimeException(e);
        }
    }
    
    public String getPassword(){
        return pwordHash;
    } 
    
    public void setUserName(String uName){
        this.userName = uName;
    }
    
    public String getUserName(){
        return userName;
    }
    
    
    private String hash(String hTarget)throws NoSuchAlgorithmException{
    
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        byte[] messageDigest = md.digest(hTarget.getBytes());
        BigInteger no = new BigInteger(1, messageDigest);
        String hashtext = no.toString(16);

        return hashtext;
    
    }
    
}