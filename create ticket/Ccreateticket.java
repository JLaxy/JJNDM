package finalProjectAOOP;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import finalProjectAOOP.Cremovemovie.cancelActionListener;
import finalProjectAOOP.Cremovemovie.removeActionListener;
import finalProjectAOOP.Cremovemovie.selectorActionlistener;

public class Ccreateticket {
	Vcreateticket createTicketView;
    Mcreateticket createTicketModel;
    // Declaring
    Database database;
    ArrayList<Ticket> unpaidTickets;
    // Storage for current selected index
    int selectedIndex;

    Ccreateticket(Database database, ArrayList<Ticket> unpaidTickets) {
       
        this.database = database;
        this.unpaidTickets = unpaidTickets;
        // Instantiating
        createTicketView = new Vcreateticket(database.getMovies());
        createTicketModel = new Mcreateticket(this.database, this.unpaidTickets);
        // Binding Action Listener
        createTicketView.setAddActionListener(new addActionListener());
        createTicketView.setCancelListener(new cancelActionListener());
        createTicketView.setSelectorListener(new selectorActionListener());
    }

    // Remove button action listener
    public class addActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                // Retrieve result
                ResultSet rs = database.getMovies();
                int movUID = 0;
                String movName = "";
                float movPrice = 0;
                String fake = "";
                int seatNo = 0;
                String timeSlot = "00:00";
                // Finding movUID of selected index
                for (int i = 0; i < selectedIndex + 1; i++) {
                    rs.next();
                    movUID = rs.getInt("movUID");
                    movName = rs.getString("movName");
                    movPrice = rs.getFloat("movPrice");
                    timeSlot = rs.getString("timeSlot");
                    fake = createTicketView.getSeatNo().getText();
                    seatNo = Integer.parseInt(fake);
                }
                // Removing movie
                createTicketModel.add(movUID, movName, movPrice, seatNo, timeSlot);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "An error has occured", "Error",
                        JOptionPane.WARNING_MESSAGE);
                ex.printStackTrace();
            }
            createTicketView.dispose();
        }
    }

    // Remove cancel action listener
    public class cancelActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            createTicketModel.cancel();
            createTicketView.dispose();
        }
    }

    public class selectorActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        
			JComboBox<String> selected = (JComboBox<String>) e.getSource();
            selectedIndex = selected.getSelectedIndex();
            System.out.print(selectedIndex);
			try {
				ResultSet rs = database.getMovies();
				String movPrice = "";
	          
	          for(int i = 0; i < selectedIndex + 1; i++) {
	        	  	rs.next();
	            	movPrice = rs.getString("movPrice");
	            	createTicketView.priceJLabel(movPrice);
	            }
	          
	          	

	        }catch(SQLException f) {
	            JOptionPane.showInputDialog(this, f.getMessage());
	        }
        }
    }

}

