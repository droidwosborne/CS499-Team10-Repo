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
import java.util.Vector;


/*
 * Class declaration for Window class extending JPanel for component placement and
 * implementing ActionListener to allow for actions on button click and text entry.
 */
public class Window extends JPanel implements ActionListener{

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
        
        assignmentsTableModel2 = new DefaultTableModel();
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
        assignmentsPane2.setVisible(false);
        
        
        assignmentAddButton1 = new JButton("+");
        assignmentAddButton1.setFont(new Font("Serif", Font.BOLD, 20));
        size = assignmentAddButton1.getPreferredSize();
        assignmentAddButton1.addActionListener(this);
        assignmentAddButton1.setBounds(370,50,size.width-35,size.height);
        add(assignmentAddButton1);
        assignmentAddButton1.setVisible(false);
        
        assignmentAddButton2 = new JButton("+");
        assignmentAddButton2.setFont(new Font("Serif", Font.BOLD, 20));
        size = assignmentAddButton2.getPreferredSize();
        assignmentAddButton2.addActionListener(this);
        assignmentAddButton2.setBounds(370,50,size.width-35,size.height);
        add(assignmentAddButton2);
        assignmentAddButton2.setVisible(false);
        
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
        
        assignmentChangesButton2 = new JButton("Submit");
        size = assignmentChangesButton2.getPreferredSize();
        assignmentChangesButton2.addActionListener(this);
        assignmentChangesButton2.setBounds(420,400,size.width,size.height+20);
        add(assignmentChangesButton2);
        assignmentChangesButton2.setVisible(false);
        
        scoreButton1 = new JButton("Edit Scores");
        size = scoreButton1.getPreferredSize();
        scoreButton1.addActionListener(this);
        scoreButton1.setBounds(30,300,size.width,size.height+20);
        add(scoreButton1);
        scoreButton1.setVisible(false);
        
        scoreButton2 = new JButton("Edit Scores");
        size = scoreButton2.getPreferredSize();
        scoreButton2.addActionListener(this);
        scoreButton2.setBounds(30,300,size.width,size.height+20);
        add(scoreButton2);
        scoreButton2.setVisible(false);
        
        scoreChangesButton1 = new JButton("Submit");
        size = scoreChangesButton1.getPreferredSize();
        scoreChangesButton1.addActionListener(this);
        scoreChangesButton1.setBounds(280,400,size.width,size.height+20);
        add(scoreChangesButton1);
        scoreChangesButton1.setVisible(false);
        
        scoreChangesButton2 = new JButton("Submit");
        size = scoreChangesButton2.getPreferredSize();
        scoreChangesButton2.addActionListener(this);
        scoreChangesButton2.setBounds(280,400,size.width,size.height+20);
        add(scoreChangesButton2);
        scoreChangesButton2.setVisible(false);
        
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
        rosterAddButton1.setBounds(300,50,size.width-35,size.height);
        add(rosterAddButton1);
        rosterAddButton1.setVisible(false);
        
        rosterAddButton2 = new JButton("+");
        rosterAddButton2.setFont(new Font("Serif", Font.BOLD, 20));
        size = rosterAddButton2.getPreferredSize();
        rosterAddButton2.addActionListener(this);
        rosterAddButton2.setBounds(300,50,size.width-35,size.height);
        add(rosterAddButton2);
        rosterAddButton2.setVisible(false);
        
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
        
        gradebookTableModel2 = new DefaultTableModel();
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
        gradebookPane2.setVisible(false);
        
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
        
        gradebookAddButton2 = new JButton("+");
        gradebookAddButton2.setFont(new Font("Serif", Font.BOLD, 20));
        size = gradebookAddButton2.getPreferredSize();
        gradebookAddButton2.addActionListener(this);
        gradebookAddButton2.setBounds(770,50,size.width-35,size.height);
        add(gradebookAddButton2);
        gradebookAddButton2.setVisible(false);
        
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
        
