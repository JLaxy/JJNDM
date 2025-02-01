import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Mremovemovie {
    // Declaring
    Database database;
    ArrayList<Ticket> unpaidTickets;

    Mremovemovie(Database database, ArrayList<Ticket> unpaidTickets) {
        this.database = database;
        this.unpaidTickets = unpaidTickets;
    }

    // Removing Movie from Database
    public void remove(int movUID) {
        database.removeMovie(movUID);
        JOptionPane.showMessageDialog(null, "Movie successfully removed", "Removed", JOptionPane.INFORMATION_MESSAGE);
        new Cmainview(this.unpaidTickets, this.database);
    }

    // Going back to Main Screen
    public void cancel() {
        new Cmainview(this.unpaidTickets, this.database);
    }
}
