/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.SaveHandling;
import java.io.BufferedWriter;
import src.*;
import java.util.Vector;
import java.util.Arrays;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


/**
 *
 * @author davis
 */
public class WriteSave {
    
    private char secSplit = 135;
    private char sbSplit = 137;
    private String sSplit = ""+secSplit;
    private String subSplit = ""+sbSplit;
    
    
    public WriteSave(){}
    
    public void saveCourse(Course course, String courseName) throws IOException{

        String lineOut = "id"+sSplit+",lName"+sSplit+",fName"+sSplit;
        Vector<Assignment> asList = new Vector<Assignment>(course.getGradebook().getAssignments());
        Vector<String> catNames = new Vector<String>(course.getGradebook().getCatNames());
        Vector<Float> catWeight = new Vector<Float>(course.getGradebook().getCatWeights());
        BufferedWriter outFile = new BufferedWriter(new FileWriter("saves/"+courseName+".csv"));
        Vector<Student> stuOut = new Vector<Student>(course.getRoster());


        for(int i = 0;i<asList.size();i++){

            lineOut = lineOut+","+ asList.get(i).getName()+subSplit+ asList.get(i).getCategory()+subSplit+ asList.get(i).getMaxScore();

        }
        lineOut = lineOut+ sSplit +",";

        for(int i=0; i<course.getGradebook().getScale().length;i++){

            if(i != 0) lineOut = lineOut+subSplit;
            lineOut = lineOut+ course.getGradebook().getScale()[i];

        }
        lineOut = lineOut+ sSplit;

        for(int i=0; i<catNames.size();i++){

            lineOut = lineOut+","+ catNames.get(i)+subSplit+ catWeight.get(i);

        }
        
        outFile.write(lineOut+"\n");
        
        for(int i=0; i<stuOut.size();i++){
        
            lineOut = "";
            lineOut = lineOut+stuOut.get(i).getID()+","+stuOut.get(i).getLastName()+","+stuOut.get(i).getFirstName();
            for(int j=0; j<stuOut.get(i).getScores().size();j++){
                lineOut = lineOut+","+stuOut.get(i).getScores().get(j);
            }
            lineOut = lineOut+","+stuOut.get(i).getOverallGrade();
            
            outFile.write(lineOut+"\n");
            
        }
        
        outFile.close();
        
    }
    
    public void saveClassList(CourseList cList)throws IOException{
    
        BufferedWriter outFile = new BufferedWriter(new FileWriter("saves/teacher.csv"));
        
        outFile.write("lName,fName\n");
        String outLine = cList.getTeacherLName()+","+cList.getTeacherFName()+","+cList.getPassword()+","+cList.getUserName();
        for(int i=0; i < cList.getCourses().size();i++) outLine = outLine+","+cList.getCourses().get(i);
        outFile.write(outLine);
        
        outFile.close();
    
    }
    
}
