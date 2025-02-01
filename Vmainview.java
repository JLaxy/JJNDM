import java.awt.event.ActionListener;
import java.awt.Insets;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Vmainview extends JFrame {
    // Declaring components
    JButton[] buttons;

    Vmainview() {
        // Intializing buttons
        buttons = new JButton[6];
        this.setLayout(new GridLayout(3, 2));
        for (int i = 0; i < buttons.length; i++) {
            String[] labels = { "Add Movie", "Remove Movie", "Create Ticket", "Checkout", "Show Total Revenue",
                    "Export Movie Data" };
            buttons[i] = new JButton(labels[i]);
            buttons[i].setFocusable(false);
            // Adding padding to buttons
            buttons[i].setMargin(new Insets(70, 70, 70, 70));
            this.add(buttons[i]);
        }

        // Configuring JFrame
        this.setTitle("JJNDM Cinema Booking");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
    }

    // Binding add movie button to listener
    public void setAddMovieListener(ActionListener listener) {
        buttons[0].addActionListener(listener);
    }

    // Binding remove movie button to listener
    public void setRemoveMovieListener(ActionListener listener) {
        buttons[1].addActionListener(listener);
    }

    // Binding clear button to listener
    public void setCreateTicketListener(ActionListener listener) {
        buttons[2].addActionListener(listener);
    }

    // Binding checkout button to listener
    public void setCheckoutListener(ActionListener listener) {
        buttons[3].addActionListener(listener);
    }

    // Binding show total revenue button to listener
    public void setTotalRevenueListener(ActionListener listener) {
        buttons[4].addActionListener(listener);
    }

    // Binding show total revenue button to listener
    public void setExportMovieDataListener(ActionListener listener) {
        buttons[5].addActionListener(listener);
    }

}
