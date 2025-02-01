package finalProjectAOOP;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Mcreateticket {
	Database database;
    ArrayList<Ticket> unpaidTickets;

    Mcreateticket(Database database, ArrayList<Ticket> unpaidTickets) {
        this.database = database;
        this.unpaidTickets = unpaidTickets;
    }

    // Add Ticket to Database
    public boolean add(int movUID, String movName, float movPrice, int seatNo, String timeSlot) {
    	if(!database.seatExists(movUID, seatNo)) {
        database.addTicket(movUID, movName, movPrice, seatNo, timeSlot);
        JOptionPane.showMessageDialog(null, "Ticket successfully added");
        new Cmainview(this.unpaidTickets, this.database);
        return true;
       }else {
    	   System.out.println(database.seatExists(movUID, seatNo) + " else block");// just to check the value that
			// cinemaExist returns. For debugging
    	   JOptionPane.showMessageDialog(null, "Seat Number is already taken");
    	   new Cmainview(this.unpaidTickets, this.database);
    	   return false;
       }
       }
    
    // Going back to Main Screen
    public void cancel() {
        new Cmainview(this.unpaidTickets, this.database);
    }
}


