import java.awt.event.ActionListener;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

public class Vremovemovie extends JFrame {
    // Declaring components
    JLabel label;
    JPanel mainPanel, btnPanel;
    JButton[] buttons;
    JComboBox<String> selector;
    ResultSet rs;

    Vremovemovie(ResultSet rs) {
        // Syncing result
        this.rs = rs;
        // Intializing panel
        mainPanel = new JPanel(new GridBagLayout());
        label = new JLabel("Movie");
        int length = 0;

        try {
            // Retrieving row length
            rs.last();
            length = rs.getRow();
            rs.beforeFirst();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "An error has occured", "Error",
                    JOptionPane.WARNING_MESSAGE);
            e.printStackTrace();
        }

        // Selector label storage
        String[] selectorLabel = new String[length];

        try {
            int j = 0;
            while (rs.next()) {
                // Configuring label for each movie
                selectorLabel[j] = rs.getString("movName") + "       " + rs.getString("timeSlot");
                ++j;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "An error has occured", "Error",
                    JOptionPane.WARNING_MESSAGE);
            e.printStackTrace();
        }

        // Building JComboBox
        selector = new JComboBox<>(selectorLabel);
        // Setting Constraints to Main Panel
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(15, 15, 15, 15);
        c.gridwidth = 1;
        c.ipadx = 50;
        mainPanel.add(label, c);
        c.gridwidth = 3;
        c.ipadx = 150;
        mainPanel.add(selector, c);

        // Intializing JButtons
        btnPanel = new JPanel(new GridLayout());
        buttons = new JButton[2];
        for (int i = 0; i < buttons.length; i++) {
            String[] text = { "Remove", "Cancel" };
            buttons[i] = new JButton(text[i]);
            buttons[i].setFocusable(false);
            btnPanel.add(buttons[i]);
        }

        this.setLayout(new BorderLayout());
        this.add(mainPanel, BorderLayout.CENTER);
        this.add(btnPanel, BorderLayout.SOUTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("Remove Movie");
        this.pack();
        this.setVisible(true);
    }

    // Binding remove button listener
    public void setRemoveActionListener(ActionListener listener) {
        buttons[0].addActionListener(listener);
    }

    // Binding cancel button listener
    public void setCancelListener(ActionListener listener) {
        buttons[1].addActionListener(listener);
    }

    // Binding JComboBox listener
    public void setSelectorListener(ActionListener listener) {
        selector.addActionListener(listener);
    }
}
