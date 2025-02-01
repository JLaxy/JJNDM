import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Cmainview {
    // Declaring Storage
    ArrayList<Ticket> unpaidTickets;
    // Declaring Database
    Database database;
    // Declaring Model and View
    Vmainview mainView;
    Mmainview mainModel;

    // Program Start Constructor
    Cmainview() {
        // Intializing
        unpaidTickets = new ArrayList<Ticket>();
        database = new Database();
        mainView = new Vmainview();
        mainModel = new Mmainview(this.unpaidTickets, database);

        // Binding action listeners to buttons in view
        mainView.setAddMovieListener(new addMovieActionListener());
        mainView.setRemoveMovieListener(new removeMovieActionListener());
        mainView.setCheckoutListener(new checkoutActionListener());
        mainView.setCreateTicketListener(new createTicketActionListener());
        mainView.setExportMovieDataListener(new exportMovieDataActionListener());
        mainView.setTotalRevenueListener(new showTotalRevenueActionListener());
    }

    // Running Program Constructor
    Cmainview(ArrayList<Ticket> unpaidTickets, Database database) {
        // Syncing
        this.unpaidTickets = unpaidTickets;
        this.database = database;
        mainView = new Vmainview();
        mainModel = new Mmainview(this.unpaidTickets, database);

        // Binding action listeners to buttons in view
        mainView.setAddMovieListener(new addMovieActionListener());
        mainView.setRemoveMovieListener(new removeMovieActionListener());
        mainView.setCheckoutListener(new checkoutActionListener());
        mainView.setCreateTicketListener(new createTicketActionListener());
        mainView.setExportMovieDataListener(new exportMovieDataActionListener());
        mainView.setTotalRevenueListener(new showTotalRevenueActionListener());
    }

    // Add movie action listener
    public class addMovieActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            mainModel.addMovie(database, unpaidTickets);
            // mainView.dispose();
        }
    }

    // Remove movie action listener
    public class removeMovieActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (mainModel.removeMovie(database, unpaidTickets))
                mainView.dispose();
        }
    }

    // Checkout action listener
    public class checkoutActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            mainModel.checkout(database, unpaidTickets);
            // mainView.dispose();
        }
    }

    // Create ticket action listener
    public class createTicketActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            mainModel.createTicket(database, unpaidTickets);
            // mainView.dispose();
        }
    }

    // Serialize action listener
    public class exportMovieDataActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            mainModel.exportMovieData(database);
        }
    }

    // Show total revenue action listener
    public class showTotalRevenueActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            mainModel.showTotalRevenue(database);
        }
    }

}
