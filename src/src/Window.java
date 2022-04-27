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
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import src.SaveHandling.*;
import java.io.IOException;
import java.lang.ArrayIndexOutOfBoundsException;
import java.security.NoSuchAlgorithmException;
import java.util.Vector;


/*
 * Class declaration for Window class extending JPanel for component placement and
 * implementing ActionListener to allow for actions on button click and text entry.
 */
public class Window extends JPanel implements ActionListener{

    //User password for identification (currently set to work automatically for easy testing)
    //CourseList clTest = new CourseList();
    //System.out.println(clTest.getTeacherLName());
    String password = "testPword";
    float oldGrade = 0.0F;
    float newGrade = 0.0F;
    int gradeChange = -1;
    
    //public char[] passwordA = new char[password.length()];
    Font labelFont = new Font("Serif", Font.PLAIN, 16);
    
    ReadSave reader = new ReadSave();
    Vector<String> teacher = new Vector <String> (reader.teacherIn());
    //Course course1 = reader.classDataIn("course1");
    //Course course2 = reader.classDataIn("course2");
    Course courseWhatIf1 = reader.classDataIn("course1");
    //Vector<Student> roster1 = course1.getRoster();
    //Vector<Student> roster2 = course2.getRoster();
    Vector<Student> rosterWhatIf1 = courseWhatIf1.getRoster();
    //Gradebook gradebook1 = course1.getGradebook();
    //Gradebook gradebook2 = course2.getGradebook();
    Gradebook gradebookWhatIf1 = courseWhatIf1.getGradebook();
    //Vector<Assignment> assignments1 = course1.getGradebook().getAssignments();
    //Vector<Assignment> assignments2 = course2.getGradebook().getAssignments();
    Vector<Assignment> assignmentsWhatIf1 = courseWhatIf1.getGradebook().getAssignments();
    
    Vector<Course> courseCList = new Vector<Course>();
     
    CourseList cList = new CourseList();
    Vector<String> courses = cList.getCourses();
    WriteSave writer = new WriteSave();
    
    
    
