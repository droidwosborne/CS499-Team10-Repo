/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.SaveHandling;
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
    
}
