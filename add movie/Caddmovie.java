package finalProjectAOOP;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class Caddmovie {
	// Declaring View and Model
    Vaddmovie addMovieView;
    Maddmovie addMovieModel;
    // Declaring
    Database database;
    ArrayList<Ticket> unpaidTickets;
    // Storage for current selected index
    int selectedIndex;
	public Caddmovie(Database database, ArrayList<Ticket> unpaidTickets) {
		// Syncing
        this.database = database;
        this.unpaidTickets = unpaidTickets;
        // Instantiating
        addMovieView = new Vaddmovie(database.getMovies());
        addMovieModel = new Maddmovie(this.database, this.unpaidTickets);
        // Binding Action Listener
        addMovieView.setAddActionListener(new addActionListener());
        addMovieView.setCancelListener(new cancelActionListener());
	}
	// Remove cancel action listener
    public class addActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	//store the user input in our variables
           String movName = addMovieView.getMovieName().getText();
           String price = addMovieView.getPriceName().getText();
           int cinemaNum = addMovieView.getCinemaNo();
           //validate user input
           if(addMovieModel.checkInputs(movName, price) && addMovieModel.checkTimeFormat(addMovieView.getTimeStart(), addMovieView.getTimeEnd())) {
        	   String timeSlot = addMovieView.getTimeStart() +" - "+addMovieView.getTimeEnd();//will only instantiate if the if condition is true
        	   //check if the cinema slot is already taken
        	   //if true then the movie is added to the database else you must  change the cinema number
        	   if(addMovieModel.add(movName, timeSlot, cinemaNum, cinemaNum)) {
        		   addMovieView.dispose();//close this window if it added a movie successfully
        	   }//end of nested if
           }//end of if 
        }//end of actionPerformed
    }//end of the listener
    
	// Remove cancel action listener
    public class cancelActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            addMovieModel.cancel();
            addMovieView.dispose();
        }
    }

}