    //Constructor for Window class consisting of components in Panel
    public Window () {
        
        for(int i = 0; i  < courses.size(); i++){

            courseCList.add(reader.classDataIn(courses.get(i)));

        }
        
        //Arbitrary size and color appearance of the window
        Dimension dim = new Dimension(900, 500);
        setMinimumSize(dim);
        setPreferredSize(dim);
        setLayout(null);
        setBackground(Color.lightGray);
        screenNumber = -1;
        
        cList.setPassword(password);
//        if (courses.size() >= 1)
//            course1.setCourseName(courses.get(0));
//        else
//            course1.setCourseName(null);
        
        /*
         * Login Page
         */
        loginPageLabel = new JLabel("Login into Gradebook");
        loginPageLabel.setFont(labelFont);
        size = loginPageLabel.getPreferredSize();
        loginPageLabel.setBounds(300, 120, size.width, size.height);
        add(loginPageLabel);

        loginLabel = new JLabel("Password:");
        loginLabel.setFont(labelFont);
        size = loginLabel.getPreferredSize();
        loginLabel.setBounds(150, 200, size.width, size.height);
        add(loginLabel);
        
        changeLabel = new JLabel("Repeat Password:");
        changeLabel.setFont(labelFont);
        size = changeLabel.getPreferredSize();
        changeLabel.setBounds(100, 240, size.width, size.height);
        add(changeLabel);
        changeLabel.setVisible(false);

        incorrectLoginLabel = new JLabel();
        incorrectLoginLabel.setFont(labelFont);
        add(incorrectLoginLabel);
        incorrectLoginLabel.setVisible(false);

        helpLabel1 = new JLabel ("<html>Input password for user login <br>to access gradebook.</html>");
        helpLabel1.setFont(labelFont);
        size =  helpLabel1.getPreferredSize();
        helpLabel1.setBounds(700, 200, size.width, size.height);
        add(helpLabel1);
        helpLabel1.setVisible(false);
        
        passwordField = new JPasswordField(30);
        size = passwordField.getPreferredSize();
        passwordField.addActionListener(this);
        passwordField.setText(password);
        passwordField.setBounds(250,195,size.width,size.height);
        add(passwordField);
        
        changeField = new JPasswordField(30);
        size = changeField.getPreferredSize();
        changeField.addActionListener(this);
        changeField.setText(password);
        changeField.setBounds(250,235,size.width,size.height);
        add(changeField);
        changeField.setVisible(false);
        
        changePwordButton = new JButton("Change Password");
        size = changePwordButton.getPreferredSize();
        changePwordButton.addActionListener(this);
        changePwordButton.setBounds(300,270,size.width,size.height+20);
        add(changePwordButton);
        
        
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
        
        whatIfLabel = new JLabel("What-If");
        whatIfLabel.setFont(new Font("Serif", Font.BOLD, 24));
        size =  whatIfLabel.getPreferredSize();
        whatIfLabel.setBounds(50, 100, size.width, size.height);
        add(whatIfLabel);
        whatIfLabel.setVisible(false);
        
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
        
        /*assignmentButton2 = new JButton("Assignments");
        size = assignmentButton2.getPreferredSize();
        assignmentButton2.addActionListener(this);
        assignmentButton2.setBounds(30,150,size.width,size.height+20);
        add(assignmentButton2);
        assignmentButton2.setVisible(false);*/
        
        assignmentWhatIfButton1 = new JButton("Assignments");
        size = assignmentWhatIfButton1.getPreferredSize();
        assignmentWhatIfButton1.addActionListener(this);
        assignmentWhatIfButton1.setBounds(30,150,size.width,size.height+20);
        add(assignmentWhatIfButton1);
        assignmentWhatIfButton1.setVisible(false);
        
        rosterButton1 = new JButton("Roster");
        size = rosterButton1.getPreferredSize();
        rosterButton1.addActionListener(this);
        rosterButton1.setBounds(30,200,size.width,size.height+20);
        add(rosterButton1);
        rosterButton1.setVisible(false);
        
        /*rosterButton2 = new JButton("Roster");
        size = rosterButton2.getPreferredSize();
        rosterButton2.addActionListener(this);
        rosterButton2.setBounds(30,200,size.width,size.height+20);
        add(rosterButton2);
        rosterButton2.setVisible(false);*/
        
        rosterWhatIfButton1 = new JButton("Roster");
        size = rosterWhatIfButton1.getPreferredSize();
        rosterWhatIfButton1.addActionListener(this);
        rosterWhatIfButton1.setBounds(30,200,size.width,size.height+20);
        add(rosterWhatIfButton1);
        rosterWhatIfButton1.setVisible(false);
        
        /*
         * Assignments Page
         */
        assignmentsTableModel1 = new DefaultTableModel();
        assignmentsTableModel1.addColumn("Name");
        assignmentsTableModel1.addColumn("Category");
        assignmentsTableModel1.addColumn("Max Score");
        assignmentsTableModel1.addRow(new Object[]{"","","",""});
        assignmentsTableModel1.addRow(new Object[]{"","","",""});
        assignmentsTableModel1.addRow(new Object[]{"","","",""});

        assignmentsTable1 = new JTable(assignmentsTableModel1);
        assignmentsTable1.setBackground(Color.lightGray);
        JTableHeader assignmentHeader1 = assignmentsTable1.getTableHeader();
        assignmentHeader1.setBackground(Color.white);
        assignmentHeader1.setFont(new Font("Serif", Font.BOLD, 14));
        assignmentsPane1 = new JScrollPane(assignmentsTable1);
        assignmentsPane1.setBounds(200, 100, 300, 250);
        add(assignmentsPane1);
        assignmentsPane1.setVisible(false);
        
        assignmentsWhatIfTableModel1 = new DefaultTableModel();
        assignmentsWhatIfTableModel1.addColumn("Name");
        assignmentsWhatIfTableModel1.addColumn("Category");
        assignmentsWhatIfTableModel1.addColumn("Max Score");
        assignmentsWhatIfTableModel1.addRow(new Object[]{"","","",""});
        assignmentsWhatIfTableModel1.addRow(new Object[]{"","","",""});
        assignmentsWhatIfTableModel1.addRow(new Object[]{"","","",""});

        assignmentsWhatIfTable1 = new JTable(assignmentsWhatIfTableModel1);
        assignmentsWhatIfTable1.setBackground(Color.lightGray);
        JTableHeader assignmentWhatIfHeader1 = assignmentsWhatIfTable1.getTableHeader();
        assignmentWhatIfHeader1.setBackground(Color.white);
        assignmentWhatIfHeader1.setFont(new Font("Serif", Font.BOLD, 14));
        assignmentsWhatIfPane1 = new JScrollPane(assignmentsWhatIfTable1);
        assignmentsWhatIfPane1.setBounds(200, 100, 300, 250);
        add(assignmentsWhatIfPane1);
        assignmentsWhatIfPane1.setVisible(false);
        
        /*assignmentsTableModel2 = new DefaultTableModel();
        assignmentsTableModel2.addColumn("Name");
        assignmentsTableModel2.addColumn("Category");
        assignmentsTableModel2.addColumn("Max Score");
        assignmentsTableModel2.addRow(new Object[]{"","","",""});
        assignmentsTableModel2.addRow(new Object[]{"","","",""});
        assignmentsTableModel2.addRow(new Object[]{"","","",""});

        assignmentsTable2 = new JTable(assignmentsTableModel2);
        assignmentsTable2.setBackground(Color.lightGray);
        JTableHeader assignmentHeader2 = assignmentsTable2.getTableHeader();
        assignmentHeader2.setBackground(Color.white);
        assignmentHeader2.setFont(new Font("Serif", Font.BOLD, 14));
        assignmentsPane2 = new JScrollPane(assignmentsTable2);
        assignmentsPane2.setBounds(200, 100, 300, 250);
        add(assignmentsPane2);
        assignmentsPane2.setVisible(false);*/
        
        
        assignmentAddButton1 = new JButton("+");
        assignmentAddButton1.setFont(new Font("Serif", Font.BOLD, 20));
        size = assignmentAddButton1.getPreferredSize();
        assignmentAddButton1.addActionListener(this);
        assignmentAddButton1.setBounds(370,50,size.width-35,size.height);
        add(assignmentAddButton1);
        assignmentAddButton1.setVisible(false);
        
        /*assignmentAddButton2 = new JButton("+");
        assignmentAddButton2.setFont(new Font("Serif", Font.BOLD, 20));
        size = assignmentAddButton2.getPreferredSize();
        assignmentAddButton2.addActionListener(this);
        assignmentAddButton2.setBounds(370,50,size.width-35,size.height);
        add(assignmentAddButton2);
        assignmentAddButton2.setVisible(false);*/
        
        assignmentDeleteButton1 = new JButton("-");
        assignmentDeleteButton1.setFont(new Font("Serif", Font.BOLD, 20));
        size = assignmentDeleteButton1.getPreferredSize();
        assignmentDeleteButton1.addActionListener(this);
        assignmentDeleteButton1.setBounds(420,50,size.width-35,size.height);
        add(assignmentDeleteButton1);
        assignmentDeleteButton1.setVisible(false);
        
        assignmentWhatIfAddButton1 = new JButton("+");
        assignmentWhatIfAddButton1.setFont(new Font("Serif", Font.BOLD, 20));
        size = assignmentWhatIfAddButton1.getPreferredSize();
        assignmentWhatIfAddButton1.addActionListener(this);
        assignmentWhatIfAddButton1.setBounds(370,50,size.width-35,size.height);
        add(assignmentWhatIfAddButton1);
        assignmentWhatIfAddButton1.setVisible(false);
        
        assignmentLabel = new JLabel(" Name:                           Category:                       Score:");
        size = assignmentLabel.getPreferredSize();
        assignmentLabel.setBounds(50, 380, size.width, size.height);
        assignmentLabel.setVisible(false);
        add(assignmentLabel);
        
        assignmentName = new JTextField(10);
        assignmentName.addActionListener(this);
        size = assignmentName.getPreferredSize();
        assignmentName.setBounds(50, 400, size.width, size.height);
        assignmentName.setVisible(false);
        add(assignmentName);
        
        assignmentCategory = new JTextField(10);
        assignmentCategory.addActionListener(this);
        size = assignmentCategory.getPreferredSize();
        assignmentCategory.setBounds(200, 400, size.width, size.height);
        assignmentCategory.setVisible(false);
        add(assignmentCategory);
        
        assignmentScore = new JTextField(3);
        assignmentScore.addActionListener(this);
        size = assignmentScore.getPreferredSize();
        assignmentScore.setBounds(350, 400, size.width, size.height);
        assignmentScore.setVisible(false);
        add(assignmentScore);
        
        assignmentChangesButton1 = new JButton("Submit");
        size = assignmentChangesButton1.getPreferredSize();
        assignmentChangesButton1.addActionListener(this);
        assignmentChangesButton1.setBounds(420,400,size.width,size.height+20);
        add(assignmentChangesButton1);
        assignmentChangesButton1.setVisible(false);
        
        /*assignmentChangesButton2 = new JButton("Submit");
        size = assignmentChangesButton2.getPreferredSize();
        assignmentChangesButton2.addActionListener(this);
        assignmentChangesButton2.setBounds(420,400,size.width,size.height+20);
        add(assignmentChangesButton2);
        assignmentChangesButton2.setVisible(false);*/
        
        assignmentWhatIfChangesButton1 = new JButton("Submit");
        size = assignmentWhatIfChangesButton1.getPreferredSize();
        assignmentWhatIfChangesButton1.addActionListener(this);
        assignmentWhatIfChangesButton1.setBounds(420,400,size.width,size.height+20);
        add(assignmentWhatIfChangesButton1);
        assignmentWhatIfChangesButton1.setVisible(false);
        
        scoreButton1 = new JButton("Edit Scales");
        size = scoreButton1.getPreferredSize();
        scoreButton1.addActionListener(this);
        scoreButton1.setBounds(30,300,size.width,size.height+10);
        add(scoreButton1);
        scoreButton1.setVisible(false);
        
        /*scoreButton2 = new JButton("Edit Scales");
        size = scoreButton2.getPreferredSize();
        scoreButton2.addActionListener(this);
        scoreButton2.setBounds(30,300,size.width,size.height+10);
        add(scoreButton2);
        scoreButton2.setVisible(false);*/
        
        scoreWhatIfButton1 = new JButton("Edit Scales");
        size = scoreWhatIfButton1.getPreferredSize();
        scoreWhatIfButton1.addActionListener(this);
        scoreWhatIfButton1.setBounds(30,300,size.width,size.height+10);
        add(scoreWhatIfButton1);
        scoreWhatIfButton1.setVisible(false);
        
        gradeButton1 = new JButton("Edit Grades");
        size = gradeButton1.getPreferredSize();
        gradeButton1.addActionListener(this);
        gradeButton1.setBounds(30,260,size.width,size.height+10);
        add(gradeButton1);
        gradeButton1.setVisible(false);
        
        scoreChangesButton1 = new JButton("Submit");
        size = scoreChangesButton1.getPreferredSize();
        scoreChangesButton1.addActionListener(this);
        scoreChangesButton1.setBounds(280,400,size.width,size.height+20);
        add(scoreChangesButton1);
        scoreChangesButton1.setVisible(false);
        
        /*scoreChangesButton2 = new JButton("Submit");
        size = scoreChangesButton2.getPreferredSize();
        scoreChangesButton2.addActionListener(this);
        scoreChangesButton2.setBounds(280,400,size.width,size.height+20);
        add(scoreChangesButton2);
        scoreChangesButton2.setVisible(false);*/
        
        scoreWhatIfChangesButton1 = new JButton("Submit");
        size = scoreWhatIfChangesButton1.getPreferredSize();
        scoreWhatIfChangesButton1.addActionListener(this);
        scoreWhatIfChangesButton1.setBounds(280,400,size.width,size.height+20);
        add(scoreWhatIfChangesButton1);
        scoreWhatIfChangesButton1.setVisible(false);
        
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
        
        /*rosterTableModel2 = new DefaultTableModel();
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
        rosterPane2.setVisible(false);*/
        
        rosterWhatIfTableModel1 = new DefaultTableModel();
        rosterWhatIfTableModel1.addColumn("First Name");
        rosterWhatIfTableModel1.addColumn("Last Name");
        rosterWhatIfTableModel1.addColumn("Student ID");
        rosterWhatIfTableModel1.addColumn("Average");
        rosterWhatIfTableModel1.addRow(new Object[]{"","","",""});
        rosterWhatIfTableModel1.addRow(new Object[]{"","","",""});
        rosterWhatIfTableModel1.addRow(new Object[]{"","","",""});

        rosterWhatIfTable1 = new JTable(rosterWhatIfTableModel1);
        rosterWhatIfTable1.setBackground(Color.lightGray);
        JTableHeader studentWhatIfHeader1 = rosterWhatIfTable1.getTableHeader();
        studentWhatIfHeader1.setBackground(Color.white);
        studentWhatIfHeader1.setFont(new Font("Serif", Font.BOLD, 14));
        rosterWhatIfPane1 = new JScrollPane(rosterWhatIfTable1);
        rosterWhatIfPane1.setBounds(200, 100, 550, 250);
        add(rosterWhatIfPane1);
        rosterWhatIfPane1.setVisible(false);
        
        rosterAddButton1 = new JButton("+");
        rosterAddButton1.setFont(new Font("Serif", Font.BOLD, 20));
        size = rosterAddButton1.getPreferredSize();
        rosterAddButton1.addActionListener(this);
        rosterAddButton1.setBounds(300,50,size.width-35,size.height);
        add(rosterAddButton1);
        rosterAddButton1.setVisible(false);
        
        rosterDeleteButton1 = new JButton("+");
        rosterDeleteButton1.setFont(new Font("Serif", Font.BOLD, 20));
        size = rosterDeleteButton1.getPreferredSize();
        rosterDeleteButton1.addActionListener(this);
        rosterDeleteButton1.setBounds(350,50,size.width-35,size.height);
        add(rosterDeleteButton1);
        rosterDeleteButton1.setVisible(false);
        
        /*rosterAddButton2 = new JButton("+");
        rosterAddButton2.setFont(new Font("Serif", Font.BOLD, 20));
        size = rosterAddButton2.getPreferredSize();
        rosterAddButton2.addActionListener(this);
        rosterAddButton2.setBounds(300,50,size.width-35,size.height);
        add(rosterAddButton2);
        rosterAddButton2.setVisible(false);*/
        
        rosterWhatIfAddButton1 = new JButton("+");
        rosterWhatIfAddButton1.setFont(new Font("Serif", Font.BOLD, 20));
        size = rosterWhatIfAddButton1.getPreferredSize();
        rosterWhatIfAddButton1.addActionListener(this);
        rosterWhatIfAddButton1.setBounds(300,50,size.width-35,size.height);
        add(rosterWhatIfAddButton1);
        rosterWhatIfAddButton1.setVisible(false);
        
        nameLabel = new JLabel(" First Name:                   Last Name:");
        size = nameLabel.getPreferredSize();
        nameLabel.setBounds(50, 380, size.width, size.height);
        nameLabel.setVisible(false);
        add(nameLabel);
        
        scoreLabel = new JLabel(" A           B          C           D");
        size = scoreLabel.getPreferredSize();
        scoreLabel.setBounds(50, 380, size.width, size.height);
        scoreLabel.setVisible(false);
        add(scoreLabel);
        
        noCategoryLabel = new JLabel(" Category does not exist. Create Category in gradebook setup first.");
        size = noCategoryLabel.getPreferredSize();
        noCategoryLabel.setBounds(50, 450, size.width, size.height);
        noCategoryLabel.setVisible(false);
        add(noCategoryLabel);
        
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
        
        rosterChangesButton1 = new JButton("Submit");
        size = rosterChangesButton1.getPreferredSize();
        rosterChangesButton1.addActionListener(this);
        rosterChangesButton1.setBounds(350,400,size.width,size.height+20);
        add(rosterChangesButton1);
        rosterChangesButton1.setVisible(false);
        
        /*rosterChangesButton2 = new JButton("Submit");
        size = rosterChangesButton2.getPreferredSize();
        rosterChangesButton2.addActionListener(this);
        rosterChangesButton2.setBounds(350,400,size.width,size.height+20);
        add(rosterChangesButton2);
        rosterChangesButton2.setVisible(false);*/
        
        rosterWhatIfChangesButton1 = new JButton("Submit");
        size = rosterWhatIfChangesButton1.getPreferredSize();
        rosterWhatIfChangesButton1.addActionListener(this);
        rosterWhatIfChangesButton1.setBounds(350,400,size.width,size.height+20);
        add(rosterWhatIfChangesButton1);
        rosterWhatIfChangesButton1.setVisible(false);
        
        /*
         * Gradebook Setup
         */
        gradebookTableModel1 = new DefaultTableModel();
        gradebookTableModel1.addColumn("Category");
        gradebookTableModel1.addColumn("ID");
        gradebookTableModel1.addColumn("Weight");
        gradebookTableModel1.addRow(new Object[]{"","",""});
        gradebookTableModel1.addRow(new Object[]{"","",""});
        gradebookTableModel1.addRow(new Object[]{"","",""});

        gradebookTable1 = new JTable(gradebookTableModel1);
        gradebookTable1.setBackground(Color.lightGray);
        JTableHeader gradebookHeader1 = gradebookTable1.getTableHeader();
        gradebookHeader1.setBackground(Color.white);
        gradebookHeader1.setFont(new Font("Serif", Font.BOLD, 14));
        gradebookPane1 = new JScrollPane(gradebookTable1);
        gradebookPane1.setBounds(550, 100, 300, 250);
        add(gradebookPane1);
        gradebookPane1.setVisible(false);
        
        /*gradebookTableModel2 = new DefaultTableModel();
        gradebookTableModel2.addColumn("Category");
        gradebookTableModel2.addColumn("ID");
        gradebookTableModel2.addColumn("Weight");
        gradebookTableModel2.addRow(new Object[]{"","",""});
        gradebookTableModel2.addRow(new Object[]{"","",""});
        gradebookTableModel2.addRow(new Object[]{"","",""});

        gradebookTable2 = new JTable(gradebookTableModel2);
        gradebookTable2.setBackground(Color.lightGray);
        JTableHeader gradebookHeader2 = gradebookTable2.getTableHeader();
        gradebookHeader2.setBackground(Color.white);
        gradebookHeader2.setFont(new Font("Serif", Font.BOLD, 14));
        gradebookPane2 = new JScrollPane(gradebookTable2);
        gradebookPane2.setBounds(550, 100, 300, 250);
        add(gradebookPane2);
        gradebookPane2.setVisible(false);*/
        
        gradebookWhatIfTableModel1 = new DefaultTableModel();
        gradebookWhatIfTableModel1.addColumn("Category");
        gradebookWhatIfTableModel1.addColumn("ID");
        gradebookWhatIfTableModel1.addColumn("Weight");
        gradebookWhatIfTableModel1.addRow(new Object[]{"","",""});
        gradebookWhatIfTableModel1.addRow(new Object[]{"","",""});
        gradebookWhatIfTableModel1.addRow(new Object[]{"","",""});

        gradebookWhatIfTable1 = new JTable(gradebookWhatIfTableModel1);
        gradebookWhatIfTable1.setBackground(Color.lightGray);
        JTableHeader gradebookWhatIfHeader1 = gradebookWhatIfTable1.getTableHeader();
        gradebookWhatIfHeader1.setBackground(Color.white);
        gradebookWhatIfHeader1.setFont(new Font("Serif", Font.BOLD, 14));
        gradebookWhatIfPane1 = new JScrollPane(gradebookWhatIfTable1);
        gradebookWhatIfPane1.setBounds(550, 100, 300, 250);
        add(gradebookWhatIfPane1);
        gradebookWhatIfPane1.setVisible(false);
        
        aScoreEntry = new JTextField(3);
        aScoreEntry.setText("90");
        aScoreEntry.addActionListener(this);
        size = aScoreEntry.getPreferredSize();
        aScoreEntry.setBounds(50, 400, size.width, size.height);
        aScoreEntry.setVisible(false);
        add(aScoreEntry);
        
        bScoreEntry = new JTextField(3);
        bScoreEntry.setText("80");
        bScoreEntry.addActionListener(this);
        size = bScoreEntry.getPreferredSize();
        bScoreEntry.setBounds(100, 400, size.width, size.height);
        bScoreEntry.setVisible(false);
        add(bScoreEntry);
        
        cScoreEntry = new JTextField(3);
        cScoreEntry.setText("70");
        cScoreEntry.addActionListener(this);
        size = cScoreEntry.getPreferredSize();
        cScoreEntry.setBounds(150, 400, size.width, size.height);
        cScoreEntry.setVisible(false);
        add(cScoreEntry);
        
        dScoreEntry = new JTextField(3);
        dScoreEntry.setText("60");
        dScoreEntry.addActionListener(this);
        size = dScoreEntry.getPreferredSize();
        dScoreEntry.setBounds(200, 400, size.width, size.height);
        dScoreEntry.setVisible(false);
        add(dScoreEntry);
        
        gradebookAddButton1 = new JButton("+");
        gradebookAddButton1.setFont(new Font("Serif", Font.BOLD, 20));
        size = gradebookAddButton1.getPreferredSize();
        gradebookAddButton1.addActionListener(this);
        gradebookAddButton1.setBounds(770,50,size.width-35,size.height);
        add(gradebookAddButton1);
        gradebookAddButton1.setVisible(false);
        
        /*gradebookAddButton2 = new JButton("+");
        gradebookAddButton2.setFont(new Font("Serif", Font.BOLD, 20));
        size = gradebookAddButton2.getPreferredSize();
        gradebookAddButton2.addActionListener(this);
        gradebookAddButton2.setBounds(770,50,size.width-35,size.height);
        add(gradebookAddButton2);
        gradebookAddButton2.setVisible(false);*/
        
        gradebookDeleteButton1 = new JButton("-");
        gradebookDeleteButton1.setFont(new Font("Serif", Font.BOLD, 20));
        size = gradebookDeleteButton1.getPreferredSize();
        gradebookDeleteButton1.addActionListener(this);
        gradebookDeleteButton1.setBounds(820,50,size.width-35,size.height);
        add(gradebookDeleteButton1);
        gradebookDeleteButton1.setVisible(false);
        
        gradebookWhatIfAddButton1 = new JButton("+");
        gradebookWhatIfAddButton1.setFont(new Font("Serif", Font.BOLD, 20));
        size = gradebookWhatIfAddButton1.getPreferredSize();
        gradebookWhatIfAddButton1.addActionListener(this);
        gradebookWhatIfAddButton1.setBounds(770,50,size.width-35,size.height);
        add(gradebookWhatIfAddButton1);
        gradebookWhatIfAddButton1.setVisible(false);
        
        newCategory = new JTextField(10);
        newCategory.addActionListener(this);
        size = newCategory.getPreferredSize();
        newCategory.setBounds(50, 400, size.width, size.height);
        newCategory.setVisible(false);
        add(newCategory);
        
        categoryWeight = new JTextField(3);
        categoryWeight.addActionListener(this);
        size = categoryWeight.getPreferredSize();
        categoryWeight.setBounds(200, 400, size.width, size.height);
        categoryWeight.setVisible(false);
        add(categoryWeight);
        
        categoryLabel = new JLabel("Category:                       Weight:");
        size = categoryLabel.getPreferredSize();
        categoryLabel.setBounds(50, 380, size.width, size.height);
        categoryLabel.setVisible(false);
        add(categoryLabel);
        
        gradebookChangesButton1 = new JButton("Submit");
        size = gradebookChangesButton1.getPreferredSize();
        gradebookChangesButton1.addActionListener(this);
        gradebookChangesButton1.setBounds(270,400,size.width,size.height+20);
        add(gradebookChangesButton1);
        gradebookChangesButton1.setVisible(false);
        
        /*gradebookChangesButton2 = new JButton("Submit");
        size = gradebookChangesButton2.getPreferredSize();
        gradebookChangesButton2.addActionListener(this);
        gradebookChangesButton2.setBounds(270,400,size.width,size.height+20);
        add(gradebookChangesButton2);
        gradebookChangesButton2.setVisible(false);*/
        
        gradebookWhatIfChangesButton1 = new JButton("Submit");
        size = gradebookWhatIfChangesButton1.getPreferredSize();
        gradebookWhatIfChangesButton1.addActionListener(this);
        gradebookWhatIfChangesButton1.setBounds(270,400,size.width,size.height+20);
        add(gradebookWhatIfChangesButton1);
        gradebookWhatIfChangesButton1.setVisible(false);
        
        
        /*assignment1Radio = new JRadioButton(assignments1.get(0).getName());
        size = assignment1Radio.getPreferredSize();
        assignment1Radio.addActionListener(this);
        assignment1Radio.setBounds(100,300,size.width,size.height+20);
        add(assignment1Radio);
        assignment1Radio.setVisible(false);
        
        assignment2Radio = new JRadioButton(assignments1.get(1).getName());
        size = assignment2Radio.getPreferredSize();
        assignment2Radio.addActionListener(this);
        assignment2Radio.setBounds(100,320,size.width,size.height+20);
        add(assignment2Radio);
        assignment2Radio.setVisible(false);
        
        assignment3Radio = new JRadioButton(assignments1.get(2).getName());
        size = assignment3Radio.getPreferredSize();
        assignment3Radio.addActionListener(this);
        assignment3Radio.setBounds(100,340,size.width,size.height+20);
        add(assignment3Radio);
        assignment3Radio.setVisible(false);
        
        assignment4Radio = new JRadioButton();
        assignment4Radio.setText(null);
        add(assignment4Radio);
        assignment4Radio.setVisible(false);
        
        assignment5Radio = new JRadioButton();
        assignment5Radio.setText(null);
        add(assignment5Radio);
        assignment5Radio.setVisible(false);
        
        assignment6Radio = new JRadioButton();
        assignment6Radio.setText(null);
        add(assignment6Radio);
        assignment6Radio.setVisible(false);
        
        student1Radio = new JRadioButton(roster1.get(0).getFirstName()+" "+roster1.get(0).getLastName());
        size = student1Radio.getPreferredSize();
        student1Radio.addActionListener(this);
        student1Radio.setBounds(250,300,size.width,size.height+20);
        add(student1Radio);
        student1Radio.setVisible(false);
        
        student2Radio = new JRadioButton(roster1.get(1).getFirstName()+" "+roster1.get(1).getLastName());
        size = student2Radio.getPreferredSize();
        student2Radio.addActionListener(this);
        student2Radio.setBounds(250,320,size.width,size.height+20);
        add(student2Radio);
        student2Radio.setVisible(false);
        
        student3Radio = new JRadioButton(roster1.get(2).getFirstName()+" "+roster1.get(2).getLastName());
        size = student3Radio.getPreferredSize();
        student3Radio.addActionListener(this);
        student3Radio.setBounds(250,340,size.width,size.height+20);
        add(student3Radio);
        student3Radio.setVisible(false);
        
        student4Radio = new JRadioButton();
        student4Radio.setText(null);
        add(student4Radio);
        student4Radio.setVisible(false);
        
        student5Radio = new JRadioButton();
        student5Radio.setText(null);
        add(student5Radio);
        student5Radio.setVisible(false);
        
        student6Radio = new JRadioButton();
        student6Radio.setText(null);
        add(student6Radio);
        student6Radio.setVisible(false);*/
        
        backButton = new JButton("Back");
        size = backButton.getPreferredSize();
        backButton.addActionListener(this);
        backButton.setBounds(600,330,size.width,size.height+20);
        add(backButton);
        backButton.setVisible(false);
        
        gradesTableModel1 = new DefaultTableModel();
        gradesTableModel1.addColumn("");
        gradesTableModel1.addColumn(courseCList.get(activeCourse).getRoster().get(0).getFirstName()+" "+courseCList.get(activeCourse).getRoster().get(0).getLastName());
        gradesTableModel1.addColumn(courseCList.get(activeCourse).getRoster().get(1).getFirstName()+" "+courseCList.get(activeCourse).getRoster().get(1).getLastName());
        gradesTableModel1.addColumn(courseCList.get(activeCourse).getRoster().get(2).getFirstName()+" "+courseCList.get(activeCourse).getRoster().get(2).getLastName());
        
        gradesTableModel1.addRow(new Object[]{"","","",""});
        gradesTableModel1.addRow(new Object[]{"","","",""});
        gradesTableModel1.addRow(new Object[]{"","","",""});
        gradesTableModel1.addRow(new Object[]{"","","",""});

        gradesTable1 = new JTable(gradesTableModel1);
        gradesTable1.setBackground(Color.lightGray);
        JTableHeader gradesHeader1 = gradesTable1.getTableHeader();
        gradesHeader1.setBackground(Color.white);
        gradesHeader1.setFont(new Font("Serif", Font.BOLD, 14));
        gradesPane1 = new JScrollPane(gradesTable1);
        gradesPane1.setBounds(100, 100, 600, 150);
        add(gradesPane1);
        gradesPane1.setVisible(false);
        
        /*gradeLabel = new JLabel("Grade:");
        size = gradeLabel.getPreferredSize();
        gradeLabel.setBounds(450, 330, size.width, size.height);
        gradeLabel.setVisible(false);
        add(gradeLabel);
        gradeLabel.setVisible(false);*/
        
        /*gradeChangeField = new JTextField(3);
        gradeChangeField.addActionListener(this);
        size = gradeChangeField.getPreferredSize();
        gradeChangeField.setBounds(450, 350, size.width, size.height);
        add(gradeChangeField);
        gradeChangeField.setVisible(false);*/
        
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
        classAddButton.setBounds(400,50,size.width-35,size.height);
        add(classAddButton);
        classAddButton.setVisible(false);
        
        classDeleteButton = new JButton("-");
        classDeleteButton.setFont(new Font("Serif", Font.BOLD, 20));
        size = classDeleteButton.getPreferredSize();
        classDeleteButton.addActionListener(this);
        classDeleteButton.setBounds(450,50,size.width-35,size.height);
        add(classDeleteButton);
        classDeleteButton.setVisible(false);
        
        courseLabel = new JLabel();
        add(courseLabel);
        courseLabel.setVisible(false);
        
        courseAddField = new JTextField(20);
        courseAddField.addActionListener(this);
        size = courseAddField.getPreferredSize();
        courseAddField.setBounds(500, 50, size.width, size.height);
        courseAddField.setVisible(false);
        add(courseAddField);
        courseAddField.setVisible(false);
        
        courseDeleteField = new JTextField(20);
        courseDeleteField.addActionListener(this);
        size = courseDeleteField.getPreferredSize();
        courseDeleteField.setBounds(500, 50, size.width, size.height);
        courseDeleteField.setVisible(false);
        add(courseDeleteField);
        courseDeleteField.setVisible(false);
        
        whatIfButton1 = new JButton("What-If");
        size = whatIfButton1.getPreferredSize();
        whatIfButton1.addActionListener(this);
        whatIfButton1.setBounds(650,440,size.width,size.height+20);
        add(whatIfButton1);
        whatIfButton1.setVisible(false);
        
        deleteStudentButton = new JButton("Delete-Student");
        size = deleteStudentButton.getPreferredSize();
        deleteStudentButton.addActionListener(this);
        deleteStudentButton.setBounds(500,440,size.width+10,size.height+20);
        add(deleteStudentButton);
        deleteStudentButton.setVisible(false);
        
        /*whatIfButton2 = new JButton("What-If");
        size = whatIfButton2.getPreferredSize();
        whatIfButton2.addActionListener(this);
        whatIfButton2.setBounds(650,440,size.width,size.height+20);
        add(whatIfButton2);
        whatIfButton2.setVisible(false);*/
        
        saveWhatIfButton1 = new JButton("Save");
        size = saveWhatIfButton1.getPreferredSize();
        saveWhatIfButton1.addActionListener(this);
        saveWhatIfButton1.setBounds(650,440,size.width,size.height+20);
        add(saveWhatIfButton1);
        saveWhatIfButton1.setVisible(false);
        
        deleteWhatIfButton1 = new JButton("Delete");
        size = deleteWhatIfButton1.getPreferredSize();
        deleteWhatIfButton1.addActionListener(this);
        deleteWhatIfButton1.setBounds(750,440,size.width,size.height+20);
        add(deleteWhatIfButton1);
        deleteWhatIfButton1.setVisible(false);
    }

