/*
 * Window.java
 * CS 499 Team 10 Spring 2022
 * Creates the Window class for the Main Class to build GUI
 */
package src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import src.SaveHandling.*;

import java.util.Vector;


/*
 * Class declaration for Window class extending JPanel for component placement and
 * implementing ActionListener to allow for actions on button click and text entry.
 */
public class Window extends JPanel implements ActionListener, MouseListener, FocusListener{

    //User pin for identification (currently set to work automatically for easy testing)
    //CourseList clTest = new CourseList();
    //System.out.println(clTest.getTeacherLName());
    String pin = "testPword";
    
    
    //public char[] pinA = new char[pin.length()];
    Font labelFont = new Font("Serif", Font.PLAIN, 16);
    
    ReadSave reader = new ReadSave();
    Vector<String> teacher = reader.teacherIn();
    Course course1 = reader.classDataIn("course1");
    Vector<Student> roster1 = course1.getRoster();
    Gradebook gradebook1 = course1.getGradebook();
    Vector<Assignment> assignments = course1.getGradebook().getAssignments();
        
    CourseList cList = new CourseList();
    Vector<String> courses = cList.getCourses();
   
    //Constructor for Window class consisting of components in Panel
    public Window () {
        //Arbitrary size and color appearance of the window
        Dimension dim = new Dimension(900, 500);
        setMinimumSize(dim);
        setPreferredSize(dim);
        setLayout(null);
        setBackground(Color.lightGray);
        screenNumber = -1;
        
        cList.setPassword(pin);
        if (courses.size() >= 1)
            course1.setCourseName(courses.get(0));
        else
            course1.setCourseName(null);
        //System.out.println(course1.getCourseName());
        /*cList.setTeacherFName("Yolanda");
        cList.setTeacherLName("Fergueson");
        Vector<String> temp = new Vector<String>(){{
            add("CS456");
            add("CS103");
        }};
        cList.setCourses(temp);
        System.out.println(cList.getCourses().size());
        System.out.println(cList.getTeacherFName());
        System.out.println(cList.getTeacherLName());
        
        WriteSave writer = new WriteSave();
        try{
        writer.saveClassList(cList);
        }
        catch (IOException e){
            System.out.println("error");
        }*/
        /*
         * Login Page
         */
        loginPageLabel = new JLabel("Login into Gradebook");
        loginPageLabel.setFont(labelFont);
        size = loginPageLabel.getPreferredSize();
        loginPageLabel.setBounds(300, 120, size.width, size.height);
        add(loginPageLabel);

        loginLabel = new JLabel("Pin:");
        loginLabel.setFont(labelFont);
        size = loginLabel.getPreferredSize();
        loginLabel.setBounds(200, 200, size.width, size.height);
        add(loginLabel);

        incorrectLoginLabel = new JLabel("<html><font color = 'FF0000'>Incorrect Pin</font></html>");
        incorrectLoginLabel.setFont(labelFont);
        size = incorrectLoginLabel.getPreferredSize();
        incorrectLoginLabel.setBounds(200, 150, size.width, size.height);
        add(incorrectLoginLabel);
        incorrectLoginLabel.setVisible(false);

        helpLabel1 = new JLabel ("<html>Input pin for user login <br>to access gradebook.</html>");
        helpLabel1.setFont(labelFont);
        size =  helpLabel1.getPreferredSize();
        helpLabel1.setBounds(700, 200, size.width, size.height);
        add(helpLabel1);
        helpLabel1.setVisible(false);
        
        pinField = new JPasswordField(30);
        size = pinField.getPreferredSize();
        pinField.addActionListener(this);
        pinField.setText(pin);
        pinField.setBounds(250,195,size.width,size.height);
        add(pinField);
        
        
        /*
         * Landing Page
         */
        titleLabel = new JLabel("Welcome, Professor " + teacher.get(0));
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        size =  titleLabel.getPreferredSize();
        titleLabel.setBounds(50, 50, size.width, size.height);
        add(titleLabel);
        titleLabel.setVisible(false);
        
        classLabel = new JLabel("");
        classLabel.setFont(new Font("Serif", Font.BOLD, 24));
        add(classLabel);
        classLabel.setVisible(false);
        
        class1Button = new JButton();
        if (courses.size() >= 1)
            class1Button.setText(courses.get(0));
        else
            class1Button.setText(null);
        size = class1Button.getPreferredSize();
        class1Button.addActionListener(this);
        class1Button.setBounds(50,100,size.width,size.height+30);
        add(class1Button);
        class1Button.setVisible(false);
        
        class2Button = new JButton();
        if (courses.size() >= 2)
            class2Button.setText(courses.get(1));
        else
            class2Button.setText(null);
        size = class2Button.getPreferredSize();
        class2Button.addActionListener(this);
        class2Button.setBounds(400,100,size.width,size.height+30);
        add(class2Button);
        class2Button.setVisible(false);
        
        class3Button = new JButton();
        if (courses.size() >= 3)
            class3Button.setText(courses.get(2));
        else
            class3Button.setText(null);
        size = class3Button.getPreferredSize();
        class3Button.addActionListener(this);
        class3Button.setBounds(750,100,size.width,size.height+30);
        add(class3Button);
        class3Button.setVisible(false);
        
        class4Button = new JButton();
        if (courses.size() >= 4)
            class4Button.setText(courses.get(3));
        else
            class4Button.setText(null);
        size = class4Button.getPreferredSize();
        class4Button.addActionListener(this);
        class4Button.setBounds(50,200,size.width,size.height+30);
        add(class4Button);
        class4Button.setVisible(false);
        
        class5Button = new JButton();
        if (courses.size() >= 5)
            class5Button.setText(courses.get(4));
        else
            class5Button.setText(null);
        size = class5Button.getPreferredSize();
        class5Button.addActionListener(this);
        class5Button.setBounds(400,200,size.width,size.height+30);
        add(class5Button);
        class5Button.setVisible(false);
        
        class6Button = new JButton();
        if (courses.size() >= 6)
            class6Button.setText(courses.get(5));
        else
            class6Button.setText(null);
        size = class6Button.getPreferredSize();
        class6Button.addActionListener(this);
        class6Button.setBounds(750,200,size.width,size.height+30);
        add(class6Button);
        class6Button.setVisible(false);
        
        class7Button = new JButton();
        if (courses.size() >= 7)
            class7Button.setText(courses.get(6));
        else
            class7Button.setText(null);
        size = class7Button.getPreferredSize();
        class7Button.addActionListener(this);
        class7Button.setBounds(400,300,size.width,size.height+30);
        add(class7Button);
        class7Button.setVisible(false);
        
        /*
         * Class Page
         */
        classesButton = new JButton("Classes");
        size = classesButton.getPreferredSize();
        classesButton.addActionListener(this);
        classesButton.setBounds(750,440,size.width,size.height+20);
        add(classesButton);
        classesButton.setVisible(false);
        
        
        assignmentButton = new JButton("Assignments");
        size = assignmentButton.getPreferredSize();
        assignmentButton.addActionListener(this);
        assignmentButton.setBounds(30,150,size.width,size.height+20);
        add(assignmentButton);
        assignmentButton.setVisible(false);
        
        rosterButton = new JButton("Roster");
        size = rosterButton.getPreferredSize();
        rosterButton.addActionListener(this);
        rosterButton.setBounds(30,200,size.width,size.height+20);
        add(rosterButton);
        rosterButton.setVisible(false);
        
        /*
         * Assignments Page
         */
        assignmentsTableModel = new DefaultTableModel();
        assignmentsTableModel.addColumn("Name");
        assignmentsTableModel.addColumn("Category");
        assignmentsTableModel.addColumn("Maximum Score");
        assignmentsTableModel.addRow(new Object[]{"","","",""});
        assignmentsTableModel.addRow(new Object[]{"","","",""});
        assignmentsTableModel.addRow(new Object[]{"","","",""});

        assignmentsTable = new JTable(assignmentsTableModel);
        assignmentsTable.setBackground(Color.lightGray);
        JTableHeader assignmentHeader = assignmentsTable.getTableHeader();
        assignmentHeader.setBackground(Color.white);
        assignmentHeader.setFont(new Font("Serif", Font.BOLD, 14));
        assignmentsPane = new JScrollPane(assignmentsTable);
        assignmentsPane.setBounds(200, 100, 480, 250);
        add(assignmentsPane);
        assignmentsPane.setVisible(false);
        for (int i = 0; i < assignments.size(); i++)
        {
            assignmentsTable.setValueAt(assignments.get(i).getName(),i,0);
            assignmentsTable.setValueAt(assignments.get(i).getCategory(),i,1);
            assignmentsTable.setValueAt(assignments.get(i).getMaxScore(),i,2);
        }
        
        
        
        assignmentAddButton = new JButton("+");
        assignmentAddButton.setFont(new Font("Serif", Font.BOLD, 20));
        size = assignmentAddButton.getPreferredSize();
        assignmentAddButton.addActionListener(this);
        assignmentAddButton.setBounds(70,150,size.width-35,size.height);
        add(assignmentAddButton);
        assignmentAddButton.setVisible(false);
        
        
        /*
         * Roster Page
         */
        rosterTableModel = new DefaultTableModel();
        rosterTableModel.addColumn("First Name");
        rosterTableModel.addColumn("Last Name");
        rosterTableModel.addColumn("Student ID");
        rosterTableModel.addColumn("Average");
        rosterTableModel.addRow(new Object[]{"","","",""});
        rosterTableModel.addRow(new Object[]{"","","",""});
        rosterTableModel.addRow(new Object[]{"","","",""});

        rosterTable = new JTable(rosterTableModel);
        rosterTable.setBackground(Color.lightGray);
        JTableHeader studentHeader = rosterTable.getTableHeader();
        studentHeader.setBackground(Color.white);
        studentHeader.setFont(new Font("Serif", Font.BOLD, 14));
        rosterPane = new JScrollPane(rosterTable);
        rosterPane.setBounds(200, 100, 550, 250);
        add(rosterPane);
        rosterPane.setVisible(false);
        
        for (int i = 0; i < roster1.size(); i++)
        {
            rosterTable.setValueAt(roster1.get(i).getFirstName(),i,0);
            rosterTable.setValueAt(roster1.get(i).getLastName(),i,1);
            rosterTable.setValueAt(roster1.get(i).getID(),i,2);
            rosterTable.setValueAt(roster1.get(i).getOverallGrade(),i,3);
        }
        
        rosterAddButton = new JButton("+");
        rosterAddButton.setFont(new Font("Serif", Font.BOLD, 20));
        size = rosterAddButton.getPreferredSize();
        rosterAddButton.addActionListener(this);
        rosterAddButton.setBounds(100,200,size.width-35,size.height);
        add(rosterAddButton);
        rosterAddButton.setVisible(false);
        
        
        gradebookButton = new JButton("Gradebook");
        size = gradebookButton.getPreferredSize();
        gradebookButton.addActionListener(this);
        gradebookButton.setBounds(30,250,size.width,size.height+20);
        add(gradebookButton);
        gradebookButton.setVisible(false);
        
        
        /*
         * Gradebook Page
         */
        gradebookTableModel = new DefaultTableModel();
        gradebookTableModel.addColumn("Assignment");
        gradebookTableModel.addColumn("Scale");
        gradebookTableModel.addColumn("Category Name");
        gradebookTableModel.addColumn("ID");
        gradebookTableModel.addColumn("Weight");
        gradebookTableModel.addRow(new Object[]{"","","","",""});
        gradebookTableModel.addRow(new Object[]{"","","","",""});
        gradebookTableModel.addRow(new Object[]{"","","","",""});

        gradebookTable = new JTable(gradebookTableModel);
        gradebookTable.setBackground(Color.lightGray);
        JTableHeader gradebookHeader = gradebookTable.getTableHeader();
        gradebookHeader.setBackground(Color.white);
        gradebookHeader.setFont(new Font("Serif", Font.BOLD, 14));
        gradebookPane = new JScrollPane(gradebookTable);
        gradebookPane.setBounds(200, 100, 650, 250);
        add(gradebookPane);
        gradebookPane.setVisible(false);
        for (int i = 0; i < assignments.size(); i++)
        {
            gradebookTable.setValueAt(assignments.get(i).getName(),i,0);
            gradebookTable.setValueAt(gradebook1.getScale()[i],i,1);
            gradebookTable.setValueAt(gradebook1.getCatNames().get(i),i,2);
            gradebookTable.setValueAt(gradebook1.getCatID().get(i),i,3);
            gradebookTable.setValueAt(gradebook1.getCatWeights().get(i),i,4);
        }
        
        
        
        gradebookAddButton = new JButton("+");
        gradebookAddButton.setFont(new Font("Serif", Font.BOLD, 20));
        size = gradebookAddButton.getPreferredSize();
        gradebookAddButton.addActionListener(this);
        gradebookAddButton.setBounds(70,250,size.width-35,size.height);
        add(gradebookAddButton);
        gradebookAddButton.setVisible(false);
        
        /*
         * Multiple Pages
         */
        helpButton = new JButton("?");
        helpButton.setFont(new Font("Serif", Font.BOLD, 20));
        size = helpButton.getPreferredSize();
        helpButton.addActionListener(this);
        helpButton.setBounds(850,450,size.width-35,size.height);
        add(helpButton);

        classAddButton = new JButton("+");
        classAddButton.setFont(new Font("Serif", Font.BOLD, 20));
        size = classAddButton.getPreferredSize();
        classAddButton.addActionListener(this);
        classAddButton.setBounds(850,100,size.width-35,size.height);
        add(classAddButton);
        classAddButton.setVisible(false);
        
        inputWindow = new JPanel();
        inputWindow.setLayout(null);
        Dimension smallerDim = new Dimension(200,100);
        inputWindow.setMinimumSize(smallerDim);
        inputWindow.setPreferredSize(smallerDim);
        //setLayout(null);
        inputWindow.setBackground(Color.lightGray);
        inputWindow.setBounds(300,200, 500,300);
        add(inputWindow);
        //inputWindow.setVisible(false);
    }

