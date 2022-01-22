/*
 * Window.java
 * CS 499 Team 10 Spring 2022
 * Creates the Window class for the Main Class to build GUI
 */
package src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/*
 * Class declaration for Window class extending JPanel for component placement and
 * implementing ActionListener to allow for actions on button click and text entry.
 */
public class Window extends JPanel implements ActionListener{

    //User pin for identification (currently set to work automatically for easy testing)
    public String pin = "KLOm9bAS";
    public char[] pinA = new char[pin.length()];

    //Constructor for Window class consisting of components in Panel
    public Window () {
        //Arbitrary size and color appearance of the window
        Dimension dim = new Dimension(800, 500);
        setMinimumSize(dim);
        setPreferredSize(dim);
        setLayout(null);
        setBackground(Color.lightGray);
        for(int i = 0; i < pin.length(); i++){
            pinA[i] = pin.charAt(i);
        }
        loginPageLabel = new JLabel("Login into Gradebook");
        size = loginPageLabel.getPreferredSize();
        loginPageLabel.setBounds(300, 120, size.width, size.height);
        add(loginPageLabel);

        loginLabel = new JLabel("Pin:");
        size = loginLabel.getPreferredSize();
        loginLabel.setBounds(200, 200, size.width, size.height);
        add(loginLabel);

        incorrectLoginLabel = new JLabel("<html><font color = 'FF0000'>Incorrect Pin</font></html>");
        size = incorrectLoginLabel.getPreferredSize();
        incorrectLoginLabel.setBounds(200, 150, size.width, size.height);
        add(incorrectLoginLabel);
        incorrectLoginLabel.setVisible(false);

        /*userField = new JTextField(20);
        size = userField.getPreferredSize();
        userField.addActionListener(this);
        userField.setText("chargerblue@uah.edu");
        userField.setBounds(250,200,size.width,size.height);
        add(userField);
        userField.setVisible(false);*/

        pinField = new JPasswordField(20);
        size = pinField.getPreferredSize();
        pinField.addActionListener(this);
        pinField.setText(pin);
        pinField.setBounds(250,195,size.width,size.height);
        add(pinField);

        helpButton = new JButton("?");
        size = helpButton.getPreferredSize();
        helpButton.addActionListener(this);
        helpButton.setBounds(750,450,size.width-35,size.height);
        add(helpButton);

        /*classButton = new JButton("?");
        size = classButton.getPreferredSize();
        classButton.addActionListener(this);
        /*classButton.setBounds(750,450,size.width-35,size.height);
        add(classButton);

        gradeButton = new JButton("?");
        size = gradeButton.getPreferredSize();
        gradeButton.addActionListener(this);
        gradeButton.setBounds(750,450,size.width-35,size.height);
        add(gradeButton);

        studentButton = new JButton("?");
        size = studentButton.getPreferredSize();
        studentButton.addActionListener(this);
        studentButton.setBounds(750,450,size.width-35,size.height);
        add(studentButton);

        DefaultListModel<String> tabs = new DefaultListModel<String>();
        tabs.addElement("Classes");
        tabs.addElement("Grades");
        tabs.addElement("Students");

        paneList = new JList<>(tabs);
        //paneList.addAncestorListener(this);
        size = paneList.getPreferredSize();
        paneList.setBounds(0,100,size.width,size.height);
        //add(paneList);

        leftPane = new JScrollPane(classButton);*/
    }

    /*
    * Method for all actions that occur due to user action,
    * such as button click or text entry.
     */
    public void actionPerformed(ActionEvent action){
        if (action.getSource().equals(pinField))
        {
            if(pinField.getText().equals(pin)) {
                loginLabel.setVisible(false);
                loginPageLabel.setVisible(false);
                pinField.setVisible(false);
                incorrectLoginLabel.setVisible(false);
            } else {
                incorrectLoginLabel.setVisible(true);
            }
        }
    }

    private JLabel loginPageLabel;
    private JLabel loginLabel;
    private JLabel incorrectLoginLabel;

    private JButton helpButton;
    /*private JButton classButton;
    private JButton gradeButton;
    private JButton studentButton;

    private JTextField userField;*/
    private JPasswordField pinField;

    /*private JScrollPane leftPane;
    private JList<String> paneList;*/

    private Dimension size;
}