    /*
    * Method for all actions that occur due to user action,
    * such as button click or text entry.
     */
    public void actionPerformed(ActionEvent action){
        
        
        if (action.getSource().equals(passwordField))
        {
            if(changeField.isVisible() == false)
            {
                String pCheck = "";
                try{
                    pCheck = cList.hash(passwordField.getText());
                 }
                 catch(NoSuchAlgorithmException e){
                     throw new RuntimeException(e);
                 }
                
                if(pCheck.equals(cList.getPassword())) {
                    screenNumber = 0;

                    helpLabel1.setVisible(false);
                    loginLabel.setVisible(false);
                    loginPageLabel.setVisible(false);
                    passwordField.setVisible(false);
                    incorrectLoginLabel.setVisible(false);
                    changePwordButton.setVisible(false);

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
                    classDeleteButton.setVisible(true);
                } else {
                    incorrectLoginLabel.setText("<html><font color = 'FF0000'>Incorrect Password</font></html>");
                    size = incorrectLoginLabel.getPreferredSize();
                    incorrectLoginLabel.setBounds(200, 150, size.width, size.height);
                    incorrectLoginLabel.setVisible(true);
                }
            }
        }
        if (action.getSource() == helpButton)
        {
            if (screenNumber == -1){
                helpLabel1.setVisible(true);
            }
            else if ((screenNumber == 0) || (screenNumber == 1) || (screenNumber == 2)){
                helpLabel1.setText("<html>Click the tab to display<br>the gradebook section.</html>");
                size = helpLabel1.getPreferredSize();
                helpLabel1.setVisible(true);
            }
        }
        if (action.getSource() == classAddButton)
        {
            courseLabel.setText("Course to Add:");
            size = courseLabel.getPreferredSize();
            courseLabel.setBounds(500, 30, size.width, size.height);
            courseLabel.setVisible(true);
            courseAddField.setText("");
            courseAddField.setVisible(true);
            
            
        }
        if (action.getSource() == classDeleteButton)
        {
            courseLabel.setText("Course to Delete:");
            size = courseLabel.getPreferredSize();
            courseLabel.setBounds(500, 30, size.width, size.height);
            courseLabel.setVisible(true);
            courseDeleteField.setText("");
            courseDeleteField.setVisible(true);
        }
        if (action.getSource() == courseAddField)
        {
            courseLabel.setVisible(false);
            courseAddField.setVisible(false);
            cList.addCourse(courseAddField.getText());
            courseCList.add(new Course());
            courses = cList.getCourses();
            int numCourses = courses.size();
            switch(numCourses){
                case 3:
                    class3Button.setText(courses.get(2));
                    size = class3Button.getPreferredSize();
                    class3Button.addActionListener(this);
                    class3Button.setBounds(750,100,size.width,size.height+30);
                    add(class3Button);
                    class3Button.setVisible(true);
                    break;
                case 4:
                    class4Button.setText(courses.get(3));
                    size = class4Button.getPreferredSize();
                    class4Button.addActionListener(this);
                    class4Button.setBounds(50,200,size.width,size.height+30);
                    add(class4Button);
                    class4Button.setVisible(true);
                    break;
                case 5:
                    class5Button.setText(courses.get(4));
                    size = class5Button.getPreferredSize();
                    class5Button.addActionListener(this);
                    class5Button.setBounds(400,200,size.width,size.height+30);
                    add(class5Button);
                    class5Button.setVisible(true);
                    break;
                case 6:
                    class6Button.setText(courses.get(5));
                    size = class6Button.getPreferredSize();
                    class6Button.addActionListener(this);
                    class6Button.setBounds(750,200,size.width,size.height+30);
                    add(class6Button);
                    class6Button.setVisible(true);
                    break;
                case 7:
                    class7Button.setText(courses.get(6));
                    size = class7Button.getPreferredSize();
                    class7Button.addActionListener(this);
                    class7Button.setBounds(400,300,size.width,size.height+30);
                    add(class7Button);
                    class7Button.setVisible(true);
                    break;
            }
            try{
                writer.saveClassList(cList);
            } catch (IOException e){
                System.out.println("error saving");
            }
        }
        if (action.getSource() == courseDeleteField)
        {
            courseLabel.setVisible(false);
            courseDeleteField.setVisible(false);
            //Vector<String> currentCourses = cList.getCourses();
            String deleteCourse = courseDeleteField.getText();
            Boolean courseFound = false;
            for(int i = 0; i < courses.size(); i++)
            {
                if(courses.get(i).equals(deleteCourse))
                {
                    cList.deleteCourse(i);
                    courses = cList.getCourses();
                    courseFound = true;
                    
                    if (courses.size() >= 1)
                    {
                        class1Button.setText(courses.get(0));
                        size = class2Button.getPreferredSize();
                        class1Button.setBounds(50,100,size.width,size.height+30);
                        class1Button.setVisible(true);
                    }
                    else
                    {
                        class1Button.setText(null);
                        class1Button.setVisible(false);
                    }
                    if (courses.size() >= 2)
                    {
                        class2Button.setText(courses.get(1));
                        size = class2Button.getPreferredSize();
                        class2Button.setBounds(400,100,size.width,size.height+30);
                        class2Button.setVisible(true);
                    }
                    else
                    {
                        class2Button.setText(null);
                        class2Button.setVisible(false);
                    }
                    if (courses.size() >= 3)
                    {
                        class3Button.setText(courses.get(2));
                        size = class3Button.getPreferredSize();
                        class3Button.setBounds(750,100,size.width,size.height+30);
                        class3Button.setVisible(true);   
                    }
                    else
                    {
                        class3Button.setText(null);
                        class3Button.setVisible(false);
                    }
                    if (courses.size() >= 4)
                    {
                        class4Button.setText(courses.get(3));
                        size = class4Button.getPreferredSize();
                        class4Button.setBounds(50,200,size.width,size.height+30);
                        class4Button.setVisible(true);
                    }
                    else
                    {
                        class4Button.setText(null);
                        class4Button.setVisible(false);
                    }
                    if (courses.size() >= 5)
                    {
                        class5Button.setText(courses.get(4));
                        size = class5Button.getPreferredSize();
                        class5Button.setBounds(400,200,size.width,size.height+30);
                        class5Button.setVisible(true);
                    }
                    else
                    {
                        class5Button.setText(null);
                        class5Button.setVisible(false);
                    }
                    if (courses.size() >= 6)
                    {
                        class6Button.setText(courses.get(5));
                        size = class6Button.getPreferredSize();
                        class6Button.setBounds(750,200,size.width,size.height+30);
                        class6Button.setVisible(true);
                    }
                    else
                    {
                        class6Button.setText(null);
                        class6Button.setVisible(false);
                    }
                    if (courses.size() >= 7){
                        class7Button.setText(courses.get(6));
                        size = class7Button.getPreferredSize();
                        class7Button.setBounds(400,300,size.width,size.height+30);
                        class7Button.setVisible(false);
                    } 
                    else
                    {
                        class7Button.setText(null);
                        class7Button.setVisible(false);
                    }
                }
            }
            try{
                writer.saveClassList(cList);
            } catch (IOException e){
                System.out.println("error saving");
            }
            if(courseFound != true)
            {
                System.out.println("Course does not exist.");
            }
            
        }
        if (action.getSource() == class1Button)
        {
            activeCourse = 0;
            assignmentButton1.setVisible(true);
            rosterButton1.setVisible(true);
            whatIfButton1.setVisible(true);
            class1Button.setVisible(false);
            class2Button.setVisible(false);
            class3Button.setVisible(false);
            class4Button.setVisible(false);
            class5Button.setVisible(false);
            class6Button.setVisible(false);
            class7Button.setVisible(false);
            classAddButton.setVisible(false);
            classDeleteButton.setVisible(false);
            titleLabel.setVisible(false);
            classesButton.setVisible(true);
            
            classLabel.setText(class1Button.getText());
            size =  classLabel.getPreferredSize();
            classLabel.setBounds(50, 50, size.width, size.height);
            classLabel.setVisible(true);
            
            for (int i = 0; i < courseCList.get(activeCourse).getGradebook().getAssignments().size(); i++)
            {
                assignmentsTable1.setValueAt(courseCList.get(activeCourse).getGradebook().getAssignments().get(i).getName(),i,0);
                assignmentsTable1.setValueAt(courseCList.get(activeCourse).getGradebook().getCatNames().get(i),i,1);
                assignmentsTable1.setValueAt(courseCList.get(activeCourse).getGradebook().getAssignments().get(i).getMaxScore(),i,2);
                
                rosterTable1.setValueAt(courseCList.get(activeCourse).getRoster().get(i).getFirstName(),i,0);
                rosterTable1.setValueAt(courseCList.get(activeCourse).getRoster().get(i).getLastName(),i,1);
                rosterTable1.setValueAt(courseCList.get(activeCourse).getRoster().get(i).getID(),i,2);
                rosterTable1.setValueAt(courseCList.get(activeCourse).getRoster().get(i).getOverallGrade(),i,3);
                
                gradebookTable1.setValueAt(courseCList.get(activeCourse).getGradebook().getCatNames().get(i),i,0);
                gradebookTable1.setValueAt(courseCList.get(activeCourse).getGradebook().getCatID().get(i),i,1);
                gradebookTable1.setValueAt(courseCList.get(activeCourse).getGradebook().getCatWeights().get(i),i,2);
            }
        }
        if (action.getSource() == class2Button)
        {
            activeCourse = 1;
            assignmentButton1.setVisible(true);
            rosterButton1.setVisible(true);
            whatIfButton1.setVisible(true);
            class1Button.setVisible(false);
            class2Button.setVisible(false);
            class3Button.setVisible(false);
            class4Button.setVisible(false);
            class5Button.setVisible(false);
            class6Button.setVisible(false);
            class7Button.setVisible(false);
            classAddButton.setVisible(false);
            classDeleteButton.setVisible(false);
            titleLabel.setVisible(false);
            classesButton.setVisible(true);
            
            classLabel.setText(class2Button.getText());
            size =  classLabel.getPreferredSize();
            classLabel.setBounds(50, 50, size.width, size.height);
            classLabel.setVisible(true);
            
            for (int i = 0; i < courseCList.get(activeCourse).getGradebook().getAssignments().size(); i++)
            {
                assignmentsTable1.setValueAt(courseCList.get(activeCourse).getGradebook().getAssignments().get(i).getName(),i,0);
                assignmentsTable1.setValueAt(courseCList.get(activeCourse).getGradebook().getCatNames().get(i),i,1);
                assignmentsTable1.setValueAt(courseCList.get(activeCourse).getGradebook().getAssignments().get(i).getMaxScore(),i,2);
                
                rosterTable1.setValueAt(courseCList.get(activeCourse).getRoster().get(i).getFirstName(),i,0);
                rosterTable1.setValueAt(courseCList.get(activeCourse).getRoster().get(i).getLastName(),i,1);
                rosterTable1.setValueAt(courseCList.get(activeCourse).getRoster().get(i).getID(),i,2);
                rosterTable1.setValueAt(courseCList.get(activeCourse).getRoster().get(i).getOverallGrade(),i,3);
                
                gradebookTable1.setValueAt(courseCList.get(activeCourse).getGradebook().getCatNames().get(i),i,0);
                gradebookTable1.setValueAt(courseCList.get(activeCourse).getGradebook().getCatID().get(i),i,1);
                gradebookTable1.setValueAt(courseCList.get(activeCourse).getGradebook().getCatWeights().get(i),i,2);
            }
        }
        if (action.getSource() == class3Button)
        {
            activeCourse = 2;
            assignmentButton1.setVisible(true);
            rosterButton1.setVisible(true);
            class1Button.setVisible(false);
            class2Button.setVisible(false);
            class3Button.setVisible(false);
            class4Button.setVisible(false);
            class5Button.setVisible(false);
            class6Button.setVisible(false);
            class7Button.setVisible(false);
            classAddButton.setVisible(false);
            classDeleteButton.setVisible(false);
            titleLabel.setVisible(false);
            classesButton.setVisible(true);
            
            classLabel.setText(class3Button.getText());
            size =  classLabel.getPreferredSize();
            classLabel.setBounds(50, 50, size.width, size.height);
            classLabel.setVisible(true);
            for (int i = 0; i < courseCList.get(activeCourse).getGradebook().getAssignments().size(); i++)
            {
                assignmentsTable1.setValueAt(courseCList.get(activeCourse).getGradebook().getAssignments().get(i).getName(),i,0);
                assignmentsTable1.setValueAt(courseCList.get(activeCourse).getGradebook().getCatNames().get(i),i,1);
                assignmentsTable1.setValueAt(courseCList.get(activeCourse).getGradebook().getAssignments().get(i).getMaxScore(),i,2);
                
                rosterTable1.setValueAt(courseCList.get(activeCourse).getRoster().get(i).getFirstName(),i,0);
                rosterTable1.setValueAt(courseCList.get(activeCourse).getRoster().get(i).getLastName(),i,1);
                rosterTable1.setValueAt(courseCList.get(activeCourse).getRoster().get(i).getID(),i,2);
                rosterTable1.setValueAt(courseCList.get(activeCourse).getRoster().get(i).getOverallGrade(),i,3);
                
                gradebookTable1.setValueAt(courseCList.get(activeCourse).getGradebook().getCatNames().get(i),i,0);
                gradebookTable1.setValueAt(courseCList.get(activeCourse).getGradebook().getCatID().get(i),i,1);
                gradebookTable1.setValueAt(courseCList.get(activeCourse).getGradebook().getCatWeights().get(i),i,2);
            }
        }
        if (action.getSource() == class4Button)
        {
            activeCourse = 3;
            assignmentButton1.setVisible(true);
            rosterButton1.setVisible(true);
            class1Button.setVisible(false);
            class2Button.setVisible(false);
            class3Button.setVisible(false);
            class4Button.setVisible(false);
            class5Button.setVisible(false);
            class6Button.setVisible(false);
            class7Button.setVisible(false);
            classAddButton.setVisible(false);
            classDeleteButton.setVisible(false);
            titleLabel.setVisible(false);
            classesButton.setVisible(true);
            
            classLabel.setText(class4Button.getText());
            size =  classLabel.getPreferredSize();
            classLabel.setBounds(50, 50, size.width, size.height);
            classLabel.setVisible(true);
        }
        if (action.getSource() == assignmentButton1)
        {
            
            assignmentsPane1.setVisible(true);
            gradebookPane1.setVisible(true);
            assignmentButton1.setVisible(false);
            rosterButton1.setVisible(true);
            rosterPane1.setVisible(false);
            assignmentAddButton1.setVisible(true);
            rosterAddButton1.setVisible(false);
            rosterDeleteButton1.setVisible(false);
            titleLabel.setText("Assignments                       Gradebook Setup");
            size =  titleLabel.getPreferredSize();
            titleLabel.setBounds(200, 50, size.width, size.height);
            titleLabel.setVisible(true);
            nameLabel.setVisible(false);
            firstNameEntry.setVisible(false);
            lastNameEntry.setVisible(false);
            rosterChangesButton1.setVisible(false);
            //rosterChangesButton2.setVisible(false);
            scoreButton1.setVisible(true);
            gradeButton1.setVisible(true);
            gradebookAddButton1.setVisible(true);
            assignmentDeleteButton1.setVisible(true);
            gradebookDeleteButton1.setVisible(true);
            gradebookChangesButton1.setVisible(false);
            newCategory.setVisible(false);
            categoryWeight.setVisible(false);
            categoryLabel.setVisible(false);
            deleteStudentButton.setVisible(false);
            
            if(rosterTable1.getRowCount() >= 2){
                stuNameUpdate(rosterTable1, activeCourse);
            }
            
            
        }
        /*if (action.getSource() == assignmentButton2)
        {
            
            assignmentsPane1.setVisible(true);
            gradebookPane1.setVisible(true);
            assignmentButton2.setVisible(false);
            rosterButton2.setVisible(true);
            rosterPane1.setVisible(false);
            assignmentAddButton2.setVisible(true);
            rosterAddButton2.setVisible(false);
            
            titleLabel.setText("Assignments                       Gradebook Setup");
            size =  titleLabel.getPreferredSize();
            titleLabel.setBounds(200, 50, size.width, size.height);
            titleLabel.setVisible(true);
            nameLabel.setVisible(false);
            firstNameEntry.setVisible(false);
            lastNameEntry.setVisible(false);
            rosterChangesButton1.setVisible(false);
            rosterChangesButton2.setVisible(false);
            scoreButton2.setVisible(true);
            gradebookAddButton2.setVisible(true);
            gradebookChangesButton2.setVisible(false);
            newCategory.setVisible(false);
            categoryWeight.setVisible(false);
            categoryLabel.setVisible(false);
            deleteStudentButton.setVisible(false);
            stuNameUpdate(rosterTable1, activeCourse);
        }*/
        if (action.getSource() == rosterButton1)
        {
            assignmentsPane1.setVisible(false);
            gradebookPane1.setVisible(false);
            assignmentButton1.setVisible(true);
            rosterButton1.setVisible(false);
            rosterPane1.setVisible(true);
            assignmentAddButton1.setVisible(false);
            rosterAddButton1.setVisible(true);
            rosterDeleteButton1.setVisible(true);
            scoreButton1.setVisible(false);
            gradeButton1.setVisible(false);
            scoreChangesButton1.setVisible(false);
            aScoreEntry.setVisible(false);
            bScoreEntry.setVisible(false);
            cScoreEntry.setVisible(false);
            dScoreEntry.setVisible(false);
            assignmentChangesButton1.setVisible(false);
            assignmentLabel.setVisible(false);
            scoreLabel.setVisible(false);
            assignmentName.setVisible(false);
            assignmentCategory.setVisible(false);
            assignmentScore.setVisible(false);
            noCategoryLabel.setVisible(false);
            gradebookAddButton1.setVisible(false);
            assignmentDeleteButton1.setVisible(false);
            gradebookDeleteButton1.setVisible(false);
            gradebookChangesButton1.setVisible(false);
            newCategory.setVisible(false);
            categoryWeight.setVisible(false);
            categoryLabel.setVisible(false);
            deleteStudentButton.setVisible(true);
            
            titleLabel.setText("Roster");
            size =  titleLabel.getPreferredSize();
            titleLabel.setBounds(200, 50, size.width, size.height);
            titleLabel.setVisible(true);
        }
        /*if (action.getSource() == rosterButton2)
        {
            assignmentsPane1.setVisible(false);
            gradebookPane1.setVisible(false);
            assignmentButton2.setVisible(true);
            rosterButton2.setVisible(false);
            rosterPane1.setVisible(true);
            assignmentAddButton2.setVisible(false);
            rosterAddButton2.setVisible(true);
            scoreButton2.setVisible(false);
            scoreChangesButton2.setVisible(false);
            aScoreEntry.setVisible(false);
            bScoreEntry.setVisible(false);
            cScoreEntry.setVisible(false);
            dScoreEntry.setVisible(false);
            assignmentChangesButton2.setVisible(false);
            assignmentLabel.setVisible(false);
            scoreLabel.setVisible(false);
            assignmentName.setVisible(false);
            assignmentCategory.setVisible(false);
            assignmentScore.setVisible(false);
            noCategoryLabel.setVisible(false);
            gradebookAddButton2.setVisible(false);
            gradebookChangesButton2.setVisible(false);
            newCategory.setVisible(false);
            categoryWeight.setVisible(false);
            categoryLabel.setVisible(false);
            deleteStudentButton.setVisible(true);
            
            titleLabel.setText("Roster");
            size =  titleLabel.getPreferredSize();
            titleLabel.setBounds(200, 50, size.width, size.height);
            titleLabel.setVisible(true);
        }*/
        if (action.getSource() == classesButton)
        {
            stuNameUpdate(rosterTable1, activeCourse);
            
            assignmentsPane1.setVisible(false);
            //assignmentsPane2.setVisible(false);
            assignmentButton1.setVisible(false);
            rosterButton1.setVisible(false);
            rosterPane1.setVisible(false);
            //rosterPane2.setVisible(false);
            gradebookPane1.setVisible(false);
            //gradebookPane2.setVisible(false);
            assignmentAddButton1.setVisible(false);
            rosterAddButton1.setVisible(false);
            rosterDeleteButton1.setVisible(false);
            /*assignmentButton2.setVisible(false);
            rosterButton2.setVisible(false);
            assignmentAddButton2.setVisible(false);
            rosterAddButton2.setVisible(false);*/
            titleLabel.setText("Welcome, Professor " + teacher.get(0));
            size =  titleLabel.getPreferredSize();
            titleLabel.setBounds(50, 50, size.width, size.height);
            classesButton.setVisible(false);
            titleLabel.setVisible(true);
            classAddButton.setVisible(true);
            classDeleteButton.setVisible(true);
            classLabel.setVisible(false);
            nameLabel.setVisible(false);
            firstNameEntry.setVisible(false);
            lastNameEntry.setVisible(false);
            rosterChangesButton1.setVisible(false);
            //rosterChangesButton2.setVisible(false);
            aScoreEntry.setVisible(false);
            bScoreEntry.setVisible(false);
            cScoreEntry.setVisible(false);
            dScoreEntry.setVisible(false);
            scoreButton1.setVisible(false);
            gradeButton1.setVisible(false);
            //scoreButton2.setVisible(false);
            scoreChangesButton1.setVisible(false);
            //scoreChangesButton2.setVisible(false);
            assignmentChangesButton1.setVisible(false);
            //assignmentChangesButton2.setVisible(false);
            assignmentLabel.setVisible(false);
            scoreLabel.setVisible(false);
            assignmentName.setVisible(false);
            assignmentCategory.setVisible(false);
            assignmentScore.setVisible(false);
            noCategoryLabel.setVisible(false);
            gradebookAddButton1.setVisible(false);
            assignmentDeleteButton1.setVisible(false);
            gradebookDeleteButton1.setVisible(false);
            //gradebookAddButton2.setVisible(false);
            gradebookChangesButton1.setVisible(false);
            //gradebookChangesButton2.setVisible(false);
            newCategory.setVisible(false);
            categoryWeight.setVisible(false);
            categoryLabel.setVisible(false);
            whatIfButton1.setVisible(false);
            //whatIfButton2.setVisible(false);
            deleteStudentButton.setVisible(false);
            
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
            
        }
        if (action.getSource() == rosterAddButton1)
        {
            nameLabel.setVisible(!nameLabel.isVisible());
            firstNameEntry.setVisible(!firstNameEntry.isVisible());
            lastNameEntry.setVisible(!lastNameEntry.isVisible());
            firstNameEntry.setText("");
            lastNameEntry.setText("");
            rosterChangesButton1.setVisible(!rosterChangesButton1.isVisible());
        }
        /*if (action.getSource() == rosterAddButton2)
        {
            nameLabel.setVisible(!nameLabel.isVisible());
            firstNameEntry.setVisible(!firstNameEntry.isVisible());
            lastNameEntry.setVisible(!lastNameEntry.isVisible());
            firstNameEntry.setText("");
            lastNameEntry.setText("");
            rosterChangesButton2.setVisible(!rosterChangesButton2.isVisible());
        }*/
        if (action.getSource() == assignmentAddButton1)
        {
            assignmentLabel.setVisible(!assignmentLabel.isVisible());
            assignmentName.setVisible(!assignmentName.isVisible());
            assignmentCategory.setVisible(!assignmentCategory.isVisible());
            assignmentScore.setVisible(!assignmentScore.isVisible());
            assignmentName.setText("");
            assignmentCategory.setText("");
            assignmentScore.setText("100");
            assignmentChangesButton1.setVisible(!assignmentChangesButton1.isVisible());
            noCategoryLabel.setVisible(false);
            scoreLabel.setVisible(false);
            aScoreEntry.setVisible(false);
            bScoreEntry.setVisible(false);
            cScoreEntry.setVisible(false);
            dScoreEntry.setVisible(false);
            scoreChangesButton1.setVisible(false);
            gradebookChangesButton1.setVisible(false);
            newCategory.setVisible(false);
            categoryWeight.setVisible(false);
            categoryLabel.setVisible(false);
            noCategoryLabel.setVisible(false);
        }
        /*if (action.getSource() == assignmentAddButton2)
        {
            assignmentLabel.setVisible(!assignmentLabel.isVisible());
            assignmentName.setVisible(!assignmentName.isVisible());
            assignmentCategory.setVisible(!assignmentCategory.isVisible());
            assignmentScore.setVisible(!assignmentScore.isVisible());
            assignmentName.setText("");
            assignmentCategory.setText("");
            assignmentScore.setText("100");
            assignmentChangesButton2.setVisible(!assignmentChangesButton2.isVisible());
            noCategoryLabel.setVisible(false);
            scoreLabel.setVisible(false);
            aScoreEntry.setVisible(false);
            bScoreEntry.setVisible(false);
            cScoreEntry.setVisible(false);
            dScoreEntry.setVisible(false);
            scoreChangesButton1.setVisible(false);
            gradebookChangesButton2.setVisible(false);
            newCategory.setVisible(false);
            categoryWeight.setVisible(false);
            categoryLabel.setVisible(false);
            noCategoryLabel.setVisible(false);
        }*/
        if(action.getSource() ==  rosterChangesButton1)
        {
            rosterTableModel1.addRow(new Object[]{"","","",""});
            String firstName = firstNameEntry.getText();
            String lastName = lastNameEntry.getText();
            courseCList.get(activeCourse).addStudent(firstName, lastName);
            rosterTable1.setValueAt(firstName,rosterTableModel1.getRowCount()-1,0);
            rosterTable1.setValueAt(lastName,rosterTableModel1.getRowCount()-1,1);
            rosterTable1.setValueAt(courseCList.get(activeCourse).getRoster().get(rosterTableModel1.getRowCount()-1).getID(),rosterTableModel1.getRowCount()-1,2);
            rosterTable1.setValueAt(courseCList.get(activeCourse).getRoster().get(rosterTableModel1.getRowCount()-1).getOverallGrade(),rosterTableModel1.getRowCount()-1,3);
            
            try{
                writer.saveCourse(courseCList.get(activeCourse), courses.get(activeCourse));
            } catch (IOException e){
                System.out.println("error saving");
            }
            
            gradesTableModel1.addColumn(courseCList.get(activeCourse).getRoster().get(courseCList.get(activeCourse).getRoster().size()-1).getFirstName()+" "+courseCList.get(activeCourse).getRoster().get(courseCList.get(activeCourse).getRoster().size()-1).getLastName());
            /*if(student4Radio.getText() == null)
            {
                student4Radio.setText(roster1.get(3).getFirstName()+" "+roster1.get(3).getLastName());
                size = student4Radio.getPreferredSize();
                student4Radio.addActionListener(this);
                student4Radio.setBounds(250,360,size.width,size.height+20);
            }
            else if(student5Radio.getText() == null)
            {
                student5Radio.setText(roster1.get(4).getFirstName()+" "+roster1.get(4).getLastName());
                size = student5Radio.getPreferredSize();
                student5Radio.addActionListener(this);
                student5Radio.setBounds(250,380,size.width,size.height+20);
            }
            else if(student6Radio.getText() == null)
            {
                student6Radio.setText(roster1.get(5).getFirstName()+" "+roster1.get(5).getLastName());
                size = student6Radio.getPreferredSize();
                student6Radio.addActionListener(this);
                student6Radio.setBounds(250,400,size.width,size.height+20);
            }*/
        }
        /*if(action.getSource() ==  rosterChangesButton2)
        {
            rosterTableModel1.addRow(new Object[]{"","","",""});
            String firstName = firstNameEntry.getText();
            String lastName = lastNameEntry.getText();
            course2.addStudent(firstName, lastName);
            rosterTable1.setValueAt(firstName,rosterTableModel2.getRowCount()-1,0);
            rosterTable2.setValueAt(lastName,rosterTableModel2.getRowCount()-1,1);
            rosterTable2.setValueAt(courseCList.get(activeCourse).getRoster().get(rosterTableModel2.getRowCount()-1).getID(),rosterTableModel2.getRowCount()-1,2);
            rosterTable2.setValueAt(courseCList.get(activeCourse).getRoster().get(rosterTableModel2.getRowCount()-1).getOverallGrade(),rosterTableModel2.getRowCount()-1,3);
            
            try{
                writer.saveCourse(course2, "course2");
            } catch (IOException e){
                System.out.println("error saving");
            }
        }*/
        if(action.getSource() ==  assignmentChangesButton1)
        {
            noCategoryLabel.setVisible(false);
            String name = assignmentName.getText();
            String cat = assignmentCategory.getText();
            int catID = -1;
            Vector<String> catNames = courseCList.get(activeCourse).getGradebook().getCatNames();
            boolean catExists = false;
            for (int i = 0; i < catNames.size(); i++)
            {
                if (cat.equals(catNames.get(i)))
                {
                    catExists = true;
                    catID = courseCList.get(activeCourse).getGradebook().getCatID().get(i);
                }
            }
            if(catExists == true)
            {    
                assignmentsTableModel1.addRow(new Object[]{"","",""});
                int score = Integer.parseInt(assignmentScore.getText());
                courseCList.get(activeCourse).addAssignment(name, catID, score);
                assignmentsTable1.setValueAt(name,assignmentsTable1.getRowCount()-1,0);
                assignmentsTable1.setValueAt(cat,assignmentsTable1.getRowCount()-1,1);
                assignmentsTable1.setValueAt(score,assignmentsTable1.getRowCount()-1,2);

                try{
                    writer.saveCourse(courseCList.get(activeCourse), courses.get(activeCourse));
                } catch (IOException e){
                    System.out.println("error saving");
                }
                
                gradesTableModel1.addRow(new Object[]{"","","",""});
                /*if(assignment4Radio.getText() == null)
                {
                    assignment4Radio.setText(assignments1.get(3).getName());
                    size = assignment4Radio.getPreferredSize();
                    assignment4Radio.addActionListener(this);
                    assignment4Radio.setBounds(100,360,size.width,size.height+20);
                }
                else if(assignment5Radio.getText() == null)
                {
                    assignment5Radio.setText(assignments1.get(4).getName());
                    size = assignment5Radio.getPreferredSize();
                    assignment5Radio.addActionListener(this);
                    assignment5Radio.setBounds(100,380,size.width,size.height+20);
                }
                else if(assignment6Radio.getText() == null)
                {
                    assignment6Radio.setText(assignments1.get(5).getName());
                    size = assignment6Radio.getPreferredSize();
                    assignment6Radio.addActionListener(this);
                    assignment6Radio.setBounds(100,400,size.width,size.height+20);
                }*/
            }
            else
            {
                noCategoryLabel.setVisible(true);
            }
        }
        /*if(action.getSource() ==  assignmentChangesButton2)
        {
            noCategoryLabel.setVisible(false);
            String name = assignmentName.getText();
            String cat = assignmentCategory.getText();
            int catID = -1;
            Vector<String> catNames = courseCList.get(activeCourse).getGradebook().getCatNames();
            boolean catExists = false;
            for (int i = 0; i < catNames.size(); i++)
            {
                if (cat.equals(catNames.get(i)))
                {
                    catExists = true;
                    catID = courseCList.get(activeCourse).getGradebook().getCatID().get(i);
                }
            }
            if(catExists == true)
            {    
                assignmentsTableModel2.addRow(new Object[]{"","",""});
                int score = Integer.parseInt(assignmentScore.getText());
                course1.addAssignment(name, catID, score);
                assignmentsTable2.setValueAt(name,assignmentsTable2.getRowCount()-1,0);
                assignmentsTable2.setValueAt(cat,assignmentsTable2.getRowCount()-1,1);
                assignmentsTable2.setValueAt(score,assignmentsTable2.getRowCount()-1,2);

                try{
                    writer.saveCourse(course2, "course2");
                } catch (IOException e){
                    System.out.println("error saving");
                }
            }
            else
            {
                noCategoryLabel.setVisible(true);
            }
        }*/
        if(action.getSource() == scoreButton1)
        {
            aScoreEntry.setVisible(!aScoreEntry.isVisible());
            bScoreEntry.setVisible(!bScoreEntry.isVisible());
            cScoreEntry.setVisible(!cScoreEntry.isVisible());
            dScoreEntry.setVisible(!dScoreEntry.isVisible());
            scoreChangesButton1.setVisible(!scoreChangesButton1.isVisible());
            scoreLabel.setVisible(!scoreLabel.isVisible());
            assignmentLabel.setVisible(false);
            assignmentName.setVisible(false);
            assignmentCategory.setVisible(false);
            assignmentScore.setVisible(false);
            assignmentChangesButton1.setVisible(false);
            noCategoryLabel.setVisible(false);
            gradebookChangesButton1.setVisible(false);
            newCategory.setVisible(false);
            categoryWeight.setVisible(false);
            categoryLabel.setVisible(false);
            noCategoryLabel.setVisible(false);
        }
        if(action.getSource() ==  scoreChangesButton1)
        {
            
            int[] inputScale = new int[4];
            inputScale[0] = Integer.parseInt(aScoreEntry.getText());
            inputScale[1] = Integer.parseInt(bScoreEntry.getText());
            inputScale[2] = Integer.parseInt(cScoreEntry.getText());
            inputScale[3] = Integer.parseInt(dScoreEntry.getText());
            courseCList.get(activeCourse).getGradebook().setScale(inputScale);
            try{
                writer.saveCourse(courseCList.get(activeCourse), courses.get(activeCourse));
            } catch (IOException e){
                System.out.println("error saving");
            }
        }
        /*if(action.getSource() == scoreButton2)
        {
            aScoreEntry.setVisible(!aScoreEntry.isVisible());
            bScoreEntry.setVisible(!bScoreEntry.isVisible());
            cScoreEntry.setVisible(!cScoreEntry.isVisible());
            dScoreEntry.setVisible(!dScoreEntry.isVisible());
            scoreChangesButton2.setVisible(!scoreChangesButton2.isVisible());
            scoreLabel.setVisible(!scoreLabel.isVisible());
            assignmentLabel.setVisible(false);
            assignmentName.setVisible(false);
            assignmentCategory.setVisible(false);
            assignmentScore.setVisible(false);
            assignmentChangesButton2.setVisible(false);
            noCategoryLabel.setVisible(false);
            gradebookChangesButton2.setVisible(false);
            newCategory.setVisible(false);
            categoryWeight.setVisible(false);
            categoryLabel.setVisible(false);
            noCategoryLabel.setVisible(false);
        }
        if(action.getSource() == scoreChangesButton2)
        {
            int[] inputScale = new int[4];
            inputScale[0] = Integer.parseInt(aScoreEntry.getText());
            inputScale[1] = Integer.parseInt(bScoreEntry.getText());
            inputScale[2] = Integer.parseInt(cScoreEntry.getText());
            inputScale[3] = Integer.parseInt(dScoreEntry.getText());
            courseCList.get(activeCourse).getGradebook().setScale(inputScale);
            try{
                writer.saveCourse(course2, "course2");
            } catch (IOException e){
                System.out.println("error saving");
            }
        }*/
        if(action.getSource() == gradebookAddButton1)
        {
            gradebookChangesButton1.setVisible(!gradebookChangesButton1.isVisible());
            newCategory.setVisible(!newCategory.isVisible());
            categoryWeight.setVisible(!categoryWeight.isVisible());
            categoryLabel.setVisible(!categoryLabel.isVisible());
            assignmentChangesButton1.setVisible(false);
            scoreChangesButton1.setVisible(false);
            assignmentLabel.setVisible(false);
            assignmentName.setVisible(false);
            assignmentCategory.setVisible(false);
            assignmentScore.setVisible(false);
            scoreLabel.setVisible(false);
            aScoreEntry.setVisible(false);
            bScoreEntry.setVisible(false);
            cScoreEntry.setVisible(false);
            dScoreEntry.setVisible(false);
            noCategoryLabel.setVisible(false);
            newCategory.setText("");
            categoryWeight.setText("");
        }
        /*if(action.getSource() == gradebookAddButton2)
        {
            gradebookChangesButton2.setVisible(!gradebookChangesButton2.isVisible());
            newCategory.setVisible(!newCategory.isVisible());
            categoryWeight.setVisible(!categoryWeight.isVisible());
            categoryLabel.setVisible(!categoryLabel.isVisible());
            assignmentChangesButton2.setVisible(false);
            scoreChangesButton2.setVisible(false);
            assignmentLabel.setVisible(false);
            assignmentName.setVisible(false);
            assignmentCategory.setVisible(false);
            assignmentScore.setVisible(false);
            scoreLabel.setVisible(false);
            aScoreEntry.setVisible(false);
            bScoreEntry.setVisible(false);
            cScoreEntry.setVisible(false);
            dScoreEntry.setVisible(false);
            noCategoryLabel.setVisible(false);
            newCategory.setText("");
            categoryWeight.setText("");
        }*/
        if(action.getSource() == gradebookChangesButton1)
        {
            gradebookTableModel1.addRow(new Object[]{"","","",""});
            String name = newCategory.getText();
            float weight = Float.parseFloat(categoryWeight.getText());
            courseCList.get(activeCourse).addCategory(name, weight);
            
            gradebookTable1.setValueAt(name,gradebookTable1.getRowCount()-1,0);
            gradebookTable1.setValueAt(courseCList.get(activeCourse).getGradebook().getCatID().size()-1,gradebookTableModel1.getRowCount()-1,1);
            gradebookTable1.setValueAt(weight,gradebookTable1.getRowCount()-1,2);
            try{
                writer.saveCourse(courseCList.get(activeCourse), courses.get(activeCourse));
            } catch (IOException e){
                System.out.println("error saving");
            }
        }
        /*if(action.getSource() == gradebookChangesButton2)
        {
            gradebookTableModel2.addRow(new Object[]{"","","",""});
            String name = newCategory.getText();
            float weight = Float.parseFloat(categoryWeight.getText());
            course2.addCategory(name, weight);
            gradebookTableModel2.setValueAt(name,gradebookTableModel2.getRowCount()-1,0);
            
            gradebookTableModel2.setValueAt(courseCList.get(activeCourse).getGradebook().getCatID().get(gradebookTableModel2.getRowCount()-1),gradebookTableModel2.getRowCount()-1,1);
            gradebookTableModel2.setValueAt(weight,gradebookTableModel2.getRowCount()-1,2);
            try{
                writer.saveCourse(course2, "course2");
            } catch (IOException e){
                System.out.println("error saving");
            }
        }*/
        if(action.getSource() == whatIfButton1)
        {
            courseWhatIf1 = courseCList.get(activeCourse);
            rosterWhatIf1 = courseWhatIf1.getRoster();
            gradebookWhatIf1 = courseWhatIf1.getGradebook();
            assignmentsWhatIf1 = courseWhatIf1.getGradebook().getAssignments();
            gradeButton1.setVisible(false);
            assignmentsPane1.setVisible(false);
            assignmentButton1.setVisible(false);
            rosterButton1.setVisible(false);
            rosterPane1.setVisible(false);
            gradebookPane1.setVisible(false);
            assignmentAddButton1.setVisible(false);
            rosterAddButton1.setVisible(false);
            rosterDeleteButton1.setVisible(false);
            classesButton.setVisible(false);
            nameLabel.setVisible(false);
            firstNameEntry.setVisible(false);
            lastNameEntry.setVisible(false);
            rosterChangesButton1.setVisible(false);
            aScoreEntry.setVisible(false);
            bScoreEntry.setVisible(false);
            cScoreEntry.setVisible(false);
            dScoreEntry.setVisible(false);
            scoreButton1.setVisible(false);
            scoreChangesButton1.setVisible(false);
            assignmentChangesButton1.setVisible(false);
            assignmentLabel.setVisible(false);
            scoreLabel.setVisible(false);
            assignmentName.setVisible(false);
            assignmentCategory.setVisible(false);
            assignmentScore.setVisible(false);
            noCategoryLabel.setVisible(false);
            gradebookAddButton1.setVisible(false);
            assignmentDeleteButton1.setVisible(false);
            gradebookDeleteButton1.setVisible(false);
            gradebookChangesButton1.setVisible(false);
            newCategory.setVisible(false);
            categoryWeight.setVisible(false);
            categoryLabel.setVisible(false);
            titleLabel.setVisible(false);
            whatIfButton1.setVisible(false);
            whatIfLabel.setVisible(true);
            saveWhatIfButton1.setVisible(true);
            deleteWhatIfButton1.setVisible(true);
            assignmentWhatIfButton1.setVisible(true);
            rosterWhatIfButton1.setVisible(true);
            deleteStudentButton.setVisible(false);
            
            for (int i = 0; i < assignmentsWhatIf1.size(); i++)
            {
                assignmentsWhatIfTable1.setValueAt(assignmentsWhatIf1.get(i).getName(),i,0);
                assignmentsWhatIfTable1.setValueAt(gradebookWhatIf1.getCatNames().get(i),i,1);
                assignmentsWhatIfTable1.setValueAt(assignmentsWhatIf1.get(i).getMaxScore(),i,2);
                
                rosterWhatIfTable1.setValueAt(rosterWhatIf1.get(i).getFirstName(),i,0);
                rosterWhatIfTable1.setValueAt(rosterWhatIf1.get(i).getLastName(),i,1);
                rosterWhatIfTable1.setValueAt(rosterWhatIf1.get(i).getID(),i,2);
                rosterWhatIfTable1.setValueAt(rosterWhatIf1.get(i).getOverallGrade(),i,3);
                
                gradebookWhatIfTable1.setValueAt(gradebookWhatIf1.getCatNames().get(i),i,0);
                gradebookWhatIfTable1.setValueAt(gradebookWhatIf1.getCatID().get(i),i,1);
                gradebookWhatIfTable1.setValueAt(gradebookWhatIf1.getCatWeights().get(i),i,2);
            }
        }
        if (action.getSource() == assignmentWhatIfButton1)
        {
            assignmentsWhatIfPane1.setVisible(true);
            gradebookWhatIfPane1.setVisible(true);
            assignmentWhatIfButton1.setVisible(false);
            rosterWhatIfButton1.setVisible(true);
            rosterWhatIfPane1.setVisible(false);
            assignmentWhatIfAddButton1.setVisible(true);
            rosterWhatIfAddButton1.setVisible(false);
            
            titleLabel.setText("Assignments                       Gradebook Setup");
            size =  titleLabel.getPreferredSize();
            titleLabel.setBounds(200, 50, size.width, size.height);
            titleLabel.setVisible(true);
            nameLabel.setVisible(false);
            firstNameEntry.setVisible(false);
            lastNameEntry.setVisible(false);
            rosterWhatIfChangesButton1.setVisible(false);
            scoreWhatIfButton1.setVisible(true);
            gradebookWhatIfAddButton1.setVisible(true);
            gradebookWhatIfChangesButton1.setVisible(false);
            newCategory.setVisible(false);
            categoryWeight.setVisible(false);
            categoryLabel.setVisible(false);
        }
        if (action.getSource() == assignmentWhatIfAddButton1)
        {
            assignmentLabel.setVisible(!assignmentLabel.isVisible());
            assignmentName.setVisible(!assignmentName.isVisible());
            assignmentCategory.setVisible(!assignmentCategory.isVisible());
            assignmentScore.setVisible(!assignmentScore.isVisible());
            assignmentName.setText("");
            assignmentCategory.setText("");
            assignmentScore.setText("100");
            assignmentWhatIfChangesButton1.setVisible(!assignmentWhatIfChangesButton1.isVisible());
            noCategoryLabel.setVisible(false);
            scoreLabel.setVisible(false);
            aScoreEntry.setVisible(false);
            bScoreEntry.setVisible(false);
            cScoreEntry.setVisible(false);
            dScoreEntry.setVisible(false);
            scoreWhatIfChangesButton1.setVisible(false);
            gradebookWhatIfChangesButton1.setVisible(false);
            newCategory.setVisible(false);
            categoryWeight.setVisible(false);
            categoryLabel.setVisible(false);
            noCategoryLabel.setVisible(false);
        }
        if(action.getSource() ==  assignmentWhatIfChangesButton1)
        {
            noCategoryLabel.setVisible(false);
            String name = assignmentName.getText();
            String cat = assignmentCategory.getText();
            int catID = -1;
            Vector<String> catNames = gradebookWhatIf1.getCatNames();
            boolean catExists = false;
            for (int i = 0; i < catNames.size(); i++)
            {
                if (cat.equals(catNames.get(i)))
                {
                    catExists = true;
                    catID = gradebookWhatIf1.getCatID().get(i);
                }
            }
            if(catExists == true)
            {    
                assignmentsWhatIfTableModel1.addRow(new Object[]{"","",""});
                int score = Integer.parseInt(assignmentScore.getText());
                courseWhatIf1.addAssignment(name, catID, score);
                assignmentsWhatIfTable1.setValueAt(name,assignmentsWhatIfTable1.getRowCount()-1,0);
                assignmentsWhatIfTable1.setValueAt(cat,assignmentsWhatIfTable1.getRowCount()-1,1);
                assignmentsWhatIfTable1.setValueAt(score,assignmentsWhatIfTable1.getRowCount()-1,2);

                /*try{
                    writer.saveCourse(course1, "course1");
                } catch (IOException e){
                    System.out.println("error saving");
                }*/
            }
            else
            {
                noCategoryLabel.setVisible(true);
            }
        }
        if(action.getSource() == scoreWhatIfButton1)
        {
            aScoreEntry.setVisible(!aScoreEntry.isVisible());
            bScoreEntry.setVisible(!bScoreEntry.isVisible());
            cScoreEntry.setVisible(!cScoreEntry.isVisible());
            dScoreEntry.setVisible(!dScoreEntry.isVisible());
            scoreWhatIfChangesButton1.setVisible(!scoreWhatIfChangesButton1.isVisible());
            scoreLabel.setVisible(!scoreLabel.isVisible());
            assignmentLabel.setVisible(false);
            assignmentName.setVisible(false);
            assignmentCategory.setVisible(false);
            assignmentScore.setVisible(false);
            assignmentWhatIfChangesButton1.setVisible(false);
            noCategoryLabel.setVisible(false);
            gradebookWhatIfChangesButton1.setVisible(false);
            newCategory.setVisible(false);
            categoryWeight.setVisible(false);
            categoryLabel.setVisible(false);
            noCategoryLabel.setVisible(false);
        }
        if(action.getSource() ==  scoreWhatIfChangesButton1)
        {
            
            int[] inputScale = new int[4];
            inputScale[0] = Integer.parseInt(aScoreEntry.getText());
            inputScale[1] = Integer.parseInt(bScoreEntry.getText());
            inputScale[2] = Integer.parseInt(cScoreEntry.getText());
            inputScale[3] = Integer.parseInt(dScoreEntry.getText());
            gradebookWhatIf1.setScale(inputScale);
            /*try{
                writer.saveCourse(course1, "course1");
            } catch (IOException e){
                System.out.println("error saving");
            }*/
        }
        if (action.getSource() == rosterWhatIfButton1)
        {
            assignmentsWhatIfPane1.setVisible(false);
            gradebookWhatIfPane1.setVisible(false);
            assignmentWhatIfButton1.setVisible(true);
            rosterWhatIfButton1.setVisible(false);
            rosterWhatIfPane1.setVisible(true);
            assignmentWhatIfAddButton1.setVisible(false);
            rosterWhatIfAddButton1.setVisible(true);
            scoreWhatIfButton1.setVisible(false);
            scoreWhatIfChangesButton1.setVisible(false);
            aScoreEntry.setVisible(false);
            bScoreEntry.setVisible(false);
            cScoreEntry.setVisible(false);
            dScoreEntry.setVisible(false);
            assignmentWhatIfChangesButton1.setVisible(false);
            assignmentLabel.setVisible(false);
            scoreLabel.setVisible(false);
            assignmentName.setVisible(false);
            assignmentCategory.setVisible(false);
            assignmentScore.setVisible(false);
            noCategoryLabel.setVisible(false);
            gradebookWhatIfAddButton1.setVisible(false);
            gradebookWhatIfChangesButton1.setVisible(false);
            newCategory.setVisible(false);
            categoryWeight.setVisible(false);
            categoryLabel.setVisible(false);
            
            titleLabel.setText("Roster");
            size =  titleLabel.getPreferredSize();
            titleLabel.setBounds(200, 50, size.width, size.height);
            titleLabel.setVisible(true);
        }
        if (action.getSource() == rosterWhatIfAddButton1)
        {
            nameLabel.setVisible(!nameLabel.isVisible());
            firstNameEntry.setVisible(!firstNameEntry.isVisible());
            lastNameEntry.setVisible(!lastNameEntry.isVisible());
            firstNameEntry.setText("");
            lastNameEntry.setText("");
            rosterWhatIfChangesButton1.setVisible(!rosterWhatIfChangesButton1.isVisible());
        }
        if(action.getSource() ==  rosterWhatIfChangesButton1)
        {
            rosterWhatIfTableModel1.addRow(new Object[]{"","","",""});
            String firstName = firstNameEntry.getText();
            String lastName = lastNameEntry.getText();
            courseWhatIf1.addStudent(firstName, lastName);
            rosterWhatIfTable1.setValueAt(firstName,rosterWhatIfTableModel1.getRowCount()-1,0);
            rosterWhatIfTable1.setValueAt(lastName,rosterWhatIfTableModel1.getRowCount()-1,1);
            rosterWhatIfTable1.setValueAt(rosterWhatIf1.get(rosterWhatIfTableModel1.getRowCount()-1).getID(),rosterWhatIfTableModel1.getRowCount()-1,2);
            rosterWhatIfTable1.setValueAt(rosterWhatIf1.get(rosterWhatIfTableModel1.getRowCount()-1).getOverallGrade(),rosterWhatIfTableModel1.getRowCount()-1,3);
            
            /*try{
                writer.saveCourse(course1, "course1");
            } catch (IOException e){
                System.out.println("error saving");
            }*/
        }
        if(action.getSource() == gradebookWhatIfAddButton1)
        {
            gradebookWhatIfChangesButton1.setVisible(!gradebookWhatIfChangesButton1.isVisible());
            newCategory.setVisible(!newCategory.isVisible());
            categoryWeight.setVisible(!categoryWeight.isVisible());
            categoryLabel.setVisible(!categoryLabel.isVisible());
            assignmentWhatIfChangesButton1.setVisible(false);
            scoreWhatIfChangesButton1.setVisible(false);
            assignmentLabel.setVisible(false);
            assignmentName.setVisible(false);
            assignmentCategory.setVisible(false);
            assignmentScore.setVisible(false);
            scoreLabel.setVisible(false);
            aScoreEntry.setVisible(false);
            bScoreEntry.setVisible(false);
            cScoreEntry.setVisible(false);
            dScoreEntry.setVisible(false);
            noCategoryLabel.setVisible(false);
            newCategory.setText("");
            categoryWeight.setText("");
        }
        if(action.getSource() == gradebookWhatIfChangesButton1)
        {
            gradebookWhatIfTableModel1.addRow(new Object[]{"","","",""});
            String name = newCategory.getText();
            float weight = Float.parseFloat(categoryWeight.getText());
            courseWhatIf1.addCategory(name, weight);
            gradebookWhatIfTableModel1.setValueAt(name,gradebookWhatIfTableModel1.getRowCount()-1,0);
            
            gradebookWhatIfTableModel1.setValueAt(gradebookWhatIf1.getCatID().get(gradebookWhatIfTableModel1.getRowCount()-1),gradebookWhatIfTableModel1.getRowCount()-1,1);
            gradebookWhatIfTableModel1.setValueAt(weight,gradebookWhatIfTableModel1.getRowCount()-1,2);
            /*try{
                writer.saveCourse(course1, "course1");
            } catch (IOException e){
                System.out.println("error saving");
            }*/
        }
        if(action.getSource() == changePwordButton)
        {
            changeField.setVisible(true);
            changeLabel.setVisible(true);
            changePwordButton.setVisible(false);
        }
        if(action.getSource() == changeField)
        {
            password = passwordField.getText();
           if(passwordField.getText().equals(changeField.getText()))
           {
               changeField.setVisible(false);
               changeLabel.setVisible(false);
               changePwordButton.setVisible(true);
               cList.setPassword(changeField.getText());
           }
           else
           {
               incorrectLoginLabel.setText("<html><font color = 'FF0000'>Passwords do not match</font></html>");
               size = incorrectLoginLabel.getPreferredSize();
               incorrectLoginLabel.setVisible(true);
            }
        }
        if(action.getSource() == saveWhatIfButton1) {
            try {
                writer.saveCourse(courseWhatIf1, courses.get(activeCourse));
            } catch (IOException e) {
                System.out.println("error saving");
            }

            courseCList.set(activeCourse, courseWhatIf1);
            //roster1 = courseWhatIf1.getRoster();
            //gradebook1 = courseWhatIf1.getGradebook();
            //assignments1 = courseWhatIf1.getGradebook().getAssignments();

            // This is the code I added - Braden
            while (true){
                if (courseCList.get(activeCourse).getGradebook().getAssignments().size() > assignmentsTable1.getRowCount()) {
                    assignmentsTableModel1.addRow(new Object[]{"", "", ""});
                }
                else break;
            }

            while (true){
                if (courseCList.get(activeCourse).getRoster().size() > rosterTable1.getRowCount()) {
                    rosterTableModel1.addRow(new Object[]{"", "", ""});
                }
                else break;
            }

            while (true){
                if (courseCList.get(activeCourse).getGradebook().getCatID().size() > gradebookTable1.getRowCount()) {
                    gradebookTableModel1.addRow(new Object[]{"", "", ""});
                }
                else break;
            }
            
            for (int i = 0; i < courseCList.get(activeCourse).getGradebook().getAssignments().size(); i++){
                assignmentsTable1.setValueAt(courseCList.get(activeCourse).getGradebook().getAssignments().get(i).getName(),i,0);
                assignmentsTable1.setValueAt(courseCList.get(activeCourse).getGradebook().getCatNames().get(i),i,1);
                assignmentsTable1.setValueAt(courseCList.get(activeCourse).getGradebook().getAssignments().get(i).getMaxScore(),i,2);
            }

            for (int i = 0; i < courseCList.get(activeCourse).getRoster().size(); i++){
                rosterTable1.setValueAt(courseCList.get(activeCourse).getRoster().get(i).getFirstName(),i,0);
                rosterTable1.setValueAt(courseCList.get(activeCourse).getRoster().get(i).getLastName(),i,1);
                rosterTable1.setValueAt(courseCList.get(activeCourse).getRoster().get(i).getID(),i,2);
                rosterTable1.setValueAt(courseCList.get(activeCourse).getRoster().get(i).getOverallGrade(),i,3);
            }

            for (int i = 0; i< courseCList.get(activeCourse).getGradebook().getCatID().size(); i++){
                gradebookTable1.setValueAt(courseCList.get(activeCourse).getGradebook().getCatNames().get(i),i,0);
                gradebookTable1.setValueAt(courseCList.get(activeCourse).getGradebook().getCatID().get(i),i,1);
                gradebookTable1.setValueAt(courseCList.get(activeCourse).getGradebook().getCatWeights().get(i),i,2);
            }
            
            assignmentButton1.setVisible(true);
            rosterButton1.setVisible(true);
            whatIfButton1.setVisible(true);
            
            assignmentWhatIfButton1.setVisible(false);
            rosterWhatIfButton1.setVisible(false);
            assignmentsWhatIfPane1.setVisible(false);
            gradebookWhatIfPane1.setVisible(false);
            rosterWhatIfPane1.setVisible(false);
            whatIfLabel.setVisible(false);
            
            assignmentWhatIfAddButton1.setVisible(false);
            gradebookWhatIfAddButton1.setVisible(false);
            rosterWhatIfAddButton1.setVisible(false);
            assignmentWhatIfChangesButton1.setVisible(false);
            gradebookWhatIfChangesButton1.setVisible(false);
            rosterWhatIfChangesButton1.setVisible(false);
            aScoreEntry.setVisible(false);
            bScoreEntry.setVisible(false);
            cScoreEntry.setVisible(false);
            dScoreEntry.setVisible(false);
            scoreLabel.setVisible(false);
            nameLabel.setVisible(false);
            assignmentLabel.setVisible(false);
            noCategoryLabel.setVisible(false);
            categoryLabel.setVisible(false);
            whatIfLabel.setVisible(false);
            firstNameEntry.setVisible(false);
            lastNameEntry.setVisible(false);
            assignmentName.setVisible(false);
            assignmentCategory.setVisible(false);
            assignmentScore.setVisible(false);
            assignmentName.setVisible(false);
            assignmentCategory.setVisible(false);
            assignmentScore.setVisible(false);
            noCategoryLabel.setVisible(false);
            titleLabel.setVisible(false);
            saveWhatIfButton1.setVisible(false);
            deleteWhatIfButton1.setVisible(false);
            
            classesButton.setVisible(true);
            
            
            classLabel.setText(class1Button.getText());
            size =  classLabel.getPreferredSize();
            classLabel.setBounds(50, 50, size.width, size.height);
            classLabel.setVisible(true);
        }
        if(action.getSource() == deleteWhatIfButton1)
        {
            assignmentButton1.setVisible(true);
            rosterButton1.setVisible(true);
            whatIfButton1.setVisible(true);
            
            assignmentWhatIfButton1.setVisible(false);
            rosterWhatIfButton1.setVisible(false);
            assignmentsWhatIfPane1.setVisible(false);
            gradebookWhatIfPane1.setVisible(false);
            rosterWhatIfPane1.setVisible(false);
            whatIfLabel.setVisible(false);
            
            assignmentWhatIfAddButton1.setVisible(false);
            gradebookWhatIfAddButton1.setVisible(false);
            rosterWhatIfAddButton1.setVisible(false);
            assignmentWhatIfChangesButton1.setVisible(false);
            gradebookWhatIfChangesButton1.setVisible(false);
            rosterWhatIfChangesButton1.setVisible(false);
            aScoreEntry.setVisible(false);
            bScoreEntry.setVisible(false);
            cScoreEntry.setVisible(false);
            dScoreEntry.setVisible(false);
            scoreLabel.setVisible(false);
            nameLabel.setVisible(false);
            assignmentLabel.setVisible(false);
            noCategoryLabel.setVisible(false);
            categoryLabel.setVisible(false);
            whatIfLabel.setVisible(false);
            firstNameEntry.setVisible(false);
            lastNameEntry.setVisible(false);
            assignmentName.setVisible(false);
            assignmentCategory.setVisible(false);
            assignmentScore.setVisible(false);
            assignmentName.setVisible(false);
            assignmentCategory.setVisible(false);
            assignmentScore.setVisible(false);
            noCategoryLabel.setVisible(false);
            titleLabel.setVisible(false);
            saveWhatIfButton1.setVisible(false);
            deleteWhatIfButton1.setVisible(false);
            
            classesButton.setVisible(true);
        }
        if(action.getSource() == gradeButton1)
        {
            courseWhatIf1 = courseCList.get(activeCourse);
            rosterWhatIf1 = courseWhatIf1.getRoster();
            gradebookWhatIf1 = courseWhatIf1.getGradebook();
            assignmentsWhatIf1 = courseWhatIf1.getGradebook().getAssignments();
            assignmentsPane1.setVisible(false);
            assignmentButton1.setVisible(false);
            rosterButton1.setVisible(false);
            rosterPane1.setVisible(false);
            gradebookPane1.setVisible(false);
            assignmentAddButton1.setVisible(false);
            rosterAddButton1.setVisible(false);
            rosterDeleteButton1.setVisible(false);
            classesButton.setVisible(false);
            nameLabel.setVisible(false);
            firstNameEntry.setVisible(false);
            lastNameEntry.setVisible(false);
            rosterChangesButton1.setVisible(false);
            aScoreEntry.setVisible(false);
            bScoreEntry.setVisible(false);
            cScoreEntry.setVisible(false);
            dScoreEntry.setVisible(false);
            scoreButton1.setVisible(false);
            scoreChangesButton1.setVisible(false);
            assignmentChangesButton1.setVisible(false);
            assignmentLabel.setVisible(false);
            scoreLabel.setVisible(false);
            assignmentName.setVisible(false);
            assignmentCategory.setVisible(false);
            assignmentScore.setVisible(false);
            noCategoryLabel.setVisible(false);
            gradebookAddButton1.setVisible(false);
            assignmentDeleteButton1.setVisible(false);
            gradebookDeleteButton1.setVisible(false);
            gradebookChangesButton1.setVisible(false);
            newCategory.setVisible(false);
            categoryWeight.setVisible(false);
            categoryLabel.setVisible(false);
            titleLabel.setVisible(false);
            whatIfButton1.setVisible(false);
            gradeButton1.setVisible(false);
            deleteStudentButton.setVisible(false);
            
            /*assignment1Radio.setVisible(true);
            assignment2Radio.setVisible(true);
            assignment3Radio.setVisible(true);
            if(assignment4Radio.getText() != null)
                assignment4Radio.setVisible(true);
            if(assignment5Radio.getText() != null)
                assignment5Radio.setVisible(true);
            if(assignment6Radio.getText() != null)
                assignment6Radio.setVisible(true);
            student1Radio.setVisible(true);
            student2Radio.setVisible(true);
            student3Radio.setVisible(true);
            if(student4Radio.getText() != null)
                student4Radio.setVisible(true);
            if(student5Radio.getText() != null)
                student5Radio.setVisible(true);
            if(student6Radio.getText() != null)
                student6Radio.setVisible(true);*/
            //gradeLabel.setVisible(true);
            gradesPane1.setVisible(true);
            //gradeChangeField.setVisible(true);
            backButton.setVisible(true);
            
            for (int i = 0; i < courseCList.get(activeCourse).getGradebook().getAssignments().size(); i++)
            {
                gradesTable1.setValueAt(courseCList.get(activeCourse).getGradebook().getAssignments().get(i).getName(), i, 0);
            }
            for (int i = 0; i < courseCList.get(activeCourse).getRoster().size(); i++)
            {
                try{
                    gradesTable1.setValueAt(courseCList.get(activeCourse).getRoster().get(0).getScores().get(i), i, 1);
                    gradesTable1.setValueAt(courseCList.get(activeCourse).getRoster().get(1).getScores().get(i), i, 2);
                    gradesTable1.setValueAt(courseCList.get(activeCourse).getRoster().get(2).getScores().get(i), i, 3);
                } catch (ArrayIndexOutOfBoundsException e)
                {
                    System.out.println("array out of bounds");
                }
            }
        }
        /*if(action.getSource() == assignment1Radio)
        {
            assignment2Radio.setSelected(false);
            assignment3Radio.setSelected(false);
            assignment4Radio.setSelected(false);
            assignment5Radio.setSelected(false);
            assignment6Radio.setSelected(false);
            if (assignment1Radio.isSelected() && student1Radio.isSelected())
            {
                gradeChangeField.setText(Float.toString(roster1.get(0).getScores().get(0)));
                gradeChange = 0;
            }
            if (assignment1Radio.isSelected() && student2Radio.isSelected())
            {
                gradeChangeField.setText(Float.toString(roster1.get(1).getScores().get(0)));
                gradeChange = 1;
            }
            if (assignment1Radio.isSelected() && student3Radio.isSelected())
            {
                gradeChangeField.setText(Float.toString(roster1.get(2).getScores().get(0)));
                gradeChange = 2;
            }
            if (assignment1Radio.isSelected() && student4Radio.isSelected())
            {
                gradeChangeField.setText(Float.toString(roster1.get(3).getScores().get(0)));
                gradeChange = 3;
            }
            if (assignment1Radio.isSelected() && student5Radio.isSelected())
            {
                gradeChangeField.setText(Float.toString(roster1.get(4).getScores().get(0)));
                gradeChange = 4;
            }
            if (assignment1Radio.isSelected() && student6Radio.isSelected())
            {
                gradeChangeField.setText(Float.toString(roster1.get(5).getScores().get(0)));
                gradeChange = 5;
            }
        }
        if(action.getSource() == assignment2Radio)
        {
            assignment1Radio.setSelected(false);
            assignment3Radio.setSelected(false);
            assignment4Radio.setSelected(false);
            assignment5Radio.setSelected(false);
            assignment6Radio.setSelected(false);
            if (assignment2Radio.isSelected() && student1Radio.isSelected())
            {
                gradeChangeField.setText(Float.toString(roster1.get(0).getScores().get(1)));
                gradeChange = 6;
            }
            if (assignment2Radio.isSelected() && student2Radio.isSelected())
            {
                gradeChangeField.setText(Float.toString(roster1.get(1).getScores().get(1)));
                gradeChange = 7;
            }
            if (assignment2Radio.isSelected() && student3Radio.isSelected())
            {
                gradeChangeField.setText(Float.toString(roster1.get(2).getScores().get(1)));
                gradeChange = 8;
            }
            if (assignment2Radio.isSelected() && student4Radio.isSelected())
            {
                gradeChangeField.setText(Float.toString(roster1.get(3).getScores().get(1)));
                gradeChange = 9;
            }
            if (assignment2Radio.isSelected() && student5Radio.isSelected())
            {
                gradeChangeField.setText(Float.toString(roster1.get(4).getScores().get(1)));
                gradeChange = 10;
            }
            if (assignment2Radio.isSelected() && student6Radio.isSelected())
            {
                gradeChangeField.setText(Float.toString(roster1.get(5).getScores().get(1)));
                gradeChange = 11;
            }
        }
        if(action.getSource() == assignment3Radio)
        {
            assignment1Radio.setSelected(false);
            assignment2Radio.setSelected(false);
            assignment4Radio.setSelected(false);
            assignment5Radio.setSelected(false);
            assignment6Radio.setSelected(false);
            if (assignment3Radio.isSelected() && student1Radio.isSelected())
            {
                gradeChangeField.setText(Float.toString(roster1.get(0).getScores().get(2)));
                gradeChange = 12;
            }
            if (assignment3Radio.isSelected() && student2Radio.isSelected())
            {
                gradeChangeField.setText(Float.toString(roster1.get(1).getScores().get(2)));
                gradeChange = 13;
            }
            if (assignment3Radio.isSelected() && student3Radio.isSelected())
            {
                gradeChangeField.setText(Float.toString(roster1.get(2).getScores().get(2)));
                gradeChange = 14;
            }
            if (assignment3Radio.isSelected() && student4Radio.isSelected())
            {
                gradeChangeField.setText(Float.toString(roster1.get(3).getScores().get(2)));
                gradeChange = 15;
            }
            if (assignment3Radio.isSelected() && student5Radio.isSelected())
            {
                gradeChangeField.setText(Float.toString(roster1.get(4).getScores().get(2)));
                gradeChange = 16;
            }
            if (assignment3Radio.isSelected() && student6Radio.isSelected())
            {
                gradeChangeField.setText(Float.toString(roster1.get(5).getScores().get(2)));
                gradeChange = 17;
            }
        }
        if(action.getSource() == assignment4Radio)
        {
            assignment1Radio.setSelected(false);
            assignment2Radio.setSelected(false);
            assignment3Radio.setSelected(false);
            assignment5Radio.setSelected(false);
            assignment6Radio.setSelected(false);
            if (assignment4Radio.isSelected() && student1Radio.isSelected())
            {
                gradeChangeField.setText(Float.toString(roster1.get(0).getScores().get(3)));
                gradeChange = 18;
            }
            if (assignment4Radio.isSelected() && student2Radio.isSelected())
            {
                gradeChangeField.setText(Float.toString(roster1.get(1).getScores().get(3)));
                gradeChange = 19;
            }
            if (assignment4Radio.isSelected() && student3Radio.isSelected())
            {
                gradeChangeField.setText(Float.toString(roster1.get(2).getScores().get(3)));
                gradeChange = 20;
            }
            if (assignment4Radio.isSelected() && student4Radio.isSelected())
            {
                gradeChangeField.setText(Float.toString(roster1.get(3).getScores().get(3)));
                gradeChange = 21;
            }
            if (assignment4Radio.isSelected() && student5Radio.isSelected())
            {
                gradeChangeField.setText(Float.toString(roster1.get(4).getScores().get(3)));
                gradeChange = 22;
            }
            if (assignment4Radio.isSelected() && student6Radio.isSelected())
            {
                gradeChangeField.setText(Float.toString(roster1.get(5).getScores().get(3)));
                gradeChange = 23;
            }
        }
        if(action.getSource() == assignment5Radio)
        {
            assignment1Radio.setSelected(false);
            assignment2Radio.setSelected(false);
            assignment3Radio.setSelected(false);
            assignment4Radio.setSelected(false);
            assignment6Radio.setSelected(false);
            if (assignment5Radio.isSelected() && student1Radio.isSelected())
            {
                gradeChangeField.setText(Float.toString(roster1.get(0).getScores().get(4)));
                gradeChange = 24;
            }
            if (assignment5Radio.isSelected() && student2Radio.isSelected())
            {
                gradeChangeField.setText(Float.toString(roster1.get(1).getScores().get(4)));
                gradeChange = 25;
            }
            if (assignment5Radio.isSelected() && student3Radio.isSelected())
            {
                gradeChangeField.setText(Float.toString(roster1.get(2).getScores().get(4)));
                gradeChange = 26;
            }
            if (assignment5Radio.isSelected() && student4Radio.isSelected())
            {
                gradeChangeField.setText(Float.toString(roster1.get(3).getScores().get(4)));
                gradeChange = 27;
            }
            if (assignment5Radio.isSelected() && student5Radio.isSelected())
            {
                gradeChangeField.setText(Float.toString(roster1.get(4).getScores().get(4)));
                gradeChange = 28;
            }
            if (assignment5Radio.isSelected() && student6Radio.isSelected())
            {
                gradeChangeField.setText(Float.toString(roster1.get(5).getScores().get(4)));
                gradeChange = 29;
            }
        }
        if(action.getSource() == assignment6Radio)
        {
            assignment1Radio.setSelected(false);
            assignment2Radio.setSelected(false);
            assignment3Radio.setSelected(false);
            assignment4Radio.setSelected(false);
            assignment5Radio.setSelected(false);
            if (assignment6Radio.isSelected() && student1Radio.isSelected())
            {
                gradeChangeField.setText(Float.toString(roster1.get(0).getScores().get(5)));
                gradeChange = 30;
            }
            if (assignment6Radio.isSelected() && student2Radio.isSelected())
            {
                gradeChangeField.setText(Float.toString(roster1.get(1).getScores().get(5)));
                gradeChange = 31;
            }
            if (assignment6Radio.isSelected() && student3Radio.isSelected())
            {
                gradeChangeField.setText(Float.toString(roster1.get(2).getScores().get(5)));
                gradeChange = 32;
            }
            if (assignment6Radio.isSelected() && student4Radio.isSelected())
            {
                gradeChangeField.setText(Float.toString(roster1.get(3).getScores().get(5)));
                gradeChange = 33;
            }
            if (assignment6Radio.isSelected() && student5Radio.isSelected())
            {
                gradeChangeField.setText(Float.toString(roster1.get(4).getScores().get(5)));
                gradeChange = 34;
            }
            if (assignment6Radio.isSelected() && student6Radio.isSelected())
            {
                gradeChangeField.setText(Float.toString(roster1.get(5).getScores().get(5)));
                gradeChange = 35;
            }
        }
        if(action.getSource() == student1Radio)
        {
            student2Radio.setSelected(false);
            student3Radio.setSelected(false);
            student4Radio.setSelected(false);
            student5Radio.setSelected(false);
            student6Radio.setSelected(false);
            if (assignment1Radio.isSelected() && student1Radio.isSelected())
            {
                gradeChangeField.setText(Float.toString(roster1.get(0).getScores().get(0)));
                gradeChange = 36;
            }
            if (assignment2Radio.isSelected() && student1Radio.isSelected())
            {
                gradeChangeField.setText(Float.toString(roster1.get(0).getScores().get(1)));
                gradeChange = 37;
            }
            if (assignment3Radio.isSelected() && student1Radio.isSelected())
            {
                gradeChangeField.setText(Float.toString(roster1.get(0).getScores().get(2)));
                oldGrade = Float.parseFloat(gradeChangeField.getText());
                gradeChange = 38;
            }
            if (assignment4Radio.isSelected() && student1Radio.isSelected())
            {
                gradeChangeField.setText(Float.toString(roster1.get(0).getScores().get(3)));
                gradeChange = 39;
            }
            if (assignment5Radio.isSelected() && student1Radio.isSelected())
            {
                gradeChangeField.setText(Float.toString(roster1.get(0).getScores().get(4)));
                gradeChange = 40;
            }
            if (assignment6Radio.isSelected() && student1Radio.isSelected())
            {
                gradeChangeField.setText(Float.toString(roster1.get(0).getScores().get(5)));
                oldGrade = Float.parseFloat(gradeChangeField.getText());
                gradeChange = 41;
            }
        }
        if(action.getSource() == student2Radio)
        {
            student1Radio.setSelected(false);
            student3Radio.setSelected(false);
            student4Radio.setSelected(false);
            student5Radio.setSelected(false);
            student6Radio.setSelected(false);
            if (assignment1Radio.isSelected() && student2Radio.isSelected())
            {
                gradeChangeField.setText(Float.toString(roster1.get(1).getScores().get(0)));
                gradeChange = 42;
            }
            if (assignment2Radio.isSelected() && student2Radio.isSelected())
            {
                gradeChangeField.setText(Float.toString(roster1.get(1).getScores().get(1)));
                gradeChange = 43;
            }
            if (assignment3Radio.isSelected() && student2Radio.isSelected())
            {
                gradeChangeField.setText(Float.toString(roster1.get(1).getScores().get(2)));
                gradeChange = 44;
            }
            if (assignment4Radio.isSelected() && student2Radio.isSelected())
            {
                gradeChangeField.setText(Float.toString(roster1.get(1).getScores().get(3)));
                gradeChange = 45;
            }
            if (assignment5Radio.isSelected() && student2Radio.isSelected())
            {
                gradeChangeField.setText(Float.toString(roster1.get(1).getScores().get(4)));
                gradeChange = 46;
            }
            if (assignment6Radio.isSelected() && student2Radio.isSelected())
            {
                gradeChangeField.setText(Float.toString(roster1.get(1).getScores().get(5)));
                gradeChange = 47;
            }
        }
        if(action.getSource() == student3Radio)
        {
            student1Radio.setSelected(false);
            student2Radio.setSelected(false);
            student4Radio.setSelected(false);
            student5Radio.setSelected(false);
            student6Radio.setSelected(false);
            if (assignment1Radio.isSelected() && student3Radio.isSelected())
            {
                gradeChangeField.setText(Float.toString(roster1.get(2).getScores().get(0)));
                gradeChange = 48;
            }
            if (assignment2Radio.isSelected() && student3Radio.isSelected())
            {
                gradeChangeField.setText(Float.toString(roster1.get(2).getScores().get(1)));
                gradeChange = 49;
            }
            if (assignment3Radio.isSelected() && student3Radio.isSelected())
            {
                gradeChangeField.setText(Float.toString(roster1.get(2).getScores().get(2)));
                gradeChange = 50;
            }
            if (assignment4Radio.isSelected() && student3Radio.isSelected())
            {
                gradeChangeField.setText(Float.toString(roster1.get(2).getScores().get(3)));
                gradeChange = 51;
            }
            if (assignment5Radio.isSelected() && student3Radio.isSelected())
            {
                gradeChangeField.setText(Float.toString(roster1.get(2).getScores().get(4)));
                gradeChange = 52;
            }
            if (assignment6Radio.isSelected() && student3Radio.isSelected())
            {
                gradeChangeField.setText(Float.toString(roster1.get(2).getScores().get(5)));
                gradeChange = 53;
            }
        }
        if(action.getSource() == student4Radio)
        {
            student1Radio.setSelected(false);
            student2Radio.setSelected(false);
            student3Radio.setSelected(false);
            student5Radio.setSelected(false);
            student6Radio.setSelected(false);
            if (assignment1Radio.isSelected() && student4Radio.isSelected())
            {
                gradeChangeField.setText(Float.toString(roster1.get(3).getScores().get(0)));
                gradeChange = 54;
            }
            if (assignment2Radio.isSelected() && student4Radio.isSelected())
            {
                gradeChangeField.setText(Float.toString(roster1.get(3).getScores().get(1)));
                gradeChange = 55;
            }
            if (assignment3Radio.isSelected() && student4Radio.isSelected())
            {
                gradeChangeField.setText(Float.toString(roster1.get(3).getScores().get(2)));
                gradeChange = 56;
            }
            if (assignment4Radio.isSelected() && student4Radio.isSelected())
            {
                gradeChangeField.setText(Float.toString(roster1.get(3).getScores().get(3)));
                gradeChange = 57;
            }
            if (assignment5Radio.isSelected() && student4Radio.isSelected())
            {
                gradeChangeField.setText(Float.toString(roster1.get(3).getScores().get(4)));
                gradeChange = 58;
            }
            if (assignment6Radio.isSelected() && student4Radio.isSelected())
            {
                gradeChangeField.setText(Float.toString(roster1.get(3).getScores().get(5)));
                gradeChange = 59;
            }
        }
        if(action.getSource() == student5Radio)
        {
            student1Radio.setSelected(false);
            student2Radio.setSelected(false);
            student3Radio.setSelected(false);
            student4Radio.setSelected(false);
            student6Radio.setSelected(false);
            if (assignment1Radio.isSelected() && student5Radio.isSelected())
            {
                gradeChangeField.setText(Float.toString(roster1.get(4).getScores().get(0)));
                gradeChange = 60;
            }
            if (assignment2Radio.isSelected() && student5Radio.isSelected())
            {
                gradeChangeField.setText(Float.toString(roster1.get(4).getScores().get(1)));
                gradeChange = 61;
            }
            if (assignment3Radio.isSelected() && student5Radio.isSelected())
            {
                gradeChangeField.setText(Float.toString(roster1.get(4).getScores().get(2)));
                gradeChange = 62;
            }
            if (assignment4Radio.isSelected() && student5Radio.isSelected())
            {
                gradeChangeField.setText(Float.toString(roster1.get(4).getScores().get(3)));
                gradeChange = 63;
            }
            if (assignment5Radio.isSelected() && student5Radio.isSelected())
            {
                gradeChangeField.setText(Float.toString(roster1.get(4).getScores().get(4)));
                gradeChange = 64;
            }
            if (assignment6Radio.isSelected() && student5Radio.isSelected())
            {
                gradeChangeField.setText(Float.toString(roster1.get(4).getScores().get(5)));
                gradeChange = 65;
            }
        }
        if(action.getSource() == student6Radio)
        {
            student1Radio.setSelected(false);
            student2Radio.setSelected(false);
            student3Radio.setSelected(false);
            student4Radio.setSelected(false);
            student5Radio.setSelected(false);
            if (assignment1Radio.isSelected() && student6Radio.isSelected())
            {
                gradeChangeField.setText(Float.toString(roster1.get(5).getScores().get(0)));
                gradeChange = 66;
            }
            if (assignment2Radio.isSelected() && student6Radio.isSelected())
            {
                gradeChangeField.setText(Float.toString(roster1.get(5).getScores().get(1)));
                gradeChange = 67;
            }
            if (assignment3Radio.isSelected() && student6Radio.isSelected())
            {
                gradeChangeField.setText(Float.toString(roster1.get(5).getScores().get(2)));
                gradeChange = 68;
            }
            if (assignment4Radio.isSelected() && student6Radio.isSelected())
            {
                gradeChangeField.setText(Float.toString(roster1.get(5).getScores().get(3)));
                gradeChange = 69;
            }
            if (assignment5Radio.isSelected() && student6Radio.isSelected())
            {
                gradeChangeField.setText(Float.toString(roster1.get(5).getScores().get(4)));
                gradeChange = 70;
            }
            if (assignment6Radio.isSelected() && student6Radio.isSelected())
            {
                gradeChangeField.setText(Float.toString(roster1.get(5).getScores().get(5)));
                gradeChange = 71;
            }
        }
        if(action.getSource() == gradeChangeField)
        {
            newGrade = Float.parseFloat(gradeChangeField.getText());
            switch(gradeChange)
            {
                case 0:
                    roster1.get(0).setScore(0, newGrade);
                    break;
                case 1:
                    roster1.get(1).setScore(0, newGrade);
                    break;
                case 2:
                    roster1.get(2).setScore(0, newGrade);
                    break;
                case 3:
                    roster1.get(3).setScore(0, newGrade);
                    break;
                case 4:
                    roster1.get(4).setScore(0, newGrade);
                    break;
                case 5:
                    roster1.get(5).setScore(0, newGrade);
                    break;
                case 6:
                    roster1.get(0).setScore(1, newGrade);
                    break;
                case 7:
                    roster1.get(1).setScore(1, newGrade);
                    break;
                case 8:
                    roster1.get(2).setScore(1, newGrade);
                    break;
                case 9:
                    roster1.get(3).setScore(1, newGrade);
                    break;
                case 10:
                    roster1.get(4).setScore(1, newGrade);
                    break;
                case 11:
                    roster1.get(5).setScore(1, newGrade);
                    break;
                case 12:
                    roster1.get(0).setScore(2, newGrade);
                    break;
                case 13:
                    roster1.get(1).setScore(2, newGrade);
                    break;
                case 14:
                    roster1.get(2).setScore(2, newGrade);
                    break;
                case 15:
                    roster1.get(3).setScore(2, newGrade);
                    break;
                case 16:
                    roster1.get(4).setScore(2, newGrade);
                    break;
                case 17:
                    roster1.get(5).setScore(2, newGrade);
                    break;
                case 18:
                    roster1.get(0).setScore(3, newGrade);
                    break;
                case 19:
                    roster1.get(1).setScore(3, newGrade);
                    break;
                case 20:
                    roster1.get(2).setScore(3, newGrade);
                    break;
                case 21:
                    roster1.get(3).setScore(3, newGrade);
                    break;
                case 22:
                    roster1.get(4).setScore(3, newGrade);
                    break;
                case 23:
                    roster1.get(5).setScore(3, newGrade);
                    break;
                case 24:
                    roster1.get(0).setScore(4, newGrade);
                    break;
                case 25:
                    roster1.get(1).setScore(4, newGrade);
                    break;
                case 26:
                    roster1.get(2).setScore(4, newGrade);
                    break;
                case 27:
                    roster1.get(3).setScore(4, newGrade);
                    break;
                case 28:
                    roster1.get(4).setScore(4, newGrade);
                    break;
                case 29:
                    roster1.get(5).setScore(4, newGrade);
                    break;
                case 30:
                    roster1.get(0).setScore(5, newGrade);
                    break;
                case 31:
                    roster1.get(1).setScore(5, newGrade);
                    break;
                case 32:
                    roster1.get(2).setScore(5, newGrade);
                    break;
                case 33:
                    roster1.get(3).setScore(5, newGrade);
                    break;
                case 34:
                    roster1.get(4).setScore(5, newGrade);
                    break;
                case 35:
                    roster1.get(5).setScore(5, newGrade);
                    break;
                case 36:
                    roster1.get(0).setScore(0, newGrade);
                    break;
                case 37:
                    roster1.get(0).setScore(1, newGrade);
                    break;
                case 38:
                    roster1.get(0).setScore(2, newGrade);
                    break;
                case 39:
                    roster1.get(0).setScore(3, newGrade);
                    break;
                case 40:
                    roster1.get(0).setScore(4, newGrade);
                    break;
                case 41:
                    roster1.get(0).setScore(5, newGrade);
                    break;
                case 42:
                    roster1.get(1).setScore(0, newGrade);
                    break;
                case 43:
                    roster1.get(1).setScore(1, newGrade);
                    break;
                case 44:
                    roster1.get(1).setScore(2, newGrade);
                    break;
                case 45:
                    roster1.get(1).setScore(3, newGrade);
                    break;
                case 46:
                    roster1.get(1).setScore(4, newGrade);
                    break;
                case 47:
                    roster1.get(1).setScore(5, newGrade);
                    break;
                case 48:
                    roster1.get(2).setScore(0, newGrade);
                    break;
                case 49:
                    roster1.get(2).setScore(1, newGrade);
                    break;
                case 50:
                    roster1.get(2).setScore(2, newGrade);
                    break;
                case 51:
                    roster1.get(2).setScore(3, newGrade);
                    break;
                case 52:
                    roster1.get(2).setScore(4, newGrade);
                    break;
                case 53:
                    roster1.get(2).setScore(5, newGrade);
                    break;
                case 54:
                    roster1.get(3).setScore(0, newGrade);
                    break;
                case 55:
                    roster1.get(3).setScore(1, newGrade);
                    break;
                case 56:
                    roster1.get(3).setScore(2, newGrade);
                    break;
                case 57:
                    roster1.get(3).setScore(3, newGrade);
                    break;
                case 58:
                    roster1.get(3).setScore(4, newGrade);
                    break;
                case 59:
                    roster1.get(3).setScore(5, newGrade);
                    break;
                case 60:
                    roster1.get(4).setScore(0, newGrade);
                    break;
                case 61:
                    roster1.get(4).setScore(1, newGrade);
                    break;
                case 62:
                    roster1.get(4).setScore(2, newGrade);
                    break;
                case 63:
                    roster1.get(4).setScore(3, newGrade);
                    break;
                case 64:
                    roster1.get(4).setScore(4, newGrade);
                    break;
                case 65:
                    roster1.get(4).setScore(5, newGrade);
                    break;
                case 66:
                    roster1.get(5).setScore(0, newGrade);
                    break;
                case 67:
                    roster1.get(5).setScore(1, newGrade);
                    break;
                case 68:
                    roster1.get(5).setScore(2, newGrade);
                    break;
                case 69:
                    roster1.get(5).setScore(3, newGrade);
                    break;
                case 70:
                    roster1.get(5).setScore(4, newGrade);
                    break;
                case 71:
                    roster1.get(5).setScore(5, newGrade);
                    break;
            }
            try{
                writer.saveCourse(courseWhatIf1, "course1");
            } catch (IOException e){
                System.out.println("error saving");
            }
            
            for (int i = 0; i < assignments1.size(); i++)
            {
                gradesTable1.setValueAt(assignments1.get(i).getName(), i, 0);
                gradesTable1.setValueAt(roster1.get(0).getScores().get(i), i, 1);
                gradesTable1.setValueAt(roster1.get(1).getScores().get(i), i, 2);
                gradesTable1.setValueAt(roster1.get(2).getScores().get(i), i, 3);
            }
        }*/
        if(action.getSource() == backButton)
        {
            /*assignment1Radio.setVisible(false);
            assignment2Radio.setVisible(false);
            assignment3Radio.setVisible(false);
            assignment4Radio.setVisible(false);
            assignment5Radio.setVisible(false);
            assignment6Radio.setVisible(false);
            student1Radio.setVisible(false);
            student2Radio.setVisible(false);
            student3Radio.setVisible(false);
            student4Radio.setVisible(false);
            student5Radio.setVisible(false);
            student6Radio.setVisible(false);*/
            //gradeLabel.setVisible(false);
            gradesPane1.setVisible(false);
            //gradeChangeField.setVisible(false);
            backButton.setVisible(false);
            
            assignmentButton1.setVisible(true);
            rosterButton1.setVisible(true);
            whatIfButton1.setVisible(true);
            classLabel.setVisible(true);
            classesButton.setVisible(true);
        }
        if (action.getSource() == deleteStudentButton){
        
            if(activeCourse == 0){
                deleteStu(rosterTable1, rosterTableModel1);
            }
            else if(activeCourse == 1){
                deleteStu(rosterTable1, rosterTableModel1);
            }
        
        }

    }
        

