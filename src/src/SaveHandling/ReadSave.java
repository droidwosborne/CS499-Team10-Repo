/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.SaveHandling;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author davis
 */
public class ReadSave {
    
    public ReadSave(){
        
        
        
    }
    
    public ArrayList<String[]> teacherIn(){
        ArrayList<String[]> teacherData = new ArrayList<String[]>();
        
        
        try{
        
            File fIn = new File("saves/teacher.csv");
            Scanner inFile = new Scanner(fIn);
            String trash = inFile.nextLine();
            String raw = inFile.nextLine();
            
            teacherData.add(raw.split(",",0));
            
            inFile.close();
            
        }catch(FileNotFoundException e){
        
            System.out.println("File not found");
            e.printStackTrace();
        
        }
        
        
        
        return teacherData;
    }
    
}
