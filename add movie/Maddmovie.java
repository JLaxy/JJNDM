package finalProjectAOOP;

import java.util.ArrayList;

import javax.swing.JOptionPane;


public class Maddmovie {
	// Declaring
    Database database;
    ArrayList<Ticket> unpaidTickets;

    Maddmovie(Database database, ArrayList<Ticket> unpaidTickets) {
        this.database = database;
        this.unpaidTickets = unpaidTickets;
    }
    
    // Add Movie to the Database
    public boolean add(String movName,String timeSlot,float price, int cinemaNo) {
//    	database.addMovie(movName, timeSlot, 200, price, cinemaNo);
    	System.out.println(database.cinemaExists(cinemaNo)+" before block");// just to check the value that cinemaExist returns. For debugging
    	//to check if the cinema number is taken
    	//placed a NOT(!) since if there is no data in the database, it will always result to false 
    	//it also adds multiple movies in a single cinema number if not placed inside a NOT operator
        if(!database.cinemaExists(cinemaNo)) {
        	database.addMovie(movName, timeSlot, 200, price, cinemaNo);
        	JOptionPane.showMessageDialog(null,"Movie successfully added");
        	new Cmainview(this.unpaidTickets, this.database);
        	System.out.println(database.cinemaExists(cinemaNo)+" if block");// just to check the value that cinemaExist returns. For debugging
        	return true;
        }else {
        	System.out.println(database.cinemaExists(cinemaNo)+ " else block");// just to check the value that cinemaExist returns. For debugging
        	JOptionPane.showMessageDialog(null,"Cinema number is already taken");
        	return false;
       }
        
    }
    //validate the input of the user in movie name and price
    public boolean checkInputs(String movieName, String price) {
    	//store the inputs in our variables
    	String movie = movieName;
    	String priceNum = price ;
    	//create boolean flags to check if all inputs are correct
    	boolean nameCheck = false;
    	boolean priceFlag = false;
    	//check if the name is in alphabets instead of numbers
    			if(!(movie.matches("[a-zA-Z ]+"))) {
    				JOptionPane.showMessageDialog(null, "Please input only letters in the name");
    				return false;
    			}else {
    				nameCheck = true;
    			}
    			//check if the value inside the price1 field is a number
    			try {
    				// if the parse int results in an error then it means that the value is not a number
    		            float checkAge = Float.parseFloat(priceNum);
    		            priceFlag = true;
    		        } catch (NumberFormatException e) { // catch statement for the error and let the user know the correct input for the field
    		            JOptionPane.showMessageDialog(null, "You must enter a number in price.");
    		            return false;
    		        }
    			//if both boolean flags are true then the method will return true
    			if(nameCheck == true && priceFlag == true) {
    				return true;
    			}
    			//default return value is false
    			return false;
    }
    //validate the time format from spinner
   public boolean checkTimeFormat(String start, String end) {
	   int startTime = Integer.parseInt(start);
	   int endTime = Integer.parseInt(end);
	   //checks if start time is greater than end time
	   if(startTime >= endTime) {
		   JOptionPane.showMessageDialog(null, "Start time cannot be greater than end time");
		   return false;
	   }else {
		   return true;
	   }
   }
    
    // Going back to Main Screen
    public void cancel() {
        new Cmainview(this.unpaidTickets, this.database);
    }
}