    private JLabel loginPageLabel;
    private JLabel loginLabel;
    private JLabel incorrectLoginLabel;
    private JLabel changeLabel;
    private JLabel helpLabel1;
    private JLabel titleLabel;
    private JLabel classLabel;
    private JLabel nameLabel;
    private JLabel scoreLabel;
    private JLabel assignmentLabel;
    private JLabel noCategoryLabel;
    private JLabel categoryLabel;
    private JLabel whatIfLabel;
    //private JLabel gradeLabel;
    private JLabel courseLabel;

    private JButton helpButton;
    private JButton changePwordButton;
    private JButton classAddButton;
    private JButton classDeleteButton;
    private JButton class1Button;
    private JButton class2Button;
    private JButton class3Button;
    private JButton class4Button;
    private JButton class5Button;
    private JButton class6Button;
    private JButton class7Button;
    private JButton assignmentAddButton1;
    //private JButton assignmentAddButton2;
    private JButton assignmentWhatIfAddButton1;
    private JButton assignmentButton1;
    //private JButton assignmentButton2;
    private JButton assignmentWhatIfButton1;
    private JButton rosterAddButton1;
    private JButton rosterDeleteButton1;
    //private JButton rosterAddButton2;
    private JButton rosterWhatIfAddButton1;
    private JButton gradebookAddButton1;
    //private JButton gradebookAddButton2;
    private JButton gradebookWhatIfAddButton1;
    private JButton rosterButton1;
    //private JButton rosterButton2;
    private JButton rosterWhatIfButton1;
    private JButton scoreButton1;
    //private JButton scoreButton2;
    private JButton gradeButton1;
    private JButton scoreWhatIfButton1;
    private JButton scoreChangesButton1;
    //private JButton scoreChangesButton2;
    private JButton scoreWhatIfChangesButton1;
    private JButton classesButton;
    private JButton assignmentChangesButton1;
    //private JButton assignmentChangesButton2;
    private JButton assignmentWhatIfChangesButton1;
    private JButton rosterChangesButton1;
    //private JButton rosterChangesButton2;
    private JButton rosterWhatIfChangesButton1;
    private JButton gradebookChangesButton1;
    //private JButton gradebookChangesButton2;
    private JButton gradebookWhatIfChangesButton1;
    private JButton whatIfButton1;
    //private JButton whatIfButton2;
    private JButton saveWhatIfButton1;
    private JButton deleteWhatIfButton1;
    private JButton backButton;
    private JButton deleteStudentButton;
    private JButton gradebookDeleteButton1;
    private JButton assignmentDeleteButton1;
    private JButton overallGradeButton;
    
