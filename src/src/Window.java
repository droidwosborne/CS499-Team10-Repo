/*
 * Window.java
 * CS 499 Team 10 Spring 2022
 * Creates the Window class for the Main Class to build GUI
 */
package src;

import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/*
 * Class declaration for Window class extending JPanel for component placement and
 * implementing ActionListener to allow for actions on button click and text entry.
 */
public class Window extends JPanel implements ActionListener, MouseListener{

    //User pin for identification (currently set to work automatically for easy testing)
    public String pin = "KLOm9bAS";
    //public char[] pinA = new char[pin.length()];
    Font labelFont = new Font("Serif", Font.PLAIN, 16);
   
    //Constructor for Window class consisting of components in Panel
    public Window () {
        //Arbitrary size and color appearance of the window
        Dimension dim = new Dimension(900, 500);
        setMinimumSize(dim);
        setPreferredSize(dim);
        setLayout(null);
        setBackground(Color.lightGray);
        screenNumber = -1;
        
        
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
        helpLabel1.setBounds(700, 100, size.width, size.height);
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
        welcomeLabel = new JLabel("Welcome, Instructor");
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 24));
        size =  welcomeLabel.getPreferredSize();
        welcomeLabel.setBounds(50, 50, size.width, size.height);
        add(welcomeLabel);
        welcomeLabel.setVisible(false);
        
        DefaultListModel<String> paneTabs = new DefaultListModel<String>();
        paneTabs.addElement("Classes");
        paneTabs.addElement("Grades");
        paneTabs.addElement("Students");

        paneList = new JList<>(paneTabs);
        paneList.addMouseListener(this);
        paneList.setPreferredSize(new Dimension(100,100));
        paneList.setFont(new Font("Serif", Font.BOLD, 20));
        size = paneList.getPreferredSize();
        paneList.setBounds(50,100,size.width,size.height);
        add(paneList);
        paneList.setVisible(false);
        
        
        /*
         * Classes Page
         */
        DefaultListModel<String> classesTabs = new DefaultListModel<String>();
        classesTabs.addElement("CS 103 - Intro to C Programming");
        classesTabs.addElement("CS 252 - Advanced C Programming");
        classesTabs.addElement("CS 307 - Intro to Linux Development");
        classesTabs.addElement("CS 430 - UNIX Kernel Development");
        classesTabs.addElement("CS 486 - Senior Research Project");

        classesList = new JList<>(classesTabs);
        classesList.addMouseListener(this);
        classesList.setPreferredSize(new Dimension(480,150));
        classesList.setFont(new Font("Serif", Font.BOLD, 20));
        size = classesList.getPreferredSize();
        classesList.setBounds(200,100,size.width,size.height);
        add(classesList);
        classesList.setVisible(false);
        
        
        /*
         * Grade Page
         */
        gradesTableModel = new DefaultTableModel();
        gradesTableModel.addColumn("Name");
        gradesTableModel.addColumn("Category");
        gradesTableModel.addColumn("Maximum Score");

        gradesTable = new JTable(gradesTableModel);
        gradesTable.setBackground(Color.lightGray);
        JTableHeader gradeHeader = gradesTable.getTableHeader();
        gradeHeader.setBackground(Color.white);
        gradeHeader.setFont(new Font("Serif", Font.BOLD, 14));
        gradesPane = new JScrollPane(gradesTable);
        gradesPane.setBounds(200, 100, 480, 250);
        add(gradesPane);
        gradesPane.setVisible(false);
        
        
        /*
         * Students Page
         */
        studentsTableModel = new DefaultTableModel();
        studentsTableModel.addColumn("First Name");
        studentsTableModel.addColumn("Last Name");
        studentsTableModel.addColumn("Average");

        studentsTable = new JTable(studentsTableModel);
        studentsTable.setBackground(Color.lightGray);
        JTableHeader studentHeader = studentsTable.getTableHeader();
        studentHeader.setBackground(Color.white);
        studentHeader.setFont(new Font("Serif", Font.BOLD, 14));
        studentsPane = new JScrollPane(studentsTable);
        studentsPane.setBounds(200, 100, 480, 250);
        add(studentsPane);
        studentsPane.setVisible(false);
        
        
        /*
         * Multiple Pages
         */
        helpButton = new JButton("?");
        size = helpButton.getPreferredSize();
        helpButton.addActionListener(this);
        helpButton.setBounds(850,450,size.width-35,size.height);
        add(helpButton);

        
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
                
                welcomeLabel.setVisible(true);
                paneList.setVisible(true);
                classesList.setVisible(true);
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
    }
    
    public void mouseEntered(MouseEvent mouse){
        
    }
    
    public void mouseReleased(MouseEvent mouse){
        
    }
    
    public void mousePressed(MouseEvent mouse){
        if (mouse.getClickCount() == 1) {
           String selectedItem = paneList.getSelectedValue();
           
           if (selectedItem.equals("Classes")){
               classesList.setVisible(true);
               gradesPane.setVisible(false);
               studentsPane.setVisible(false);
               screenNumber = 0;
           }
           if (selectedItem.equals("Grades")){
               classesList.setVisible(false);
               gradesPane.setVisible(true);
               studentsPane.setVisible(false);
               screenNumber = 1;
           }
           if (selectedItem.equals("Students")){
               classesList.setVisible(false);
               gradesPane.setVisible(false);
               studentsPane.setVisible(true);
               screenNumber = 2;
           }
        }
    }
    
    public void mouseClicked(MouseEvent mouse) {
        if (mouse.getClickCount() == 1) {
           String selectedItem = paneList.getSelectedValue();
           
           if (selectedItem.equals("Classes")){
               classesList.setVisible(true);
               gradesPane.setVisible(false);
               studentsPane.setVisible(false);
               screenNumber = 0;
           }
           if (selectedItem.equals("Grades")){
               classesList.setVisible(false);
               gradesPane.setVisible(true);
               studentsPane.setVisible(false);
               screenNumber = 1;
           }
           if (selectedItem.equals("Students")){
               classesList.setVisible(false);
               gradesPane.setVisible(false);
               studentsPane.setVisible(true);
               screenNumber = 2;
           }
        }
    }
    
    
    
    public void mouseExited(MouseEvent mouse){
        
    }

    private JLabel loginPageLabel;
    private JLabel loginLabel;
    private JLabel incorrectLoginLabel;
    private JLabel helpLabel1;
    private JLabel welcomeLabel;

    private JButton helpButton;
    
    private JPasswordField pinField;

    
    private JList<String> paneList;
    private JList<String> classesList;
    
    private JTable gradesTable;
    private JTable studentsTable;
    
    private JScrollPane gradesPane;
    private JScrollPane studentsPane;
    
    private DefaultTableModel gradesTableModel;
    private DefaultTableModel studentsTableModel;

    private Dimension size;
    private int screenNumber;
}
