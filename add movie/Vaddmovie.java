package finalProjectAOOP;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

public class Vaddmovie extends JFrame {
	// Declaring components
    JLabel movLabel, startLabel, endLabel, cinemaNumLabel, priceLabel, frameTitle;
    JPanel mainPanel, labelPanel, inputPanel, btnPanel;
    JButton[] buttons;
    JSpinner startSpinner, endSpinner, cinemaNumSpinner; 
    JTextField nameInput, priceInput;
    JComboBox<String> selector;
    ArrayList<String> data = new ArrayList<String>();;
    ResultSet rs;
    
	public Vaddmovie(ResultSet rs) {
		this.rs = rs;
		//initialize panel for labels
		labelPanel = new JPanel(new GridBagLayout());
		//create the labels
		frameTitle = new JLabel("Add a Movie", JLabel.CENTER);
		movLabel = new JLabel("Movie Name");
		startLabel = new JLabel("Time Start");
		endLabel = new JLabel("Time End");
		cinemaNumLabel = new JLabel("Cinema Number");
		priceLabel = new JLabel("Price");
		 
		//place the labels to the main panel
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.WEST;
       
        c.insets = new Insets(10,12,10,12);
        c.gridx = 0;
        c.gridy = 0;
        labelPanel.add(movLabel, c);
        c.gridx = 0;
        c.gridy = 1;
        labelPanel.add(startLabel, c);
        c.gridx = 0;
        c.gridy = 2;
        labelPanel.add(endLabel, c);
        c.gridx = 0;
        c.gridy = 3;
        labelPanel.add(cinemaNumLabel, c);
        c.gridx = 0;
        c.gridy = 4;
        labelPanel.add(priceLabel, c);
		//---------------------Input panel and its components-----------------------------------------
        //create the panel for user inputs and initialize the components to be added
		inputPanel = new JPanel(new GridBagLayout());
		
		//component to get the price and movie title
		nameInput = new JTextField(10);
		priceInput = new JTextField(10);
		
		//create the spinner components to have time input
		//for start time input
		SpinnerModel start =  
                new SpinnerNumberModel(100, //initial value  
                   100, //minimum value  
                   2400, //maximum value  
                   100); //step/increment value 
		startSpinner = new JSpinner(start);
        
        //for end time input
		SpinnerModel end =  
                new SpinnerNumberModel(100, //initial value  
                   100, //minimum value  
                   2400, //maximum value  
                   100); //step/increment value 
        endSpinner = new JSpinner(end);
       
        ////for cinema number input
        SpinnerModel value =  
                new SpinnerNumberModel(1, //initial value  
                   1, //minimum value  
                   10, //maximum value  
                   1); //step/increment value 
       //spinner to get the cinema number
       cinemaNumSpinner = new JSpinner(value);
       //place the components inside the input panel
       c.gridx = 0;
       c.gridy = 0;
       inputPanel.add(nameInput, c);
       c.gridx = 0;
       c.gridy = 1;
       inputPanel.add(startSpinner, c);
       c.gridx = 0;
       c.gridy = 2;
       inputPanel.add(endSpinner, c);
       c.gridx = 0;
       c.gridy = 3;
       inputPanel.add(cinemaNumSpinner, c);
       c.gridx = 0;
       c.gridy = 4;
       inputPanel.add(priceInput, c);
       
       //------------Main panel-------------------
       mainPanel = new JPanel();
       mainPanel.setLayout((new BoxLayout(mainPanel, BoxLayout.X_AXIS)));
       mainPanel.add(labelPanel);
       mainPanel.add(inputPanel);
       
        
        //--------------Button panel and its buttons------------------------------------
        //create panel for buttons
		btnPanel = new JPanel(new GridLayout());
		//create buttons to add to the btnPanel
	        buttons = new JButton[2];
	        for (int i = 0; i < buttons.length; i++) {
	            String[] text = { "Add", "Cancel" };
	            buttons[i] = new JButton(text[i]);
	            buttons[i].setFocusable(false);
	            btnPanel.add(buttons[i]);
	   }
	    
	   this.setLayout(new BorderLayout());
	   this.add(frameTitle, BorderLayout.NORTH);
	   this.add(mainPanel, BorderLayout.CENTER);
	   this.add(btnPanel, BorderLayout.SOUTH);
	   this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   this.setLocationRelativeTo(null);
	   this.setTitle("Add Movie");
	   this.pack();
	   this.setVisible(true);
	}//end of constructor
	
	// Binding remove button listener
    public void setAddActionListener(ActionListener listener) {
        buttons[0].addActionListener(listener);
    }

    // Binding cancel button listener
    public void setCancelListener(ActionListener listener) {
        buttons[1].addActionListener(listener);
    }
    //getters for the value inside the text fields
  	public JTextField getMovieName() {
  		return nameInput;	
  	}
  	public JTextField getPriceName() {
  		return priceInput;	
  	}
  	//---------------Getters for spinners-----------------------------
  	public String getTimeStart() {
  		//try-catch to check if the user manually input data from the spinner
  		try {
  		    startSpinner.commitEdit();//accept the manually placed input
  		} catch ( java.text.ParseException e ) { //catch the error if a letter was placed in the spinner
  			JOptionPane.showMessageDialog(null, "Please enter a number only");
  			e.printStackTrace();
  		}

  		return startSpinner.getValue().toString();
  	}
  	public String getTimeEnd() {
  	//try-catch to check if the user manually input data from the spinner
  		try {
  		    endSpinner.commitEdit();//accept the manually placed input
  		} catch ( java.text.ParseException e ) { //catch the error if a letter was placed in the spinner
  			JOptionPane.showMessageDialog(null, "Please enter a number only");
  			e.printStackTrace();
  		}
  		return endSpinner.getValue().toString();
  	}
  	public int getCinemaNo() {
  	//try-catch to check if the user manually input data from the spinner
  		try {
  		    cinemaNumSpinner.commitEdit();//accept the manually placed input
  		   
  		} catch ( java.text.ParseException e ) { //catch the error if a letter was placed in the spinner
  			JOptionPane.showMessageDialog(null, "Please enter a number only");
  			e.printStackTrace();
  		}
  		return (Integer) cinemaNumSpinner.getValue();
  	}


}