    /*private JRadioButton assignment1Radio;
    private JRadioButton assignment2Radio;
    private JRadioButton assignment3Radio;
    private JRadioButton assignment4Radio;
    private JRadioButton assignment5Radio;
    private JRadioButton assignment6Radio;
    private JRadioButton student1Radio;
    private JRadioButton student2Radio;
    private JRadioButton student3Radio;
    private JRadioButton student4Radio;
    private JRadioButton student5Radio;
    private JRadioButton student6Radio;*/
    
    private JPasswordField passwordField;
    private JPasswordField changeField;

    private JTextField firstNameEntry;
    private JTextField lastNameEntry;
    private JTextField aScoreEntry;
    private JTextField bScoreEntry;
    private JTextField cScoreEntry;
    private JTextField dScoreEntry;
    private JTextField assignmentName;
    private JTextField assignmentCategory;
    private JTextField assignmentScore;
    private JTextField newCategory;
    private JTextField categoryWeight;
    //private JTextField gradeChangeField;
    private JTextField courseAddField;
    private JTextField courseDeleteField;
    
    private JTable assignmentsTable1;
    //private JTable assignmentsTable2;
    private JTable assignmentsWhatIfTable1;
    private JTable rosterTable1;
    //private JTable rosterTable2;
    private JTable rosterWhatIfTable1;
    private JTable gradebookTable1;
    //private JTable gradebookTable2;
    private JTable gradebookWhatIfTable1;
    private JTable gradesTable1;
    
