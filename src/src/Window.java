/*
 * Window.java
 * CS 499 Team 10 Spring 2022
 * Creates the Window class for the Main Class to build GUI
 */
package src;

import java.awt.CardLayout;
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

/*
 * Class declaration for Window class extending JPanel for component placement and
 * implementing ActionListener to allow for actions on button click and text entry.
 */
public class Window extends JPanel implements ActionListener, MouseListener, FocusListener{

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
        welcomeLabel = new JLabel("Welcome, Instructor");
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 24));
        size =  welcomeLabel.getPreferredSize();
        welcomeLabel.setBounds(50, 50, size.width, size.height);
        add(welcomeLabel);
        welcomeLabel.setVisible(false);
        
        overviewPaneTabs = new DefaultListModel<String>();
        overviewPaneTabs.addElement("CS499");
        //overviewPaneTabs.addElement("Grades");
        //overviewPaneTabs.addElement("Roster");

        overviewPaneList = new JList<>(overviewPaneTabs);
        overviewPaneList.addMouseListener(this);
        overviewPaneList.addFocusListener(this);
        overviewPaneList.setPreferredSize(new Dimension(100,100));
        overviewPaneList.setFont(new Font("Serif", Font.BOLD, 20));
        size = overviewPaneList.getPreferredSize();
        overviewPaneList.setBounds(50,100,size.width,size.height);
        add(overviewPaneList);
        overviewPaneList.setVisible(false);
        
        classPaneTabs = new DefaultListModel<String>();
        classPaneTabs.addElement("Assignments");
        classPaneTabs.addElement("Roster");

        classPaneList = new JList<>(classPaneTabs);
        classPaneList.addMouseListener(this);
        classPaneList.addFocusListener(this);
        classPaneList.setPreferredSize(new Dimension(150,100));
        classPaneList.setFont(new Font("Serif", Font.BOLD, 20));
        size = classPaneList.getPreferredSize();
        classPaneList.setBounds(170,100,size.width,size.height);
        add(classPaneList);
        classPaneList.setVisible(false);
        
        
        /*
         * Assignments Page
         */
        assignmentsTableModel = new DefaultTableModel();
        assignmentsTableModel.addColumn("Name");
        assignmentsTableModel.addColumn("Category");
        assignmentsTableModel.addColumn("Maximum Score");

        assignmentsTable = new JTable(assignmentsTableModel);
        assignmentsTable.setBackground(Color.lightGray);
        JTableHeader gradeHeader = assignmentsTable.getTableHeader();
        gradeHeader.setBackground(Color.white);
        gradeHeader.setFont(new Font("Serif", Font.BOLD, 14));
        assignmentsPane = new JScrollPane(assignmentsTable);
        assignmentsPane.setBounds(350, 100, 480, 250);
        add(assignmentsPane);
        assignmentsPane.setVisible(false);
        
        
        /*
         * Roster Page
         */
        rosterTableModel = new DefaultTableModel();
        rosterTableModel.addColumn("First Name");
        rosterTableModel.addColumn("Last Name");
        rosterTableModel.addColumn("Average");

        rosterTable = new JTable(rosterTableModel);
        rosterTable.setBackground(Color.lightGray);
        JTableHeader studentHeader = rosterTable.getTableHeader();
        studentHeader.setBackground(Color.white);
        studentHeader.setFont(new Font("Serif", Font.BOLD, 14));
        rosterPane = new JScrollPane(rosterTable);
        rosterPane.setBounds(350, 100, 480, 250);
        add(rosterPane);
        rosterPane.setVisible(false);
        
        
        /*
         * Multiple Pages
         */
        helpButton = new JButton("?");
        size = helpButton.getPreferredSize();
        helpButton.addActionListener(this);
        helpButton.setBounds(850,450,size.width-35,size.height);
        add(helpButton);

        addButton = new JButton("+");
        size = addButton.getPreferredSize();
        addButton.addActionListener(this);
        addButton.setBounds(850,100,size.width-35,size.height);
        add(addButton);
        addButton.setVisible(false);
        
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
                
                welcomeLabel.setVisible(true);
                overviewPaneList.setVisible(true);
                //classesList.setVisible(true);
                //addButton.setVisible(true);
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
        if (action.getSource() == addButton)
        {
            inputWindow.setVisible(true);
        }
    }
    
    public void mouseEntered(MouseEvent mouse){
        
    }
    
    public void mouseReleased(MouseEvent mouse){
        
    }
    
    public void mousePressed(MouseEvent mouse){
        if (mouse.getClickCount() == 1) {
           String selectedOverviewItem = overviewPaneList.getSelectedValue();
           if (selectedOverviewItem != null){
               if (selectedOverviewItem.equals(overviewPaneTabs.get(0))){
                   //classesList.setVisible(true);
                   classPaneList.setVisible(true);
                   screenNumber = 0;
               }
           }
           String selectedClassItem = classPaneList.getSelectedValue();
           
           if (selectedClassItem != null){
               if (selectedClassItem.equals(classPaneTabs.get(0))){
                   //classesList.setVisible(false);
                   assignmentsPane.setVisible(true);
                   addButton.setVisible(true);
                   rosterPane.setVisible(false);
                   screenNumber = 1;
               }
               if (selectedClassItem.equals(classPaneTabs.get(1))){
                   //classesList.setVisible(false);
                   assignmentsPane.setVisible(false);
                   rosterPane.setVisible(true);
                   addButton.setVisible(true);
                   screenNumber = 2;
               }
            }
        }
    }
    
    public void mouseClicked(MouseEvent mouse) {
        if (mouse.getClickCount() == 1) {
           String selectedOverviewItem = overviewPaneList.getSelectedValue();
           
           if (selectedOverviewItem != null){
               if (selectedOverviewItem.equals(overviewPaneTabs.get(0))){
                   //classesList.setVisible(true);
                   assignmentsPane.setVisible(false);
                   rosterPane.setVisible(false);
                   screenNumber = 0;
               }
           }
           String selectedClassItem = classPaneList.getSelectedValue();
           
           if (selectedClassItem != null){
               if (selectedClassItem.equals(classPaneTabs.get(0))){
                   //classesList.setVisible(false);
                   assignmentsPane.setVisible(true);
                   rosterPane.setVisible(false);
                   screenNumber = 1;
               }
               if (selectedClassItem.equals(classPaneTabs.get(1))){
                   //classesList.setVisible(false);
                   assignmentsPane.setVisible(false);
                   rosterPane.setVisible(true);
                   screenNumber = 2;
               }
            }
        }
    }
    
    public void mouseExited(MouseEvent mouse){
        
    }
    
    public void focusLost(FocusEvent focus){
        if (!overviewPaneList.getSelectionModel().isSelectionEmpty())
            overviewPaneList.getSelectionModel().clearSelection();
        if (!classPaneList.getSelectionModel().isSelectionEmpty())
            classPaneList.getSelectionModel().clearSelection();
    }
    
    public void focusGained(FocusEvent focus){
        
    }

    private JLabel loginPageLabel;
    private JLabel loginLabel;
    private JLabel incorrectLoginLabel;
    private JLabel helpLabel1;
    private JLabel welcomeLabel;

    private JButton helpButton;
    private JButton addButton;
    
    private JPasswordField pinField;

    
    private JList<String> overviewPaneList;
    private JList<String> classPaneList;
    //private JList<String> classesList;
    
    private JTable assignmentsTable;
    private JTable rosterTable;
    
    private JScrollPane assignmentsPane;
    private JScrollPane rosterPane;
    
    private JPanel inputWindow;
    
    private DefaultTableModel assignmentsTableModel;
    private DefaultTableModel rosterTableModel;
    private DefaultListModel<String> overviewPaneTabs;
    private DefaultListModel<String> classPaneTabs;

    private Dimension size;
    private int screenNumber;
}
