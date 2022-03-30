/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.SaveHandling;
import src.*;
import java.util.Vector;
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author davis
 */
public class ReadSave {
    
    private static String teacherPath = "saves/teacher.csv";
    
    
    public ReadSave(){
        
        
        
    }
    
    public Vector<String> teacherIn(){
        Vector<String> teacherData;
        
        
        try{
        
            File fIn = new File(teacherPath);
            Scanner inFile = new Scanner(fIn);
            String trash = inFile.nextLine();
            String raw = inFile.nextLine();
            
            teacherData = new Vector<String>(Arrays.asList(raw.split(",",0)));
            
            inFile.close();
            
        }catch(FileNotFoundException e){
        
            System.out.println("File not found");
            teacherData = new Vector<String>();
            e.printStackTrace();
        
        }
        
        
        
        return teacherData;
    }
    
    public Course classDataIn(String clName){
    
        Vector<String> clData = new Vector<String>();
        String clPath = "saves/"+clName+".csv";
        char secSplit = 135;
        char sbSplit = 137;
        String sSplit = ""+secSplit;
        String subSplit = ""+sbSplit;
        int numAsighn;
        int numCat;
        Vector<String[]> asighnInfo = new Vector<String[]>();
        String rawScale = "";
        Vector<String[]> catInfo = new Vector<String[]>();
        Vector<String[]> studentsInfo = new Vector<String[]>();
        Vector<Float> studentGrades = new Vector<Float>();
        Vector<Student> studentVector = new Vector<Student>();
        Vector<Assignment> assighnments = new Vector<Assignment>();
        Vector<String> catNames = new Vector<String>();
        Vector<Integer> catID = new Vector<Integer>();
        Vector<Float> catWeight = new Vector<Float>();
        Gradebook gradebook;
        
        try {
        
        File clFile = new File(clPath);
        Scanner clIn = new Scanner(clFile);
        String raw = clIn.nextLine();
        String[] template = raw.split(sSplit,0);
        String temp = template[3].substring(1);
        String[] rawAsighn = temp.split(",",0);
        numAsighn = rawAsighn.length;
        for (int i = 0; i < numAsighn; i++){
        
            asighnInfo.add(rawAsighn[i].split(subSplit));  // assighnment information is saved in the format
            //name of assighnment, assighnment catagory number, max score
        
        }
        
        rawScale = template[4].substring(1);
        
        temp = template[5].substring(1);
        String[] rawCat = temp.split(",");
        numCat = rawCat.length;
        for (int i = 0; i < numCat; i++){
            catInfo.add(rawCat[i].split(subSplit));
        //catInof in the format Name of catagory, catagory weigh
        }
        String rawStudent;
        while(clIn.hasNextLine()){
        
            rawStudent = clIn.nextLine();
            studentsInfo.add(rawStudent.split(","));
        
        }
        
        }catch(FileNotFoundException e){
        
            System.out.println("File not found");
            clData = new Vector<String>();
            e.printStackTrace();
        
        }
        
        for (int i=0; i<studentsInfo.size(); i++){
            
            
            for (int j=3; j<studentsInfo.size()-1;j++){
            
                studentGrades.add(Float.parseFloat(studentsInfo.get(i)[j]));
            
            }
            studentVector.add(new Student(studentsInfo.get(i)[2],studentsInfo.get(i)[1],Integer.parseInt(studentsInfo.get(i)[0]),Integer.parseInt(studentsInfo.get(i)[studentsInfo.get(i).length-1]),studentGrades));
            studentGrades.clear();
        
        }
        
        for (int i=0; i<asighnInfo.size();i++){
            
            assighnments.add(new Assignment(asighnInfo.get(i)[0],Integer.parseInt(asighnInfo.get(i)[1]),Integer.parseInt(asighnInfo.get(i)[2])));
            
        }
        for(int i=0; i<catInfo.size();i++){
            catNames.add(catInfo.get(i)[0]);
            catID.add(i);
            catWeight.add(Float.parseFloat(catInfo.get(i)[1]));
        }
        String[] temp = rawScale.split(subSplit);
        int[] scale = new int[temp.length];
        for(int i=0;i<scale.length;i++) scale[i] = Integer.parseInt(temp[i]);
        gradebook = new Gradebook(assighnments, scale, catNames, catID, catWeight);
        
        return new Course(gradebook, studentVector);
    }
    
}