    private JScrollPane assignmentsPane1;
    //private JScrollPane assignmentsPane2;
    private JScrollPane assignmentsWhatIfPane1;
    private JScrollPane rosterPane1;
    //private JScrollPane rosterPane2;
    private JScrollPane rosterWhatIfPane1;
    private JScrollPane gradebookPane1;
    //private JScrollPane gradebookPane2;
    private JScrollPane gradebookWhatIfPane1;
    private JScrollPane gradesPane1;
    
    private DefaultTableModel assignmentsTableModel1;
    //private DefaultTableModel assignmentsTableModel2;
    private DefaultTableModel assignmentsWhatIfTableModel1;
    private DefaultTableModel rosterTableModel1;
    //private DefaultTableModel rosterTableModel2;
    private DefaultTableModel rosterWhatIfTableModel1;
    private DefaultTableModel gradebookTableModel1;
    //private DefaultTableModel gradebookTableModel2;
    private DefaultTableModel gradebookWhatIfTableModel1;
    private DefaultTableModel gradesTableModel1;

    private Dimension size;
    private int screenNumber;
    private int activeCourse = 0;
    
    private void stuNameUpdate(JTable roster, int classNum){
    
        for (int row = 0; row < 2; row ++){
        
            
            if(!roster.getValueAt(row, 0).toString().equals(courseCList.get(classNum).getRoster().get(row).getFirstName()) && !"".equals(roster.getValueAt(row,0).toString())){
                courseCList.get(classNum).getRoster().get(row).setFirstName(roster.getValueAt(row, 0).toString());
                try{
                    writer.saveCourse(courseCList.get(classNum), courses.get(classNum));
                } catch (IOException e){
                    System.out.println("error saving");
                }
            }
                if(!roster.getValueAt(row, 1).toString().equals(courseCList.get(classNum).getRoster().get(row).getLastName()) && !"".equals(roster.getValueAt(row,1).toString())){
                courseCList.get(classNum).getRoster().get(row).setLastName(roster.getValueAt(row, 1).toString());
                try{
                    writer.saveCourse(courseCList.get(classNum), courses.get(classNum));
                } catch (IOException e){
                    System.out.println("error saving");
                }
            }
            
        } 
    }
    