        gradebookChangesButton2 = new JButton("Submit");
        size = gradebookChangesButton2.getPreferredSize();
        gradebookChangesButton2.addActionListener(this);
        gradebookChangesButton2.setBounds(270,400,size.width,size.height+20);
        add(gradebookChangesButton2);
        gradebookChangesButton2.setVisible(false);
        
        
        
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
            
        }
        if (action.getSource() == class1Button)
        {
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
            titleLabel.setVisible(false);
            classesButton.setVisible(true);
            
            classLabel.setText(class1Button.getText());
            size =  classLabel.getPreferredSize();
            classLabel.setBounds(50, 50, size.width, size.height);
            classLabel.setVisible(true);
            
            for (int i = 0; i < assignments1.size(); i++)
            {
                assignmentsTable1.setValueAt(assignments1.get(i).getName(),i,0);
                assignmentsTable1.setValueAt(gradebook1.getCatNames().get(i),i,1);
                assignmentsTable1.setValueAt(assignments1.get(i).getMaxScore(),i,2);
                
                rosterTable1.setValueAt(roster1.get(i).getFirstName(),i,0);
                rosterTable1.setValueAt(roster1.get(i).getLastName(),i,1);
                rosterTable1.setValueAt(roster1.get(i).getID(),i,2);
                rosterTable1.setValueAt(roster1.get(i).getOverallGrade(),i,3);
                
                gradebookTable1.setValueAt(gradebook1.getCatNames().get(i),i,0);
                gradebookTable1.setValueAt(gradebook1.getCatID().get(i),i,1);
                gradebookTable1.setValueAt(gradebook1.getCatWeights().get(i),i,2);
            }
        }
        if (action.getSource() == class2Button)
        {
            assignmentButton2.setVisible(true);
            rosterButton2.setVisible(true);
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
                assignmentsTable2.setValueAt(gradebook2.getCatNames().get(i),i,1);
                assignmentsTable2.setValueAt(assignments2.get(i).getMaxScore(),i,2);
                
                rosterTable2.setValueAt(roster2.get(i).getFirstName(),i,0);
                rosterTable2.setValueAt(roster2.get(i).getLastName(),i,1);
                rosterTable2.setValueAt(roster2.get(i).getID(),i,2);
                rosterTable2.setValueAt(roster2.get(i).getOverallGrade(),i,3);
                
                gradebookTable2.setValueAt(gradebook2.getCatNames().get(i),i,0);
                gradebookTable2.setValueAt(gradebook2.getCatID().get(i),i,1);
                gradebookTable2.setValueAt(gradebook2.getCatWeights().get(i),i,2);
            }
        }
        if (action.getSource() == class3Button)
        {
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
            gradebookPane1.setVisible(true);
            assignmentButton1.setVisible(false);
            rosterButton1.setVisible(true);
            rosterPane1.setVisible(false);
            assignmentAddButton1.setVisible(true);
            rosterAddButton1.setVisible(false);
            
            titleLabel.setText("Assignments                       Gradebook Setup");
            size =  titleLabel.getPreferredSize();
            titleLabel.setBounds(200, 50, size.width, size.height);
            titleLabel.setVisible(true);
            nameLabel.setVisible(false);
            firstNameEntry.setVisible(false);
            lastNameEntry.setVisible(false);
            rosterChangesButton1.setVisible(false);
            rosterChangesButton2.setVisible(false);
            scoreButton1.setVisible(true);
            gradebookAddButton1.setVisible(true);
            gradebookChangesButton1.setVisible(false);
            newCategory.setVisible(false);
            categoryWeight.setVisible(false);
            categoryLabel.setVisible(false);
        }
        if (action.getSource() == assignmentButton2)
        {
            assignmentsPane2.setVisible(true);
            gradebookPane2.setVisible(true);
            assignmentButton2.setVisible(false);
            rosterButton2.setVisible(true);
            rosterPane2.setVisible(false);
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
        }
        if (action.getSource() == rosterButton1)
        {
            assignmentsPane1.setVisible(false);
            gradebookPane1.setVisible(false);
            assignmentButton1.setVisible(true);
            rosterButton1.setVisible(false);
            rosterPane1.setVisible(true);
            assignmentAddButton1.setVisible(false);
            rosterAddButton1.setVisible(true);
            scoreButton1.setVisible(false);
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
            gradebookChangesButton1.setVisible(false);
            newCategory.setVisible(false);
            categoryWeight.setVisible(false);
            categoryLabel.setVisible(false);
            
            titleLabel.setText("Roster");
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
            rosterPane2.setVisible(true);
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
            
            titleLabel.setText("Roster");
            size =  titleLabel.getPreferredSize();
            titleLabel.setBounds(200, 50, size.width, size.height);
            titleLabel.setVisible(true);
        }
        if (action.getSource() == classesButton)
        {
            assignmentsPane1.setVisible(false);
            assignmentsPane2.setVisible(false);
            assignmentButton1.setVisible(false);
            rosterButton1.setVisible(false);
            rosterPane1.setVisible(false);
            rosterPane2.setVisible(false);
            gradebookPane1.setVisible(false);
            gradebookPane2.setVisible(false);
            assignmentAddButton1.setVisible(false);
            rosterAddButton1.setVisible(false);
            assignmentButton2.setVisible(false);
            rosterButton2.setVisible(false);
            assignmentAddButton2.setVisible(false);
            rosterAddButton2.setVisible(false);
            titleLabel.setText("Welcome, Professor " + teacher.get(0));
            size =  titleLabel.getPreferredSize();
            titleLabel.setBounds(50, 50, size.width, size.height);
            classesButton.setVisible(false);
            titleLabel.setVisible(true);
            classAddButton.setVisible(true);
            classLabel.setVisible(false);
            nameLabel.setVisible(false);
            firstNameEntry.setVisible(false);
            lastNameEntry.setVisible(false);
            rosterChangesButton1.setVisible(false);
            rosterChangesButton2.setVisible(false);
            aScoreEntry.setVisible(false);
            bScoreEntry.setVisible(false);
            cScoreEntry.setVisible(false);
            dScoreEntry.setVisible(false);
            scoreButton1.setVisible(false);
            scoreButton2.setVisible(false);
            scoreChangesButton1.setVisible(false);
            scoreChangesButton2.setVisible(false);
            assignmentChangesButton1.setVisible(false);
            assignmentChangesButton2.setVisible(false);
            assignmentLabel.setVisible(false);
            scoreLabel.setVisible(false);
            assignmentName.setVisible(false);
            assignmentCategory.setVisible(false);
            assignmentScore.setVisible(false);
            noCategoryLabel.setVisible(false);
            gradebookAddButton1.setVisible(false);
            gradebookAddButton2.setVisible(false);
            gradebookChangesButton1.setVisible(false);
            gradebookChangesButton2.setVisible(false);
            newCategory.setVisible(false);
            categoryWeight.setVisible(false);
            categoryLabel.setVisible(false);
            
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
        if (action.getSource() == rosterAddButton2)
        {
            nameLabel.setVisible(!nameLabel.isVisible());
            firstNameEntry.setVisible(!firstNameEntry.isVisible());
            lastNameEntry.setVisible(!lastNameEntry.isVisible());
            firstNameEntry.setText("");
            lastNameEntry.setText("");
            rosterChangesButton2.setVisible(!rosterChangesButton2.isVisible());
        }
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
        if (action.getSource() == assignmentAddButton2)
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
        if(action.getSource() ==  assignmentChangesButton1)
        {
            noCategoryLabel.setVisible(false);
            String name = assignmentName.getText();
            String cat = assignmentCategory.getText();
            int catID = -1;
            Vector<String> catNames = gradebook1.getCatNames();
            boolean catExists = false;
            for (int i = 0; i < catNames.size(); i++)
            {
                if (cat.equals(catNames.get(i)))
                {
                    catExists = true;
                    catID = gradebook1.getCatID().get(i);
                }
            }
            if(catExists == true)
            {    
                assignmentsTableModel1.addRow(new Object[]{"","",""});
                int score = Integer.parseInt(assignmentScore.getText());
                course1.addAssignment(name, catID, score);
                assignmentsTable1.setValueAt(name,assignmentsTable1.getRowCount()-1,0);
                assignmentsTable1.setValueAt(cat,assignmentsTable1.getRowCount()-1,1);
                assignmentsTable1.setValueAt(score,assignmentsTable1.getRowCount()-1,2);

                try{
                    writer.saveCourse(course1, "course1");
                } catch (IOException e){
                    System.out.println("error saving");
                }
            }
            else
            {
                noCategoryLabel.setVisible(true);
            }
        }
        if(action.getSource() ==  assignmentChangesButton2)
        {
            noCategoryLabel.setVisible(false);
            String name = assignmentName.getText();
            String cat = assignmentCategory.getText();
            int catID = -1;
            Vector<String> catNames = gradebook2.getCatNames();
            boolean catExists = false;
            for (int i = 0; i < catNames.size(); i++)
            {
                if (cat.equals(catNames.get(i)))
                {
                    catExists = true;
                    catID = gradebook2.getCatID().get(i);
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
        }
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
            gradebook1.setScale(inputScale);
            try{
                writer.saveCourse(course1, "course1");
            } catch (IOException e){
                System.out.println("error saving");
            }
        }
        if(action.getSource() == scoreButton2)
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
            gradebook2.setScale(inputScale);
            try{
                writer.saveCourse(course2, "course2");
            } catch (IOException e){
                System.out.println("error saving");
            }
        }
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
        if(action.getSource() == gradebookAddButton2)
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
        }
        if(action.getSource() == gradebookChangesButton1)
        {
            gradebookTableModel1.addRow(new Object[]{"","","",""});
            String name = newCategory.getText();
            float weight = Float.parseFloat(categoryWeight.getText());
            course1.addCategory(name, weight);
            gradebookTableModel1.setValueAt(name,gradebookTableModel1.getRowCount()-1,0);
            
            gradebookTableModel1.setValueAt(gradebook1.getCatID().get(gradebookTableModel1.getRowCount()-1),gradebookTableModel1.getRowCount()-1,1);
            gradebookTableModel1.setValueAt(weight,gradebookTableModel1.getRowCount()-1,2);
            try{
                writer.saveCourse(course1, "course1");
            } catch (IOException e){
                System.out.println("error saving");
            }
        }
        if(action.getSource() == gradebookChangesButton2)
        {
            gradebookTableModel2.addRow(new Object[]{"","","",""});
            String name = newCategory.getText();
            float weight = Float.parseFloat(categoryWeight.getText());
            course2.addCategory(name, weight);
            gradebookTableModel2.setValueAt(name,gradebookTableModel2.getRowCount()-1,0);
            
            gradebookTableModel2.setValueAt(gradebook2.getCatID().get(gradebookTableModel2.getRowCount()-1),gradebookTableModel2.getRowCount()-1,1);
            gradebookTableModel2.setValueAt(weight,gradebookTableModel2.getRowCount()-1,2);
            try{
                writer.saveCourse(course2, "course2");
            } catch (IOException e){
                System.out.println("error saving");
            }
        }
    }
        

    private JLabel loginPageLabel;
    private JLabel loginLabel;
    private JLabel incorrectLoginLabel;
    private JLabel helpLabel1;
    private JLabel titleLabel;
    private JLabel classLabel;
    private JLabel nameLabel;
    private JLabel scoreLabel;
    private JLabel assignmentLabel;
    private JLabel noCategoryLabel;
    private JLabel categoryLabel;

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
    private JButton gradebookAddButton1;
    private JButton gradebookAddButton2;
    private JButton rosterButton1;
    private JButton rosterButton2;
    private JButton scoreButton1;
    private JButton scoreButton2;
    private JButton scoreChangesButton1;
    private JButton scoreChangesButton2;
    private JButton classesButton;
    private JButton assignmentChangesButton1;
    private JButton assignmentChangesButton2;
    private JButton rosterChangesButton1;
    private JButton rosterChangesButton2;
    private JButton gradebookChangesButton1;
    private JButton gradebookChangesButton2;
    
    private JPasswordField pinField;

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
