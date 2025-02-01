import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Mmainview {
    // Declaring Storage
    ArrayList<Ticket> unpaidTickets;
    // Declaring Database
    Database database;

    Mmainview(ArrayList<Ticket> unpaidTickets, Database database) {
        // Syncing
        this.unpaidTickets = unpaidTickets;
        this.database = database;
    }

    // Add movie function
    public void addMovie(Database database, ArrayList<Ticket> unpaidTickets) {
        database.addMovie("Kung Fu Panda", "2300 - 0000", 200, 700, 5);
        database.addMovie("Whiplash", "0900 - 1000", 200, 250, 5);
        database.addMovie("Wolf of Wallstreet", "1100 - 1200", 200, 350, 2);
        System.out.println("adding movie...");
        // make sure to run database.cinemaExists first before adding to database
        // new Caddmovie(this.database, this.unpaidTickets);
    }

    // Remove movie function
    public boolean removeMovie(Database database, ArrayList<Ticket> unpaidTickets) {
        System.out.println("removing movie...");
        // Checks if movies exists in database; returns true if exists; false if
        // otherwise
        if (database.moviesExists()) {
            new Cremovemovie(this.database, this.unpaidTickets);
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "There are no movies in the database! Please add a movie first",
                    "No Movies in Database", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }

    // Checkout function
    public void checkout(Database database, ArrayList<Ticket> unpaidTickets) {
        System.out.println("checking out...");
        // new Ccheckout(this.database, this.unpaidTickets);
    }

    // Create ticket function
    public void createTicket(Database database, ArrayList<Ticket> unpaidTickets) {
        System.out.println("creating new ticket...");
        // make sure to run database.seatExists first
        // new Ccreateticket(this.database, this.unpaidTickets);
    }

    // Show total revenue function
    public void showTotalRevenue(Database database) {
        // Retrieving Result
        ResultSet rs = database.getRevenue();
        float revenue = 0;
        try {
            while (rs.next()) {
                // Adding summing revenues
                revenue += rs.getFloat(1);
            }
            JOptionPane.showMessageDialog(null, "Total Revenue: " + revenue, "Total Revenue",
                    JOptionPane.PLAIN_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "An error has occured", "Error",
                    JOptionPane.WARNING_MESSAGE);
            e.printStackTrace();
        }
    }

    // Serialize function
    public void exportMovieData(Database database) {
        System.out.println("exporting movie data...");
        // Make sure to run database.movieExists first
        // new Serialize(this.database);
    }
}