    private void deleteStu(JTable roster , DefaultTableModel model /*int classNum, int stuNum*/){
    
        courseCList.get(activeCourse).getRoster().remove(roster.getSelectedRow());
        model.removeRow(roster.getSelectedRow());
        
        try{
            writer.saveCourse(courseCList.get(activeCourse), courses.get(activeCourse));
        } catch (IOException e){
            System.out.println("error saving");
        }      
    
    }
    
    private void assignmentNameUpdate(JTable assignment, int classNum){
    
        for (int row = 0; row < 2; row ++){
        
            
            if(!assignment.getValueAt(row, 0).toString().equals(courseCList.get(classNum).getGradebook().getAssignments().get(row).getName()) && !"".equals(assignment.getValueAt(row,0).toString())){
                courseCList.get(classNum).getGradebook().getAssignments().get(row).setName(assignment.getValueAt(row, 0).toString());
                try{
                    writer.saveCourse(courseCList.get(classNum), courses.get(classNum));
                } catch (IOException e){
                    System.out.println("error saving");
                }
            }
        } 
    }
    
    private void deleteAssignment(JTable assignment , DefaultTableModel model /*int classNum, int stuNum*/){
    
        courseCList.get(activeCourse).getGradebook().getAssignments().remove(assignment.getSelectedRow());
        model.removeRow(assignment.getSelectedRow());
        
        try{
            writer.saveCourse(courseCList.get(activeCourse), courses.get(activeCourse));
        } catch (IOException e){
            System.out.println("error saving");
        }      
    
    }
}