    /*
    * Method for all actions that occur due to user action,
    * such as button click or text entry.
     */
    public void actionPerformed(ActionEvent action){
        if (action.getSource().equals(pinField))
        {
            if(pinField.getText().equals(pin)) {
                screenNumber = 0;
                
                helpLabel1.setVisible(false);
                loginLabel.setVisible(false);
                loginPageLabel.setVisible(false);
                pinField.setVisible(false);
                incorrectLoginLabel.setVisible(false);
                
                titleLabel.setVisible(true);
                
                if (class1Button.getText() != null)
                    class1Button.setVisible(true);
                if (class2Button.getText() != null)
                    class2Button.setVisible(true);
                if (class3Button.getText() != null)
                    class3Button.setVisible(true);
                if (class4Button.getText() != null)    
                    class4Button.setVisible(true);
                if (class5Button.getText() != null)    
                    class5Button.setVisible(true);
                if (class6Button.getText() != null)    
                    class6Button.setVisible(true);
                if (class7Button.getText() != null)    
                    class7Button.setVisible(true);
                    
                    classAddButton.setVisible(true);
            } else {
                incorrectLoginLabel.setVisible(true);
            }
        }
        if (action.getSource() == helpButton)
        {
            if (screenNumber == -1){
                helpLabel1.setVisible(true);
            }
            else if ((screenNumber == 0) || (screenNumber == 1) || (screenNumber == 2)){
                helpLabel1.setText("<html>Click the tab to display<br>the gradebook section.");
                size = helpLabel1.getPreferredSize();
                helpLabel1.setVisible(true);
            }
        }
        if (action.getSource() == classAddButton)
        {
            inputWindow.setVisible(true);
        }
        if (action.getSource() == class1Button)
        {
            assignmentButton.setVisible(true);
            rosterButton.setVisible(true);
            gradebookButton.setVisible(true);
            class1Button.setVisible(false);
            class2Button.setVisible(false);
            class3Button.setVisible(false);
            class4Button.setVisible(false);
            class5Button.setVisible(false);
            class6Button.setVisible(false);
            class7Button.setVisible(false);
            classAddButton.setVisible(false);
            titleLabel.setVisible(false);
            classesButton.setVisible(true);
            
            classLabel.setText(class1Button.getText());
            size =  classLabel.getPreferredSize();
            classLabel.setBounds(50, 50, size.width, size.height);
            classLabel.setVisible(true);
        }
        if (action.getSource() == class2Button)
        {
            assignmentButton.setVisible(true);
            rosterButton.setVisible(true);
            gradebookButton.setVisible(true);
            class1Button.setVisible(false);
            class2Button.setVisible(false);
            class3Button.setVisible(false);
            class4Button.setVisible(false);
            class5Button.setVisible(false);
            class6Button.setVisible(false);
            class7Button.setVisible(false);
            classAddButton.setVisible(false);
            titleLabel.setVisible(false);
            classesButton.setVisible(true);
            
            classLabel.setText(class2Button.getText());
            size =  classLabel.getPreferredSize();
            classLabel.setBounds(50, 50, size.width, size.height);
            classLabel.setVisible(true);
        }
        if (action.getSource() == class3Button)
        {
            assignmentButton.setVisible(true);
            rosterButton.setVisible(true);
            gradebookButton.setVisible(true);
            class1Button.setVisible(false);
            class2Button.setVisible(false);
            class3Button.setVisible(false);
            class4Button.setVisible(false);
            class5Button.setVisible(false);
            class6Button.setVisible(false);
            class7Button.setVisible(false);
            classAddButton.setVisible(false);
            titleLabel.setVisible(false);
            classesButton.setVisible(true);
            
            classLabel.setText(class3Button.getText());
            size =  classLabel.getPreferredSize();
            classLabel.setBounds(50, 50, size.width, size.height);
            classLabel.setVisible(true);
        }
        if (action.getSource() == class4Button)
        {
            assignmentButton.setVisible(true);
            rosterButton.setVisible(true);
            gradebookButton.setVisible(true);
            class1Button.setVisible(false);
            class2Button.setVisible(false);
            class3Button.setVisible(false);
            class4Button.setVisible(false);
            class5Button.setVisible(false);
            class6Button.setVisible(false);
            class7Button.setVisible(false);
            classAddButton.setVisible(false);
            titleLabel.setVisible(false);
            classesButton.setVisible(true);
            
            classLabel.setText(class4Button.getText());
            size =  classLabel.getPreferredSize();
            classLabel.setBounds(50, 50, size.width, size.height);
            classLabel.setVisible(true);
        }
        if (action.getSource() == class5Button)
        {
            assignmentButton.setVisible(true);
            rosterButton.setVisible(true);
            gradebookButton.setVisible(true);
            class1Button.setVisible(false);
            class2Button.setVisible(false);
            class3Button.setVisible(false);
            class4Button.setVisible(false);
            class5Button.setVisible(false);
            class6Button.setVisible(false);
            class7Button.setVisible(false);
            classAddButton.setVisible(false);
            titleLabel.setVisible(false);
            classesButton.setVisible(true);
            
            classLabel.setText(class5Button.getText());
            size =  classLabel.getPreferredSize();
            classLabel.setBounds(50, 50, size.width, size.height);
            classLabel.setVisible(true);
        }
        if (action.getSource() == class6Button)
        {
            assignmentButton.setVisible(true);
            rosterButton.setVisible(true);
            gradebookButton.setVisible(true);
            class1Button.setVisible(false);
            class2Button.setVisible(false);
            class3Button.setVisible(false);
            class4Button.setVisible(false);
            class5Button.setVisible(false);
            class6Button.setVisible(false);
            class7Button.setVisible(false);
            classAddButton.setVisible(false);
            titleLabel.setVisible(false);
            classesButton.setVisible(true);
            
            classLabel.setText(class6Button.getText());
            size =  classLabel.getPreferredSize();
            classLabel.setBounds(50, 50, size.width, size.height);
            classLabel.setVisible(true);
        }
        if (action.getSource() == class7Button)
        {
            assignmentButton.setVisible(true);
            rosterButton.setVisible(true);
            gradebookButton.setVisible(true);
            class1Button.setVisible(false);
            class2Button.setVisible(false);
            class3Button.setVisible(false);
            class4Button.setVisible(false);
            class5Button.setVisible(false);
            class6Button.setVisible(false);
            class7Button.setVisible(false);
            classAddButton.setVisible(false);
            titleLabel.setVisible(false);
            classesButton.setVisible(true);
            
            classLabel.setText(class7Button.getText());
            size =  classLabel.getPreferredSize();
            classLabel.setBounds(50, 50, size.width, size.height);
            classLabel.setVisible(true);
        }
        if (action.getSource() == assignmentButton)
        {
            assignmentsPane.setVisible(true);
            gradebookPane.setVisible(false);
            assignmentButton.setVisible(false);
            rosterButton.setVisible(true);
            gradebookButton.setVisible(true);
            rosterPane.setVisible(false);
            assignmentAddButton.setVisible(true);
            rosterAddButton.setVisible(false);
            gradebookAddButton.setVisible(false);
            
            titleLabel.setText("Assignments");
            //titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
            size =  titleLabel.getPreferredSize();
            titleLabel.setBounds(200, 50, size.width, size.height);
            titleLabel.setVisible(true);
        }
        if (action.getSource() == rosterButton)
        {
            assignmentsPane.setVisible(false);
            gradebookPane.setVisible(false);
            assignmentButton.setVisible(true);
            rosterButton.setVisible(false);
            gradebookButton.setVisible(true);
            rosterPane.setVisible(true);
            assignmentAddButton.setVisible(false);
            rosterAddButton.setVisible(true);
            gradebookAddButton.setVisible(false);
            
            titleLabel.setText("Roster");
            //titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
            size =  titleLabel.getPreferredSize();
            titleLabel.setBounds(200, 50, size.width, size.height);
            titleLabel.setVisible(true);
        }
        if (action.getSource() == gradebookButton)
        {
            assignmentsPane.setVisible(false);
            rosterPane.setVisible(false);
            assignmentButton.setVisible(true);
            rosterButton.setVisible(true);
            gradebookButton.setVisible(false);
            gradebookPane.setVisible(true);
            assignmentAddButton.setVisible(false);
            rosterAddButton.setVisible(false);
            gradebookAddButton.setVisible(true);
            
            titleLabel.setText("Gradebook");
            //titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
            size =  titleLabel.getPreferredSize();
            titleLabel.setBounds(200, 50, size.width, size.height);
            titleLabel.setVisible(true);
        }
        if (action.getSource() == classesButton)
        {
            assignmentsPane.setVisible(false);
            assignmentButton.setVisible(false);
            rosterButton.setVisible(false);
            rosterPane.setVisible(false);
            gradebookButton.setVisible(false);
            gradebookPane.setVisible(false);
            assignmentAddButton.setVisible(false);
            rosterAddButton.setVisible(false);
            gradebookAddButton.setVisible(false);
            titleLabel.setText("Welcome, Professor " + teacher.get(0));
            size =  titleLabel.getPreferredSize();
            titleLabel.setBounds(50, 50, size.width, size.height);
            classesButton.setVisible(false);
            titleLabel.setVisible(true);
            if (class1Button.getText() != null)
                class1Button.setVisible(true);
            if (class2Button.getText() != null)
               class2Button.setVisible(true);
            if (class3Button.getText() != null)
                class3Button.setVisible(true);
            if (class4Button.getText() != null)    
                class4Button.setVisible(true);
            if (class5Button.getText() != null)    
                class5Button.setVisible(true);
            if (class6Button.getText() != null)    
                class6Button.setVisible(true);
            if (class7Button.getText() != null)    
                class7Button.setVisible(true);
            classAddButton.setVisible(true);
            classLabel.setVisible(false);
        }
    }
    
