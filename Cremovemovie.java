import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class Cremovemovie {
    // Declaring View and Model
    Vremovemovie removeMovieView;
    Mremovemovie removeMovieModel;
    // Declaring
    Database database;
    ArrayList<Ticket> unpaidTickets;
    // Storage for current selected index
    int selectedIndex;

    Cremovemovie(Database database, ArrayList<Ticket> unpaidTickets) {
        // Syncing
        this.database = database;
        this.unpaidTickets = unpaidTickets;
        // Instantiating
        removeMovieView = new Vremovemovie(database.getMovies());
        removeMovieModel = new Mremovemovie(this.database, this.unpaidTickets);
        // Binding Action Listener
        removeMovieView.setRemoveActionListener(new removeActionListener());
        removeMovieView.setCancelListener(new cancelActionListener());
        removeMovieView.setSelectorListener(new selectorActionlistener());
    }

    // Remove button action listener
    public class removeActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                // Retrieve result
                ResultSet rs = database.getMovies();
                int movUID = 0;
                // Finding movUID of selected index
                for (int i = 0; i < selectedIndex + 1; i++) {
                    rs.next();
                    movUID = rs.getInt("movUID");
                }
                // Removing movie
                removeMovieModel.remove(movUID);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "An error has occured", "Error",
                        JOptionPane.WARNING_MESSAGE);
                ex.printStackTrace();
            }
            removeMovieView.dispose();
        }
    }

    // Remove cancel action listener
    public class cancelActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            removeMovieModel.cancel();
            removeMovieView.dispose();
        }
    }

    public class selectorActionlistener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Casting event to JComboBox to get selected index
            JComboBox<String> selected = (JComboBox<String>) e.getSource();
            selectedIndex = selected.getSelectedIndex();
        }
    }

}
