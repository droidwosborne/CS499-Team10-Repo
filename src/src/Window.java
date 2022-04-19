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
import java.io.IOException;
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
    Course course2 = reader.classDataIn("course2");
    Vector<Student> roster1 = course1.getRoster();
    Vector<Student> roster2 = course2.getRoster();
    Gradebook gradebook1 = course1.getGradebook();
    Gradebook gradebook2 = course2.getGradebook();
    Vector<Assignment> assignments1 = course1.getGradebook().getAssignments();
    Vector<Assignment> assignments2 = course2.getGradebook().getAssignments();
        
    CourseList cList = new CourseList();
    Vector<String> courses = cList.getCourses();
    WriteSave writer = new WriteSave();
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
        
        
        assignmentButton1 = new JButton("Assignments");
        size = assignmentButton1.getPreferredSize();
        assignmentButton1.addActionListener(this);
        assignmentButton1.setBounds(30,150,size.width,size.height+20);
        add(assignmentButton1);
        assignmentButton1.setVisible(false);
        
        assignmentButton2 = new JButton("Assignments");
        size = assignmentButton2.getPreferredSize();
        assignmentButton2.addActionListener(this);
        assignmentButton2.setBounds(30,150,size.width,size.height+20);
        add(assignmentButton2);
        assignmentButton2.setVisible(false);
        
        rosterButton1 = new JButton("Roster");
        size = rosterButton1.getPreferredSize();
        rosterButton1.addActionListener(this);
        rosterButton1.setBounds(30,200,size.width,size.height+20);
        add(rosterButton1);
        rosterButton1.setVisible(false);
        
        rosterButton2 = new JButton("Roster");
        size = rosterButton2.getPreferredSize();
        rosterButton2.addActionListener(this);
        rosterButton2.setBounds(30,200,size.width,size.height+20);
        add(rosterButton2);
        rosterButton2.setVisible(false);
        
        /*
         * Assignments Page
         */
        assignmentsTableModel1 = new DefaultTableModel();
        assignmentsTableModel1.addColumn("Name");
        assignmentsTableModel1.addColumn("Category");
        assignmentsTableModel1.addColumn("Maximum Score");
        assignmentsTableModel1.addRow(new Object[]{"","","",""});
        assignmentsTableModel1.addRow(new Object[]{"","","",""});
        assignmentsTableModel1.addRow(new Object[]{"","","",""});

        assignmentsTable1 = new JTable(assignmentsTableModel1);
        assignmentsTable1.setBackground(Color.lightGray);
        JTableHeader assignmentHeader1 = assignmentsTable1.getTableHeader();
        assignmentHeader1.setBackground(Color.white);
        assignmentHeader1.setFont(new Font("Serif", Font.BOLD, 14));
        assignmentsPane1 = new JScrollPane(assignmentsTable1);
        assignmentsPane1.setBounds(200, 100, 480, 250);
        add(assignmentsPane1);
        assignmentsPane1.setVisible(false);
        
        assignmentsTableModel2 = new DefaultTableModel();
        assignmentsTableModel2.addColumn("Name");
        assignmentsTableModel2.addColumn("Category");
        assignmentsTableModel2.addColumn("Maximum Score");
        assignmentsTableModel2.addRow(new Object[]{"","","",""});
        assignmentsTableModel2.addRow(new Object[]{"","","",""});
        assignmentsTableModel2.addRow(new Object[]{"","","",""});

        assignmentsTable2 = new JTable(assignmentsTableModel2);
        assignmentsTable2.setBackground(Color.lightGray);
        JTableHeader assignmentHeader2 = assignmentsTable2.getTableHeader();
        assignmentHeader2.setBackground(Color.white);
        assignmentHeader2.setFont(new Font("Serif", Font.BOLD, 14));
        assignmentsPane2 = new JScrollPane(assignmentsTable2);
        assignmentsPane2.setBounds(200, 100, 480, 250);
        add(assignmentsPane2);
        assignmentsPane2.setVisible(false);
        
        
        assignmentAddButton1 = new JButton("+");
        assignmentAddButton1.setFont(new Font("Serif", Font.BOLD, 20));
        size = assignmentAddButton1.getPreferredSize();
        assignmentAddButton1.addActionListener(this);
        assignmentAddButton1.setBounds(70,150,size.width-35,size.height);
        add(assignmentAddButton1);
        assignmentAddButton1.setVisible(false);
        
        assignmentAddButton2 = new JButton("+");
        assignmentAddButton2.setFont(new Font("Serif", Font.BOLD, 20));
        size = assignmentAddButton2.getPreferredSize();
        assignmentAddButton2.addActionListener(this);
        assignmentAddButton2.setBounds(70,150,size.width-35,size.height);
        add(assignmentAddButton2);
        assignmentAddButton2.setVisible(false);
        
        
        /*
         * Roster Page
         */
        rosterTableModel1 = new DefaultTableModel();
        rosterTableModel1.addColumn("First Name");
        rosterTableModel1.addColumn("Last Name");
        rosterTableModel1.addColumn("Student ID");
        rosterTableModel1.addColumn("Average");
        rosterTableModel1.addRow(new Object[]{"","","",""});
        rosterTableModel1.addRow(new Object[]{"","","",""});
        rosterTableModel1.addRow(new Object[]{"","","",""});

        rosterTable1 = new JTable(rosterTableModel1);
        rosterTable1.setBackground(Color.lightGray);
        JTableHeader studentHeader1 = rosterTable1.getTableHeader();
        studentHeader1.setBackground(Color.white);
        studentHeader1.setFont(new Font("Serif", Font.BOLD, 14));
        rosterPane1 = new JScrollPane(rosterTable1);
        rosterPane1.setBounds(200, 100, 550, 250);
        add(rosterPane1);
        rosterPane1.setVisible(false);
        
        rosterTableModel2 = new DefaultTableModel();
        rosterTableModel2.addColumn("First Name");
        rosterTableModel2.addColumn("Last Name");
        rosterTableModel2.addColumn("Student ID");
        rosterTableModel2.addColumn("Average");
        rosterTableModel2.addRow(new Object[]{"","","",""});
        rosterTableModel2.addRow(new Object[]{"","","",""});
        rosterTableModel2.addRow(new Object[]{"","","",""});

        rosterTable2 = new JTable(rosterTableModel2);
        rosterTable2.setBackground(Color.lightGray);
        JTableHeader studentHeader = rosterTable2.getTableHeader();
        studentHeader.setBackground(Color.white);
        studentHeader.setFont(new Font("Serif", Font.BOLD, 14));
        rosterPane2 = new JScrollPane(rosterTable2);
        rosterPane2.setBounds(200, 100, 550, 250);
        add(rosterPane2);
        rosterPane2.setVisible(false);
        
        rosterAddButton1 = new JButton("+");
        rosterAddButton1.setFont(new Font("Serif", Font.BOLD, 20));
        size = rosterAddButton1.getPreferredSize();
        rosterAddButton1.addActionListener(this);
        rosterAddButton1.setBounds(100,200,size.width-35,size.height);
        add(rosterAddButton1);
        rosterAddButton1.setVisible(false);
        
        rosterAddButton2 = new JButton("+");
        rosterAddButton2.setFont(new Font("Serif", Font.BOLD, 20));
        size = rosterAddButton2.getPreferredSize();
        rosterAddButton2.addActionListener(this);
        rosterAddButton2.setBounds(100,200,size.width-35,size.height);
        add(rosterAddButton2);
        rosterAddButton2.setVisible(false);
        
        nameLabel = new JLabel(" First Name:                   Last Name:");
        size = nameLabel.getPreferredSize();
        nameLabel.setBounds(50, 380, size.width, size.height);
        nameLabel.setVisible(false);
        add(nameLabel);
        
        firstNameEntry = new JTextField(10);
        firstNameEntry.addActionListener(this);
        size = firstNameEntry.getPreferredSize();
        firstNameEntry.setBounds(50, 400, size.width, size.height);
        firstNameEntry.setVisible(false);
        add(firstNameEntry);
        
        lastNameEntry = new JTextField(10);
        lastNameEntry.addActionListener(this);
        size = lastNameEntry.getPreferredSize();
        lastNameEntry.setBounds(200, 400, size.width, size.height);
        lastNameEntry.setVisible(false);
        add(lastNameEntry);
        
        
        gradebookButton1 = new JButton("Gradebook");
        size = gradebookButton1.getPreferredSize();
        gradebookButton1.addActionListener(this);
        gradebookButton1.setBounds(30,250,size.width,size.height+20);
        add(gradebookButton1);
        gradebookButton1.setVisible(false);
        
        gradebookButton2 = new JButton("Gradebook");
        size = gradebookButton2.getPreferredSize();
        gradebookButton2.addActionListener(this);
        gradebookButton2.setBounds(30,250,size.width,size.height+20);
        add(gradebookButton2);
        gradebookButton2.setVisible(false);
        
        
        /*
         * Gradebook Page
         */
        gradebookTableModel1 = new DefaultTableModel();
        gradebookTableModel1.addColumn("Assignment");
        gradebookTableModel1.addColumn("Scale");
        gradebookTableModel1.addColumn("Category Name");
        gradebookTableModel1.addColumn("ID");
        gradebookTableModel1.addColumn("Weight");
        gradebookTableModel1.addRow(new Object[]{"","","","",""});
        gradebookTableModel1.addRow(new Object[]{"","","","",""});
        gradebookTableModel1.addRow(new Object[]{"","","","",""});

        gradebookTable1 = new JTable(gradebookTableModel1);
        gradebookTable1.setBackground(Color.lightGray);
        JTableHeader gradebookHeader1 = gradebookTable1.getTableHeader();
        gradebookHeader1.setBackground(Color.white);
        gradebookHeader1.setFont(new Font("Serif", Font.BOLD, 14));
        gradebookPane1 = new JScrollPane(gradebookTable1);
        gradebookPane1.setBounds(200, 100, 650, 250);
        add(gradebookPane1);
        gradebookPane1.setVisible(false);
        
        gradebookTableModel2 = new DefaultTableModel();
        gradebookTableModel2.addColumn("Assignment");
        gradebookTableModel2.addColumn("Scale");
        gradebookTableModel2.addColumn("Category Name");
        gradebookTableModel2.addColumn("ID");
        gradebookTableModel2.addColumn("Weight");
        gradebookTableModel2.addRow(new Object[]{"","","","",""});
        gradebookTableModel2.addRow(new Object[]{"","","","",""});
        gradebookTableModel2.addRow(new Object[]{"","","","",""});

        gradebookTable2 = new JTable(gradebookTableModel2);
        gradebookTable2.setBackground(Color.lightGray);
        JTableHeader gradebookHeader2 = gradebookTable2.getTableHeader();
        gradebookHeader2.setBackground(Color.white);
        gradebookHeader2.setFont(new Font("Serif", Font.BOLD, 14));
        gradebookPane2 = new JScrollPane(gradebookTable2);
        gradebookPane2.setBounds(200, 100, 650, 250);
        add(gradebookPane2);
        gradebookPane2.setVisible(false);
        
        
        gradebookAddButton1 = new JButton("+");
        gradebookAddButton1.setFont(new Font("Serif", Font.BOLD, 20));
        size = gradebookAddButton1.getPreferredSize();
        gradebookAddButton1.addActionListener(this);
        gradebookAddButton1.setBounds(70,250,size.width-35,size.height);
        add(gradebookAddButton1);
        gradebookAddButton1.setVisible(false);
        
        gradebookAddButton2 = new JButton("+");
        gradebookAddButton2.setFont(new Font("Serif", Font.BOLD, 20));
        size = gradebookAddButton2.getPreferredSize();
        gradebookAddButton2.addActionListener(this);
        gradebookAddButton2.setBounds(70,250,size.width-35,size.height);
        add(gradebookAddButton2);
        gradebookAddButton2.setVisible(false);
        
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
        
        rosterChangesButton1 = new JButton("Submit");
        size = rosterChangesButton1.getPreferredSize();
        rosterChangesButton1.addActionListener(this);
        rosterChangesButton1.setBounds(350,400,size.width,size.height+20);
        add(rosterChangesButton1);
        rosterChangesButton1.setVisible(false);
        
        rosterChangesButton2 = new JButton("Submit");
        size = rosterChangesButton2.getPreferredSize();
        rosterChangesButton2.addActionListener(this);
        rosterChangesButton2.setBounds(350,400,size.width,size.height+20);
        add(rosterChangesButton2);
        rosterChangesButton2.setVisible(false);
        
        /*inputWindow = new JPanel();
        inputWindow.setLayout(null);
        Dimension smallerDim = new Dimension(200,100);
        inputWindow.setMinimumSize(smallerDim);
        inputWindow.setPreferredSize(smallerDim);
        //setLayout(null);
        inputWindow.setBackground(Color.lightGray);
        inputWindow.setBounds(300,200, 500,300);
        add(inputWindow);*/
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
            //inputWindow.setVisible(true);
        }
        if (action.getSource() == class1Button)
        {
            assignmentButton1.setVisible(true);
            rosterButton1.setVisible(true);
            gradebookButton1.setVisible(true);
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
            /*for (int i = 0; i < assignments1.size(); i++)
        {
            
        }*/
            for (int i = 0; i < assignments1.size(); i++)
            {
                assignmentsTable1.setValueAt(assignments1.get(i).getName(),i,0);
                assignmentsTable1.setValueAt(assignments1.get(i).getCategory(),i,1);
                assignmentsTable1.setValueAt(assignments1.get(i).getMaxScore(),i,2);
                
                rosterTable1.setValueAt(roster1.get(i).getFirstName(),i,0);
                rosterTable1.setValueAt(roster1.get(i).getLastName(),i,1);
                rosterTable1.setValueAt(roster1.get(i).getID(),i,2);
                rosterTable1.setValueAt(roster1.get(i).getOverallGrade(),i,3);
                
                gradebookTable1.setValueAt(assignments1.get(i).getName(),i,0);
                gradebookTable1.setValueAt(gradebook1.getScale()[i],i,1);
                gradebookTable1.setValueAt(gradebook1.getCatNames().get(i),i,2);
                gradebookTable1.setValueAt(gradebook1.getCatID().get(i),i,3);
                gradebookTable1.setValueAt(gradebook1.getCatWeights().get(i),i,4);
            }
        }
        if (action.getSource() == class2Button)
        {
            assignmentButton2.setVisible(true);
            rosterButton2.setVisible(true);
            gradebookButton2.setVisible(true);
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
            
            for (int i = 0; i < assignments2.size(); i++)
            {
                assignmentsTable2.setValueAt(assignments2.get(i).getName(),i,0);
                assignmentsTable2.setValueAt(assignments2.get(i).getCategory(),i,1);
                assignmentsTable2.setValueAt(assignments2.get(i).getMaxScore(),i,2);
                
                rosterTable2.setValueAt(roster2.get(i).getFirstName(),i,0);
                rosterTable2.setValueAt(roster2.get(i).getLastName(),i,1);
                rosterTable2.setValueAt(roster2.get(i).getID(),i,2);
                rosterTable2.setValueAt(roster2.get(i).getOverallGrade(),i,3);
                
                gradebookTable2.setValueAt(assignments2.get(i).getName(),i,0);
                gradebookTable2.setValueAt(gradebook2.getScale()[i],i,1);
                gradebookTable2.setValueAt(gradebook2.getCatNames().get(i),i,2);
                gradebookTable2.setValueAt(gradebook2.getCatID().get(i),i,3);
                gradebookTable2.setValueAt(gradebook2.getCatWeights().get(i),i,4);
            }
        }
        if (action.getSource() == class3Button)
        {
            assignmentButton1.setVisible(true);
            rosterButton1.setVisible(true);
            gradebookButton1.setVisible(true);
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
            assignmentButton1.setVisible(true);
            rosterButton1.setVisible(true);
            gradebookButton1.setVisible(true);
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
            assignmentButton1.setVisible(true);
            rosterButton1.setVisible(true);
            gradebookButton1.setVisible(true);
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
            assignmentButton1.setVisible(true);
            rosterButton1.setVisible(true);
            gradebookButton1.setVisible(true);
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
            assignmentButton1.setVisible(true);
            rosterButton1.setVisible(true);
            gradebookButton1.setVisible(true);
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
        if (action.getSource() == assignmentButton1)
        {
            assignmentsPane1.setVisible(true);
            gradebookPane1.setVisible(false);
            assignmentButton1.setVisible(false);
            rosterButton1.setVisible(true);
            gradebookButton1.setVisible(true);
            rosterPane1.setVisible(false);
            assignmentAddButton1.setVisible(true);
            rosterAddButton1.setVisible(false);
            gradebookAddButton1.setVisible(false);
            
            titleLabel.setText("Assignments");
            //titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
            size =  titleLabel.getPreferredSize();
            titleLabel.setBounds(200, 50, size.width, size.height);
            titleLabel.setVisible(true);
            nameLabel.setVisible(false);
            firstNameEntry.setVisible(false);
            lastNameEntry.setVisible(false);
            rosterChangesButton1.setVisible(false);
            rosterChangesButton2.setVisible(false);
        }
        if (action.getSource() == assignmentButton2)
        {
            assignmentsPane2.setVisible(true);
            gradebookPane2.setVisible(false);
            assignmentButton2.setVisible(false);
            rosterButton2.setVisible(true);
            gradebookButton2.setVisible(true);
            rosterPane2.setVisible(false);
            assignmentAddButton2.setVisible(true);
            rosterAddButton2.setVisible(false);
            gradebookAddButton2.setVisible(false);
            
            titleLabel.setText("Assignments");
            //titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
            size =  titleLabel.getPreferredSize();
            titleLabel.setBounds(200, 50, size.width, size.height);
            titleLabel.setVisible(true);
            nameLabel.setVisible(false);
            firstNameEntry.setVisible(false);
            lastNameEntry.setVisible(false);
            rosterChangesButton1.setVisible(false);
            rosterChangesButton2.setVisible(false);
        }
        if (action.getSource() == rosterButton1)
        {
            assignmentsPane1.setVisible(false);
            gradebookPane1.setVisible(false);
            assignmentButton1.setVisible(true);
            rosterButton1.setVisible(false);
            gradebookButton1.setVisible(true);
            rosterPane1.setVisible(true);
            assignmentAddButton1.setVisible(false);
            rosterAddButton1.setVisible(true);
            gradebookAddButton1 .setVisible(false);
            
            titleLabel.setText("Roster");
            //titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
            size =  titleLabel.getPreferredSize();
            titleLabel.setBounds(200, 50, size.width, size.height);
            titleLabel.setVisible(true);
        }
        if (action.getSource() == rosterButton2)
        {
            assignmentsPane2.setVisible(false);
            gradebookPane2.setVisible(false);
            assignmentButton2.setVisible(true);
            rosterButton2.setVisible(false);
            gradebookButton2.setVisible(true);
            rosterPane2.setVisible(true);
            assignmentAddButton2.setVisible(false);
            rosterAddButton2.setVisible(true);
            gradebookAddButton2.setVisible(false);
            
            titleLabel.setText("Roster");
            //titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
            size =  titleLabel.getPreferredSize();
            titleLabel.setBounds(200, 50, size.width, size.height);
            titleLabel.setVisible(true);
        }
        if (action.getSource() == gradebookButton1)
        {
            assignmentsPane1.setVisible(false);
            rosterPane1.setVisible(false);
            assignmentButton1.setVisible(true);
            rosterButton1.setVisible(true);
            gradebookButton1.setVisible(false);
            gradebookPane1.setVisible(true);
            assignmentAddButton1.setVisible(false);
            rosterAddButton1.setVisible(false);
            gradebookAddButton1.setVisible(true);
            
            titleLabel.setText("Gradebook");
            //titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
            size =  titleLabel.getPreferredSize();
            titleLabel.setBounds(200, 50, size.width, size.height);
            titleLabel.setVisible(true);
            nameLabel.setVisible(false);
            firstNameEntry.setVisible(false);
            lastNameEntry.setVisible(false);
            rosterChangesButton1.setVisible(false);
            rosterChangesButton2.setVisible(false);
        }
        if (action.getSource() == gradebookButton2)
        {
            assignmentsPane2.setVisible(false);
            rosterPane2.setVisible(false);
            assignmentButton2.setVisible(true);
            rosterButton2.setVisible(true);
            gradebookButton2.setVisible(false);
            gradebookPane1.setVisible(true);
            assignmentAddButton2.setVisible(false);
            rosterAddButton2.setVisible(false);
            gradebookAddButton2.setVisible(true);
            
            titleLabel.setText("Gradebook");
            //titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
            size =  titleLabel.getPreferredSize();
            titleLabel.setBounds(200, 50, size.width, size.height);
            titleLabel.setVisible(true);
            nameLabel.setVisible(false);
            firstNameEntry.setVisible(false);
            lastNameEntry.setVisible(false);
            rosterChangesButton1.setVisible(false);
            rosterChangesButton2.setVisible(false);
        }
        if (action.getSource() == classesButton)
        {
            assignmentsPane1.setVisible(false);
            assignmentsPane2.setVisible(false);
            assignmentButton1.setVisible(false);
            rosterButton1.setVisible(false);
            rosterPane1.setVisible(false);
            rosterPane2.setVisible(false);
            gradebookButton1.setVisible(false);
            gradebookPane1.setVisible(false);
            gradebookPane2.setVisible(false);
            assignmentAddButton1.setVisible(false);
            rosterAddButton1.setVisible(false);
            gradebookAddButton1.setVisible(false);
            assignmentButton2.setVisible(false);
            rosterButton2.setVisible(false);
            gradebookButton2.setVisible(false);
            assignmentAddButton2.setVisible(false);
            rosterAddButton2.setVisible(false);
            gradebookAddButton2.setVisible(false);
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
            nameLabel.setVisible(false);
            firstNameEntry.setVisible(false);
            lastNameEntry.setVisible(false);
            rosterChangesButton1.setVisible(false);
            rosterChangesButton2.setVisible(false);
        }
        if (action.getSource() == rosterAddButton1)
        {
            nameLabel.setVisible(true);
            firstNameEntry.setVisible(true);
            lastNameEntry.setVisible(true);
            firstNameEntry.setText("");
            lastNameEntry.setText("");
            rosterChangesButton1.setVisible(true);
        }
        if (action.getSource() == rosterAddButton2)
        {
            nameLabel.setVisible(true);
            firstNameEntry.setVisible(true);
            lastNameEntry.setVisible(true);
            firstNameEntry.setText("");
            lastNameEntry.setText("");
            rosterChangesButton2.setVisible(true);
        }
        if (action.getSource() == firstNameEntry)
        {
            
        }
        if (action.getSource() == lastNameEntry)
        {
            
        }
        if(action.getSource() ==  rosterChangesButton1)
        {
            rosterTableModel1.addRow(new Object[]{"","","",""});
            String firstName = firstNameEntry.getText();
            String lastName = lastNameEntry.getText();
            course1.addStudent(firstName, lastName);
            rosterTable1.setValueAt(firstName,rosterTableModel1.getRowCount()-1,0);
            rosterTable1.setValueAt(lastName,rosterTableModel1.getRowCount()-1,1);
            rosterTable1.setValueAt(roster1.get(rosterTableModel1.getRowCount()-1).getID(),rosterTableModel1.getRowCount()-1,2);
            rosterTable1.setValueAt(roster1.get(rosterTableModel1.getRowCount()-1).getOverallGrade(),rosterTableModel1.getRowCount()-1,3);
            
            try{
                writer.saveCourse(course1, "course1");
            } catch (IOException e){
                System.out.println("error saving");
            }
        }
        if(action.getSource() ==  rosterChangesButton2)
        {
            rosterTableModel2.addRow(new Object[]{"","","",""});
            String firstName = firstNameEntry.getText();
            String lastName = lastNameEntry.getText();
            course2.addStudent(firstName, lastName);
            rosterTable2.setValueAt(firstName,rosterTableModel2.getRowCount()-1,0);
            rosterTable2.setValueAt(lastName,rosterTableModel2.getRowCount()-1,1);
            rosterTable2.setValueAt(roster2.get(rosterTableModel2.getRowCount()-1).getID(),rosterTableModel2.getRowCount()-1,2);
            rosterTable2.setValueAt(roster2.get(rosterTableModel2.getRowCount()-1).getOverallGrade(),rosterTableModel2.getRowCount()-1,3);
            
            try{
                writer.saveCourse(course2, "course2");
            } catch (IOException e){
                System.out.println("error saving");
            }
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
    private JLabel nameLabel;

    private JButton helpButton;
    private JButton classAddButton;
    private JButton class1Button;
    private JButton class2Button;
    private JButton class3Button;
    private JButton class4Button;
    private JButton class5Button;
    private JButton class6Button;
    private JButton class7Button;
    private JButton assignmentAddButton1;
    private JButton assignmentAddButton2;
    private JButton assignmentButton1;
    private JButton assignmentButton2;
    private JButton rosterAddButton1;
    private JButton rosterAddButton2;
    private JButton rosterButton1;
    private JButton rosterButton2;
    private JButton gradebookAddButton1;
    private JButton gradebookAddButton2;
    private JButton gradebookButton1;
    private JButton gradebookButton2;
    private JButton classesButton;
    private JButton rosterChangesButton1;
    private JButton rosterChangesButton2;
    
    private JPasswordField pinField;

    private JTextField firstNameEntry;
    private JTextField lastNameEntry;
    
    private JTable assignmentsTable1;
    private JTable assignmentsTable2;
    private JTable rosterTable1;
    private JTable rosterTable2;
    private JTable gradebookTable1;
    private JTable gradebookTable2;
    
    private JScrollPane assignmentsPane1;
    private JScrollPane assignmentsPane2;
    private JScrollPane rosterPane1;
    private JScrollPane rosterPane2;
    private JScrollPane gradebookPane1;
    private JScrollPane gradebookPane2;
    
    //private JPanel inputWindow;
    
    private DefaultTableModel assignmentsTableModel1;
    private DefaultTableModel assignmentsTableModel2;
    private DefaultTableModel rosterTableModel1;
    private DefaultTableModel rosterTableModel2;
    private DefaultTableModel gradebookTableModel1;
    private DefaultTableModel gradebookTableModel2;
    private DefaultListModel<String> overviewPaneTabs;
    private DefaultListModel<String> classPaneTabs;

    private Dimension size;
    private int screenNumber;
}