    public void mouseEntered(MouseEvent mouse){
        
    }
    
    public void mouseReleased(MouseEvent mouse){
        
    }
    
    public void mousePressed(MouseEvent mouse){
        
    }
    
    public void mouseClicked(MouseEvent mouse) {
        
    }
    
    public void mouseExited(MouseEvent mouse){
        
    }
    
    public void focusLost(FocusEvent focus){
       
    }
    
    public void focusGained(FocusEvent focus){
        
    }

    private JLabel loginPageLabel;
    private JLabel loginLabel;
    private JLabel incorrectLoginLabel;
    private JLabel helpLabel1;
    private JLabel titleLabel;
    private JLabel classLabel;

    private JButton helpButton;
    private JButton classAddButton;
    private JButton class1Button;
    private JButton class2Button;
    private JButton class3Button;
    private JButton class4Button;
    private JButton class5Button;
    private JButton class6Button;
    private JButton class7Button;
    private JButton assignmentAddButton;
    private JButton assignmentButton;
    private JButton rosterAddButton;
    private JButton rosterButton;
    private JButton gradebookAddButton;
    private JButton gradebookButton;
    private JButton classesButton;
    
    private JPasswordField pinField;

    
    private JTable assignmentsTable;
    private JTable rosterTable;
    private JTable gradebookTable;
    
    private JScrollPane assignmentsPane;
    private JScrollPane rosterPane;
    private JScrollPane gradebookPane;
    
    private JPanel inputWindow;
    
    private DefaultTableModel assignmentsTableModel;
    private DefaultTableModel rosterTableModel;
    private DefaultTableModel gradebookTableModel;
    private DefaultListModel<String> overviewPaneTabs;
    private DefaultListModel<String> classPaneTabs;

    private Dimension size;
    private int screenNumber;
}
